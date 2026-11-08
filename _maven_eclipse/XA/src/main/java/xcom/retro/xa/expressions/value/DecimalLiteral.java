

package xcom.retro.xa.expressions.value ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asBytes ;

import xcom.retro.xa.AssemblyContext ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
public class DecimalLiteral extends _ValueNode {

	//
	// Constructor(s) ...
	//

	public DecimalLiteral(final AssemblyContext actx, final String value) {
		super(actx, EXPR_asBytes(Integer.valueOf(value))) ;
	}
}
