

package xcom.retro.xa.expressions.op.unary ;


import java.util.Map ;

import xcom.retro.xa.Moniker ;
import xcom.retro.xa.expressions.value.DecimalLiteral ;
import xcom.retro.xa.expressions.value._ValueNode ;


public class HighByte extends _UnaryOpNode {

	public HighByte(final Integer precedence) {
		super(precedence) ;
	}


	@Override
	public _ValueNode eval(final Map<String, Moniker> symbols) {
		return new DecimalLiteral(node.eval(symbols).value()[1]) ;
	}
}
