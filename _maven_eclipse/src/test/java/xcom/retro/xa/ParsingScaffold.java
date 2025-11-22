

package xcom.retro.xa ;


import java.io.IOException ;
import java.util.ArrayList ;

import org.antlr.v4.runtime.CharStream ;
import org.antlr.v4.runtime.CharStreams ;
import org.antlr.v4.runtime.CommonTokenStream ;
import org.antlr.v4.runtime.Lexer ;
import org.antlr.v4.runtime.Parser ;
import org.antlr.v4.runtime.ParserRuleContext ;
import org.antlr.v4.runtime.TokenStream ;
import org.antlr.v4.runtime.tree.ParseTreeWalker ;
import org.fest.reflect.core.Reflection ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

import xcom.utils4j.logging.aspects.api.annotations.Log ;


public class ParsingScaffold {

	private static final Logger Logger = LoggerFactory.getLogger(ParsingScaffold.class) ;


	Lexer lexer ;
	Parser parser ;

	ParsingListener processor ;

	public ParsingListener processor() {
		return processor ;
	}

	ParseTreeWalker walker ;


	@Log
	public ParsingScaffold init(final String processor) throws Exception {

		Logger.info("uP grammer: {}", processor) ;

		final String lexerClassname = "xcom.retro.xa.antlr.processors." + processor + '.' + processor + "_Lexer" ;
		final Class<? extends Lexer> lexerClass = Reflection.type(lexerClassname).loadAs(Lexer.class) ;
		lexer = Reflection.constructor().withParameterTypes(CharStream.class).in(lexerClass).newInstance(CharStreams.fromString("")) ;

		final String parserClassname = "xcom.retro.xa.antlr.processors." + processor + '.' + processor + "_Parser" ;
		final Class<? extends Parser> parserClass = Reflection.type(parserClassname).loadAs(Parser.class) ;
		parser = Reflection.constructor().withParameterTypes(TokenStream.class).in(parserClass).newInstance(new CommonTokenStream(lexer)) ;

		this.processor = new ParsingListener() ;
		walker = new ParseTreeWalker() ;

		return this ;
	}


	@Log
	public ParsingScaffold parse(final String rule, final String src) throws IOException {

		processor.walked = new ArrayList<>() ;

		lexer.setInputStream(CharStreams.fromString(src)) ;
		parser.setTokenStream(new CommonTokenStream(lexer)) ;

		final ParserRuleContext prc = Reflection.method(rule).withReturnType(ParserRuleContext.class).in(parser).invoke() ;
		walker.walk(processor, prc) ;

		return this ;
	}


	@Log
	public static void main(final String[] args) throws IOException, Exception {

		final ParsingScaffold $ = new ParsingScaffold() ;
		$.init("MOS6502") ;

		String src ;

		System.out.println((src = "xyz ASL A") + " - " + $.parse("statement", src).processor.walked) ;
		System.out.println((src = "1 + xyz") + " - " + $.parse("expr", src).processor.walked) ;
	}
}
