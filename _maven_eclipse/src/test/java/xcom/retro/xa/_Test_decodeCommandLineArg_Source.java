

package xcom.retro.xa ;


import static org.junit.Assert.assertEquals ;

import java.io.IOException ;
import java.util.Arrays ;
import java.util.Collection ;
import java.util.HashMap ;
import java.util.Map ;

import org.apache.commons.cli.CommandLine ;
import org.apache.commons.cli.ParseException ;
import org.junit.BeforeClass ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.junit.runners.Parameterized ;

import xcom.retro.xa.api.annotations.aProcessor ;
import xcom.utils4j.JUnits ;
import xcom.utils4j.data.structured.list.Lists ;


@RunWith(Parameterized.class)
public class _Test_decodeCommandLineArg_Source {

	@Parameterized.Parameters()
	public static Collection<Object[][]> data() {

		//@formatter:off

        final Object[][][] results = {

                // { String[] args,  String[] args }

                { { }, { new IllegalArgumentException("Must specify source file for assembly.") }, },
                { { "", }, { new IllegalArgumentException("Must specify source file for assembly.") }, },
                { { "-l", }, { new IllegalArgumentException("Must specify source file for assembly.") }, },
                { { "XYZ.a65", }, { "p=MOS6502", "source=XYZ.a65", }, },
                { { "-b", "XYZ.bin", "-l", "XYZ.a65", }, { "p=MOS6502", "source=XYZ.a65", }, },
                { { "-b", "-l", "XYZ.a65", }, { "p=MOS6502", "source=XYZ.a65", }, },
                { { "XYZ.xyz", }, { "p=MOS6502", "source=XYZ.xyz", }, },
                { { "XYZ.z80", }, { "p=Z80", "source=XYZ.z80", }, },
                { { "XYZ.asm", }, { "p=Z80", "source=XYZ.asm", }, },
                { { "-p", "Z80", "XYZ.a65", }, { "p=Z80", "source=XYZ.a65", }, },
          } ;

        //@formatter:on

		return (Arrays.asList(results)) ;
	}


	static XA xa ;
	static Map<String, aProcessor> srcFNExts ;

	String[] args ;
	Map<String, String> expected ;
	Exception exception ;


	public _Test_decodeCommandLineArg_Source(final Object[] args, final Object[] expected) {

		this.args = Arrays.copyOf(args, args.length, String[].class) ;

		if ( expected.length > 0 && expected[0] instanceof Exception )
			this.exception = (Exception) expected[0] ;
		
		else {
			this.expected = new HashMap<>() ;
			for ( Object e : expected ) {
				String[] parts = ((String) e).split("=") ;
				this.expected.put(parts[0], parts[1]) ;
			}
		}
	}


	@BeforeClass
	public static void setupBeforeClass() throws Exception {

		xa = new XA() ;
		xa.props = XA.loadXAProperties() ;

		final String path = xa.props.getProperty("XA.processor.scan.classpath") ;
		xa.processors = XA.scanAndLoadByAnnotationType(path, aProcessor.class.getName(), xa.processors) ;
		srcFNExts = XA.buildSourceFilenameExtensions(xa.processors) ;
	}


	@Test
	public void decodeCommandLineArg_Source() throws IOException, ParseException {

		CommandLine cmdLine = XA.parseCommandLine(args, XA.CommandLineOptions) ;
		String srcFSpec = Lists.last(cmdLine.getArgList()) ;
		Map<String, String> decodedArgs = XA.decodeCommandLineArg_Processor(cmdLine, xa.props, new HashMap<>()) ;

		try {
			final Map<String, String> actual = XA.decodeCommandLineArg_Source(srcFSpec, srcFNExts, cmdLine, xa.props, decodedArgs) ;
			System.out.println(actual) ;
			assertEquals(expected, actual) ;
		}
		catch ( Exception actual ) {
			JUnits.assertExceptionEquals("", exception, actual) ;
		}
	}
}
