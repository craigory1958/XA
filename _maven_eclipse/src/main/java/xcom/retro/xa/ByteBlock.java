

package xcom.retro.xa ;


import java.util.Arrays ;


public class ByteBlock {

	//@formatter:off

	Integer org ;
	public Integer org() { return org ; }

	int loc ;
	public ByteBlock loc(final int loc) {
		this.loc = loc ;
		return this ;
	}

	int numBytes ;
	public int numBytes() { return numBytes ; }

	byte[] bytes ;
	public byte[] bytes() { return bytes ; }

	//@formatter:on


	public ByteBlock() {
		loc = 0 ;
		numBytes = 0 ;
		bytes = new byte[0] ;
	}


	public int allocateBytes(final byte[] bytes) {

		if ( org == null )
			org = loc ;

		this.bytes = ((numBytes + bytes.length) > this.bytes.length) ? Arrays.copyOf(this.bytes, this.bytes.length + 16) : this.bytes ;

		System.arraycopy(bytes, 0, this.bytes, numBytes, bytes.length) ;
		numBytes += bytes.length ;

		return (loc += bytes.length) ;
	}


	public void fillBytes(final int address, final byte[] bytes) {

		try {
			for ( int b = 0; b < bytes.length; b++ )
				this.bytes[(address - org) + b] = bytes[b] ;
		}
		catch ( final Exception ex ) {}
	}


	public int setOrg(final int value) {
		return loc = org = value ;
	}


	public boolean hasOrg() {
		return (org == null) ;
	}
}
