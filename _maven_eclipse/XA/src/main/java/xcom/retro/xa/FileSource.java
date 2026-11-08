

package xcom.retro.xa ;


import java.io.BufferedReader ;
import java.io.FileInputStream ;
import java.io.FileNotFoundException ;
import java.io.IOException ;
import java.io.InputStreamReader ;
import java.util.HashMap ;
import java.util.LinkedList ;
import java.util.Map ;
import java.util.Queue ;
import java.util.UUID ;

import org.apache.commons.io.FilenameUtils ;
import org.apache.commons.lang3.StringUtils ;

import xcom.retro.xa.api.interfaces.iSource ;
import xcom.utils4j.format.Templator ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
public class FileSource implements iSource {

	public static final String[] $$Delimiters = { "$${", "}", "%%{", "}", "!!{", "}" } ;

	//
	// Attributes ...
	//

	//@formatter:off

	BufferedReader br ;

	String[] delimiters = new String[0] ;

	int fn ;

	String fSpec ;

	boolean list ;
	public FileSource list(final boolean list) {
		this.list = list ;
		return this ;
	}

	int ln ;

	String qualifier = "" ;

	Queue<String> queuedLines ;

	String scopeID ;

	Segment segment ;

	Map<String, Object> substituions = new HashMap<>() ;

	boolean wasNull ;

	boolean wasEmpty ;

	//@formatter:on


	//
	// Constructor(s) ...
	//

	public FileSource(final int fn, final String fSpec, final boolean list, final Segment segment) throws FileNotFoundException {

		this.fn = fn ;
		this.fSpec = fSpec ;
		this.list = list ;
		this.segment = segment ;

		_initialize(this) ;
	}


	public FileSource(final int fn, final String fSpec, final boolean list, final Segment segment, final Map<String, Object> substituions, final String[] delimiters)
			throws FileNotFoundException {

		this.fn = fn ;
		this.fSpec = fSpec ;
		this.list = list ;
		this.segment = segment ;
		this.substituions = substituions ;
		this.delimiters = delimiters ;

		_initialize(this) ;
	}


	public FileSource(final int fn, final String fSpec, final boolean list, final Segment segment, final String qualifier) throws FileNotFoundException {

		this.fn = fn ;
		this.fSpec = fSpec ;
		this.list = list ;
		this.segment = segment ;
		this.qualifier = qualifier ;

		_initialize(this) ;
	}


	public FileSource(final int fn, final String fSpec, final boolean list, final Segment segment, final String qualifier, final Map<String, Object> substituions,
			final String[] delimiters) throws FileNotFoundException {

		this.fn = fn ;
		this.fSpec = fSpec ;
		this.list = list ;
		this.segment = segment ;
		this.qualifier = qualifier ;
		this.substituions = substituions ;
		this.delimiters = delimiters ;

		_initialize(this) ;
	}


	void _initialize(final FileSource $) throws FileNotFoundException {

		$.br = new BufferedReader(new InputStreamReader(new FileInputStream($.fSpec))) ;
		$.queuedLines = new LinkedList<>() ;
		$.scopeID = UUID.randomUUID().toString().replaceAll("[-]", "") ;
		$.ln = 0 ;
		$.wasNull = false ;
		$.wasEmpty = false ;
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
	public String next() throws IOException { return readLine() ; }

	@Override
	public String peek() throws IOException {

		final String line = readLine() ;
		queuedLines.offer(line) ;

		return line ;
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

	public String readLine() throws IOException {

		String line = null ;

		if ( !wasNull ) {
			ln++ ;
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

		if ( (line != null) && !substituions.isEmpty() )
			line = Templator.delimiters(delimiters).template(line).inject(substituions) ;

		return line ;
	}


	//
	//
	//

	@Override
	public String toString() {
		return String.format("%s: (ln:%d id:%d qual:%s list:%b)", FilenameUtils.getName(fSpec), ln, fn, qualifier, list) ;
	}
}
