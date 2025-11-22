

package xcom.retro.xa.expressions.op.unary ;


import java.util.Map ;

import xcom.retro.xa.Moniker ;
import xcom.retro.xa.expressions.value.DecimalLiteral ;
import xcom.retro.xa.expressions.value._ValueNode ;


public class LowByte extends _UnaryOpNode {

	public LowByte(final Integer precedence) {
		super(precedence) ;
	}


	@Override
	public _ValueNode eval(final Map<String, Moniker> symbols) {
		return new DecimalLiteral(node.eval(symbols).value()[0]) ;
	}
}
