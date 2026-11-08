

package xcom.retro.xa.expressions.value ;


import static xcom.retro.xa.XA.logEvent ;

import java.util.Map ;
import java.util.Optional ;
import java.util.function.Predicate ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.Identifier ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;
import xcom.utils4j.logging.aspects.api.annotations.NoLog ;


@Log
public class Token extends _ValueNode {

	//
	// Constructor(s) ...
	//

	public Token(final AssemblyContext actx, final String value) {
		super(actx, value.getBytes()) ;
	}


	public Token(final AssemblyContext actx, final byte[] value) {
		super(actx, value) ;
	}


	//
	// Implements abstract class ...
	//

	@Override
	public _ValueNode eval(final Map<String, Identifier> identifiers) {

		final String id = new String(value()) ;
		logEvent(actx.$args(), actx.phase(), "eval", "(eval) {}: {}", this.getClass(), id) ;
//		System.err.println(identifiers.containsKey(id.toLowerCase())) ;
//		System.err.println((identifiers.get(id.toLowerCase()).value() != null)) ;

		if ( identifiers.containsKey(id.toLowerCase()) && (identifiers.get(id.toLowerCase()).value() != null) )
			return new Token(actx, identifiers.get(id.toLowerCase()).value()) ;


		final Optional<Identifier> identifier = identifiers.values().stream().filter(new Predicate<Identifier>() {
			@NoLog
			@Override
			public boolean test(final Identifier v) {
				return v.moniker().toLowerCase().equals(id.toLowerCase()) ;
			}
		}).findFirst() ;

		if ( identifier.isPresent() && (identifier.get().value() != null) )
			return new Token(actx, identifier.get().value()) ;

		return null ;
	}


	//
	//
	//

	@Override
	public String toString() {
		return new String(value) ;
	}
}
