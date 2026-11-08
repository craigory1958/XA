

package xcom.retro.xa ;


import java.util.Arrays ;
import java.util.HashMap ;
import java.util.Map ;
import java.util.Properties ;

import org.apache.commons.cli.CommandLine ;
import org.apache.commons.cli.Option ;
import org.apache.commons.cli.Options ;
import org.apache.commons.cli.ParseException ;
import org.apache.commons.io.FilenameUtils ;
import org.apache.commons.lang3.StringUtils ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

import xcom.retro.xa.api.annotations.aProcessor ;
import xcom.utils4j.data.structured.list.Lists ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
public class XA$Args extends Tool$Args {

	private static final Logger Logger = LoggerFactory.getLogger(XA$Args.class) ;


	public static final String $Arg_Binary = "b" ;
	public static final String $ArgLong_Binary = "bin" ;
	public static final Option $ArgOption_Binary = Option.builder($Arg_Binary).longOpt($ArgLong_Binary).optionalArg(true).hasArg(true) //
			.argName("[fnSpec]").desc("generate assembly binary").build() ;

	public static final String $Arg_Format = "f" ;
	public static final String $ArgLong_Format = "format" ;
	public static final Option $ArgOption_Format = Option.builder($Arg_Format).longOpt($ArgLong_Format).hasArg(true) //
			.argName("{format}").desc("specify binray format").build() ;

	public static final String $Arg_List = "l" ;
	public static final String $ArgLong_List = "list" ;
	public static final Option $ArgOption_List = Option.builder($Arg_List).longOpt($ArgLong_List).optionalArg(true).hasArg(true) //
			.argName("[fnSpec]").desc("generate assembly listing").build() ;

	public static final String $Arg_XRef = "x" ;
	public static final String $ArgLong_XRef = "xref" ;
	public static final Option $ArgOption_XRef = Option.builder($Arg_XRef).longOpt($ArgLong_XRef).hasArg(true).optionalArg(true) //
			.argName("[fnSpec]").desc("generate cross reference listing").build() ;

	public static final String $Arg_Processor = "p" ;
	public static final String $ArgLong_Processor = "proc" ;
	public static final Option $ArgOption_Processor = Option.builder($Arg_Processor).longOpt($ArgLong_Processor).hasArg(true) //
			.argName("{processor}").desc("specify processor").build() ;


	public static final Options CommandLineOptions = new Options() ;
	static {
		CommandLineOptions.addOption(CLArgOption_Help) ;
		CommandLineOptions.addOption(CLArgOption_Log) ;
		CommandLineOptions.addOption(CLArgOption_Version) ;

		CommandLineOptions.addOption($ArgOption_Binary) ;
		CommandLineOptions.addOption($ArgOption_Format) ;
		CommandLineOptions.addOption(Tool$ArgOption_Library) ;
		CommandLineOptions.addOption($ArgOption_List) ;
		CommandLineOptions.addOption($ArgOption_XRef) ;
		CommandLineOptions.addOption($ArgOption_Processor) ;
	}


	public XA$Args(final CommandLine cmd, final Properties props, final String[] args, final Logger logger) {
		super(cmd, props, args, logger) ;
	}


	static Map<String, Object> decodeCommandLine(final CommandLine cmd, final Map<String, aProcessor> srcFNExts, final Properties props,
			Map<String, Object> decodedArgs) {

		if ( decodedArgs == null )
			decodedArgs = new HashMap<>() ;


		decodedArgs = decodeArg_Processor(cmd, props, decodedArgs) ;

		decodedArgs = decodeArg_Source(Lists.last(cmd.getArgList()), srcFNExts, cmd, props, decodedArgs) ;

		decodedArgs = decodeArg_Binary((String) decodedArgs.get("source"), cmd, props, decodedArgs) ;

		decodedArgs = decodeArg_Format(cmd, props, decodedArgs) ;

		decodedArgs = decodeArg_List((String) decodedArgs.get("source"), cmd, props, decodedArgs) ;

		decodedArgs = decodeArg_XRef((String) decodedArgs.get("source"), cmd, props, decodedArgs) ;


		Logger.debug("args: {}", Arrays.asList(decodedArgs)) ;

		return decodedArgs ;
	}


	@Override
	public void validateCommandLine(final CommandLine cmd, final Options options) throws ParseException {}


