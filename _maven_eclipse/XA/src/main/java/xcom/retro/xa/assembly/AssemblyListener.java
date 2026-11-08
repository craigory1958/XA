

package xcom.retro.xa.assembly ;


import static xcom.retro.xa.XA.logEvent ;
import static xcom.retro.xa.Identifier.IdentifierTypes.* ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_formatGeneratedID ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_formatQualifiedID ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_formatScopedID ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_invokeMethodFromContext ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_parsedContextName ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_parsedText ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_trimToContextName ;
import static xcom.retro.xa.directives.DirectiveUtils.DIRS_parseDirective ;
import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_buildArgumentExpressionTree ;

import java.util.Map ;

import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.retro.xa.Argument ;
import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.Identifier ;
import xcom.retro.xa.Operand ;
import xcom.retro.xa.Option ;
import xcom.retro.xa.Parameter ;
import xcom.retro.xa.antlr.AssemblyBaseListener ;
import xcom.retro.xa.antlr.AssemblyParser.IdentifierContext ;
import xcom.retro.xa.antlr.AssemblyParser.SymbolContext ;
import xcom.retro.xa.api.interfaces.iSource ;
import xcom.retro.xa.expressions._ExprNode ;
import xcom.utils4j.Signatures ;
import xcom.utils4j.data.structured.map.Maps ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


public class AssemblyListener extends AssemblyBaseListener {

	static final String IDENTIFIER = ASMB_trimToContextName(IdentifierContext.class) ;
	static final String SYMBOL = ASMB_trimToContextName(SymbolContext.class) ;


	static final Identifier $Identifier = new Identifier(null) ;


	//@formatter:off

	/**
	 * 
	 */
	AssemblyContext actx ;
	public AssemblyContext actx() { return actx ; }

	//@formatter:on


	//
	//
	//

	public AssemblyListener(final AssemblyContext actx) {
		this.actx = actx ;
	}


	//
	//
	//

	@Override
	public void enterEveryRule(final ParserRuleContext pctx) {
		ASMB_invokeMethodFromContext(actx, "asmb", this, "enter", pctx) ;
	}

	@Override
	public void exitEveryRule(final ParserRuleContext pctx) {
		ASMB_invokeMethodFromContext(actx, "asmb", this, "exit", pctx) ;
	}

//	public void invokeMethodFromContext(final String prefix, final ParserRuleContext pctx) {
//
//		final String _methodContext = ASMB_parsedContextName(pctx) ;
//		final String method = prefix + _methodContext ;
//
//		try {
//			this.getClass().getMethod(method, ParserRuleContext.class) ;
//
//			logEvent(actx.$args(), actx.phase(), "asmb", "(asmb) {}: invoke {}({}) in {}", this.getClass(), method, pctx.getClass().getSimpleName(),
//					this.getClass()) ;
//
//			Reflection //
//					.method(method) //
//					.withParameterTypes(ParserRuleContext.class) //
//					.in(this) //
//					.invoke(pctx) ;
//		}
//		catch ( final NoSuchMethodException ex ) {}
//		catch ( final ReflectionError ex ) {
//			System.err.println("Impossible error 52: Error walking tree: Method " + method + " not found.") ;
//		}
//	}


	//
	//
	//

