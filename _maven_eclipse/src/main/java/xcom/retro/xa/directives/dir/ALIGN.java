

package xcom.retro.xa.directives.dir ;


import java.util.Map ;

import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.retro.xa.Operand ;
import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.api.annotations.aDirective ;
import xcom.retro.xa.api.interfaces.iDirective ;
import xcom.retro.xa.expressions.value._ValueNode ;
import xcom.utils4j.data.structured.map.Maps ;


@aDirective
public class ALIGN implements iDirective {

	AssemblyContext actx ;


	public ALIGN(final AssemblyContext actx) {
		this.actx = actx ;
	}


	@Override
	public void parse(final ParserRuleContext pctx) {

		final Map<String, Operand> _operands = actx.statement().operands() ;

		final Operand operand1 = Maps.firstEntryValue(_operands) ;
		final _ValueNode value = operand1.assignment().eval(actx.symbols()) ;
		actx.segment().setOrg((actx.statement().loc() + ((int) value.getValue())) - (actx.statement().loc() % ((int) value.getValue()))) ;
	}
}
