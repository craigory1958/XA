

package xcom.retro.xa.expressions.value ;


public class CharacterLiteral extends _ValueNode {

	public CharacterLiteral(final String value) {
		super(value.substring(1).getBytes()) ;
	}
}
