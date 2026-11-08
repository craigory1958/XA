

package xcom.retro.xa.extruders ;


import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_asInteger ;
import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_formatAsHexLiterial ;

import java.io.PrintWriter ;
import java.util.function.Function ;

import org.apache.commons.lang3.StringUtils ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.Identifier ;
import xcom.retro.xa.Identifier.Reference ;
import xcom.retro.xa.api.annotations.aExtruder ;
import xcom.retro.xa.api.interfaces.iExtruder ;
import xcom.utils4j.data.structured.map.Maps ;


@aExtruder(format = "XRef")
public class XRefLister implements iExtruder {

	@Override
	public void extrude(final PrintWriter out, final AssemblyContext actx) {

		final int monikerColWidth ;

		if ( actx.identifiers().size() > 0 )
			monikerColWidth = actx.identifiers().values().stream().map(new Function<Identifier, Integer>() {
				@Override
				public Integer apply(final Identifier s) {
					return s.scopedMoniker().length() + s.moniker().length() ;
				}
			}).max(Integer::compareTo).get() ;
		else
			monikerColWidth = 0 ;

		final int lnColWidth = (int) Math.floor(Math.log10(actx.sn())) + 1 ;
		final int srcColWidth = (int) Math.floor(Math.log10(actx.sources().size())) + 1 ;
//		int lnSrcColWidth = 0 ;


		for ( final Identifier _id : Maps.sortByKey(actx.identifiers()).values() )

			if ( (_id.scopedMoniker() != null) && (_id.value() != null) ) {

				String moniker = _id.scopedMoniker() ;
				if ( !StringUtils.trimToEmpty(_id.moniker()).equals(_id.scopedMoniker()) )
					moniker += " (" + _id.moniker() + ")" ;

				out.print(String.format("  %-" + (monikerColWidth + 3) + "s", moniker)) ;

				out.print(String.format("  %8s", EXPR_formatAsHexLiterial(EXPR_asInteger(_id.value())))) ;

				out.print(String.format("  %-15s", formatReference(_id.origin(), lnColWidth, srcColWidth))) ;

				for ( final Reference ref : _id.references() )
					out.print(String.format("  %-15s", formatReference(ref, lnColWidth, srcColWidth))) ;

				out.println() ;
			}
	}

	static String formatReference(final Reference ref, final int lnColWidth, final int srcColWidth) {

		if ( ref == null )
			return "" ;

		String _ref = String.format("%" + lnColWidth + "d", ref.ln()) ;

		if ( ref.fn() > 0 )
			_ref += " (" + "   A B C D E F G H I J K L M O N P Q R S T U V W X Y ZAAABACADAEAFAGAHAIAJAKALAMANAOAPAQARASATAUAVAWAXAYAZ"
					.substring(ref.fn() * 2, (ref.fn() * 2) + 2).strip() + '.' + ref.ln() + ")" ;

		return _ref ;
	}
}
