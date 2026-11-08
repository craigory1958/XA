

package xcom.retro.xa.expressions.value ;


import xcom.retro.xa.AssemblyContext ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
public class CharacterLiteral extends _ValueNode {

	//
	// Constructor(s) ...
	//

	public CharacterLiteral(final AssemblyContext actx, final String value) {
		super(actx, value.substring(1).getBytes()) ;
	}


	//
	//
	//

	@Override
	public String toString() {
		return new String(value) ;
	}
}
