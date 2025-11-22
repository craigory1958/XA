

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
public class _Test_NamedBlockParsing {

	@Parameterized.Parameters()
	public static Collection<Object[]> data() {

		//@formatter:off

        final Object[][] results = {

                // { String src, String[] expectedContexts }

                { "    .xyz:.def:.abc",                "Statement, Directive, Invocation, QualifiedSymbol, Symbol, Symbol, Symbol" },
                { "    .xyz:.def:.abc .xyz:.def:abc",  "Statement, Directive, Invocation, QualifiedSymbol, Symbol, Symbol, Symbol, ParameterList, Parameter, Argument, Expr, Term, QualifiedIdentifier, DottedIdentifier, Identifier, DottedIdentifier, Identifier, Identifier" },
                { "    JMP .xyz:.def:abc",             "Statement, Instruction, Opcode, AddressingMode, Absolute, Argument, Expr, Term, QualifiedIdentifier, DottedIdentifier, Identifier, DottedIdentifier, Identifier, Identifier" },
        } ;

        //@formatter:on

		return (Arrays.asList(results)) ;
	}


	private static final Logger Logger = LoggerFactory.getLogger(_Test_NamedBlockParsing.class) ;

	static ParsingScaffold parser ;

	String src ;
	List<String> expectedContexts ;


	public _Test_NamedBlockParsing(final String src, final String expectedContexts) {

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
