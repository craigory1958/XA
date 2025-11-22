

package xcom.retro.xa.expressions.value ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asBytes ;


public class HexLiteral extends _ValueNode {

	public HexLiteral(final String value) {
		super(EXPR_asBytes(Integer.valueOf((value.substring(0, 1).equals("$") ? value.substring(1) : value.substring(2)), 16))) ;
	}
}
