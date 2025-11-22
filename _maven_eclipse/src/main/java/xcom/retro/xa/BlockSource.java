

package xcom.retro.xa ;


import java.io.IOException ;
import java.util.List ;
import java.util.UUID ;

import xcom.retro.xa.api.interfaces.iSource ;


public class BlockSource implements iSource {

	//@formatter:off

	String qualifier ;
	@Override
	public String qualifier() { return qualifier ; }

	String fSpec ;
	@Override
	public String fSpec() { return fSpec ; }

	boolean list ;
	@Override
	public boolean list() { return list ; }
	public BlockSource list(final boolean list) {
		this.list = list ;
		return this ;
	}

	String scopeID ;
	@Override
	public String scopeID() { return scopeID ; }

	int sourceID ;
	@Override
	public int sourceID() { return sourceID ; }

	int sourceLN ;
	@Override
	public int sourceLN() { return sourceLN ; }

	//@formatter:on


	int curLN ;
	List<String> lines ;


	public BlockSource(final int sourceID, final int ln, final List<String> lines, final boolean list, final String qualifier) {

		this.sourceID = sourceID ;
		sourceLN = ln - 1 ;
		this.lines = lines ;
		this.list = list ;
		this.qualifier = qualifier ;

		scopeID = UUID.randomUUID().toString().replaceAll("[-]", "") ;
		curLN = 0 ;
	}


	@Override
	public String next() throws IOException {
		sourceLN++ ;
		return (curLN < lines.size() ? lines.get(curLN++) : null) ;
	}


	@Override
	public String peek() throws IOException {
		return null ;
	}
}
