

package xcom.retro.xa.directives ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_formatAsHexLiterial ;

import java.util.HashMap ;
import java.util.Iterator ;
import java.util.Map ;

import org.antlr.v4.runtime.tree.ParseTreeListener ;
import org.antlr.v4.runtime.tree.ParseTreeWalker ;

import xcom.retro.xa.Operand ;
import xcom.retro.xa.Moniker ;
import xcom.retro.xa.XA.AssemblyContext ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


public class DirectiveUtils {

	public static void DIR_parseDirective(final AssemblyContext actx) {

		final ParseTreeListener listener = new DirectiveListener(actx) ;
		final ParseTreeWalker walker = new ParseTreeWalker() ;

		walker.walk(listener, actx.statement().pctx()) ;
	}


	@Log
	public static Map<String, Object> DIR_matchInvocationOperands(final Map<String, Operand> operands, final Map<String, Operand> options,
			final Map<String, Moniker> identifiers) {

		final Iterator<Operand> _operands = operands.values().iterator() ;
		final Iterator<Operand> _options = options.values().iterator() ;

		final Map<String, Object> parms = new HashMap<>() ;
		boolean ordinalMode = true ;

		for ( /* no loop var */ ; _operands.hasNext() || _options.hasNext(); /* no increment */ ) {

			final Operand operand = (_operands.hasNext() ? _operands.next() : null) ;
			final Operand option = (_options.hasNext() ? _options.next() : null) ;

			if ( ordinalMode && ((operand == null) || !operand.moniker().startsWith("{generated}.")) )
				ordinalMode = false ;

			if ( !ordinalMode && (operand != null) && !operand.moniker().startsWith("{generated}.") )
				parms.put(operand.moniker(), EXPR_formatAsHexLiterial(operand.assignment().eval(identifiers).getValue())) ;

			else if ( ordinalMode && (operand != null) && operand.moniker().startsWith("{generated}.") )
				parms.put(option.moniker(), EXPR_formatAsHexLiterial(operand.assignment().eval(identifiers).getValue())) ;

			else if ( !ordinalMode && (option != null) && (option.assignment() != null) )
				parms.put(option.moniker(), EXPR_formatAsHexLiterial(option.assignment().eval(identifiers).getValue())) ;
		}
		return parms ;
	}


	public static boolean DIR_isList(final Map<String, Object> parms) {
		return (parms.containsKey("list") ? ((String) parms.get("list")).equalsIgnoreCase(".list") : false) ;
	}


	public static boolean DIR_isList(final Operand _operandList, final Map<String, Moniker> _identifiers) {
		return (_operandList != null ? _operandList.assignment().eval(_identifiers).getValue().equals(".list") : false) ;
	}
}
