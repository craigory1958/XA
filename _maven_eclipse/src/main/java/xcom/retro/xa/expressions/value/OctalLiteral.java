

package xcom.retro.xa.expressions.value ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asBytes ;


public class OctalLiteral extends _ValueNode {

	public OctalLiteral(final String value) {
		super(EXPR_asBytes(Integer.valueOf(value.substring(2), 8))) ;
	}
}
