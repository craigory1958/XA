

package xcom.retro.xa.assembly ;


import static xcom.retro.xa.XA.logEvent ;

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
import org.fest.reflect.exception.ReflectionError ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.Operand ;
import xcom.retro.xa.Statement ;
import xcom.retro.xa.antlr.AssemblyParser.AssemblerContext ;
import xcom.retro.xa.antlr.AssemblyParser.DirectiveContext ;
import xcom.retro.xa.api.interfaces.iSource ;
import xcom.retro.xa.expressions._ExprNode ;
import xcom.utils4j.data.structured.list.Lists ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
public class AssemblyUtils {

	static final String ASSEMBLER = ASMB_trimToContextName(AssemblerContext.class) ;
	static final String DIRECTIVE = ASMB_trimToContextName(DirectiveContext.class) ;

	//
	//
	//

	public static void ASMB_invokeMethodFromContext(final AssemblyContext actx, final String event, final Object target, final String prefix,
			final ParserRuleContext pctx) {

		final String _methodContext = ASMB_parsedContextName(pctx) ;
		final String method = prefix + _methodContext ;

		try {
			target.getClass().getMethod(method, ParserRuleContext.class) ;

			logEvent(actx.$args(), actx.phase(), event, "({}) {}: entering {}({}) in {}", event, target.getClass().getSimpleName(), method,
					pctx.getClass().getSimpleName(), target.getClass().getSimpleName()) ;

			Reflection //
					.method(method) //
					.withParameterTypes(ParserRuleContext.class) //
					.in(target) //
					.invoke(pctx) ;
		}
		catch ( final NoSuchMethodException ex ) {}
		catch ( final ReflectionError ex ) {
			System.err.println("Impossible error 52: Error walking tree: Method " + method + " not found.") ;
		}
	}


	public static ParserRuleContext ASMB_parseStatement(final String line, final Lexer lexer, final Parser parser) {

		lexer.setInputStream(CharStreams.fromString(line)) ;
		parser.setTokenStream(new CommonTokenStream(lexer)) ;

		return Reflection.method("statement").withReturnType(ParserRuleContext.class).in(parser).invoke() ;
	}


	public static List<String> ASMB_readSource(final iSource source, final AssemblyContext actx) {
		return ASMB_readSourceBlock(source, null, actx) ;
	}


	public static List<String> ASMB_readSourceBlock(final iSource source, final Class<?> eobToken, final AssemblyContext actx) {

		final List<String> lines = new ArrayList<>() ;

		try {
			String line ;
			while ( ((line = source.next()) != null)
					&& ((eobToken == null) || !AssemblyUtils.ASMB_isEndOfBlock(line, eobToken.getSimpleName(), actx.lexer(), actx.parser())) ) {

				lines.add(line) ;
				actx.statements().add(new Statement(source.fn(), source.ln(), line, 0, actx.list(), actx.assemblyEnable())) ;

				logEvent(actx.$args(), actx.phase(), "src", "+++{}", line) ;
				logEvent(actx.$args(), actx.phase(), "srcs", "(srcs) {}: sources: {}", AssemblyUtils.class, actx.sources()) ;
				logEvent(actx.$args(), actx.phase(), "srcs", "(srcs) {}: source: {}", AssemblyUtils.class, actx.source()) ;
				logEvent(actx.$args(), actx.phase(), "srcs", "(srcs} {}: statement: {}", AssemblyUtils.class, actx.statement()) ;
			}

			actx.statements().add(new Statement(source.fn(), source.ln(), line, 0, actx.list(), actx.assemblyEnable())) ;
			actx.statement(Lists.last(actx.statements())) ;

			logEvent(actx.$args(), actx.phase(), "src", "+++{}", line) ;
			logEvent(actx.$args(), actx.phase(), "srcs", "(srcs) {}: sources: {}", AssemblyUtils.class, actx.sources()) ;
			logEvent(actx.$args(), actx.phase(), "srcs", "(srcs) {}: source: {}", AssemblyUtils.class, actx.source()) ;
			logEvent(actx.$args(), actx.phase(), "srcs", "(srcs} {}: statement: {}", AssemblyUtils.class, actx.statement()) ;
		}
		catch ( final IOException e ) {}


		return lines ;
	}


	public static boolean ASMB_isEndOfBlock(final String line, final String eobToken, final Lexer lexer, final Parser parser) {

		final ParserRuleContext pctx = ASMB_parseStatement(line, lexer, parser) ;

		if ( (pctx.getChildCount() == 0) || !ASMB_parsedContextName(pctx.getChild(pctx.getChildCount() - 1)).equalsIgnoreCase(DIRECTIVE)
				|| !ASMB_parsedContextName(pctx.getChild(pctx.getChildCount() - 1).getChild(0)).equalsIgnoreCase(ASSEMBLER) )
			return false ;

		return ASMB_parsedText(pctx.getChild(pctx.getChildCount() - 1).getChild(0).getChild(1)).toLowerCase().equalsIgnoreCase(eobToken) ;
	}


	public static String ASMB_formatQualifiedID(final String id, final String qualifier) {
		return ((qualifier != null) && !id.toLowerCase().startsWith(qualifier.toLowerCase()) ? qualifier + ":" + id : id) ;
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


	public static String ASMB_exprToPostfix(final _ExprNode expr) {
		return expr.toPostfix(new StringBuilder()) ;
	}
}
