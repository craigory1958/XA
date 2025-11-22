

package xcom.retro.xa.expressions.op.unary ;


import java.util.Stack ;

import xcom.retro.xa.expressions._ExprNode ;
import xcom.retro.xa.expressions.op._OpNode ;


public abstract class _UnaryOpNode extends _OpNode {

	protected _ExprNode node ;


	public _UnaryOpNode(final Integer precedence) {
		super(precedence) ;
	}


	@Override
	public void popOperands(final Stack<_ExprNode> node) {
		this.node = node.pop() ;
	}
}
