

package xcom.retro.xa.directives.dir ;


import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.api.annotations.aDirective ;
import xcom.retro.xa.api.interfaces.iDirective ;


@aDirective
public class LIST implements iDirective {

	AssemblyContext actx ;


	public LIST(final AssemblyContext actx) {
		this.actx = actx ;
	}


	@Override
	public void parse(final ParserRuleContext pctx) {
		actx.list(true) ;
		actx.statement().list(false) ;
	}
}
