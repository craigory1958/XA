

package xcom.retro.xa.directives.dir ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_lsb ;
import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_msb ;

import java.util.Map ;

import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.retro.xa.Operand ;
import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.api.annotations.aDirective ;
import xcom.retro.xa.api.interfaces.iDirective ;
import xcom.utils4j.data.structured.map.Maps ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@aDirective
public class WORD implements iDirective {

	AssemblyContext actx ;


	public WORD(final AssemblyContext actx) {
		this.actx = actx ;
	}


	@Log
	@Override
	public void parse(final ParserRuleContext pctx) {

		final Map<String, Operand> _operands = actx.statement().operands() ;

		final byte[] bytes = new byte[_operands.size() * 2] ;
		actx.statement().bytes(bytes) ;
		actx.segment().allocateBytes(actx.statement().bytes()) ;

		actx.statement().assemblyCallbackMethod("assemble") ;
		actx.statement().assemblyCallbackObject(this) ;
	}


	@Log
	public void assemble() {

		final Map<String, Operand> _operands = actx.statement().operands() ;
		final Operand _operand1 = Maps.firstEntryValue(_operands) ;

		final byte[] bytes = actx.statement().bytes() ;

		for ( int b = 0; b < bytes.length; b = b + 2 ) {
			bytes[b] = EXPR_lsb(_operand1.assignment().eval(actx.symbols()).value()) ;
			bytes[b + 1] = EXPR_msb(_operand1.assignment().eval(actx.symbols()).value()) ;
		}

		actx.statement().bytes(bytes) ;
	}
}
