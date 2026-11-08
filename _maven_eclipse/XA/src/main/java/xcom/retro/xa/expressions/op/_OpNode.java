

package xcom.retro.xa.expressions.op ;


import java.util.Stack ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.expressions._ExprNode ;


public abstract class _OpNode extends _ExprNode {

	//
	// Attributes ...
	//

	//@formatter:off

	Integer precedence ;
	public Integer precedence() {
		return precedence ;
	}

	//@formatter:on


	//
	// Constructor(s) ...
	//

	public _OpNode(final AssemblyContext actx, final Integer precedence) {

		super(actx) ;

		this.precedence = precedence ;
	}


	//
	// Abstract class methods ...
	//

	public abstract void popOperands(Stack<_ExprNode> stack) ;
}
