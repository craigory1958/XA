

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
public class _Test_AddressingModes_MOS6502 {

	@Parameterized.Parameters()
	public static Collection<Object[]> data() {

		//@formatter:off

        final Object[][] results = {

                // { String mode,  String src, String[] expectedContexts }

                { "Imm", 		" ADC #abc",    	"Statement, Instruction, Opcode, AddressingMode, Immediate, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
                { "Abs", 		" ADC abs",     	"Statement, Instruction, Opcode, AddressingMode, Absolute, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
                { "ZP", 		" ADC zp",      	"Statement, Instruction, Opcode, AddressingMode, Absolute, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
                { "Acc", 		" ASL A",       	"Statement, Instruction, Opcode, AddressingMode, Accumulator" },
                { "Imp", 		" BRK",         	"Statement, Instruction, Opcode, AddressingMode, Implied" },
                { "[Ind,X]", 	" ADC [zp,X]",		"Statement, Instruction, Opcode, AddressingMode, ZpIndirectX, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
                { "[Ind],Y", 	" ADC [zp],Y", 		"Statement, Instruction, Opcode, AddressingMode, ZpIndirectY, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
                { "Abs,X", 		" ADC abs,X",   	"Statement, Instruction, Opcode, AddressingMode, IndexedX, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
                { "Abs,Y", 		" ADC abs,Y",   	"Statement, Instruction, Opcode, AddressingMode, IndexedY, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
                { "ZP,X", 		" ADC zp,X",   		"Statement, Instruction, Opcode, AddressingMode, IndexedX, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
                { "ZP,Y", 		" LDX zp,Y",   	    "Statement, Instruction, Opcode, AddressingMode, IndexedY, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
                { "Rel", 		" BCC rel",     	"Statement, Instruction, BranchOpcode, BranchAddressingMode, Relative, Argument, Expr, Term, QualifiedIdentifier, Identifier" },
           } ;

        //@formatter:on

		return (Arrays.asList(results)) ;
	}


	private static final Logger Logger = LoggerFactory.getLogger(_Test_AddressingModes_MOS6502.class) ;

	static ParsingScaffold parser ;

	String mode ;
	String src ;
	List<String> expectedContexts ;


	public _Test_AddressingModes_MOS6502(final String mode, final String src, final String expectedContexts) {

		this.mode = mode ;
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

		Logger.info("{}", String.format("%-8s  %-12s  %s", mode, src, actual)) ;

		assertArrayEquals(expectedContexts.toArray(), actual.toArray()) ;
	}
}
