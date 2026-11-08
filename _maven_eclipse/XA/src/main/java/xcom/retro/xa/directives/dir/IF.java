

package xcom.retro.xa.directives.dir ;


import java.util.Map ;

import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.Operand ;
import xcom.retro.xa.api.annotations.aDirective ;
import xcom.retro.xa.api.interfaces.iDirective ;
import xcom.utils4j.data.structured.map.Maps ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@aDirective
public class IF implements iDirective {

	//@formatter:off

	AssemblyContext actx ;

	//@formatter:on


	public IF(final AssemblyContext actx) {
		this.actx = actx ;
	}


	@Log
	@Override
	public void parse(final ParserRuleContext pctx) {

		final Map<String, Operand> _operands = actx.statement().operands() ;

		final Operand operand1 = Maps.firstEntryValue(_operands) ;
		actx.assemble().push(actx.assemble().peek() && ((int) operand1.assignment().eval(actx.identifiers()).getValue() != 0)) ;
	}
}
