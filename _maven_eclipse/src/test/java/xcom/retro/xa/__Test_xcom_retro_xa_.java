

package xcom.retro.xa ;


import org.junit.runner.RunWith ;
import org.junit.runners.Suite ;

import xcom.retro.xa.Directives.__Test_xcom_retro_xa_Directives_ ;
import xcom.retro.xa.Parsing.__Test_xcom_retro_xa_Parsing_ ;
import xcom.retro.xa.References.__Test_xcom_retro_xa_References_ ;
import xcom.retro.xa.SmokeTests.__Test_xcom_retro_xa_SmokeTests_ ;


//@formatter:off
@RunWith(Suite.class)
@Suite.SuiteClasses({
	__Test_xcom_retro_xa_Directives_.class,
	__Test_xcom_retro_xa_Parsing_.class,
	__Test_xcom_retro_xa_References_.class,
	__Test_xcom_retro_xa_SmokeTests_.class
})
//@formatter:on

public class __Test_xcom_retro_xa_ {}
