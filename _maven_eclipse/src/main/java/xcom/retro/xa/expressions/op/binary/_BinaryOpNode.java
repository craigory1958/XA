

package xcom.retro.xa.expressions.op.binary ;


import java.util.Stack ;

import xcom.retro.xa.expressions._ExprNode ;
import xcom.retro.xa.expressions.op._OpNode ;


public abstract class _BinaryOpNode extends _OpNode {

	protected _ExprNode lNode ;
	protected _ExprNode rNode ;


	public _BinaryOpNode(final Integer precedence) {
		super(precedence) ;
	}


	@Override
	public void popOperands(final Stack<_ExprNode> node) {
		rNode = node.pop() ;
		lNode = node.pop() ;
	}
}
