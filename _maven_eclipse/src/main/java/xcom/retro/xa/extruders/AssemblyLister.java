

package xcom.retro.xa.extruders ;


import java.io.PrintWriter ;
import java.math.BigInteger ;
import java.util.Comparator ;
import java.util.HashMap ;
import java.util.List ;
import java.util.Map ;
import java.util.NoSuchElementException ;
import java.util.function.Predicate ;

import org.apache.commons.lang3.StringUtils ;

import xcom.retro.xa.Statement ;
import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.api.annotations.aExtruder ;
import xcom.retro.xa.api.interfaces.iExtruder ;
import xcom.utils4j.format.Strings ;


@aExtruder(format = "Listing")
public class AssemblyLister implements iExtruder {

	@Override
	public void extrude(final PrintWriter out, final AssemblyContext actx) {

		final int lnColWidth = (int) Math.floor(Math.log10(actx.ln())) + 1 ;
		final Map<Integer, Integer> sourceLineCounts = new HashMap<>() ;

		for ( final Statement _statement : actx.statements() )
			if ( _statement.list() ) {

				if ( _statement.sourceID() > 0 ) {

					final int lnSrcColWidth =
							(int) Math.floor(Math.log10(calcMaxSourceLineCount(_statement.sourceID(), actx.statements(), sourceLineCounts))) + 1 ;

					out.print(Strings.fillString(3 - lnSrcColWidth)) ;
					out.print("   A B C D E F G H I J K L M O N P Q R S T U V W X Y ZAAABACADAEAFAGAHAIAJAKALAMANAOAPAQARASATAUAVAWAXAYAZ"
							.substring(_statement.sourceID() * 2, (_statement.sourceID() * 2) + 2)) ;
					out.print(String.format(".%0" + lnSrcColWidth + "d", _statement.sourceLN())) ;
				}
				else
					out.print("      ") ;

				out.print(String.format("  %" + lnColWidth + "d:", _statement.ln())) ;


				out.print( //
						((_statement.line().isEmpty() || (_statement.bytes() == null) || (_statement.bytes().length == 0)) //
								&& ((_statement.label() == null) || StringUtils.trimToEmpty(_statement.label().scopedMoniker()).isEmpty()) //
						) || ((_statement.pctx() != null) && (_statement.pctx().getChild(1) != null //
						) && (_statement.pctx().getChild(1).getChild(0) != null //
						) && _statement.pctx().getChild(1).getChild(0).getClass().getSimpleName().equals("MacroContext")) //
								|| ((_statement.pctx() != null) && (_statement.pctx().getChild(1) != null //
								) && (_statement.pctx().getChild(1).getChild(0) != null //
								) && (_statement.pctx().getChild(1).getChild(0).getChild(1) != null //
								) && _statement.pctx().getChild(1).getChild(0).getChild(1).getText().equalsIgnoreCase("EQU") //
										&& (_statement.label() != null))  //
												? ((_statement.pctx() != null) && (_statement.pctx().getChild(1) != null //
												) && (_statement.pctx().getChild(1).getChild(0) != null //
												) && (_statement.pctx().getChild(1).getChild(0).getChild(1) != null //
												) && _statement.pctx().getChild(1).getChild(0).getChild(1).getText().equalsIgnoreCase("EQU") //
														&& (_statement.label() != null)  //
																? String.format(" (%04X)",
																		new BigInteger(actx.symbols().get(_statement.label().scopedMoniker()).value())
																				.intValue()) //
																: "       ")
												: String.format("  %04X ", _statement.loc()) //
				) ;


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

						if ( (b % 4) == 0 )
							out.print(String.format(Strings.fillString(lnColWidth) + "           %04X", _statement.loc() + b)) ;

						out.print(String.format(" %02X", _statement.bytes()[b])) ;
					}

					out.println() ;
				}
			}
	}


	static int calcMaxSourceLineCount(final int sourceID, final List<Statement> statements, final Map<Integer, Integer> lnCounts) {

		if ( !lnCounts.containsKey(sourceID) ) {

			final int max = statements //
					.stream() //
					.filter(new Predicate<Statement>() {
						@Override
						public boolean test(final Statement s) {
							return s.sourceID() == sourceID ;
						}
					}) //
					.max(Comparator.comparingInt(Statement::sourceLN)) //
					.orElseThrow(NoSuchElementException::new) //
					.sourceLN() ;

			lnCounts.put(sourceID, max) ;
		}


		return lnCounts.get(sourceID) ;
	}
}
