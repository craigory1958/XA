

package xcom.retro.xa.directives ;


import static xcom.retro.xa.XA.logEvent ;

import java.nio.charset.StandardCharsets ;
import java.util.HashMap ;
import java.util.Iterator ;
import java.util.Map ;

import org.antlr.v4.runtime.tree.ParseTreeListener ;
import org.antlr.v4.runtime.tree.ParseTreeWalker ;
import org.apache.commons.lang3.StringUtils ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.Identifier ;
import xcom.retro.xa.Operand ;
import xcom.retro.xa.expressions.value._ValueNode ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
public class DirectiveUtils {

	public static void DIRS_parseDirective(final AssemblyContext actx) {

		final ParseTreeListener listener = new DirectiveListener(actx) ;
		final ParseTreeWalker walker = new ParseTreeWalker() ;

		walker.walk(listener, actx.statement().pctx()) ;
	}


	public static Map<String, Object> DIRS_matchInvocationArguments(final AssemblyContext actx, final Map<String, Operand> options,
			final Map<String, Operand> operands, final Map<String, Identifier> identifiers) {

		logEvent(actx.$args(), actx.phase(), "args", "(args) {}: options: {}", DirectiveUtils.class, options) ;
		logEvent(actx.$args(), actx.phase(), "args", "(args) {}: operands: {}", DirectiveUtils.class, operands) ;


		final Iterator<Operand> _options = options.values().iterator() ;
		Operand option = null ;
		final Iterator<Operand> _operands = operands.values().iterator() ;
		Operand operand = null ;

		final Map<String, Object> args = new HashMap<>() ;
		boolean ordinalMode = true ;

		for ( /* no loop var */ ; _options.hasNext() || _operands.hasNext(); /* no increment */ ) {

			option = (_options.hasNext() && (option == null) ? _options.next() : null) ;
			operand = (_operands.hasNext() && (operand == null) ? _operands.next() : null) ;


			final String optionSource = (option != null ? StringUtils.trimToEmpty(option.source()) : "") ;
			final String optionSourceAssignment = (optionSource.contains("=") ? optionSource.substring(optionSource.indexOf("=") + 1) : optionSource) ;

			final String operandSource = (option != null ? StringUtils.trimToEmpty(operand.source()) : "") ;
			final String operandSourceAssignment = (operandSource.contains("=") ? operandSource.substring(operandSource.indexOf("=") + 1) : operandSource) ;

			logEvent(actx.$args(), actx.phase(), "args", "(args) {}: option: {} {}", DirectiveUtils.class, option, optionSourceAssignment) ;
			logEvent(actx.$args(), actx.phase(), "args", "(args) {}: operand: {} {}", DirectiveUtils.class, operand, operandSourceAssignment) ;


			if ( ordinalMode && ((option == null) || (operand == null) || !operand.moniker().startsWith("{generated}.")) )
				ordinalMode = false ;


			if ( ordinalMode && (option != null) && (operand != null) && operand.moniker().startsWith("{generated}.") ) {

				args.put(option.moniker(), operandSourceAssignment) ;

				option = null ;
				operand = null ;
			}
			else if ( !ordinalMode && (operand != null) && !operand.moniker().startsWith("{generated}.") ) {

				args.put(operand.moniker(), operandSourceAssignment) ;

				operand = null ;
			}
			else if ( !ordinalMode && (option != null) && !optionSourceAssignment.isEmpty() ) {

				args.put(option.moniker(), optionSourceAssignment) ;

				option = null ;
			}
		}

		logEvent(actx.$args(), actx.phase(), "args", "(args) {}: args: {}", DirectiveUtils.class, args) ;

		return args ;
	}


	public static String DIRS_coerceAs(final Operand operandAs) {
		return (operandAs != null ? new String(((_ValueNode) operandAs.assignment()).value(), StandardCharsets.UTF_8) : "") ;
	}


	public static String DIRS_coerceList(final Operand operandList) {
		return ((operandList != null) && operandList.source().equalsIgnoreCase("list=.list") ? ".list" : ".nolist") ;
	}


	public static boolean DIRS_isList(final Map<String, Object> parms) {
		return (parms.containsKey("list") ? ((String) parms.get("list")).equalsIgnoreCase(".list") : false) ;
	}


	public static boolean DIRS_isList(final Operand operandList, final Map<String, Identifier> identifiers) {
		return (operandList != null ? operandList.assignment().eval(identifiers).getValue().equals(".list") : false) ;
	}
}
