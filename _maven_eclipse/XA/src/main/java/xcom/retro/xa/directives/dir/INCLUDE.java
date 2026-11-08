

package xcom.retro.xa.directives.dir ;


import static xcom.retro.xa.FileSource.$$Delimiters ;
import static xcom.retro.xa.XA.logEvent ;
import static xcom.retro.xa.directives.DirectiveUtils.DIRS_coerceAs ;
import static xcom.retro.xa.directives.DirectiveUtils.DIRS_coerceList ;

import java.io.FileNotFoundException ;
import java.util.HashMap ;
import java.util.Map ;

import org.antlr.v4.runtime.ParserRuleContext ;
import org.apache.commons.io.FilenameUtils ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.FileSource ;
import xcom.retro.xa.Identifier ;
import xcom.retro.xa.Operand ;
import xcom.retro.xa.api.annotations.aDirective ;
import xcom.retro.xa.api.interfaces.iDirective ;
import xcom.utils4j.data.structured.list.Lists ;
import xcom.utils4j.data.structured.map.Maps ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
@aDirective
public class INCLUDE implements iDirective {

	//
	// Attributes ...
	//

	AssemblyContext actx ;


	//
	// Constructor(s) ...
	//

	public INCLUDE(final AssemblyContext actx) {
		this.actx = actx ;
	}


	//
	// Implements iDirective ...
	//

	@Override
	public void parse(final ParserRuleContext pctx) {

//		logEvent(actx.$args(), actx.phase(), "dirs", "(dirs) {}: {}({})", this.getClass(), "parse", pctx) ;
		logEvent(actx.$args(), actx.phase(), "args", "(args) {}: args: {}", this.getClass(), actx.statement().operands()) ;


		final Map<String, Identifier> _ids = actx.identifiers() ;

		final Map<String, Operand> _operands = actx.statement().operands() ;
		final Operand _operand1 = Maps.firstEntryValue(_operands) ;
		final Operand _operandAs = _operands.get("as") ;
		final Operand _operandList = _operands.get("list") ;

		final String operand1_value = (String) _operand1.assignment().eval(_ids).getValue() ;
		final String operandAs_value = DIRS_coerceAs(_operandAs) ;
		final String operandList_value = DIRS_coerceList(_operandList) ;

		final String fSpec = FilenameUtils.getFullPath((String) actx.decodedArgs().get("source")) + operand1_value ;

		logEvent(actx.$args(), actx.phase(), "INCLUDE", "(INCLUDE) {}: fSpec: {}", this.getClass(), fSpec) ;
		logEvent(actx.$args(), actx.phase(), "INCLUDE", "(INCLUDE) {}: as: {}", this.getClass(), operandAs_value) ;
		logEvent(actx.$args(), actx.phase(), "INCLUDE", "(INCLUDE) {}: list: {}", this.getClass(), operandList_value) ;

		actx.list(operandList_value.equalsIgnoreCase(".list")) ;


		final Map<String, Object> parms = new HashMap<>() ;
		parms.put("as", operandAs_value) ;
		parms.put("list", operandList_value) ;


		try {
			final int fn = actx.fn(actx.fn() + 1).fn() ;
			actx.sources().add(new FileSource(fn, fSpec, actx.list(), actx.segment(), operandAs_value, parms, $$Delimiters)) ;
			actx.source().push(Lists.last(actx.sources())) ;
		}
		catch ( final FileNotFoundException e ) {}

		logEvent(actx.$args(), actx.phase(), "srcs", "(srcs) {}: sources: {}", this.getClass(), actx.sources()) ;
		logEvent(actx.$args(), actx.phase(), "srcs", "(srcs) {}: source: {}", this.getClass(), actx.source()) ;
		logEvent(actx.$args(), actx.phase(), "srcs", "(srcs} {}: statement: {}", this.getClass(), actx.statement()) ;
	}
}
