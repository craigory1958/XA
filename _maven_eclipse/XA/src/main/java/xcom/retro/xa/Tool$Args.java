

package xcom.retro.xa ;


import java.util.Properties ;

import org.apache.commons.cli.CommandLine ;
import org.apache.commons.cli.Option ;
import org.apache.commons.cli.Options ;
import org.apache.commons.cli.ParseException ;
import org.slf4j.Logger ;

import xcom.utils4j.CLArgs ;


public class Tool$Args extends CLArgs {

	public static final String Tool$ArgLong_Library = "lib" ;
	public static final Option Tool$ArgOption_Library = Option.builder(null).longOpt(Tool$ArgLong_Library).hasArg(true) //
			.argName("fnSpec [, fnSpec ...]").desc("libraries").build() ;


	public Tool$Args(final CommandLine cmd, final Properties props, final String[] args, final Logger logger) {
		super(cmd, props, args, logger) ;
	}


	@Override
	public void validateCommandLine(final CommandLine cmd, final Options options) throws ParseException {}
}
