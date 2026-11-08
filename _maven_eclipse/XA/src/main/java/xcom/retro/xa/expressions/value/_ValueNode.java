

package xcom.retro.xa.expressions.value ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asInteger ;
import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asString ;

import java.nio.ByteBuffer ;
import java.util.Map ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.Identifier ;
import xcom.retro.xa.expressions._ExprNode ;


public abstract class _ValueNode extends _ExprNode {

	//
	// Attributes ...
	//

	//@formatter:off

	byte[] value ;
	public byte[] value() { return value ; }

	//@formatter:on


	//
	// Constructor(s) ...
	//

	public _ValueNode(final AssemblyContext actx, final byte[] value) {

		super(actx) ;

		this.value = value.clone() ;
	}


	public _ValueNode(final AssemblyContext actx, final byte value) {

		super(actx) ;

		final byte[] b = { value } ;
		this.value = b ;
	}


	//
	// Instance methods ...
	//

	public Object getValue() {

		if ( this instanceof StringLiteral )
			return EXPR_asString(value) ;
		else
			return EXPR_asInteger(value) ;
	}


	//
	// Implements abstract class ...
	//

	@Override
	public _ValueNode eval(final Map<String, Identifier> identifiers) {
		return this ;
	}


	@Override
	public String toPostfix(final StringBuilder pfx) {
		return pfx.append(toString()).append(" ").toString() ;
	}


	//
	//
	//

	@Override
	public String toString() {
		return String.valueOf(ByteBuffer.wrap(value).getInt()) ;
	}
}
