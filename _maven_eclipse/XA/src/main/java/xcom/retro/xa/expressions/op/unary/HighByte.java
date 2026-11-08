

package xcom.retro.xa.expressions.op.unary ;


import java.util.Map ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.Identifier ;
import xcom.retro.xa.expressions.value.HexLiteral ;
import xcom.retro.xa.expressions.value._ValueNode ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
public class HighByte extends _UnaryOpNode {

	//
	// Constructor(s) ...
	//

	public HighByte(final AssemblyContext actx, final Integer precedence) {
		super(actx, precedence) ;
	}


	//
	// Implements abstract class ...
	//

	@Override
	public _ValueNode eval(final Map<String, Identifier> identifiers) {
		return new HexLiteral(actx, node.eval(identifiers).value()[1]) ;
	}


	//
	//
	//

	@Override
	public String toString() {
		return "<" ;
	}
}
