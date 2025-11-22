

package xcom.retro.xa.SmokeTests ;


import static org.junit.Assert.assertEquals ;

import java.io.File ;
import java.nio.charset.StandardCharsets ;
import java.util.Arrays ;

import org.apache.commons.io.FileUtils ;
import org.apache.commons.io.FilenameUtils ;
import org.junit.Test ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

import xcom.retro.xa.XA ;


public class _Test_KIM1_ROM {

	private static final Logger Logger = LoggerFactory.getLogger(_Test_KIM1_ROM.class) ;


	@Test
	public void assemblerTest() throws Exception {

		final String srcFSpec = "KIM-1 ROM.a65" ;

		final String _dSpec = FilenameUtils.getFullPath(this.getClass().getResource(srcFSpec).toURI().getPath()) ;
		final String _fSpec = FilenameUtils.getBaseName(FilenameUtils.getBaseName(srcFSpec)) ;

		final String[] args = { "-b", "-l", _dSpec + srcFSpec } ;

		Logger.info("{}", String.format("%s - XA %s", "KIM-1 ROM", Arrays.asList(args))) ;

		XA.main(args) ;

		final String expected = FileUtils.readFileToString(new File(_dSpec + _fSpec + ".expected.bin"), StandardCharsets.UTF_8) ;
		final String actual = FileUtils.readFileToString(new File(_dSpec + _fSpec + ".bin"), StandardCharsets.UTF_8) ;

		assertEquals(expected, actual) ;
	}
}
