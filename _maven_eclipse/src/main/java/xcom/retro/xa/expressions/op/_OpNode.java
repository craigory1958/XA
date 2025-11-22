

package xcom.retro.xa.expressions.op ;


import java.util.Stack ;

import xcom.retro.xa.expressions._ExprNode ;


public abstract class _OpNode extends _ExprNode {

	Integer precedence ;

	public int precedence() {
		return precedence ;
	}


	public _OpNode(final Integer precedence) {
		super() ;
		this.precedence = precedence ;
	}


	public abstract void popOperands(Stack<_ExprNode> stack) ;
}
