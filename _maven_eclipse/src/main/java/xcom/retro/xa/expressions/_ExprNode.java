

package xcom.retro.xa.expressions ;


import java.util.Map ;

import xcom.retro.xa.Moniker ;
import xcom.retro.xa.expressions.value._ValueNode ;


public abstract class _ExprNode {

	public abstract _ValueNode eval(Map<String, Moniker> symbols) ;
}
