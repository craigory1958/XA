

package xcom.retro.xa ;


import java.io.IOException ;
import java.util.List ;
import java.util.UUID ;

import org.apache.commons.io.FilenameUtils ;

import xcom.retro.xa.api.interfaces.iSource ;


public class BlockSource implements iSource {

	//@formatter:off

	int fn ;

	String fSpec ;

	boolean list ;

	int ln ;

	String qualifier ;

	String scopeID ;

	Segment segment ;

	//@formatter:on


	int curLN ;
	List<String> lines ;


	public BlockSource(final int fn, final int ln, final List<String> lines, final boolean list, final Segment segment, final String qualifier) {

		this.fn = fn ;
		this.ln = ln - 1 ;
		this.lines = lines ;
		this.list = list ;
		this.segment = segment ;
		this.qualifier = qualifier ;

		scopeID = UUID.randomUUID().toString().replaceAll("[-]", "") ;
		curLN = 0 ;
	}


	//
	// Implements iSource ...
	//

	//@formatter:off

	@Override
	public String fSpec() { return fSpec ; }

	@Override
	public boolean list() { return list ; }

	@Override
	public String next() throws IOException {
		ln++ ;
		return (curLN < lines.size() ? lines.get(curLN++) : null) ;
	}

	@Override
	public String peek() throws IOException {
		return null ;
	}

	@Override
	public String qualifier() { return qualifier ; }

	@Override
	public String scopeID() { return scopeID ; }

	@Override
	public Segment segment() { return segment ; }

	@Override
	public int fn() { return fn ; }

	@Override
	public int ln() { return ln ; }

	//@formatter:on


	//
	//
	//

	@Override
	public String toString() {
		return String.format("%s: (ln:%d id:%d qual:%s list:%b)", FilenameUtils.getName(fSpec), ln, fn, qualifier, list) ;
	}
}
