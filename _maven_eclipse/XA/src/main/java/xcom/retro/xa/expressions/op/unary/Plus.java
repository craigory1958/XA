

package xcom.retro.xa.expressions.op.unary ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asInteger ;

import java.util.Map ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.Identifier ;
import xcom.retro.xa.expressions.value.HexLiteral ;
import xcom.retro.xa.expressions.value._ValueNode ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
public class Plus extends _UnaryOpNode {

	//
	// Constructor(s) ...
	//

	public Plus(final AssemblyContext actx, final Integer precedence) {
		super(actx, precedence) ;
	}


	//
	// Implements abstract class ...
	//

	@Override
	public _ValueNode eval(final Map<String, Identifier> identifiers) {
		return new HexLiteral(actx, EXPR_asInteger(node.eval(identifiers).value())) ;
	}


	//
	//
	//

	@Override
	public String toString() {
		return "+" ;
	}
}