	static Map<String, Object> decodeArg_Binary(final String srcFSpec, final CommandLine cmd, final Properties props, final Map<String, Object> decodedArgs) {

		if ( cmd.hasOption($Arg_Binary) ) {

			final String bin = StringUtils.trimToEmpty(cmd.getOptionValue($Arg_Binary)) ;

			if ( bin.isEmpty() ) {
				final String srcDSpec = FilenameUtils.getFullPath(srcFSpec) ;
				final String srcFNSpec = FilenameUtils.getName(srcFSpec) ;
				final String[] srcFNParts = srcFNSpec.split("\\.") ;

				decodedArgs.put($Arg_Binary, srcDSpec + srcFNParts[0] + ".bin") ;
			}
			else
				decodedArgs.put($Arg_Binary, bin) ;
		}


		return decodedArgs ;
	}


	static Map<String, Object> decodeArg_Format(final CommandLine cmd, final Properties props, final Map<String, Object> decodedArgs) {

		String format = StringUtils.trimToEmpty(cmd.getOptionValue($Arg_Format)) ;

		if ( format.isEmpty() )
			format = props.getProperty("XA.extruder.default") ;

		decodedArgs.put($Arg_Format, format) ;


		return decodedArgs ;
	}


	static Map<String, Object> decodeArg_List(final String srcFSpec, final CommandLine cmd, final Properties props, final Map<String, Object> decodedArgs) {

		if ( cmd.hasOption($Arg_List) ) {

			final String list = StringUtils.trimToEmpty(cmd.getOptionValue($Arg_List)) ;

			if ( list.isEmpty() ) {
				final String srcDSpec = FilenameUtils.getFullPath(srcFSpec) ;
				final String srcFNSpec = FilenameUtils.getName(srcFSpec) ;
				final String[] srcFNParts = srcFNSpec.split("\\.") ;

				decodedArgs.put($Arg_List, srcDSpec + srcFNParts[0] + ".lis") ;
			}
			else
				decodedArgs.put($Arg_List, list) ;
		}


		return decodedArgs ;
	}


	static Map<String, Object> decodeArg_Processor(final CommandLine cmd, final Properties props, final Map<String, Object> decodedArgs) {

		final String processor = StringUtils.trimToEmpty(cmd.getOptionValue($Arg_Processor)) ;

		decodedArgs.put($Arg_Processor, processor) ;


		return decodedArgs ;
	}


	static Map<String, Object> decodeArg_Source(final String srcFSpec, final Map<String, aProcessor> srcFNExts, final CommandLine cmd, final Properties props,
			final Map<String, Object> decodedArgs) {


		if ( StringUtils.trimToEmpty(srcFSpec).isEmpty() )
			throw new IllegalArgumentException("Must specify source file for assembly.") ;

		if ( FilenameUtils.getName(srcFSpec).isEmpty() || FilenameUtils.getBaseName(srcFSpec).isEmpty() || FilenameUtils.getExtension(srcFSpec).isEmpty() )
			throw new IllegalArgumentException("") ;


		if ( !cmd.hasOption($Arg_Processor) ) {

			final String srcFNExt = FilenameUtils.getExtension(srcFSpec) ;
			final String processor = (srcFNExts.containsKey(srcFNExt.toLowerCase()) //
					? srcFNExts.get(srcFNExt.toLowerCase()).listener().getSimpleName() //
					: props.getProperty("XA.processor.default") //
			) ;

			decodedArgs.put($Arg_Processor, processor) ;
		}

		decodedArgs.put("source", srcFSpec) ;


		return decodedArgs ;
	}


	static Map<String, Object> decodeArg_XRef(final String srcFSpec, final CommandLine cmd, final Properties props, final Map<String, Object> decodedArgs) {

		if ( cmd.hasOption($Arg_XRef) ) {
			final String list = StringUtils.trimToEmpty(cmd.getOptionValue($Arg_XRef)) ;

			if ( list.isEmpty() ) {
				final String srcDSpec = FilenameUtils.getFullPath(srcFSpec) ;
				final String srcFNSpec = FilenameUtils.getName(srcFSpec) ;
				final String[] srcFNParts = srcFNSpec.split("\\.") ;

				decodedArgs.put($Arg_XRef, srcDSpec + srcFNParts[0] + ".xref") ;
			}
			else
				decodedArgs.put($Arg_XRef, list) ;
		}


		return decodedArgs ;
	}
}
