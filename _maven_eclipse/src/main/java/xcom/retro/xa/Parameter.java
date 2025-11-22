

package xcom.retro.xa ;


import xcom.retro.xa.expressions._ExprNode ;


public class Parameter extends Operand {

	public Parameter(final String moniker, final _ExprNode assignment) {
		super(moniker, assignment) ;
	}

	public Parameter(final String moniker) {
		super(moniker) ;
	}
}
