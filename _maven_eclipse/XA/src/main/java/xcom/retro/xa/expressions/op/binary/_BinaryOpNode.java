

package xcom.retro.xa.expressions.op.binary ;


import java.util.Stack ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.expressions._ExprNode ;
import xcom.retro.xa.expressions.op._OpNode ;


public abstract class _BinaryOpNode extends _OpNode {

	//
	// Attributes ...
	//

	//@formatter:off

	protected _ExprNode lNode ;
	protected _ExprNode rNode ;

	//@formatter:on


	//
	// Constructor(s) ...
	//

	public _BinaryOpNode(final AssemblyContext actx, final Integer precedence) {
		super(actx, precedence) ;
	}


	//
	// Implements abstract class ...
	//

	@Override
	public void popOperands(final Stack<_ExprNode> stack) {
		rNode = stack.pop() ;
		lNode = stack.pop() ;
	}


	@Override
	public String toPostfix(final StringBuilder pfx) {

		lNode.toPostfix(pfx) ;
		rNode.toPostfix(pfx) ;
		return pfx.append(toString()).append(" ").toString() ;
	}
}
