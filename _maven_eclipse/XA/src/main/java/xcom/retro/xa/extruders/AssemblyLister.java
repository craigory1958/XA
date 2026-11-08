

package xcom.retro.xa.extruders ;


import static xcom.retro.xa.Identifier.IdentifierTypes.* ;

import java.io.PrintWriter ;
import java.math.BigInteger ;
import java.util.Comparator ;
import java.util.List ;
import java.util.Map ;
import java.util.Optional ;
import java.util.function.Function ;
import java.util.stream.Collectors ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.Statement ;
import xcom.retro.xa.api.annotations.aExtruder ;
import xcom.retro.xa.api.interfaces.iExtruder ;
import xcom.utils4j.format.Strings ;


@aExtruder(format = "Listing")
public class AssemblyLister implements iExtruder {

	static final String FileNames = "   A B C D E F G H I J K L M O N P Q R S T U V W X Y ZAAABACADAEAFAGAHAIAJAKALAMANAOAPAQARASATAUAVAWAXAYAZ" ;


	@Override
	public void extrude(final PrintWriter out, final AssemblyContext actx) {

		final Map<Integer, Integer> maxSrcLNColWidths = calcMaxSourceLineCounts(actx.statements()) ;

		final int maxFNColWidth = calcNeededMaxColWidth(actx.sources().size()) ;
		final int maxLNColWidth = calcNeededMaxColWidth(actx.sn()) ;
		final int maxSrcLNColWidth = calcNeededMaxColWidth(maxSrcLNColWidths.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue()) ;


		for ( final Statement _statement : actx.statements() )

			if ( _statement.list() ) {

				// Format source file information ...

				if ( _statement.fn() > 0 ) {

					final String fn = FileNames.substring(_statement.fn() * 2, (_statement.fn() * 2) + 2).trim() ;
					out.print(String.format("%-" + maxFNColWidth + "S", fn)) ;

					final int lnSrcColWidth = calcNeededMaxColWidth(maxSrcLNColWidths.get(_statement.fn())) ;
					out.print(String.format(".%0" + lnSrcColWidth + "d", _statement.ln()) + Strings.fillString(maxSrcLNColWidth - lnSrcColWidth)) ;
				}
				else
					out.print(Strings.fillString(maxFNColWidth + maxSrcLNColWidth + 1)) ;


				// Format statement number ...

				if ( _statement.sn() > 0 )

					out.print(String.format("  %" + maxLNColWidth + "d:", _statement.sn())) ;
				else
					out.print(Strings.fillString(maxLNColWidth + 3)) ;


				// Format location/value ...

//				if ( (_statement.pctx() != null) && (_statement.pctx().getChild(1) != null) && (_statement.pctx().getChild(1).getChild(0) != null)
//						&& (_statement.pctx().getChild(1).getChild(0).getChild(1) != null)
//						&& _statement.pctx().getChild(1).getChild(0).getChild(1).getText().equalsIgnoreCase("EQU") && (_statement.label() != null) )

				if ( (_statement.label() != null) && (_statement.label().type() == Value) )

					out.print(String.format(" (%04X)",
							new BigInteger(actx.identifiers().get(_statement.label().scopedMoniker().toLowerCase()).value()).intValue())) ;

				else if ( ((_statement.bytes() != null) && (_statement.bytes().length > 0)) //
						|| ((_statement.label() != null) && (_statement.label().type() == Label)) )

					out.print(String.format("  %04X ", _statement.loc())) ;

				else
					out.print(Strings.fillString(7)) ;


				// Format byte data ...
//				if ( (_statement.bytes() != null) )
//					System.err.println(Arrays.toString( _statement.bytes())) ;
//				if ( (_statement.bytes() != null) )
//					System.err.println(Arrays.toString( _statement.block().bytes())) ;

				for ( int b = 0; b < 4; b++ )

					if ( (_statement.bytes() != null) && (b < _statement.bytes().length) )
						out.print(String.format("%02X ", _statement.bytes()[b])) ;
					else
						out.print(Strings.fillString(3)) ;


				// Extrude statement listing ...

				out.print(" " + _statement.line()) ;


				//

				out.println() ;


				// Format extended and extrude byte data ...

				if ( (_statement.bytes() != null) && (_statement.bytes().length > 4) ) {

					for ( int b = 4; b < _statement.bytes().length; b++ ) {

						if ( ((b % 4) == 0) && (b > 4) )
							out.println() ;

						if ( (b % 4) == 0 )
							out.print(String.format(Strings.fillString(2 * maxLNColWidth + 7) + "%04X", _statement.loc() + b)) ;

						out.print(String.format(" %02X", _statement.bytes()[b])) ;
					}

					out.println() ;
				}
			}
	}


	int calcNeededMaxColWidth(int integer) {
		return (int) Math.floor(Math.log10(integer)) + 1 ;
	}


	static Map<Integer, Integer> calcMaxSourceLineCounts(final List<Statement> statements) {

		return statements.stream() //
				.collect(Collectors.groupingBy( //
						Statement::fn, //
						Collectors.collectingAndThen( //
								Collectors.maxBy(Comparator.comparingInt(Statement::ln)),  //
								new Function<Optional<Statement>, Integer>() {
									@Override
									public Integer apply(final Optional<Statement> opt) {
										return opt.map(Statement::ln).orElse(0) ;
									}
								}) //
				) //
				) ;
	}
}
