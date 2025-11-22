

package xcom.retro.xa ;


import java.io.BufferedReader ;
import java.io.FileInputStream ;
import java.io.FileNotFoundException ;
import java.io.IOException ;
import java.io.InputStreamReader ;
import java.util.LinkedList ;
import java.util.Queue ;
import java.util.UUID ;

import org.apache.commons.lang3.StringUtils ;

import xcom.retro.xa.api.interfaces.iSource ;


public class FileSource implements iSource {

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
	public FileSource list(final boolean list) {
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


	BufferedReader br ;
	Queue<String> queuedLines ;
	boolean wasNull = false ;
	boolean wasEmpty = false ;


	//
	//
	//

	public FileSource(final int sourceID, final String fSpec, final boolean list) throws FileNotFoundException {

		this.sourceID = sourceID ;
		this.fSpec = fSpec ;
		this.list = list ;

		_initialize(this) ;
	}

	public FileSource(final int sourceID, final String fSpec, final boolean list, final String qualifier) throws FileNotFoundException {

		this.sourceID = sourceID ;
		this.fSpec = fSpec ;
		this.list = list ;
		this.qualifier = qualifier ;

		_initialize(this) ;
	}


	void _initialize(final FileSource $) throws FileNotFoundException {

		$.scopeID = UUID.randomUUID().toString().replaceAll("[-]", "") ;
		$.br = new BufferedReader(new InputStreamReader(new FileInputStream($.fSpec))) ;
		$.sourceLN = 0 ;
		$.queuedLines = new LinkedList<>() ;
	}


	//
	//
	//

	@Override
	public String next() throws IOException {

//		if ( queuedLines.size() > 1 )
//			return queuedLines.poll() ;
//
//		if ( queuedLines.size() > 0 )
//			queuedLines.poll() ;

		return readLine() ;
	}


	@Override
	public String peek() throws IOException {

		final String line = readLine() ;
		queuedLines.offer(line) ;

		return line ;
	}


	public String readLine() throws IOException {

		String line = null ;

		if ( !wasNull ) {
			sourceLN++ ;
			line = br.readLine() ;
		}

		if ( line != null )
			wasEmpty = StringUtils.trimToEmpty(line).isEmpty() ;

		else if ( wasNull )
			line = null ;

		else {
			wasNull = true ;
			br.close() ;

			if ( !wasEmpty )
				line = "" ;
		}

		return line ;
	}
}
