

package xcom.retro.xa.directives.dir ;


import static xcom.retro.xa.XA.logEvent ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_formatQualifiedID ;

import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.Identifier ;
import xcom.retro.xa.api.annotations.aDirective ;
import xcom.retro.xa.api.interfaces.iDirective ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@aDirective
public class IFDEF implements iDirective {

	//@formatter:off

	AssemblyContext actx ;

	//@formatter:on


	public IFDEF(final AssemblyContext actx) {
		this.actx = actx ;
	}


	@Log
	@Override
	public void parse(final ParserRuleContext pctx) {

		logEvent(actx.$args(), actx.phase(), "args", "(args) {}: args: {}", this.getClass(), actx.statement().operands()) ;

		final String _id = pctx.getChild(0).getChild(2).getChild(0).getChild(0).getChild(0).getChild(0).getText() ;
		final String _idFQN = ASMB_formatQualifiedID(_id, actx.source().peek().qualifier()).toLowerCase() ;
//		final iSource _src = actx.source().peek() ;
//		final String _idFQN = ASMB_formatQualifiedID(_id, _src.qualifier()).toLowerCase() ;

		final Identifier identifier = actx.identifiers().get(_idFQN) ;
		final boolean defined = ((identifier != null) && (identifier.value() != null)) ;

		actx.assemble().push(actx.assemble().peek() && defined) ;
	}
}
