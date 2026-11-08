

package xcom.retro.xa.expressions ;


import java.util.Map ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.Identifier ;
import xcom.retro.xa.expressions.value._ValueNode ;


public abstract class _ExprNode {

	//
	// Attributes ...
	//

	//@formatter:off

	protected AssemblyContext actx ;

	//@formatter:on


	//
	// Constructor(s) ...
	//

	public _ExprNode(final AssemblyContext actx) {
		this.actx = actx ;
	}


	//
	// Abstract class methods ...
	//

	public abstract _ValueNode eval(Map<String, Identifier> identifiers) ;

	public abstract String toPostfix(StringBuilder pfx) ;
}
