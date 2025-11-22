

package xcom.retro.xa ;


import xcom.retro.xa.expressions._ExprNode ;


public class Argument extends Operand {

	//@formatter:off

	String moniker ;
	@Override
	public String moniker() { return moniker ; }

	//@formatter:on

	public Argument(final String moniker, final _ExprNode assignment) {
		super(assignment) ;

		this.moniker = moniker ;
	}
}
