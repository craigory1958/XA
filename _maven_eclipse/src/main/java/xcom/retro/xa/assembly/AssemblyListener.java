

package xcom.retro.xa.assembly ;


import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_formatGeneratedID ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_formatQualifiedID ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_formatScopedID ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_parsedContextName ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_parsedText ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_trimToContextName ;
import static xcom.retro.xa.directives.DirectiveUtils.DIR_parseDirective ;
import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_buildArgumentExpressionTree ;

import java.util.Map ;
import java.util.function.Consumer ;

import org.antlr.v4.runtime.ParserRuleContext ;
import org.fest.reflect.core.Reflection ;
import org.fest.reflect.exception.ReflectionError ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

import xcom.retro.xa.Argument ;
import xcom.retro.xa.Operand ;
import xcom.retro.xa.Option ;
import xcom.retro.xa.Parameter ;
import xcom.retro.xa.Moniker ;
import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.antlr.AssemblyBaseListener ;
import xcom.retro.xa.antlr.AssemblyParser.IdentifierContext ;
import xcom.retro.xa.antlr.AssemblyParser.SymbolContext ;
import xcom.retro.xa.api.interfaces.iSource ;
import xcom.retro.xa.expressions._ExprNode ;
import xcom.utils4j.data.structured.map.Maps ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


public class AssemblyListener extends AssemblyBaseListener {

	private static final Logger Logger = LoggerFactory.getLogger(AssemblyListener.class) ;


	final static String IDENTIFIER = ASMB_trimToContextName(IdentifierContext.class) ;
	final static String SYMBOL = ASMB_trimToContextName(SymbolContext.class) ;


	//@formatter:off

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

		actx.statement().operands().values().forEach(new Consumer<Operand>() {
			@Override
			public void accept(Operand o) { //
				System.err.print("operand (before) >>> " + o.moniker() + ": " + o.getClass().getSimpleName()) ; //
				if ( o.assignment() != null )
					System.err.print(" - " + o.assignment().getClass().getSimpleName() + ": " + o.assignment()) ; //
				System.err.println() ; //
			}
		}) ;

		final Map<String, Operand> _operands = actx.statement().operands() ;
		final Operand _operand99 = Maps.lastEntryValue(_operands) ;

		final String identifier = ((_operand99 instanceof Option) || (_operand99 instanceof Parameter) //
				? Maps.lastEntryKey(_operands) //
				: ASMB_formatGeneratedID(_operands)) ;

		final _ExprNode argument = EXPR_buildArgumentExpressionTree(actx, pctx) ;

		if ( _operands.isEmpty() || (!(_operand99 instanceof Option) && !(_operand99 instanceof Parameter)) )
			_operands.put(identifier, new Argument(identifier, argument)) ;

		else if ( (_operand99 instanceof Option) || (_operand99 instanceof Parameter) )
			_operand99.assignment(argument) ;

//		actx.statement().operands().values().forEach(new Consumer<Operand>() {
//			@Override
//			public void accept(Operand o) { //
//				System.err.print("operand (after) >>> " + o.moniker() + ": " + o.getClass().getSimpleName()) ; //
//				if ( o.assignment() != null )
//					System.err.print(" - " + o.assignment().getClass().getSimpleName() + ": " + o.assignment()) ; //
//				System.err.println() ; //
//			}
//		}) ;
	}


	@Log
	public void exitDirective(final ParserRuleContext pctx) {
		DIR_parseDirective(actx) ;
	}


	@Log
	public void exitLabel(final ParserRuleContext pctx) {

		if ( actx.statement().assemblyEnable() ) {

			final iSource _source = actx.source().peek() ;
			final String _label = ASMB_parsedText(pctx) ;
			final String _qualifedLabel = ASMB_formatQualifiedID(_label, _source.qualifier()) ;
//			System.err.println("_qualifedLabel: " + _qualifedLabel + ", _label: " + _label) ;

			if ( !actx.symbols().containsKey(_qualifedLabel) )
				actx.symbols().put(_qualifedLabel, new Moniker(_qualifedLabel, _label)) ;

			actx.identifier(actx.symbols().get(_qualifedLabel)) ;
			actx.identifier().value(actx.segment().loc()) ;
			actx.identifier().origin(actx.identifier().new Reference(actx.ln(), _source.sourceID(), _source.sourceLN())) ;

			actx.statement().label(actx.identifier()) ;
		}
	}


	@Log
	public void exitScopedLabel(final ParserRuleContext pctx) {

		if ( actx.statement().assemblyEnable() ) {

			final iSource _source = actx.source().peek() ;
			final String _label = ASMB_parsedText(pctx) ;
			final String _scopedLabel = ASMB_formatScopedID(_label, _source) ;
//			System.err.println("_scopedLabel: " + _scopedLabel + ", _label: " + _label) ;

			if ( !actx.symbols().containsKey(_scopedLabel) )
				actx.symbols().put(_scopedLabel, new Moniker(_scopedLabel, _label)) ;

			actx.identifier(actx.symbols().get(_scopedLabel)) ;
			actx.identifier().value(actx.segment().loc()) ;
			actx.identifier().origin(actx.identifier().new Reference(actx.ln(), _source.sourceID(), _source.sourceLN())) ;

			actx.statement().label(actx.identifier()) ;
		}
	}


	@Log
	public void enterOption(final ParserRuleContext pctx) {

//		actx.statement().operands().values().forEach(new Consumer<Operand>() {
//			@Override
//			public void accept(Operand o) { //
//				System.err.print("operand (before) >>> " + o.moniker() + ": " + o.getClass().getSimpleName()) ; //
//				if ( o.assignment() != null )
//					System.err.print(" - " + o.assignment().getClass().getSimpleName() + ": " + o.assignment()) ; //
//				System.err.println() ; //
//			}
//		}) ;

		final Map<String, Operand> _operands = actx.statement().operands() ;
		final String _identifier = ASMB_parsedText(pctx.getChild(0)) ;

		_operands.put(_identifier, new Option(_identifier)) ;

//		actx.statement().operands().values().forEach(new Consumer<Operand>() {
//			@Override
//			public void accept(Operand o) { //
//				System.err.print("operand (after) >>> " + o.moniker() + ": " + o.getClass().getSimpleName()) ; //
//				if ( o.assignment() != null )
//					System.err.print(" - " + o.assignment().getClass().getSimpleName() + ": " + o.assignment()) ; //
//				System.err.println() ; //
//			}
//		}) ;
	}


	@Log
	public void enterParameter(final ParserRuleContext pctx) {

		final Map<String, Operand> _operands = actx.statement().operands() ;
		final String _parameter = ASMB_parsedText(pctx.getChild(0)) ;
		final String _parameterContext = ASMB_parsedContextName(pctx.getChild(0)) ;

		if ( _parameterContext.equals(IDENTIFIER) || _parameterContext.equals(SYMBOL) )
			_operands.put(_parameter, new Parameter(_parameter)) ;
		else
			_operands.put(ASMB_formatGeneratedID(_operands), new Parameter(ASMB_formatGeneratedID(_operands))) ;

		actx.statement().operands().values().forEach(new Consumer<Operand>() {
			@Override
			public void accept(final Operand o) { //
//				System.err.print("operand (after) >>> " + o.moniker() + ": " + o.getClass().getSimpleName()) ; //
//				if ( o.assignment() != null )
//					System.err.print(" - " + o.assignment().getClass().getSimpleName() + ": " + o.assignment()) ; //
//				System.err.println() ; //
			}
		}) ;
	}
}
