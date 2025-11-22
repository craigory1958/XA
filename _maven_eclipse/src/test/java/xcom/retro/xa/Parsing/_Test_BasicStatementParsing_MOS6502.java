

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
public class _Test_BasicStatementParsing_MOS6502 {

	@Parameterized.Parameters()
	public static Collection<Object[]> data() {

		//@formatter:off

        final Object[][] results = {

                // { String src, String[] expectedContexts }

                { "     NOP",  		"Statement, Instruction, Opcode, AddressingMode, Implied" },
                { "     LDA #imm",  "Statement, Instruction, Opcode, AddressingMode, Immediate, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
                { "abc  LDA #imm", 	"Statement, Label, Instruction, Opcode, AddressingMode, Immediate, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
                { "abc  NOP",  		"Statement, Label, Instruction, Opcode, AddressingMode, Implied" },

                { "",  							"Statement" },
                { "; w/comment",  				"Statement" },
                { "     NOP ; w/comment",  		"Statement, Instruction, Opcode, AddressingMode, Implied" },
                { "     LDA #imm ; w/comment",	"Statement, Instruction, Opcode, AddressingMode, Immediate, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
                { "abc  LDA #imm ; w/comment",  "Statement, Label, Instruction, Opcode, AddressingMode, Immediate, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
                { "abc  NOP ; w/comment",  		"Statement, Label, Instruction, Opcode, AddressingMode, Implied" },
        } ;

        //@formatter:on

		return (Arrays.asList(results)) ;
	}


	private static final Logger Logger = LoggerFactory.getLogger(_Test_BasicStatementParsing_MOS6502.class) ;

	static ParsingScaffold parser ;

	String src ;
	List<String> expectedContexts ;


	public _Test_BasicStatementParsing_MOS6502(final String src, final String expectedContexts) {

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
