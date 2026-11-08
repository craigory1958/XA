

package xcom.retro.xa.expressions ;


import static xcom.retro.xa.XA.logEvent ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_exprToPostfix ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_formatQualifiedID ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_formatScopedID ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_invokeMethodFromContext ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_parsedContextName ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_parsedText ;
import static xcom.retro.xa.expressions.ExpressionListener.OperatorOperands.Binary ;
import static xcom.retro.xa.expressions.ExpressionListener.OperatorOperands.Unary ;
import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asBytes ;

import java.util.Map ;
import java.util.Stack ;

import org.antlr.v4.runtime.ParserRuleContext ;
import org.fest.reflect.core.Reflection ;
import org.fest.reflect.exception.ReflectionError ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.Identifier ;
import xcom.retro.xa.antlr.AssemblyBaseListener ;
import xcom.retro.xa.api.interfaces.iSource ;
import xcom.retro.xa.expressions.op._OpNode ;
import xcom.retro.xa.expressions.op.binary._BinaryOpNode ;
import xcom.retro.xa.expressions.op.unary._UnaryOpNode ;
import xcom.retro.xa.expressions.value.ExprMarker ;
import xcom.retro.xa.expressions.value.HexLiteral ;
import xcom.retro.xa.expressions.value.StringLiteral ;
import xcom.retro.xa.expressions.value.Token ;
import xcom.retro.xa.expressions.value._ValueNode ;
import xcom.utils4j.Enums ;
import xcom.utils4j.Signatures ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
public class ExpressionListener extends AssemblyBaseListener {

	enum OperatorOperands {
		Unary, Binary, Ternary;
	}

	// https://en.cppreference.com/w/c/language/operator_precedence
	enum Operators {

		BitwiseAnd(Binary, 8), //
		BitwiseShiftOr(Binary, 10), //
		BitwiseShiftLeft(Binary, 5), //
		BitwiseShiftRight(Binary, 5), //

		IntegerAdd(Binary, 4), //
		IntegerDivide(Binary, 3), //
		IntegerMultiply(Binary, 3), //
		IntegerSubtract(Binary, 4), //

		Equal(Binary, 7), //

		HighByte(Unary, 2), //
		LogicalNot(Unary, 2), //
		LowByte(Unary, 2), //
		Minus(Unary, 2), //
		Plus(Unary, 2), //
		;


		OperatorOperands operands ;
		int precedence ;


		Operators(final OperatorOperands operands, final int precedence) {
			this.operands = operands ;
			this.precedence = precedence ;
		}
	}


	static final Identifier $Identifier = new Identifier(null) ;


	//
	// Attributes ...
	//

	AssemblyContext actx ;
	_ExprNode expr ;
	Stack<_ExprNode> shunt ;
	Stack<_ExprNode> stack ;


	//
	// Constructor(s) ...
	//

	public ExpressionListener(final AssemblyContext actx) {
		this.actx = actx ;
	}


	//
	//
	//

	@Override
	public void enterEveryRule(final ParserRuleContext pctx) {
		ASMB_invokeMethodFromContext(actx, "expr", this, "enter", pctx) ;
	}

	@Override
	public void exitEveryRule(final ParserRuleContext pctx) {
		ASMB_invokeMethodFromContext(actx, "expr", this, "exit", pctx) ;
	}


	//
	//
	//

	public void enterArgument(final ParserRuleContext pctx) {

		shunt = new Stack<>() ;
		stack = new Stack<>() ;

		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: reset stack: {}", this.getClass(), stack) ;
		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: reset shunt: {}", this.getClass(), shunt) ;


		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}


	public void enterExpr(final ParserRuleContext pctx) {

		shunt.push(new ExprMarker(actx, "(")) ;
		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: push shunt: {}", this.getClass(), shunt) ;


		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}


	public void exitArgument(final ParserRuleContext pctx) {

		expr = stack.pop() ;
		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: pop stack: {}", this.getClass(), stack) ;

		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: expr: {}", this.getClass(), ASMB_exprToPostfix(expr)) ;

		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}


	public void exitExpr(final ParserRuleContext pctx) {

		while ( !shunt.isEmpty() && !(shunt.peek() instanceof ExprMarker) ) {

			final _OpNode t = (_OpNode) shunt.pop() ;
			t.popOperands(stack) ;
			logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: pop stack: {}", this.getClass(), stack) ;

			stack.push(t) ;
			logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: push stack: {}", this.getClass(), stack) ;
		}

		shunt.pop() ;
		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: pop shunt: {}", this.getClass(), shunt) ;


		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}


	public void exitDottedIdentifier(final ParserRuleContext pctx) {

		final String _id = ASMB_parsedText(pctx) ;
		final String _idFQN = _id.toLowerCase() ;

		stack.push(new StringLiteral(actx, _idFQN)) ;
		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: push stack: {}", this.getClass(), stack) ;


		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}


	public void exitQualifiedIdentifier(final ParserRuleContext pctx) {

		final Map<String, Identifier> _ids = actx.identifiers() ;
		final iSource _src = actx.source().peek() ;

		final String _id = ASMB_parsedText(pctx) ;
		final String _idFQN = ASMB_formatQualifiedID(_id, _src.qualifier()).toLowerCase() ;

		stack.push(new Token(actx, _idFQN)) ;
		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: push stack: {}", this.getClass(), stack) ;


		if ( !actx.identifiers().containsKey(_idFQN) ) {

			_ids.put(_idFQN, new Identifier(_idFQN, _id)) ;
			logEvent(actx.$args(), actx.phase(), "ids", "(ids) {}: add identifier: {}", this.getClass(), _idFQN) ;

//			_ids.get(_idFQN).type(Label) ;
//			logEvent(actx.$args(), actx.phase(), "ids", "(ids) {}: set identifier-type: {}: {}", this.getClass(), _idFQN,
//					_ids.get(_idFQN).type()) ;
		}

		_ids.get(_idFQN).references().add($Identifier.new Reference(actx.sn(), _src.fn(), _src.ln())) ;
		logEvent(actx.$args(), actx.phase(), "ids", "(ids) {}: add identifier-xRef: {}", this.getClass(), _idFQN) ;


		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}


