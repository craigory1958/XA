

package xcom.retro.xa.expressions ;


import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_formatQualifiedID ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_formatScopedID ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_parsedContextName ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_parsedText ;
import static xcom.retro.xa.expressions.ExpressionListener.OperatorOperands.Binary ;
import static xcom.retro.xa.expressions.ExpressionListener.OperatorOperands.Unary ;
import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asBytes ;

import java.util.Stack ;

import org.antlr.v4.runtime.ParserRuleContext ;
import org.fest.reflect.core.Reflection ;
import org.fest.reflect.exception.ReflectionError ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

import xcom.retro.xa.Moniker ;
import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.antlr.AssemblyBaseListener ;
import xcom.retro.xa.api.interfaces.iSource ;
import xcom.retro.xa.expressions.op._OpNode ;
import xcom.retro.xa.expressions.op.binary._BinaryOpNode ;
import xcom.retro.xa.expressions.op.unary._UnaryOpNode ;
import xcom.retro.xa.expressions.value.DecimalLiteral ;
import xcom.retro.xa.expressions.value.ExprMarker ;
import xcom.retro.xa.expressions.value.Identifier ;
import xcom.retro.xa.expressions.value.StringLiteral ;
import xcom.retro.xa.expressions.value._ValueNode ;
import xcom.utils4j.Enums ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


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


	//
	//
	//

	private static final Logger Logger = LoggerFactory.getLogger(ExpressionListener.class) ;

	final static Moniker $Identifier = new Moniker(null) ;


	AssemblyContext actx ;
	Stack<_ExprNode> shunt = new Stack<>() ;
	Stack<_ExprNode> stack = new Stack<>() ;
	_ExprNode expr ;


	//
	//
	//

	public ExpressionListener(final AssemblyContext actx) {
		this.actx = actx ;
	}


	//
	//
	//

	@Override
	public void enterEveryRule(final ParserRuleContext pctx) {

		Logger.debug("invoke: enter{} w/{}", ASMB_parsedContextName(pctx), pctx.getClass().getSimpleName()) ;
		invokeMethodFromContext("enter", pctx) ;
	}

	@Override
	public void exitEveryRule(final ParserRuleContext pctx) {

		Logger.debug("invoke: exit{} w/{}", ASMB_parsedContextName(pctx), pctx.getClass().getSimpleName()) ;
		invokeMethodFromContext("exit", pctx) ;
	}

	public void invokeMethodFromContext(final String prefix, final ParserRuleContext pctx) {

		final String _methodContext = ASMB_parsedContextName(pctx) ;

		try {
			final String method = prefix + _methodContext ;
			Reflection //
					.method(method) //
					.withParameterTypes(ParserRuleContext.class) //
					.in(this) //
					.invoke(pctx) ;
		}
		catch ( final ReflectionError ex ) {}
	}


	//
	//
	//

	@Log
	public void exitArgument(final ParserRuleContext pctx) {
		expr = stack.pop() ;
	}


	@Log
	public void enterExpr(final ParserRuleContext pctx) {
		shunt.push(new ExprMarker("(")) ;
	}


	@Log
	public void exitExpr(final ParserRuleContext pctx) {

		while ( !shunt.isEmpty() && !(shunt.peek() instanceof ExprMarker) ) {

			final _OpNode t = (_OpNode) shunt.pop() ;
			t.popOperands(stack) ;
			stack.push(t) ;
		}

		shunt.pop() ;
	}


//	@Log
//	public void exitGlobalLabel(final ParserRuleContext pctx) {
//
//		final iSource _source = actx.source().peek() ;
//		final String _identfier = ASMB_parsedText(pctx) ;
//		final String _qualifedIdentifier = ASMB_formatQualifiedID(_identfier, _source.qualifier()) ;
//
//		stack.push(new IdentifierValue(_qualifedIdentifier)) ;
//
//		if ( !actx.symbols().containsKey(_qualifedIdentifier) )
//			actx.symbols().put(_qualifedIdentifier, new Identifier(_qualifedIdentifier)) ;
//
//		actx.symbols().get(_qualifedIdentifier).references().add($Identifier.new Reference(actx.ln(), _source.sourceID(), _source.sourceLN())) ;
//	}


	@Log
	public void exitDottedIdentifier(final ParserRuleContext pctx) {

		final iSource _source = actx.source().peek() ;
		final String _identifier = ASMB_parsedText(pctx) ;
//		final String _qualifedIdentifier = ASMB_formatQualifiedID(_identifier, _source.qualifier()) ;
//		System.err.println("_qualifedIdentifier: " + _qualifedIdentifier + ", _identifier: " + _identifier) ;
//		System.err.println("_identifier: " + _identifier) ;

		stack.push(new StringLiteral(_identifier)) ;

		if ( !actx.symbols().containsKey(_identifier) )
			actx.symbols().put(_identifier, new Moniker(_identifier, _identifier)) ;

		actx.symbols().get(_identifier).references().add($Identifier.new Reference(actx.ln(), _source.sourceID(), _source.sourceLN())) ;
	}


	@Log
	public void exitQualifiedIdentifier(final ParserRuleContext pctx) {

		final iSource _source = actx.source().peek() ;
		final String _identifier = ASMB_parsedText(pctx) ;
		final String _qualifedIdentifier = ASMB_formatQualifiedID(_identifier, _source.qualifier()) ;
//		System.err.println("_qualifedIdentifier: " + _qualifedIdentifier + ", _identifier: " + _identifier) ;

		stack.push(new Identifier(_qualifedIdentifier)) ;

		if ( !actx.symbols().containsKey(_qualifedIdentifier) )
			actx.symbols().put(_qualifedIdentifier, new Moniker(_qualifedIdentifier, _identifier)) ;

		actx.symbols().get(_qualifedIdentifier).references().add($Identifier.new Reference(actx.ln(), _source.sourceID(), _source.sourceLN())) ;
	}


	@Log
	public void exitScopedIdentifier(final ParserRuleContext pctx) {

		final iSource _source = actx.source().peek() ;
		final String _identifier = ASMB_parsedText(pctx) ;
		final String _scopedIdentfier = ASMB_formatScopedID(_identifier, _source) ;
//		System.err.println("_scopedIdentfier: " + _scopedIdentfier + ", _identifier: " + _identifier) ;

		stack.push(new Identifier(_scopedIdentfier)) ;

		if ( !actx.symbols().containsKey(_scopedIdentfier) )
			actx.symbols().put(_scopedIdentfier, new Moniker(_scopedIdentfier, _identifier)) ;

		actx.symbols().get(_scopedIdentfier).references().add($Identifier.new Reference(actx.ln(), _source.sourceID(), _source.sourceLN())) ;
	}


	@Log
	public void exitOrg(final ParserRuleContext pctx) {
		stack.push(new DecimalLiteral(EXPR_asBytes(actx.segment().loc()))) ;
	}


	@Log
	public void exitParameter(final ParserRuleContext pctx) {
		expr = (_ExprNode) pctx.getChild(0) ;
	}


