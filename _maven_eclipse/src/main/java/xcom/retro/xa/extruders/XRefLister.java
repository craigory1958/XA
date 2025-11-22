

package xcom.retro.xa.extruders ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asInteger ;
import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_formatAsHexLiterial ;

import java.io.PrintWriter ;
import java.util.function.Function ;

import org.apache.commons.lang3.StringUtils ;

import xcom.retro.xa.Moniker ;
import xcom.retro.xa.Moniker.Reference ;
import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.api.annotations.aExtruder ;
import xcom.retro.xa.api.interfaces.iExtruder ;
import xcom.utils4j.data.structured.map.Maps ;


@aExtruder(format = "XRef")
public class XRefLister implements iExtruder {

	@Override
	public void extrude(final PrintWriter out, final AssemblyContext actx) {

//		System.err.println(actx.symbols().keySet()) ;

		final int monikerColWidth = actx.symbols().values().stream().map(new Function<Moniker, Integer>() {
			@Override
			public Integer apply(Moniker s) {
				return s.scopedMoniker().length() + s.moniker().length() ;
			}
		}).max(Integer::compareTo).get() ;

		final int lnColWidth = (int) Math.floor(Math.log10(actx.ln())) + 1 ;
		final int srcColWidth = (int) Math.floor(Math.log10(actx.sources().size())) + 1 ;
//		int lnSrcColWidth = 0 ;


		for ( final Moniker symbol : Maps.sortByKey(actx.symbols()).values() )

			if ( (symbol.scopedMoniker() != null) && (symbol.value() != null) ) {

				String moniker = symbol.scopedMoniker() ;
				if ( !StringUtils.trimToEmpty(symbol.moniker()).equals(symbol.scopedMoniker()) )
					moniker += " (" + symbol.moniker() + ")" ;

				out.print(String.format("  %-" + (monikerColWidth + 3) + "s", moniker)) ;

				out.print(String.format("  %8s", EXPR_formatAsHexLiterial(EXPR_asInteger(symbol.value())))) ;

				out.print(String.format("  %-15s", formatReference(symbol.origin(), lnColWidth, srcColWidth))) ;

				for ( final Reference ref : symbol.references() )
					out.print(String.format("  %-15s", formatReference(ref,lnColWidth,srcColWidth))) ;

				out.println() ;
			}
	}

	static String formatReference(final Reference ref, int lnColWidth, int srcColWidth) {

		if ( ref == null )
			return "" ;

		String _ref = String.format("%" + lnColWidth + "d", ref.ln()) ;

		if ( ref.sourceID() > 0 )
			_ref += " (" + "   A B C D E F G H I J K L M O N P Q R S T U V W X Y ZAAABACADAEAFAGAHAIAJAKALAMANAOAPAQARASATAUAVAWAXAYAZ"
					.substring(ref.sourceID() * 2, (ref.sourceID() * 2) + 2).strip() + '.' + ref.sourceLN() + ")" ;

		return _ref ;
	}
}
