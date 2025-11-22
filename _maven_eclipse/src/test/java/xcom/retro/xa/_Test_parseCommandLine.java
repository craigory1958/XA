

package xcom.retro.xa ;


import static org.junit.Assert.assertEquals ;

import java.io.IOException ;
import java.util.Arrays ;
import java.util.Collection ;

import org.apache.commons.cli.CommandLine ;
import org.apache.commons.cli.Option ;
import org.apache.commons.cli.ParseException ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.junit.runners.Parameterized ;


@RunWith(Parameterized.class)
public class _Test_parseCommandLine {

	@Parameterized.Parameters()
	public static Collection<String[][]> data() {

		//@formatter:off

        final String[][][] results = {

                // { String[] args,  String[] args }

                { { }, { }, },
                { { "", }, { }, },
                { { "VMK.a65", }, { }, },
                { { "-l", "VMK.a65", }, { "l", }, },
                { { "-l", "VMK.lis", }, { "l", }, },
                { { "-l", "VMK.lis", "VMK.a65", }, { "l" }, },
                { { "-b", "-l", "VMK.a65", }, { "b", "l" }, },
                { { "-b", "VMK.bin", "-l", "VMK.a65", }, { "b", "l" }, },
           } ;

        //@formatter:on

		return (Arrays.asList(results)) ;
	}


	String[] args ;
	String[] expected ;


	public _Test_parseCommandLine(final String[] args, final String[] expected) {

		this.args = args ;
		this.expected = expected ;
	}


	@Test
	public void parseCommandLine() throws IOException, ParseException {

		final CommandLine actual = XA.parseCommandLine(args, XA.CommandLineOptions) ;

		int e = 0 ;
		for ( Option option : actual.getOptions() )
			assertEquals("", expected[e++], option.getOpt()) ;
	}
}
