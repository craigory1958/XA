

package xcom.retro.xa.directives.dir ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_lsb ;

import java.util.Map ;

import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.Operand ;
import xcom.retro.xa.Statement ;
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

		Statement _stmt = actx.statement() ;
		final Map<String, Operand> _operands = _stmt.operands() ;

		int size = 0 ;
		for ( final Operand operand : _operands.values() )
			size += (operand.assignment() instanceof StringLiteral ? ((StringLiteral) operand.assignment()).value().length : 1) ;

		final byte[] bytes = new byte[size] ;
		_stmt.bytes(bytes) ;
		actx.segment().allocateBytes(_stmt.bytes()) ;


		_stmt.assemblyCallbackMethod("assemble") ;
		_stmt.assemblyCallbackObject(this) ;
	}


	@Log
	public void assemble() {

		Statement _stmt = actx.statement() ;
		final Map<String, Operand> _operands = _stmt.operands() ;

		final byte[] bytes = _stmt.bytes() ;

		int b = 0 ;
		for ( final Operand operand : _operands.values() ) {
			final byte[] value = operand.assignment().eval(actx.identifiers()).value() ;

			if ( operand.assignment() instanceof StringLiteral )
				for ( final byte _byte : value )
					bytes[b++] = _byte ;
			else
				bytes[b++] = EXPR_lsb(value) ;
		}

		_stmt.bytes(bytes) ;
	}
}