//	@Log
//	public void exitSymbol(final ParserRuleContext pctx) {
//
//		final iSource _source = actx.source().peek() ;
//		final String _symbol = ASMB_parsedText(pctx) ;
//		System.err.println("_symbol: " + _symbol) ;
//
//		stack.push(new StringLiteral(_symbol)) ;
//
//		if ( !actx.symbols().containsKey(_symbol) )
//			actx.symbols().put(_symbol, new Symbol(_symbol, _symbol.getBytes())) ;
//
//		actx.symbols().get(_symbol).references().add($Identifier.new Reference(actx.ln(), _source.sourceID(), _source.sourceLN())) ;
//	}


//	@Log
//	public void exitDottedSymbol(final ParserRuleContext pctx) {
//
//		final iSource _source = actx.source().peek() ;
//		final String _dottedSymbol = ASMB_parsedText(pctx) ;
//		System.err.println("_dottedSymbol: " + _dottedSymbol) ;
//
//		stack.push(new StringLiteral(_dottedSymbol)) ;
//
//		if ( !actx.symbols().containsKey(_dottedSymbol) )
//			actx.symbols().put(_dottedSymbol, new Symbol(_dottedSymbol, _dottedSymbol.getBytes())) ;
//
//		actx.symbols().get(_dottedSymbol).references().add($Identifier.new Reference(actx.ln(), _source.sourceID(), _source.sourceLN())) ;
//	}


	//
	// Operators ...
	//

	@Log
	public void exitBinary(final ParserRuleContext pctx) {
		exitOperator(pctx, _BinaryOpNode.class) ;
	}

	@Log
	public void exitUnary(final ParserRuleContext pctx) {
		exitOperator(pctx, _UnaryOpNode.class) ;
	}


	@Log
	public void exitOperator(final ParserRuleContext pctx, final Class<? extends _OpNode> clazz) {

		final String _operator = ASMB_parsedContextName(pctx.getChild(0)) ;
		final Operators _op = Enums.valueOfIgnoreCase(Operators.class, _operator) ;

		final _OpNode operator = Reflection //
				.constructor() //
				.withParameterTypes(Integer.class) //
				.in(Reflection //
						.type(clazz.getPackageName() + '.' + _op.name()) //
						.loadAs(clazz)) //
				.newInstance(_op.precedence) ;

		while ( !shunt.isEmpty() && !(shunt.peek() instanceof ExprMarker) && (((_OpNode) shunt.peek()).precedence() < operator.precedence()) ) {

			final _OpNode t = (_OpNode) shunt.pop() ;
			t.popOperands(stack) ;

			stack.push(t) ;
		}

		shunt.push(operator) ;
	}


	//
	// Literals ...
	//

	@Log
	public void exitAlphanumericLiteral(final ParserRuleContext pctx) {
		exitAlphanumericOrNumericLiteral(pctx) ;
	}

	@Log
	public void exitNumericLiteral(final ParserRuleContext pctx) {
		exitAlphanumericOrNumericLiteral(pctx) ;
	}

	public void exitAlphanumericOrNumericLiteral(final ParserRuleContext pctx) {

		final String _literal = ASMB_parsedText(pctx.getChild(0).getChild(0)) ;
		final String _literalContext = ASMB_parsedContextName(pctx.getChild(0)) ;

		final _ValueNode literal = Reflection //
				.constructor() //
				.withParameterTypes(String.class) //
				.in(Reflection //
						.type(_ValueNode.class.getPackageName() + '.' + _literalContext) //
						.loadAs(_ValueNode.class)) //
				.newInstance(_literal) ;

		stack.push(literal) ;
	}
}
