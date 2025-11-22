

package xcom.retro.xa.Parsing ;


import static org.junit.Assert.assertArrayEquals ;

import java.io.IOException ;
import java.util.ArrayList ;
import java.util.Arrays ;
import java.util.Collection ;
import java.util.List ;

import org.junit.BeforeClass ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.junit.runners.Parameterized ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

import xcom.retro.xa.ParsingScaffold ;


@RunWith(Parameterized.class)
public class _Test_DirectiveParsing {

	@Parameterized.Parameters()
	public static Collection<Object[]> data() {

		//@formatter:off

        final Object[][] results = {

                // { String src, String[] expectedContexts }

                { "    .byte abc",  	"Statement, Directive, Assembler, ParameterList, Parameter, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
                { "abc .byte abc",  	"Statement, Label, Directive, Assembler, ParameterList, Parameter, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
                { ".abc .macro abc",  	"Statement, Directive, Macro, Symbol, OptionList, Option, Identifier" },
                { "abc .abc abc",  		"Statement, Label, Directive, Invocation, QualifiedSymbol, Symbol, ParameterList, Parameter, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
                { "abc .abc abc=def", 	"Statement, Label, Directive, Invocation, QualifiedSymbol, Symbol, ParameterList, Parameter, Identifier, Assignment, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
        } ;

        //@formatter:on

		return (Arrays.asList(results)) ;
	}


	private static final Logger Logger = LoggerFactory.getLogger(_Test_DirectiveParsing.class) ;

	static ParsingScaffold parser ;

	String src ;
	List<String> expectedContexts ;


	public _Test_DirectiveParsing(final String src, final String expectedContexts) {

		this.src = src ;

		this.expectedContexts = new ArrayList<String>(Arrays.asList(expectedContexts.split(", "))) ;
	}


	@BeforeClass
	public static void setupBeforeClass() throws Exception {

		parser = new ParsingScaffold() ;
		parser.init("MOS6502") ;
	}


	@Test
	public void parseTest() throws IOException {

		final List<String> actual = parser.parse("statement", src).processor().walked() ;

		Logger.info("{}", String.format("%-12s  %s", src, actual)) ;

		assertArrayEquals(expectedContexts.toArray(), actual.toArray()) ;
	}
}
