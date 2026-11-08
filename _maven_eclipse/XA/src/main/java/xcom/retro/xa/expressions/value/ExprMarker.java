

package xcom.retro.xa.expressions.value ;


import xcom.retro.xa.AssemblyContext ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
public class ExprMarker extends _ValueNode {

	//
	// Constructor(s) ...
	//

	public ExprMarker(final AssemblyContext actx, final String value) {
		super(actx, value.getBytes()) ;
	}


	//
	//
	//

	@Override
	public String toString() {
		return new String(value) ;
	}
}
