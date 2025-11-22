

package xcom.retro.xa.References ;


import static org.junit.Assert.assertEquals ;

import java.io.File ;
import java.nio.charset.StandardCharsets ;
import java.util.Arrays ;
import java.util.Collection ;

import org.apache.commons.io.FileUtils ;
import org.apache.commons.io.FilenameUtils ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.junit.runners.Parameterized ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

import xcom.retro.xa.XA ;


@RunWith(Parameterized.class)
public class _Test_XA_ForwardReference {

	@Parameterized.Parameters
	public static Collection<Object[]> data() {

		//@formatter:off

        final Object[][] results = {

                // { String desc, String fSpec }

                { "JMP AHEAD", "ForwardReference_JMP.a65", },
                { "BEQ AHEAD", "ForwardReference_BEQ.a65", },
                { ".word AHEAD", "ForwardReference_WORD.a65", },
                { ".byte AHEAD", "ForwardReference_BYTE.a65", },
        } ;

        //@formatter:on

		return (Arrays.asList(results)) ;
	}


	private static final Logger Logger = LoggerFactory.getLogger(_Test_XA_ForwardReference.class) ;

	String desc ;
	String srcFSpec ;


	public _Test_XA_ForwardReference(final String desc, final String srcFSpec) {

		this.desc = desc ;
		this.srcFSpec = srcFSpec ;
	}


	@Test
	public void assemblerTest() throws Exception {

		final String _dSpec = FilenameUtils.getFullPath(this.getClass().getResource(srcFSpec).toURI().getPath()) ;
		final String _fSpec = FilenameUtils.getBaseName(FilenameUtils.getBaseName(srcFSpec)) ;

		final String[] args = { "-b", "-l", _dSpec + srcFSpec } ;

		Logger.info("{}", String.format("%s - XA %s", desc, Arrays.asList(args))) ;

		XA.main(args) ;

		final String expected = FileUtils.readFileToString(new File(_dSpec + _fSpec + ".expected.bin"), StandardCharsets.UTF_8) ;
		final String actual = FileUtils.readFileToString(new File(_dSpec + _fSpec + ".bin"), StandardCharsets.UTF_8) ;

		assertEquals(expected, actual) ;
	}
}
