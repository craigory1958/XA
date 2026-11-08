

package xcom.retro.xa.expressions.value ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asBytes ;

import xcom.retro.xa.AssemblyContext ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
public class HexLiteral extends _ValueNode {

	//
	// Constructor(s) ...
	//

	public HexLiteral(final AssemblyContext actx, final byte[] value) {
		super(actx, value) ;
	}


	public HexLiteral(final AssemblyContext actx, final byte value) {
		super(actx, value) ;
	}


	public HexLiteral(final AssemblyContext actx, final int value) {
		super(actx, EXPR_asBytes(value)) ;
	}


	public HexLiteral(final AssemblyContext actx, final String value) {
		super(actx, EXPR_asBytes(Integer.valueOf((value.substring(0, 1).equals("$") ? value.substring(1) : value.substring(2)), 16))) ;
	}
}
