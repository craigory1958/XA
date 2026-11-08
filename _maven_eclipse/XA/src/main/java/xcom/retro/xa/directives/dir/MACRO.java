

package xcom.retro.xa.directives.dir ;


import static xcom.retro.xa.XA.logEvent ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_formatQualifiedID ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_parsedText ;
import static xcom.retro.xa.directives.DirectiveUtils.DIRS_coerceList ;
import static xcom.retro.xa.directives.DirectiveUtils.DIRS_matchInvocationArguments ;
import static xcom.utils4j.format.Templator.UnixDelimiters ;

import java.util.ArrayList ;
import java.util.List ;
import java.util.Map ;

import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.BlockSource ;
import xcom.retro.xa.Identifier ;
import xcom.retro.xa.Operand ;
import xcom.retro.xa.api.annotations.aDirective ;
import xcom.retro.xa.api.interfaces.iDirective ;
import xcom.retro.xa.api.interfaces.iSource ;
import xcom.retro.xa.assembly.AssemblyUtils ;
import xcom.utils4j.data.structured.list.Lists ;
import xcom.utils4j.format.Templator ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
@aDirective
public class MACRO implements iDirective {

	//
	// Attributes ...
	//

	AssemblyContext actx ;
	String qualifier ;
	String name ;
	Map<String, Operand> options ;
	List<String> lines ;
	int sourceID ;
	int ln ;


	//
	// Constructor(s) ...
	//

	public MACRO(final AssemblyContext actx) {
		this.actx = actx ;
	}

	public MACRO(final AssemblyContext actx, final String name, final Map<String, Operand> options, final List<String> lines, final int sourceID,
			final int ln) {

		this.actx = actx ;
		this.name = name ;
		this.options = options ;
		this.lines = lines ;
		this.sourceID = sourceID ;
		this.ln = ln ;

		qualifier = actx.source().peek().qualifier() ;

		logEvent(actx.$args(), actx.phase(), "MACRO", "(MACRO) {}: sources: {}", this.getClass(), actx.sources()) ;
		logEvent(actx.$args(), actx.phase(), "MACRO", "(MACRO) {}: source: {}", this.getClass(), actx.source()) ;
	}


	//
	//
	//

	public void expand(final ParserRuleContext pctx) {

		logEvent(actx.$args(), actx.phase(), "dirs", "(dirs) {}: {}({})", this.getClass(), "expand", pctx) ;
		logEvent(actx.$args(), actx.phase(), "args", "(args) {}: args: {}", this.getClass(), actx.statement().operands()) ;

		final Map<String, Identifier> _ids = actx.identifiers() ;

		final Map<String, Operand> _options = options ;
		final Map<String, Operand> _operands = actx.statement().operands() ;
		final Operand _operandList = _operands.get("list") ;

		final String operandList_value = DIRS_coerceList(_operandList) ;

		logEvent(actx.$args(), actx.phase(), "MACRO", "(MACRO) {}: options: {}", this.getClass(), _options) ;
		logEvent(actx.$args(), actx.phase(), "MACRO", "(MACRO) {}: operands: {}", this.getClass(), _operands) ;
		logEvent(actx.$args(), actx.phase(), "MACRO", "(MACRO) {}: list: {}", this.getClass(), operandList_value) ;

		final Map<String, Object> parms = DIRS_matchInvocationArguments(actx, _options, _operands, _ids) ;
		logEvent(actx.$args(), actx.phase(), "parms", "(parms) {}: parms: {}", this.getClass(), parms) ;

		final List<String> lines = new ArrayList<>() ;
		for ( final String line : this.lines )
			lines.add(Templator.delimiters(UnixDelimiters).template(line).inject(parms)) ;


//		actx.sources().add(new BlockSource(sourceID, ln, lines, operandList_value.equalsIgnoreCase(".list"), actx.segment(), actx.source().peek().qualifier())) ;
		actx.sources().add(new BlockSource(sourceID, ln, lines, operandList_value.equalsIgnoreCase(".list"), actx.segment(), qualifier)) ;
		actx.source().push(Lists.last(actx.sources())) ;

		logEvent(actx.$args(), actx.phase(), "srcs", "(srcs) {}: sources: {}", this.getClass(), actx.sources()) ;
		logEvent(actx.$args(), actx.phase(), "srcs", "(srcs) {}: source: {}", this.getClass(), actx.source()) ;
		logEvent(actx.$args(), actx.phase(), "srcs", "(srcs} {}: statement: {}", this.getClass(), actx.statement()) ;
	}


	//
	// Implements iDirective ...
	//

	@Override
	public void parse(final ParserRuleContext pctx) {

//		logEvent(actx.$args(), actx.phase(), "dirs", "(dirs) {}: {}({})", this.getClass(), "parse", pctx.getClass().getSimpleName()) ;
		logEvent(actx.$args(), actx.phase(), "args", "(args) {}: args: {}", this.getClass(), actx.statement().operands()) ;

		final iSource _src = actx.source().peek() ;
		final int _sourceFN = _src.fn() ;
		final int _sourceLN = _src.ln() + 1 ;

		final String _macroName = ASMB_parsedText(pctx.getChild(0).getChild(0)) ;
		final String _macroNameFQN = ASMB_formatQualifiedID(_macroName, _src.qualifier()).toLowerCase() ;

		final Map<String, Operand> _options = actx.statement().operands() ;

		logEvent(actx.$args(), actx.phase(), "MACRO", "(MACRO) {}: options: {}", this.getClass(), _options) ;
		logEvent(actx.$args(), actx.phase(), "MACRO", "(MACRO) {}: fn: {}", this.getClass(), _sourceFN) ;
		logEvent(actx.$args(), actx.phase(), "MACRO", "(MACRO) {}: ln: {}", this.getClass(), _sourceLN) ;
		logEvent(actx.$args(), actx.phase(), "MACRO", "(MACRO) {}: macroName: {}", this.getClass(), _macroName) ;


		final List<String> lines = AssemblyUtils.ASMB_readSourceBlock(_src, ENDMACRO.class, actx) ;


		final MACRO macro = new MACRO(actx, _macroNameFQN, _options, lines, _sourceFN, _sourceLN) ;

		if ( !actx.macros().containsKey(macro.name.toUpperCase()) ) {
			actx.macros().put(macro.name.toUpperCase(), macro) ;
			logEvent(actx.$args(), actx.phase(), "MACRO", "(MACRO) {}: add MACRO: {}", this.getClass(), macro.name) ;
		}
		else
			System.err.println("Duplicate MACRO " + macro.name + ".") ;
	}
}
