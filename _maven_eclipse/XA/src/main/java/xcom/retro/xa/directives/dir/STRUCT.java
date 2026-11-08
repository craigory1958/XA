

package xcom.retro.xa.directives.dir ;


import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_formatQualifiedID ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_parsedText ;
import static xcom.retro.xa.directives.DirectiveUtils.DIRS_isList ;
import static xcom.retro.xa.directives.DirectiveUtils.DIRS_matchInvocationArguments ;
import static xcom.utils4j.format.Templator.UnixDelimiters ;

import java.util.ArrayList ;
import java.util.List ;
import java.util.Map ;

import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.BlockSource ;
import xcom.retro.xa.Identifier ;
import xcom.retro.xa.Operand ;
import xcom.retro.xa.Option ;
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

	int fn ;

	int ln ;

	//@formatter:on


	//
	//
	//

	public STRUCT(final AssemblyContext actx) {
		this.actx = actx ;
	}


	public STRUCT(final AssemblyContext actx, final String name, final Map<String, Operand> Option, final List<String> lines) {

		this.actx = actx ;
		this.name = name ;
		options = Option ;
		this.lines = lines ;

		fn = actx.sources().size() - 1 ;
		ln = actx.source().peek().ln() - 1 ;
		qualifier = actx.source().peek().qualifier() ;
	}


	//
	//
	//

	@Log
	public void expand(final ParserRuleContext pctx) {

		final String x = pctx.getParent().getChild(0).getText() ;

		final Map<String, Identifier> _ids = actx.identifiers() ;
		final Map<String, Operand> _options = options ;
		final Map<String, Operand> _operands = actx.statement().operands() ;

		final Map<String, Object> parms = DIRS_matchInvocationArguments(actx, _options, _operands, _ids) ;

		final List<String> lines = new ArrayList<>() ;
		for ( final String line : this.lines )
			lines.add(Templator.delimiters(UnixDelimiters).template(line).inject(parms)) ;

		actx.list(DIRS_isList(parms)) ;

		actx.sources().add(new BlockSource(fn, ln, lines, actx.list(), actx.segment(), (qualifier != null ? qualifier + ":" + x : x))) ;
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

		final iSource _src = actx.source().peek() ;
		final Map<String, Operand> _operands = actx.statement().operands() ;
		final String _structName = ASMB_parsedText(pctx.getChild(0).getChild(0)) ;

		_operands.put("list", new Option("list").assignment(new StringLiteral(actx, ".nolist"))) ;
		final String _qualifiedStructName = ASMB_formatQualifiedID(_structName, _src.qualifier()) ;

		final List<String> lines = AssemblyUtils.ASMB_readSourceBlock(_src, ENDSTRUCT.class, actx) ;


		return new STRUCT(actx, _qualifiedStructName, _operands, lines) ;
	}
}
