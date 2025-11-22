

package xcom.retro.xa.expressions.op.unary ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asBytes ;
import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asInteger ;

import java.util.Map ;

import xcom.retro.xa.Moniker ;
import xcom.retro.xa.expressions.value.DecimalLiteral ;
import xcom.retro.xa.expressions.value._ValueNode ;


public class LogicalNot extends _UnaryOpNode {

	public LogicalNot(final Integer precedence) {
		super(precedence) ;
	}


	@Override
	public _ValueNode eval(final Map<String, Moniker> symbols) {
		return new DecimalLiteral(EXPR_asBytes(0 - EXPR_asInteger(node.eval(symbols).value()))) ;
	}
}
