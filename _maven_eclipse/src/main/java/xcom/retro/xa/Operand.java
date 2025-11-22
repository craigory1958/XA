

package xcom.retro.xa ;


import xcom.retro.xa.expressions._ExprNode ;


public class Operand {

	//@formatter:off

	String moniker ;
	public String moniker() { return moniker ; }

	_ExprNode assignment ;
	public _ExprNode assignment() { return assignment ; }
	public Operand assignment(final _ExprNode assignment) {
		this.assignment=assignment ;
		return this ;
	}

	//@formatter:on


	public Operand(final String moniker, final _ExprNode assignment) {
		this.moniker = moniker ;
		this.assignment = assignment ;
	}

	public Operand(final String moniker) {
		this.moniker = moniker ;
	}

	public Operand(final _ExprNode assignment) {
		this.assignment = assignment ;
	}
}
