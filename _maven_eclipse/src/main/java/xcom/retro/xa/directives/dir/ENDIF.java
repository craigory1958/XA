

package xcom.retro.xa.directives.dir ;


import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.api.annotations.aDirective ;
import xcom.retro.xa.api.interfaces.iDirective ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@aDirective
public class ENDIF implements iDirective {

	AssemblyContext actx ;


	public ENDIF(final AssemblyContext actx) {
		this.actx = actx ;
	}


	@Log
	@Override
	public void parse(final ParserRuleContext pctx) {

//		for(int i=0;i<actx.ifBlocks().size();i++) {
//			System.out.println(actx.ifBlocks().elementAt(i)) ;
//		}
		actx.ifBlocks().pop() ;
	}
}
