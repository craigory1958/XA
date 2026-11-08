

package xcom.retro.xa.Directives ;


import java.util.Arrays ;
import java.util.Collection ;

import org.junit.runner.RunWith ;
import org.junit.runners.Parameterized ;

import xcom.retro.xa.AssemblyTest ;


@RunWith(Parameterized.class)
public class _Test_AssemblyTest_LIST extends AssemblyTest {

	@Parameterized.Parameters
	public static Collection<Object[]> data() {

		final String pfx = _Test_AssemblyTest_LIST.class.getSimpleName().substring("_Test_".length()) ;

		//@formatter:off

        final Object[][] results = {

                // { String desc, String srcFSpec }

                { "Basic .list/.nolist", pfx + "_Basic.a65", },
                { "IFDEF", pfx + "_IFDEF.a65", },
                { "IFNDEF", pfx + "_IFNDEF.a65", },
                { "INCLUDE", pfx + "_INCLUDE.a65", },
                { "MACRO", pfx + "_MACRO.a65", },
        } ;

        //@formatter:on

		return (Arrays.asList(results)) ;
	}


	public _Test_AssemblyTest_LIST(final String desc, final String srcFSpec) throws Exception {
		super(desc, srcFSpec) ;
	}
}
