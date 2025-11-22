

package xcom.retro.xa.expressions.op.binary ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asBytes ;
import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asInteger ;

import java.util.Map ;

import xcom.retro.xa.Moniker ;
import xcom.retro.xa.expressions.value.DecimalLiteral ;
import xcom.retro.xa.expressions.value._ValueNode ;


public class BitwiseShiftLeft extends _BinaryOpNode {

	public BitwiseShiftLeft(final Integer precedence) {
		super(precedence) ;
	}


	@Override
	public _ValueNode eval(final Map<String, Moniker> symbols) {
		return new DecimalLiteral(EXPR_asBytes(EXPR_asInteger(lNode.eval(symbols).value()) << EXPR_asInteger(rNode.eval(symbols).value()))) ;
	}
}
