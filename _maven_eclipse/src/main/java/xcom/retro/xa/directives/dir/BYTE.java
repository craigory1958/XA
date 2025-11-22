

package xcom.retro.xa.directives.dir ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_lsb ;

import java.util.Map ;

import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.retro.xa.Operand ;
import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.api.annotations.aDirective ;
import xcom.retro.xa.api.interfaces.iDirective ;
import xcom.retro.xa.expressions.value.StringLiteral ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@aDirective
public class BYTE implements iDirective {

	AssemblyContext actx ;


	public BYTE(final AssemblyContext actx) {
		this.actx = actx ;
	}


	@Log
	@Override
	public void parse(final ParserRuleContext pctx) {

		final Map<String, Operand> _operands = actx.statement().operands() ;

		int size = 0 ;
		for ( final Operand operand : _operands.values() )
			size = size + (operand.assignment() instanceof StringLiteral ? ((StringLiteral) operand.assignment()).value().length : 1) ;

		final byte[] bytes = new byte[size] ;
		actx.statement().bytes(bytes) ;
		actx.segment().allocateBytes(actx.statement().bytes()) ;

		actx.statement().assemblyCallbackMethod("assemble") ;
		actx.statement().assemblyCallbackObject(this) ;
	}


	@Log
	public void assemble() {

		final Map<String, Operand> _operands = actx.statement().operands() ;

		final byte[] bytes = actx.statement().bytes() ;

		int b = 0 ;
		for ( final Operand operand : _operands.values() ) {
			final byte[] value = operand.assignment().eval(actx.symbols()).value() ;

			if ( operand.assignment() instanceof StringLiteral )
				for ( final byte _byte : value )
					bytes[b++] = _byte ;
			else
				bytes[b++] = EXPR_lsb(value) ;
		}

		actx.statement().bytes(bytes) ;
	}
}
