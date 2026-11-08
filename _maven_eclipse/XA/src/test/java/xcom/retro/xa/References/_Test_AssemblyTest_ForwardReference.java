

package xcom.retro.xa.References ;


import java.util.Arrays ;
import java.util.Collection ;

import org.junit.runner.RunWith ;
import org.junit.runners.Parameterized ;

import xcom.retro.xa.AssemblyTest ;


@RunWith(Parameterized.class)
public class _Test_AssemblyTest_ForwardReference extends AssemblyTest{

	@Parameterized.Parameters
	public static Collection<Object[]> data() {

		final String pfx = _Test_AssemblyTest_ForwardReference.class.getSimpleName().substring("_Test_".length()) ;

		//@formatter:off

        final Object[][] results = {

                // { String desc, String fSpec }

                { "BEQ AHEAD", pfx + "_BEQ.a65", },
                { ".byte AHEAD", pfx + "_BYTE.a65", },
                { "JMP AHEAD", pfx + "_JMP.a65", },
                { ".macro AHEAD", pfx + "_MACRO.a65", },
                { ".word AHEAD", pfx + "_WORD.a65", },
        } ;

        //@formatter:on

		return (Arrays.asList(results)) ;
	}


	public _Test_AssemblyTest_ForwardReference(final String desc, final String srcFSpec) throws Exception {
		super(desc, srcFSpec, "-log", "asm") ;
	}
}
