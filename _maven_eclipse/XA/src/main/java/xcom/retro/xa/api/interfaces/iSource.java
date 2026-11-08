

package xcom.retro.xa.api.interfaces ;


import java.io.IOException ;

import xcom.retro.xa.Segment ;


public interface iSource {

	int fn() ;

	String fSpec() ;

	boolean list() ;

	int ln() ;

	String next() throws IOException ;

	String peek() throws IOException ;

	String qualifier() ;

	String scopeID() ;

	Segment segment() ;
}