	public void exitScopedIdentifier(final ParserRuleContext pctx) {

		final Map<String, Identifier> _ids = actx.identifiers() ;
		final iSource _src = actx.source().peek() ;

		final String _id = ASMB_parsedText(pctx) ;
		final String _idFQN = ASMB_formatScopedID(_id, _src).toLowerCase() ;

		stack.push(new Token(actx, _idFQN)) ;
		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: push stack: {}", this.getClass(), stack) ;


//		final String mode = (actx.identifiers().containsKey(_idFQN) ? "update" : "add") ;
//		_ids.put(_idFQN, new Identifier(_idFQN, _id)) ;
//		logEvent(actx.$args(), actx.phase(), "ids", "(ids) {}: {} add identifier: {}", this.getClass(), mode, _idFQN) ;
		
		if ( !actx.identifiers().containsKey(_idFQN) ) {

			_ids.put(_idFQN, new Identifier(_idFQN, _id)) ;
			logEvent(actx.$args(), actx.phase(), "ids", "(ids) {}: add identifier: {}", this.getClass(), _idFQN) ;

//			_ids.get(_idFQN).type(Label) ;
//			logEvent(actx.$args(), actx.phase(), "ids", "(ids) {}: set identifier-type: {}: {}", this.getClass(), _idFQN,
//					_ids.get(_idFQN).type()) ;
		}

		_ids.get(_idFQN).references().add($Identifier.new Reference(actx.sn(), _src.fn(), _src.ln())) ;
		logEvent(actx.$args(), actx.phase(), "ids", "(ids) {}: add identifier-xRef: {}", this.getClass(), _idFQN) ;


		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}


	public void exitOrg(final ParserRuleContext pctx) {

		stack.push(new HexLiteral(actx, EXPR_asBytes(actx.segment().loc()))) ;
		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: push stack: {}", this.getClass(), stack) ;


		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}


	//
	// Operators ...
	//

	public void exitBinary(final ParserRuleContext pctx) {

		exitOperator(pctx, _BinaryOpNode.class) ;

		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}

	public void exitUnary(final ParserRuleContext pctx) {

		exitOperator(pctx, _UnaryOpNode.class) ;

		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}


	public void exitOperator(final ParserRuleContext pctx, final Class<? extends _OpNode> clazz) {

		final String _operator = ASMB_parsedContextName(pctx.getChild(0)) ;
		final Operators _op = Enums.valueOfIgnoreCase(Operators.class, _operator) ;

		_OpNode operator = null ;
		try {
			operator = Reflection //
					.constructor() //
					.withParameterTypes(AssemblyContext.class, Integer.class) //
					.in(Reflection //
							.type(clazz.getPackageName() + '.' + _op.name()) //
							.loadAs(clazz)) //
					.newInstance(actx, _op.precedence) ;
			logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: operator: {}", this.getClass(), operator) ;
		}
		catch ( final ReflectionError ex ) {
			System.err.println("Impossible error 52: Error instantiating: Class " + clazz + ".") ;
		}

		while ( !shunt.isEmpty() && !(shunt.peek() instanceof ExprMarker) && (((_OpNode) shunt.peek()).precedence() < operator.precedence()) ) {
			final _OpNode t = (_OpNode) shunt.pop() ;
			t.popOperands(stack) ;
			logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: pop stack: {}", this.getClass(), stack) ;

			stack.push(t) ;
			logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: push stack: {}", this.getClass(), stack) ;
		}

		shunt.push(operator) ;
		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: push shunt: {}", this.getClass(), shunt) ;
	}


	//
	// Literals ...
	//

	public void exitAlphanumericLiteral(final ParserRuleContext pctx) {

		exitAlphanumericOrNumericLiteral(pctx) ;

		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}

	public void exitNumericLiteral(final ParserRuleContext pctx) {

		exitAlphanumericOrNumericLiteral(pctx) ;

		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}


	//
	//
	//

	public void exitAlphanumericOrNumericLiteral(final ParserRuleContext pctx) {

		final String _literal = ASMB_parsedText(pctx.getChild(0).getChild(0)) ;
		final String _literalContext = ASMB_parsedContextName(pctx.getChild(0)) ;
		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: literal: {}, {}", this.getClass(), _literal, _literalContext) ;

		_ValueNode literal = null ;
		try {
			literal = Reflection //
					.constructor() //
					.withParameterTypes(AssemblyContext.class, String.class) //
					.in(Reflection //
							.type(_ValueNode.class.getPackageName() + '.' + _literalContext) //
							.loadAs(_ValueNode.class)) //
					.newInstance(actx, _literal) ;
		}
		catch ( final ReflectionError ex ) {
			System.err.println("Impossible error 52: Error instantiating: Class " + _ValueNode.class + ".") ;
		}

		stack.push(literal) ;
		logEvent(actx.$args(), actx.phase(), "expr", "(expr) {}: push stack: {}", this.getClass(), stack) ;
	}
}
