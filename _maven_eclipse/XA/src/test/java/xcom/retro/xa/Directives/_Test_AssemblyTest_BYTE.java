

package xcom.retro.xa.Directives ;


import java.util.Arrays ;
import java.util.Collection ;

import org.junit.runner.RunWith ;
import org.junit.runners.Parameterized ;

import xcom.retro.xa.AssemblyTest ;


@RunWith(Parameterized.class)
public class _Test_AssemblyTest_BYTE extends AssemblyTest {

	@Parameterized.Parameters
	public static Collection<Object[]> data() {

		final String pfx = _Test_AssemblyTest_BYTE.class.getSimpleName().substring(6) ;

		//@formatter:off

        final Object[][] results = {

                // { String desc, String srcFSpec }

                { "Multiple Operands", pfx + "_MultipleOperands.a65", },
                { "Single Operand", pfx + "_SingleOperand.a65", },
        } ;

        //@formatter:on

		return (Arrays.asList(results)) ;
	}


	public _Test_AssemblyTest_BYTE(final String desc, final String srcFSpec) throws Exception {
		super(desc, srcFSpec) ;
	}
}
