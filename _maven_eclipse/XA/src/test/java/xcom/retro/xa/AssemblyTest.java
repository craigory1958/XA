

package xcom.retro.xa ;


import static org.junit.Assert.assertEquals ;
import static org.junit.Assert.assertTrue ;

import java.io.File ;
import java.io.IOException ;
import java.nio.charset.StandardCharsets ;

import org.apache.commons.io.FileUtils ;
import org.apache.commons.io.FilenameUtils ;
import org.apache.commons.lang3.ArrayUtils ;
import org.junit.Test ;


public class AssemblyTest {
	
	static final String[] Empty_optArgs = {} ;


	String desc ;
	String srcFSpec ;
	String[] optArgs ;

	String dirSpec ;
	String fSpec ;
	String[] srcArg ;


	public AssemblyTest(final String desc, final String srcFSpec) throws Exception {

		this.desc = desc ;
		this.srcFSpec = srcFSpec ;

		this.optArgs = Empty_optArgs ;
		this.dirSpec = FilenameUtils.getFullPath(this.getClass().getResource(srcFSpec).toURI().getPath()) ;
		this.fSpec = FilenameUtils.getBaseName(FilenameUtils.getBaseName(srcFSpec)) ;
	}


	public AssemblyTest(String desc, String srcFSpec, String... optArgs) throws Exception {

		this.desc = desc ;
		this.srcFSpec = srcFSpec ;
		this.optArgs = optArgs ;

		this.dirSpec = FilenameUtils.getFullPath(this.getClass().getResource(srcFSpec).toURI().getPath()) ;
		this.fSpec = FilenameUtils.getBaseName(FilenameUtils.getBaseName(srcFSpec)) ;
	}


	public void assemble(String asmArg, String[] optArgs) throws Exception {

		final String[] asmArgs = { asmArg } ;
		final String[] srcArg = { dirSpec + srcFSpec } ;

		String[] args = ArrayUtils.addAll(ArrayUtils.addAll(asmArgs, optArgs), srcArg) ;

		XA.main(args) ;
	}


	@Test
	public void assembleBinaryTest() throws Exception {

		String extension = ".bin" ;
		String expectedFSpec = dirSpec + fSpec + ".expected" + extension ;
		String actualFSpec = dirSpec + fSpec + extension ;

		if ( new File(expectedFSpec).exists() ) {

			assemble("-b", optArgs) ;

			boolean equal = compareFileContentIgnoreEOL(expectedFSpec, actualFSpec) ;

			if ( equal )
				assertTrue(equal) ;
			
			else {
				final String expected = FileUtils.readFileToString(new File(expectedFSpec), StandardCharsets.UTF_8) ;
				final String actual = FileUtils.readFileToString(new File(actualFSpec), StandardCharsets.UTF_8) ;

				assertEquals(expected, actual) ;
			}
		}
	}


	@Test
	public void assembleListingTest() throws Exception {

		String extension = ".lis" ;
		String expectedFSpec = dirSpec + fSpec + ".expected" + extension ;
		String actualFSpec = dirSpec + fSpec + extension ;

		if ( new File(expectedFSpec).exists() ) {

			assemble("-l", optArgs) ;

			boolean equal = compareFileContentIgnoreEOL(expectedFSpec, actualFSpec) ;

			if ( equal )
				assertTrue(equal) ;
			
			else {
				final String expected = FileUtils.readFileToString(new File(expectedFSpec), StandardCharsets.UTF_8) ;
				final String actual = FileUtils.readFileToString(new File(actualFSpec), StandardCharsets.UTF_8) ;

				assertEquals(expected, actual) ;
			}
		}
	}


	@Test
	public void assembleCrossReferenceTest() throws Exception {

		String extension = ".xref" ;
		String expectedFSpec = dirSpec + fSpec + ".expected" + extension ;
		String actualFSpec = dirSpec + fSpec + extension ;

		if ( new File(expectedFSpec).exists() ) {

			assemble("-x", optArgs) ;

			boolean equal = compareFileContentIgnoreEOL(expectedFSpec, actualFSpec) ;

			if ( equal )
				assertTrue(equal) ;
			
			else {
				final String expected = FileUtils.readFileToString(new File(expectedFSpec), StandardCharsets.UTF_8) ;
				final String actual = FileUtils.readFileToString(new File(actualFSpec), StandardCharsets.UTF_8) ;

				assertEquals(expected, actual) ;
			}
		}
	}


	public static boolean compareFileContentIgnoreEOL(String file1, String file2) throws IOException {
		return FileUtils.contentEqualsIgnoreEOL(new File(file1), new File(file2), StandardCharsets.UTF_8.displayName()) ;
	}
}
