

package xcom.retro.xa.expressions.value ;


import xcom.utils4j.format.Strings ;


public class StringLiteral extends _ValueNode {

	public StringLiteral(final byte[] value) {
		super(value) ;
	}


	public StringLiteral(final String value) {
		super(Strings.trimQuotedString(value).getBytes()) ;
	}
}
