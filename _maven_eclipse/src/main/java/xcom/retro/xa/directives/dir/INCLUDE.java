

package xcom.retro.xa.directives.dir ;


import static xcom.retro.xa.directives.DirectiveUtils.DIR_isList ;

import java.io.FileNotFoundException ;
import java.nio.charset.StandardCharsets ;
import java.util.Map ;
import java.util.function.Consumer ;

import org.antlr.v4.runtime.ParserRuleContext ;
import org.apache.commons.io.FilenameUtils ;

import xcom.retro.xa.FileSource ;
import xcom.retro.xa.Operand ;
import xcom.retro.xa.Moniker ;
import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.api.annotations.aDirective ;
import xcom.retro.xa.api.interfaces.iDirective ;
import xcom.retro.xa.expressions.value._ValueNode ;
import xcom.utils4j.data.structured.list.Lists ;
import xcom.utils4j.data.structured.map.Maps ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@aDirective
public class INCLUDE implements iDirective {

	//@formatter:off

	AssemblyContext actx ;

	//@formatter:on


	//
	//
	//

	public INCLUDE(final AssemblyContext actx) {
		this.actx = actx ;
	}


	//
	//
	//

	@Log
	@Override
	public void parse(final ParserRuleContext pctx) {

		actx.statement().operands().values().forEach(new Consumer<Operand>() {
			@Override
			public void accept(final Operand o) { //
//				System.err.print("operand (before) >>> " + o.moniker() + ": " + o.getClass().getSimpleName()) ; //
//				if ( o.assignment() != null )
//					System.err.print(" - " + o.assignment().getClass().getSimpleName() + ": " + o.assignment()) ; //
//				System.err.println() ; //
			}
		}) ;

		final Map<String, Moniker> _identifiers = actx.symbols() ;
		final Map<String, Operand> _operands = actx.statement().operands() ;
		final Operand _operand1 = Maps.firstEntryValue(_operands) ;
		final Operand _operandAs = _operands.get("as") ;
		final Operand _operandList = _operands.get("list") ;

		final String fSpec = FilenameUtils.getFullPath(actx.decodedArgs().get("source")) + _operand1.assignment().eval(_identifiers).getValue() ;

		actx.list(DIR_isList(_operandList, _identifiers)) ;

		final String qualifier = (_operandAs != null ? new String(((_ValueNode) _operandAs.assignment()).value(), StandardCharsets.UTF_8) : null) ;

		try {
			actx.sources().add(new FileSource(actx.sources().size(), fSpec, actx.list(), qualifier)) ;
			actx.source().push(Lists.last(actx.sources())) ;
		}
		catch ( final FileNotFoundException e ) {}
	}
}
