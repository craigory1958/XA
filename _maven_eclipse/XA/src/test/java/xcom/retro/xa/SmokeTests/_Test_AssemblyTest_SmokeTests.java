

package xcom.retro.xa.SmokeTests ;


import java.util.Arrays ;
import java.util.Collection ;

import org.junit.runner.RunWith ;
import org.junit.runners.Parameterized ;

import xcom.retro.xa.AssemblyTest ;


@RunWith(Parameterized.class)
public class _Test_AssemblyTest_SmokeTests extends AssemblyTest {

	@Parameterized.Parameters
	public static Collection<Object[]> data() {

		final String pfx = _Test_AssemblyTest_SmokeTests.class.getSimpleName().substring("_Test_".length()) ;

		//@formatter:off

        final Object[][] results = {

                // { String desc, String srcFSpec }

                { "KIM-1 ROM 002 & 003", pfx + "_KIM-1_ROM.a65", },
        } ;

        //@formatter:on

		return (Arrays.asList(results)) ;
	}


	public _Test_AssemblyTest_SmokeTests(final String desc, final String srcFSpec) throws Exception {
		super(desc, srcFSpec) ;
	}
}
