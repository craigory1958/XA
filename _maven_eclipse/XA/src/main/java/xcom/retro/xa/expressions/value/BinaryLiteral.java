

package xcom.retro.xa.expressions.value ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asBytes ;

import xcom.retro.xa.AssemblyContext ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
public class BinaryLiteral extends _ValueNode {

	//
	// Constructor(s) ...
	//

	public BinaryLiteral(final AssemblyContext actx, final String value) {
		super(actx, EXPR_asBytes(Integer.valueOf(value.substring(2), 2))) ;
	}
}
