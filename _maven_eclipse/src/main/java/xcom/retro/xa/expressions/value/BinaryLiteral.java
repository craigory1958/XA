

package xcom.retro.xa.expressions.value ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asBytes ;


public class BinaryLiteral extends _ValueNode {

	public BinaryLiteral(final String value) {
		super(EXPR_asBytes(Integer.valueOf(value.substring(2), 2))) ;
	}
}
