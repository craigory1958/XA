

package xcom.retro.xa.directives.dir ;


import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.retro.xa.Operand ;
import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.api.annotations.aDirective ;
import xcom.retro.xa.api.interfaces.iDirective ;
import xcom.retro.xa.expressions.value._ValueNode ;
import xcom.utils4j.data.structured.map.Maps ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@aDirective
public class ORG implements iDirective {

	AssemblyContext actx ;


	public ORG(final AssemblyContext actx) {
		this.actx = actx ;
	}


	@Log
	@Override
	public void parse(final ParserRuleContext pctx) {

		final Operand operand1 = Maps.firstEntryValue(actx.statement().operands()) ;
		final _ValueNode value = operand1.assignment().eval(actx.symbols()) ;
		actx.segment().setOrg((int) value.getValue()) ;
	}
}
