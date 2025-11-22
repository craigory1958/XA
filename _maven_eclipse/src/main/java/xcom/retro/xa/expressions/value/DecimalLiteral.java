

package xcom.retro.xa.expressions.value ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asBytes ;


public class DecimalLiteral extends _ValueNode {

	public DecimalLiteral(final byte[] value) {
		super(value) ;
	}


	public DecimalLiteral(final byte value) {
		super(value) ;
	}


	public DecimalLiteral(final int value) {
		super(EXPR_asBytes(value)) ;
	}


	public DecimalLiteral(final String value) {
		super(EXPR_asBytes(Integer.valueOf(value))) ;
	}
}
