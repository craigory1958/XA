

package xcom.retro.xa ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asBytes ;

import java.util.ArrayList ;
import java.util.List ;

import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
public class Identifier {

	public enum IdentifierTypes {
		Label, Value, Symbol,;
	}


	public class Reference {

		//@formatter:off

		/**
		 * 
		 */
		int fn ;
		public int fn() { return fn ; }
		public Reference fn(final int fn) {
			this.fn = fn ;
			return this ;
		}

		/**
		 * 
		 */
		int ln ;
		public int ln() { return ln ; }
		public Reference ln(final int ln) {
			this.ln = ln ;
			return this ;
		}

		/**
		 * 
		 */
		int sn ;
		public int sn() { return sn ; }
		public Reference xln(final int sn) {
			this.sn = sn ;
			return this ;
		}

		//@formatter:on


		public Reference(final int sn, final int fn, final int ln) {

			this.sn = sn ;
			this.fn = fn ;
			this.ln = ln ;
		}
	}


	//
	// Attributes ...
	//

	//@formatter:off

	/**
	 * 
	 */
	String moniker ;
	public String moniker() { return moniker ; }

	/**
	 * 
	 */
	Reference origin ;
	public Reference origin() { return origin ; }
	public Identifier origin(final Reference origin) {
		this.origin = origin ;
		return this ;
	}

	/**
	 * 
	 */
	List<Reference> references ;
	public List<Reference> references() { return references ; }

	/**
	 * 
	 */
	String scopedMoniker ;
	public String scopedMoniker() { return scopedMoniker ; }

	/**
	 * 
	 */
	Integer sizeInBytes ;
	public Integer sizeInBytes() { return sizeInBytes ; }

	/**
	 * 
	 */
	IdentifierTypes type ;
	public IdentifierTypes type() { return type ; }
	public Identifier type(final IdentifierTypes type) {
		this.type = type ;
		return this ;
	}
	
	/**
	 * 
	 */
	byte[] value ;
	public byte[] value() { return value ; }
	public Identifier value(final byte[] value) {
		this.value = value ;
		return this ;
	}
	public Identifier value(final int value) {
		this.value = EXPR_asBytes(value) ;
		return this ;
	}

	//@formatter:on


	//
	// Constructor(s) ...
	//

	public Identifier(final String scopedMoniker) {

		this.scopedMoniker = scopedMoniker ;

		references = new ArrayList<>() ;
	}


	public Identifier(final String scopedMoniker, final String moniker) {

		this.scopedMoniker = scopedMoniker ;
		this.moniker = moniker ;

		references = new ArrayList<>() ;
	}


	public Identifier(final String moniker, final byte[] value) {

		this.moniker = moniker ;
		this.value = value.clone() ;

		references = new ArrayList<>() ;
	}


	//
	//
	//

	@Override
	public String toString() {
		return "(" + moniker + ", " + scopedMoniker + ", type: " + getClass().getSimpleName() + ", value: " + value + ")" ;
	}
}
