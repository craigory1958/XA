

package xcom.retro.xa.directives.dir ;


import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.api.annotations.aDirective ;
import xcom.retro.xa.api.interfaces.iDirective ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@aDirective
public class END implements iDirective {

	AssemblyContext actx ;


	public END(final AssemblyContext actx) {
		this.actx = actx ;
	}


	@Log
	@Override
	public void parse(final ParserRuleContext pctx) {}
}
