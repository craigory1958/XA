

package xcom.retro.xa.expressions.value ;


import xcom.retro.xa.AssemblyContext ;
import xcom.utils4j.format.Strings ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
public class StringLiteral extends _ValueNode {

	//
	// Constructor(s) ...
	//

//	public StringLiteral(final AssemblyContext actx, final byte[] value) {
//		super(actx, value) ;
//	}


	public StringLiteral(final AssemblyContext actx, final String value) {
		super(actx, Strings.trimQuotedString(value).getBytes()) ;
	}


	//
	//
	//

	@Override
	public String toString() {
		return new String(value) ;
	}
}
