

package xcom.retro.xa.assembly ;


import java.io.IOException ;
import java.util.ArrayList ;
import java.util.List ;
import java.util.Map ;

import org.antlr.v4.runtime.CharStreams ;
import org.antlr.v4.runtime.CommonTokenStream ;
import org.antlr.v4.runtime.Lexer ;
import org.antlr.v4.runtime.Parser ;
import org.antlr.v4.runtime.ParserRuleContext ;
import org.antlr.v4.runtime.tree.ParseTree ;
import org.fest.reflect.core.Reflection ;

import xcom.retro.xa.Operand ;
import xcom.retro.xa.Statement ;
import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.antlr.AssemblyParser.AssemblerContext ;
import xcom.retro.xa.antlr.AssemblyParser.DirectiveContext ;
import xcom.retro.xa.api.interfaces.iSource ;
import xcom.utils4j.data.structured.list.Lists ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


public class AssemblyUtils {

	final static String ASSEMBLER = ASMB_trimToContextName(AssemblerContext.class) ;
	final static String DIRECTIVE = ASMB_trimToContextName(DirectiveContext.class) ;

	//
	//
	//

	@Log
	public static ParserRuleContext ASMB_parseStatement(final String line, final Lexer lexer, final Parser parser) {

		lexer.setInputStream(CharStreams.fromString(line)) ;
		parser.setTokenStream(new CommonTokenStream(lexer)) ;

		return Reflection.method("statement").withReturnType(ParserRuleContext.class).in(parser).invoke() ;
	}


	@Log
	public static boolean ASMB_isEndOfBlock(final String line, final Class<?> eobToken, final Lexer lexer, final Parser parser) {

		final ParserRuleContext pctx = ASMB_parseStatement(line, lexer, parser) ;
//		System.err.println(">>>"+line) ;

//		System.err.println("class: "+ pctx.getClass()) ;
//		System.err.println("getChildCount: "+ pctx.getChildCount()) ;


//		System.err.println("right: "+ EXPR_parsedContextName(pctx.getChild(pctx.getChildCount() - 1))) ;


//		System.err.println("child: "+ EXPR_parsedContextName(pctx.getChild(pctx.getChildCount() - 1).getChild(0))) ;

		if ( (pctx.getChildCount() == 0) || !ASMB_parsedContextName(pctx.getChild(pctx.getChildCount() - 1)).equalsIgnoreCase(DIRECTIVE)
				|| !ASMB_parsedContextName(pctx.getChild(pctx.getChildCount() - 1).getChild(0)).equalsIgnoreCase(ASSEMBLER) )
			return false ;
//		System.err.println("text: "+EXPR_parsedText(pctx.getChild(pctx.getChildCount() - 1).getChild(0).getChild(1))) ;

		return ASMB_parsedText(pctx.getChild(pctx.getChildCount() - 1).getChild(0).getChild(1)).toLowerCase().equalsIgnoreCase(eobToken.getSimpleName()) ;
	}

	@Log
	public static List<String> ASMB_readSourceBlock(final iSource source, final Class<?> eobToken, final AssemblyContext actx) {

		final List<String> lines = new ArrayList<>() ;

		try {
			String line ;
			while ( ((line = source.next()) != null) && !AssemblyUtils.ASMB_isEndOfBlock(line, eobToken, actx.lexer(), actx.parser()) ) {
				lines.add(line) ;
				actx.statements().add(new Statement(source.sourceID(), source.sourceLN(), line, 0, actx.list(), actx.assembleEnable())) ;
				actx.statement(Lists.last(actx.statements())) ;
			}

			actx.statements().add(new Statement(source.sourceID(), source.sourceLN(), line, 0, actx.list(), actx.assembleEnable())) ;
			actx.statement(Lists.last(actx.statements())) ;
		}
		catch ( final IOException e ) {}


		return lines ;
	}


	public static String ASMB_formatQualifiedID(final String id, final String qualifier) {
		return (qualifier != null ? qualifier + ":" + id : id) ;
	}

	public static String ASMB_formatGeneratedID(final Map<String, Operand> operands) {
		return "{generated}." + Integer.valueOf(operands.size() + 1).toString() ;
	}

	public static String ASMB_formatScopedID(final String id, final iSource src) {
		return id + "_" + src.scopeID() ;
	}


	public static String ASMB_parsedContextName(final ParserRuleContext pctx) {
		return ASMB_trimContextName(pctx.getClass().getSimpleName()) ;
	}

	public static String ASMB_parsedContextName(final ParseTree pt) {
		return ASMB_trimContextName(pt.getClass().getSimpleName()) ;
	}

	public static String ASMB_trimToContextName(final Class<?> clazz) {
		return ASMB_trimContextName(clazz.getSimpleName()) ;
	}

	public static String ASMB_trimContextName(final String context) {
		return context.substring(0, context.length() - 7) ;
	}


	public static String ASMB_parsedText(final ParseTree pt) {
		return pt.getText() ;
	}

	public static String ASMB_dumpParseTreeNode(final ParseTree pt) {
		return "parseTreeNode: @" + ASMB_trimToContextName(pt.getClass()) + " - " + pt.getText() ;
	}
}
