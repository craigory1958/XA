

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


	public static final String XA$Arg_Binary = "b" ;
	public static final String XA$ArgLong_Binary = "bin" ;
	public static final Option XA$ArgOption_Binary = Option.builder(XA$Arg_Binary).argName("file").longOpt(XA$ArgLong_Binary).hasArg().optionalArg(true)
			.argName("[fnSpec]").desc("generate assembly binary").build() ;

	public static final String XA$Arg_Format = "f" ;
	public static final String XA$ArgLong_Format = "format" ;
	public static final Option XA$ArgOption_Format = Option.builder(XA$Arg_Format).longOpt(XA$ArgLong_Format).argName("format").hasArg().optionalArg(false)
			.argName("{format}").desc("specify binray format").build() ;

	public static final String XA$Arg_List = "l" ;
	public static final String XA$ArgLong_List = "list" ;
	public static final Option XA$ArgOption_List = Option.builder(XA$Arg_List).longOpt(XA$ArgLong_List).argName("file").hasArg().optionalArg(true)
			.argName("[fnSpec]").desc("generate assembly listing").build() ;

	public static final String XA$Arg_XRef = "x" ;
	public static final String XA$ArgLong_XRef = "xref" ;
	public static final Option XA$ArgOption_XRef = Option.builder(XA$Arg_XRef).argName("file").longOpt(XA$ArgLong_XRef).hasArg().optionalArg(true)
			.argName("[fnSpec]").desc("generate cross reference listing").build() ;

	public static final String XA$Arg_Processor = "p" ;
	public static final String XA$ArgLong_Processor = "proc" ;
	public static final Option XA$ArgOption_Processor = Option.builder(XA$Arg_Processor).longOpt(XA$ArgLong_Processor).hasArg().optionalArg(false)
			.argName("{processor}").desc("specify processor").build() ;


	public static final Options CommandLineOptions = new Options() ;
	static {
		CommandLineOptions.addOption(CLArgOption_Help) ;
		CommandLineOptions.addOption(CLArgOption_Log) ;
		CommandLineOptions.addOption(CLArgOption_Version) ;

		CommandLineOptions.addOption(XA$ArgOption_Binary) ;
		CommandLineOptions.addOption(XA$ArgOption_Format) ;
		CommandLineOptions.addOption(Tool$ArgOption_Library) ;
		CommandLineOptions.addOption(XA$ArgOption_List) ;
		CommandLineOptions.addOption(XA$ArgOption_XRef) ;
		CommandLineOptions.addOption(XA$ArgOption_Processor) ;
	}


	public XA$Args(CommandLine cmd, Properties props, String[] args, Logger logger) {
		super(cmd, props, args, logger) ;
	}


	static Map<String, Object> decodeCommandLine(final CommandLine cmd, Map<String, aProcessor> srcFNExts, final Properties props,
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
	public void validateCommandLine(CommandLine cmd, Options options) throws ParseException {}


	static Map<String, Object> decodeArg_Binary(String srcFSpec, CommandLine cmd, Properties props, Map<String, Object> decodedArgs) {

		if ( cmd.hasOption(XA$Arg_Binary) ) {

			final String bin = StringUtils.trimToEmpty(cmd.getOptionValue(XA$Arg_Binary)) ;

			if ( bin.isEmpty() ) {
				final String srcDSpec = FilenameUtils.getFullPath(srcFSpec) ;
				final String srcFNSpec = FilenameUtils.getName(srcFSpec) ;
				final String[] srcFNParts = srcFNSpec.split("\\.") ;

				decodedArgs.put(XA$Arg_Binary, srcDSpec + srcFNParts[0] + ".bin") ;
			}
			else
				decodedArgs.put(XA$Arg_Binary, bin) ;
		}


		return decodedArgs ;
	}


	static Map<String, Object> decodeArg_Format(CommandLine cmd, Properties props, Map<String, Object> decodedArgs) {

		String format = StringUtils.trimToEmpty(cmd.getOptionValue(XA$Arg_Format)) ;

		if ( format.isEmpty() )
			format = props.getProperty("XA.extruder.default") ;

		decodedArgs.put(XA$Arg_Format, format) ;


		return decodedArgs ;
	}


	static Map<String, Object> decodeArg_List(String srcFSpec, CommandLine cmd, Properties props, Map<String, Object> decodedArgs) {

		if ( cmd.hasOption(XA$Arg_List) ) {

			final String list = StringUtils.trimToEmpty(cmd.getOptionValue(XA$Arg_List)) ;

			if ( list.isEmpty() ) {
				final String srcDSpec = FilenameUtils.getFullPath(srcFSpec) ;
				final String srcFNSpec = FilenameUtils.getName(srcFSpec) ;
				final String[] srcFNParts = srcFNSpec.split("\\.") ;

				decodedArgs.put(XA$Arg_List, srcDSpec + srcFNParts[0] + ".lis") ;
			}
			else
				decodedArgs.put(XA$Arg_List, list) ;
		}


		return decodedArgs ;
	}


	static Map<String, Object> decodeArg_Processor(CommandLine cmd, Properties props, Map<String, Object> decodedArgs) {

		String processor = StringUtils.trimToEmpty(cmd.getOptionValue(XA$Arg_Processor)) ;

		decodedArgs.put(XA$Arg_Processor, processor) ;


		return decodedArgs ;
	}


	static Map<String, Object> decodeArg_Source(String srcFSpec, Map<String, aProcessor> srcFNExts, CommandLine cmd, final Properties props,
			Map<String, Object> decodedArgs) {


		if ( StringUtils.trimToEmpty(srcFSpec).isEmpty() )
			throw new IllegalArgumentException("Must specify source file for assembly.") ;

		if ( FilenameUtils.getName(srcFSpec).isEmpty() )
			throw new IllegalArgumentException("") ;

		if ( FilenameUtils.getBaseName(srcFSpec).isEmpty() )
			throw new IllegalArgumentException("") ;

		if ( FilenameUtils.getExtension(srcFSpec).isEmpty() )
			throw new IllegalArgumentException("") ;


		if ( !cmd.hasOption(XA$Arg_Processor) ) {

			String srcFNExt = FilenameUtils.getExtension(srcFSpec) ;
			String processor = (srcFNExts.containsKey(srcFNExt.toLowerCase()) //
					? srcFNExts.get(srcFNExt.toLowerCase()).listener().getSimpleName() //
					: props.getProperty("XA.processor.default") //
			) ;

			decodedArgs.put(XA$Arg_Processor, processor) ;
		}

		decodedArgs.put("source", srcFSpec) ;


		return decodedArgs ;
	}


	static Map<String, Object> decodeArg_XRef(String srcFSpec, CommandLine cmd, Properties props, Map<String, Object> decodedArgs) {

		if ( cmd.hasOption(XA$Arg_XRef) ) {
			final String list = StringUtils.trimToEmpty(cmd.getOptionValue(XA$Arg_XRef)) ;

			if ( list.isEmpty() ) {
				final String srcDSpec = FilenameUtils.getFullPath(srcFSpec) ;
				final String srcFNSpec = FilenameUtils.getName(srcFSpec) ;
				final String[] srcFNParts = srcFNSpec.split("\\.") ;

				decodedArgs.put(XA$Arg_XRef, srcDSpec + srcFNParts[0] + ".xref") ;
			}
			else
				decodedArgs.put(XA$Arg_XRef, list) ;
		}


		return decodedArgs ;
	}
}
