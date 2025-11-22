

package xcom.retro.xa.expressions.value ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asInteger ;
import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asString ;

import java.util.Map ;

import com.google.common.primitives.Bytes ;

import xcom.retro.xa.Moniker ;
import xcom.retro.xa.expressions._ExprNode ;


public abstract class _ValueNode extends _ExprNode {

	//@formatter:off

	byte[] value ;
	public byte[] value() { return value ; }

	//@formatter:on


	public _ValueNode(final byte[] value) {
		this.value = value.clone() ;
	}


	public _ValueNode(final byte value) {
		final byte[] b = { value } ;
		this.value = b ;
	}


	public Object getValue() {

		if ( this instanceof StringLiteral )
			return EXPR_asString(value) ;
		else
			return EXPR_asInteger(value) ;
	}


	@Override
	public _ValueNode eval(final Map<String, Moniker> symbols) {
		return this ;
	}


	@Override
	public String toString() {
		return Bytes.asList(value).toString() ;
	}
}
