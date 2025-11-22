

package xcom.retro.xa.extruders ;


import java.io.PrintWriter ;
import java.util.ArrayList ;
import java.util.Comparator ;
import java.util.List ;

import xcom.retro.xa.Statement ;
import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.api.annotations.aExtruder ;
import xcom.retro.xa.api.interfaces.iExtruder ;


@aExtruder(format = "AIS")
public class AIS implements iExtruder {

	@Override
	public void extrude(final PrintWriter out, final AssemblyContext actx) {

		List<Statement> _statements = new ArrayList<>(actx.statements()) ;
		_statements.sort(Comparator.comparing(Statement::loc)) ;

		for ( final Statement _statement : _statements )
			if ( _statement.bytes() != null && _statement.bytes().length > 0 ) {

				for ( int b = 0; b < 4; b++ )
					if ( (_statement.bytes() != null) && (b < _statement.bytes().length) )
						out.print(String.format("0x%02X, ", _statement.bytes()[b])) ;
					else
						out.print("      ") ;

				out.print(String.format("//  %04X ", _statement.loc())) ;

				for ( int b = 0; b < 4; b++ )
					if ( (_statement.bytes() != null) && (b < _statement.bytes().length) )
						out.print(String.format("%02X ", _statement.bytes()[b])) ;
					else
						out.print("   ") ;

				out.println(String.format(" %s", _statement.line())) ;

				if ( (_statement.bytes() != null) && (_statement.bytes().length > 4) ) {
					for ( int b = 4; b < _statement.bytes().length; b++ ) {

						if ( ((b % 4) == 0) && (b > 4) )
							out.println() ;

						out.print(String.format("0x%02X,", _statement.bytes()[b])) ;
					}

					out.println() ;
				}
			}
	}
}
