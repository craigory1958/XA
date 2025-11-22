

package xcom.retro.xa.directives.dir ;


import java.util.Map ;

import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.retro.xa.Operand ;
import xcom.retro.xa.XA.AssemblyContext ;
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

//		actx.statement().operands().values().forEach(new Consumer<Operand>() {
//			@Override
//			public void accept(final Operand o) { //
//				System.err.print("operand (before) >>> " + o.moniker() + ": " + o.getClass().getSimpleName()) ; //
//				if ( o.assignment() != null )
//					System.err.print(" - " + o.assignment().getClass().getSimpleName() + ": " + o.assignment()) ; //
//				System.err.println() ; //
//			}
//		}) ;

		final Map<String, Operand> _operands = actx.statement().operands() ;

		final Operand operand1 = Maps.firstEntryValue(_operands) ;
//		System.err.println(operand1.moniker()) ;
		actx.ifBlocks().push(actx.ifBlocks().peek() && ((int) operand1.assignment().eval(actx.symbols()).getValue() != 0)) ;
	}
}
