

package xcom.retro.xa.expressions.op.unary ;


import java.util.Stack ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.expressions._ExprNode ;
import xcom.retro.xa.expressions.op._OpNode ;


public abstract class _UnaryOpNode extends _OpNode {

	//
	// Attributes ...
	//

	//@formatter:off

	protected _ExprNode node ;

	//@formatter:on


	//
	// Constructor(s) ...
	//

	public _UnaryOpNode(final AssemblyContext actx, final Integer precedence) {
		super(actx, precedence) ;
	}


	//
	// Implements abstract class ...
	//

	@Override
	public void popOperands(final Stack<_ExprNode> node) {
		this.node = node.pop() ;
	}


	@Override
	public String toPostfix(final StringBuilder pfx) {

		node.toPostfix(pfx) ;

		return pfx.append(toString()).append(" ").toString() ;
	}
}
