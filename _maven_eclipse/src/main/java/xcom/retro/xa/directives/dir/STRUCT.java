

package xcom.retro.xa.directives.dir ;


import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_formatQualifiedID ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_parsedText ;
import static xcom.retro.xa.directives.DirectiveUtils.DIR_isList ;
import static xcom.retro.xa.directives.DirectiveUtils.DIR_matchInvocationOperands ;
import static xcom.utils4j.format.Templator.UnixDelimiters ;

import java.util.ArrayList ;
import java.util.List ;
import java.util.Map ;

import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.retro.xa.BlockSource ;
import xcom.retro.xa.Operand ;
import xcom.retro.xa.Option ;
import xcom.retro.xa.Moniker ;
import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.api.annotations.aDirective ;
import xcom.retro.xa.api.interfaces.iDirective ;
import xcom.retro.xa.api.interfaces.iSource ;
import xcom.retro.xa.assembly.AssemblyUtils ;
import xcom.retro.xa.expressions.value.StringLiteral ;
import xcom.utils4j.data.structured.list.Lists ;
import xcom.utils4j.format.Templator ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@aDirective
public class STRUCT implements iDirective {

	//@formatter:off

	AssemblyContext actx ;

	String qualifier ;

	String name ;
	public String name() { return name ; }

	Map<String, Operand> options ;
	public Map<String, Operand> options() { return options ; }

	List<String> lines ;

	int sourceID ;

	int sourceLN ;

	//@formatter:on


	//
	//
	//

	public STRUCT(final AssemblyContext actx) {
		this.actx = actx ;
	}


	public STRUCT(final AssemblyContext actx, final String name, final Map<String, Operand> optioms, final List<String> lines) {

		this.actx = actx ;
		this.name = name ;
		options = optioms ;
		this.lines = lines ;

		sourceID = actx.sources().size() - 1 ;
		sourceLN = actx.source().peek().sourceLN() - 1 ;
		qualifier = actx.source().peek().qualifier() ;
	}


	//
	//
	//

	@Log
	public void expand(final ParserRuleContext pctx) {

		final String x = pctx.getParent().getChild(0).getText() ;

		final Map<String, Moniker> _identifiers = actx.symbols() ;
		final Map<String, Operand> _operands = actx.statement().operands() ;
		final Map<String, Operand> _options = options ;

		final Map<String, Object> parms = DIR_matchInvocationOperands(_operands, _options, _identifiers) ;

		final List<String> lines = new ArrayList<>() ;
		for ( final String line : this.lines )
			lines.add(Templator.delimiters(UnixDelimiters).template(line).inject(parms)) ;

		actx.list(DIR_isList(parms)) ;

		actx.sources().add(new BlockSource(sourceID, sourceLN, lines, actx.list(), (qualifier != null ? qualifier + ":" + x : x))) ;
		actx.source().push(Lists.last(actx.sources())) ;
	}


	@Log
	@Override
	public void parse(final ParserRuleContext pctx) {

		final STRUCT struct = buildStruct(actx, pctx) ;
		actx.structs().put(struct.name().toLowerCase(), struct) ;
	}


	@Log
	public static STRUCT buildStruct(final AssemblyContext actx, final ParserRuleContext pctx) {

		final iSource _source = actx.source().peek() ;
		final Map<String, Operand> _operands = actx.statement().operands() ;
		final String _structName = ASMB_parsedText(pctx.getChild(0).getChild(0)) ;

		_operands.put("list", new Option("list").assignment(new StringLiteral(".nolist"))) ;
		final String _qualifiedStructName = ASMB_formatQualifiedID(_structName, _source.qualifier()) ;

		final List<String> lines = AssemblyUtils.ASMB_readSourceBlock(_source, ENDSTRUCT.class, actx) ;


		return new STRUCT(actx, _qualifiedStructName, _operands, lines) ;
	}
}