	@Log
	public void enterOption(final ParserRuleContext pctx) {

		final Map<String, Operand> _operands = actx.statement().operands() ;
		final String _id = ASMB_parsedText(pctx.getChild(0)) ;

		_operands.put(_id, new Option(_id)) ;

		logEvent(actx.$args(), actx.phase(), "asmb", "(asmb) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}


	@Log
	public void enterParameter(final ParserRuleContext pctx) {

		final Map<String, Operand> _operands = actx.statement().operands() ;
		final String _moniker = ASMB_parsedText(pctx.getChild(0)) ;
		final String _context = ASMB_parsedContextName(pctx.getChild(0)) ;
		final String _source = pctx.getText() ;

		if ( _context.equals(IDENTIFIER) || _context.equals(SYMBOL) )
			_operands.put(_moniker, new Parameter(_moniker, _source)) ;
		else
			_operands.put(ASMB_formatGeneratedID(_operands), new Parameter(ASMB_formatGeneratedID(_operands), _source)) ;

		logEvent(actx.$args(), actx.phase(), "asmb", "(asmb) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}


	//
	//
	//

	@Log
	public void exitArgument(final ParserRuleContext pctx) {

		logEvent(actx.$args(), actx.phase(), "args", "(args) {}: args: {}", this.getClass(), actx.statement().operands()) ;

		final Map<String, Operand> _operands = actx.statement().operands() ;
		final Operand _operand99 = Maps.lastEntryValue(_operands) ;

		final String id = ((_operand99 instanceof Option) || (_operand99 instanceof Parameter) //
				? Maps.lastEntryKey(_operands) //
				: ASMB_formatGeneratedID(_operands)) ;

		final _ExprNode argument = EXPR_buildArgumentExpressionTree(actx, pctx) ;

		if ( _operands.isEmpty() || (!(_operand99 instanceof Option) && !(_operand99 instanceof Parameter)) )
			_operands.put(id, new Argument(id, argument)) ;

		else if ( (_operand99 instanceof Option) || (_operand99 instanceof Parameter) )
			_operand99.assignment(argument) ;

		logEvent(actx.$args(), actx.phase(), "asmb", "(asmb) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}


	@Log
	public void exitDirective(final ParserRuleContext pctx) {

		DIRS_parseDirective(actx) ;

		logEvent(actx.$args(), actx.phase(), "asmb", "(asmb) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}


	@Log
	public void exitLabel(final ParserRuleContext pctx) {

		final iSource _src = actx.source().peek() ;
		final String _id = ASMB_parsedText(pctx) ;
		final String _idFQN = ASMB_formatQualifiedID(_id, _src.qualifier()).toLowerCase() ;

		exitLabelOrScopedLabel(pctx, _id, _idFQN) ;

		logEvent(actx.$args(), actx.phase(), "asmb", "(asmb) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}


	@Log
	public void exitScopedLabel(final ParserRuleContext pctx) {

		final iSource _src = actx.source().peek() ;
		final String _id = ASMB_parsedText(pctx) ;
		final String _idFQN = ASMB_formatScopedID(_id, _src).toLowerCase() ;

		exitLabelOrScopedLabel(pctx, _id, _idFQN) ;

		logEvent(actx.$args(), actx.phase(), "asmb", "(asmb) {}: exiting {} ", this.getClass(), Signatures.coerceMethodName()) ;
	}


	public void exitLabelOrScopedLabel(final ParserRuleContext pctx, final String identifier, final String idFQN) {

		if ( actx.statement().assemblyEnable() ) {

			final Map<String, Identifier> _ids = actx.identifiers() ;
			final iSource _src = actx.source().peek() ;


			if ( !actx.identifiers().containsKey(idFQN) ) {
				_ids.put(idFQN, new Identifier(idFQN, identifier)) ;
				logEvent(actx.$args(), actx.phase(), "ids", "(ids) {}: add identifier: {}", this.getClass(), idFQN) ;

				_ids.get(idFQN).type(Label) ;
				logEvent(actx.$args(), actx.phase(), "ids", "(ids) {}: set identifier-type: {}: {}", this.getClass(), idFQN, _ids.get(idFQN).type()) ;
			}

			actx.statement().label(_ids.get(idFQN)) ;

			_ids.get(idFQN).origin($Identifier.new Reference(actx.sn(), _src.fn(), _src.ln())) ;
			logEvent(actx.$args(), actx.phase(), "ids", "(ids) {}: set identifier-origin: {}: {}", this.getClass(), idFQN, _ids.get(idFQN).origin().ln()) ;

			_ids.get(idFQN).value(actx.segment().loc()) ;
			logEvent(actx.$args(), actx.phase(), "ids", "(ids) {}: set identifier-value: {}: {}", this.getClass(), idFQN, _ids.get(idFQN).value()) ;
		}
	}
}
