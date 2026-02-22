

package xcom.retro.xa ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asBytes ;

import java.util.ArrayList ;
import java.util.List ;


public class Moniker {

//	public enum MonikerTypes {
//		Identifer, Label, Symbol,;
//	}


	public class Reference {

		//@formatter:off

		int ln ;
		public int ln() { return ln ; }
		public Reference ln(final int ln) {
			this.ln = ln ;
			return this ;
		}

		int sourceID ;
		public int sourceID() { return sourceID ; }
		public Reference sourceID(final int sourceID) {
			this.sourceID = sourceID ;
			return this ;
		}

		int sourceLN ;
		public int sourceLN() { return sourceLN ; }
		public Reference sourceLN(final int sourceLN) {
			this.sourceLN = sourceLN ;
			return this ;
		}

		//@formatter:on


		public Reference(final int ln, final int sourceID, final int sourceLN) {

			this.ln = ln ;
			this.sourceID = sourceID ;
			this.sourceLN = sourceLN ;
		}
	}


	//@formatter:off

	String scopedMoniker ;
	public String scopedMoniker() { return scopedMoniker ; }

	String moniker ;
	public String moniker() { return moniker ; }

	Reference origin ;
	public Reference origin() { return origin ; }
	public Moniker origin(final Reference origin) {
		this.origin = origin ;
		return this ;
	}

	List<Reference> references ;
	public List<Reference> references() { return references ; }

//	String rootName ;
//	public String rootName() { return rootName ; }

//	int ln ;
//	public int ln() { return ln ; }
//	public Identifier ln(int ln) {
//		this.ln = ln ;
//		return this ;
//	}
//
//	int sourceID ;
//	public int sourceID() { return sourceID ; }
//	public Identifier sourceID(int sourceID) {
//		this.sourceID = sourceID ;
//		return this ;
//	}
//
//	int sourceLN ;
//	public int sourceLN() { return sourceLN ; }
//	public Identifier sourceLN(int sourceLN) {
//		this.sourceLN = sourceLN ;
//		return this ;
//	}

//	MonikerTypes type ;
//	public MonikerTypes type() { return type ; }

	byte[] value ;
	public byte[] value() { return value ; }
	public Moniker value(final byte[] value) {
		this.value = value ;
		return this ;
	}
	public Moniker value(final int value) {
		this.value = EXPR_asBytes(value) ;
		return this ;
	}

	//@formatter:on


	//
	//
	//

	public Moniker(final String scopedMoniker) {

		this.scopedMoniker = scopedMoniker ;

		references = new ArrayList<>() ;
	}


	public Moniker(final String scopedMoniker, final String moniker) {

		this.scopedMoniker = scopedMoniker ;
		this.moniker = moniker ;

		references = new ArrayList<>() ;
	}


	public Moniker(final String moniker, final byte[] value) {

		this.moniker = moniker ;
		this.value = value.clone() ;

		references = new ArrayList<>() ;
	}


//	public Identifier(final String name, final int value) {
//
//		this.name = name ;
//		this.value = asBytes(value) ;
//
//		this.references = new ArrayList<>();
//	}
}
