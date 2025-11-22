

package xcom.retro.xa ;


import static xcom.retro.xa.XA.AssemblyPhases.Assemble ;
import static xcom.retro.xa.XA.AssemblyPhases.Extrude ;
import static xcom.retro.xa.XA.AssemblyPhases.Generate ;
import static xcom.retro.xa.XA.AssemblyPhases.Init ;
import static xcom.retro.xa.XA.AssemblyPhases.List ;
import static xcom.retro.xa.XA.AssemblyPhases.Parse ;
import static xcom.retro.xa.XA.AssemblyPhases.XRef ;
import static xcom.utils4j.logging.Loggers.ConsoleLoggerName ;

import java.io.File ;
import java.io.FileWriter ;
import java.io.IOException ;
import java.io.PrintWriter ;
import java.lang.annotation.Annotation ;
import java.nio.charset.Charset ;
import java.util.ArrayList ;
import java.util.Arrays ;
import java.util.HashMap ;
import java.util.List ;
import java.util.Map ;
import java.util.Map.Entry ;
import java.util.Properties ;
import java.util.Set ;
import java.util.Stack ;

import org.antlr.v4.runtime.CharStream ;
import org.antlr.v4.runtime.CharStreams ;
import org.antlr.v4.runtime.CommonTokenStream ;
import org.antlr.v4.runtime.Lexer ;
import org.antlr.v4.runtime.Parser ;
import org.antlr.v4.runtime.TokenStream ;
import org.antlr.v4.runtime.tree.ParseTreeListener ;
import org.antlr.v4.runtime.tree.ParseTreeWalker ;
import org.apache.commons.cli.CommandLine ;
import org.apache.commons.cli.DefaultParser ;
import org.apache.commons.cli.HelpFormatter ;
import org.apache.commons.cli.Option ;
import org.apache.commons.cli.Options ;
import org.apache.commons.cli.ParseException ;
import org.apache.commons.io.FileUtils ;
import org.apache.commons.io.FilenameUtils ;
import org.apache.commons.lang3.StringUtils ;
import org.fest.reflect.core.Reflection ;
import org.reflections.Reflections ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import org.slf4j.event.Level ;

import xcom.retro.xa.api.annotations.aDirective ;
import xcom.retro.xa.api.annotations.aExtruder ;
import xcom.retro.xa.api.annotations.aProcessor ;
import xcom.retro.xa.api.interfaces.iDirective ;
import xcom.retro.xa.api.interfaces.iExtruder ;
import xcom.retro.xa.api.interfaces.iProcessor ;
import xcom.retro.xa.api.interfaces.iSource ;
import xcom.retro.xa.assembly.AssemblyUtils ;
import xcom.retro.xa.directives.dir.MACRO ;
import xcom.retro.xa.directives.dir.STRUCT ;
import xcom.retro.xa.extruders.AssemblyLister ;
import xcom.retro.xa.extruders.XRefLister ;
import xcom.utils4j.data.structured.list.Lists ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;
import xcom.utils4j.resources.Props ;


@Log
public class XA {

	public enum AssemblyPhases {
		Init, Parse, Assemble, Generate, Extrude, List, XRef;
	}


	public class AssemblyContext {

		//@formatter:off

		CommandLine cmdLine ;

		Map<String, String> decodedArgs ;
		public Map<String, String> decodedArgs() { return decodedArgs ; }

		Map<String, iDirective> directives ;
		public Map<String, iDirective> directives() { return directives ; }

		Moniker identifier ;
		public Moniker identifier() { return identifier ; }
		public AssemblyContext identifier(final Moniker identifier) {
			this.identifier = identifier ;
			return this ;
		}

		Map<String, Moniker> symbols = new HashMap<>() ;
		public Map<String, Moniker> symbols() { return symbols ; }

		Stack<Boolean> ifBlocks ;
		public Stack<Boolean> ifBlocks() { return ifBlocks ; }

		boolean list ;
		public boolean list() { return list ; }
		public AssemblyContext list(final boolean list) {
			this.list = list ;
			return this ;
		}

		int ln ;
		public int ln() { return ln ; }

		Map<String, MACRO> macros = new HashMap<>() ;
		public Map<String, MACRO> macros() { return macros ; }

		AssemblyPhases phase ;
		public AssemblyPhases phase() { return phase ; }

		Segment segment ;
		public Segment segment() { return segment ; }
		public AssemblyContext segment(final Segment segment) {
			this.segment = segment ;
			return this ;
		}

		Map<String, Segment> segments = new HashMap<>() ;
		public Map<String, Segment> segments() { return segments ; }

		Stack<iSource> source = new Stack<>() ;
		public Stack<iSource> source() { return source ; }

		List<iSource> sources = new ArrayList<>() ;
		public List<iSource> sources() { return sources ; }

		Statement statement ;
		public Statement statement() { return statement ; }
		public AssemblyContext statement(final Statement statement) {
			this.statement = statement ;
			return this ;
		}

		List<Statement> statements = new ArrayList<>() ;
		public List<Statement> statements() { return statements ; }

		Map<String, STRUCT> structs = new HashMap<>() ;
		public Map<String, STRUCT> structs() { return structs ; }

		Lexer lexer ;
		public Lexer lexer() { return lexer ; }

		Parser parser ;
		public Parser parser() { return parser ; }

		//@formatter:on


		public AssemblyContext() {

			list = true ;
			ifBlocks = new Stack<>() ;
			ifBlocks.push(true) ;

			ln = 0 ;
		}


		public boolean assembleEnable() {
			return ifBlocks.peek() ;
		}

	}


	private static final Logger Logger = LoggerFactory.getLogger(XA.class) ;
	private static final Logger Console = LoggerFactory.getLogger(ConsoleLoggerName) ;

	static final String AppName = XA.class.getSimpleName() ;


	/**
	 * The valid command line options.
	 */
	static final String CommandLineOption_Help = "h" ;
	static final String CommandLineOptionLong_Help = "help" ;
	static final String CommandLineOption_Version = "v" ;
	static final String CommandLineOptionLong_Version = "version" ;
	static final String CommandLineOption_Binary = "b" ;
	static final String CommandLineOption_Format = "f" ;
	static final String CommandLineOption_List = "l" ;
	static final String CommandLineOption_XRef = "x" ;
	static final String CommandLineOption_Processor = "p" ;

	static final Options CommandLineOptions = new Options() ;

	static {
		CommandLineOptions.addOption(Option.builder(CommandLineOption_Help).longOpt(CommandLineOptionLong_Help).desc("display this message").build()) ;
		CommandLineOptions
				.addOption(Option.builder(CommandLineOption_Version).longOpt(CommandLineOptionLong_Version).desc("display version information").build()) ;

		CommandLineOptions
				.addOption(Option.builder(CommandLineOption_Binary).argName("file").hasArg().optionalArg(true).desc("generate assembly binary").build()) ;
		CommandLineOptions.addOption(Option.builder(CommandLineOption_Format).argName("format").hasArg().optionalArg(true).desc("binray format").build()) ;
		CommandLineOptions
				.addOption(Option.builder(CommandLineOption_List).argName("file").hasArg().optionalArg(true).desc("generate assembly listing").build()) ;
		CommandLineOptions
				.addOption(Option.builder(CommandLineOption_XRef).argName("file").hasArg().optionalArg(true).desc("generate cross reference listing").build()) ;
		CommandLineOptions.addOption(Option.builder(CommandLineOption_Processor).argName("processor").hasArg().desc("processor").build()) ;
	}


	Properties props ;
	AssemblyContext actx ;

	Map<String, Class<Class<? extends iExtruder>>> extruders ;
	Map<String, Class<Class<? extends iProcessor>>> processors ;
	ParseTreeListener processor ;


	public XA() {
		actx = new AssemblyContext() ;
	}


	@Log(Level.INFO)
	XA init() throws Exception {

		actx.phase = Init ;

		{
			aProcessor processor = (aProcessor) processors.get(actx.decodedArgs.get(CommandLineOption_Processor)).getAnnotation(aProcessor.class) ;

			final Class<? extends Lexer> lexerClass = Reflection.type(processor.lexar().getName()).loadAs(Lexer.class) ;
			actx.lexer = Reflection.constructor().withParameterTypes(CharStream.class).in(lexerClass).newInstance(CharStreams.fromString("")) ;

			final Class<? extends Parser> parserClass = Reflection.type(processor.parser().getName()).loadAs(Parser.class) ;
			actx.parser = Reflection.constructor().withParameterTypes(TokenStream.class).in(parserClass).newInstance(new CommonTokenStream(actx.lexer)) ;

			final Class<? extends ParseTreeListener> listenerClass = Reflection.type(processor.listener().getName()).loadAs(ParseTreeListener.class) ;
			this.processor = Reflection.constructor().withParameterTypes(AssemblyContext.class).in(listenerClass).newInstance(actx) ;
		}

		return this ;
	}


	//
	//
	//

	@Log(Level.INFO)
	XA parse() throws IOException {  // Pass 1

		actx.phase = Parse ;

		actx.sources.add(new FileSource(actx.sources.size(), actx.decodedArgs.get("source"), actx.list())) ;
		actx.source.push(Lists.last(actx.sources)) ;

		actx.segments.put("<default>", new Segment("<default>")) ;
		actx.segment = actx.segments.get("<default>") ;
		final ParseTreeWalker walker = new ParseTreeWalker() ;

		while ( !actx.source.isEmpty() ) {

			actx.list(actx.source.peek().list()) ;

			for ( String line; (line = actx.source.peek().next()) != null; ) {

				line = line.stripTrailing() ;
				Console.debug(">>>{}", line) ;

				if ( actx.list() )
					actx.ln += 1 ;

				actx.statements.add( //
						new Statement( //
								actx.source.peek().sourceID(), //
								actx.source.peek().sourceLN(), //
								line, actx.segment.loc, //
								actx.list(), //
								actx.assembleEnable() //
						)) ;

				actx.statement = Lists.last(actx.statements) ;
				actx.statement.ln(actx.ln) ;

//				actx.lexer.setInputStream(CharStreams.fromString(line)) ;
//				actx.parser.setTokenStream(new CommonTokenStream(actx.lexer)) ;
//
//				actx.statement.pctx = Reflection.method("statement").withReturnType(ParserRuleContext.class).in(actx.parser).invoke() ;
				actx.statement.pctx = AssemblyUtils.ASMB_parseStatement(line, actx.lexer, actx.parser) ;

				walker.walk(processor, actx.statement.pctx) ;

				if ( actx.statement.assemblyEnable() )
					actx.statement.block = Lists.last(actx.segment.blocks) ;
			}

			actx.source.pop() ;
		}

		return this ;
	}


	@Log(Level.INFO)
	XA assemble() { // Pass 2

		actx.phase = Assemble ;
//		System.err.println(actx.symbols.keySet()) ;

		for ( final Statement statement : actx.statements ) {

			actx.statement = statement ;
			Console.debug("***{}", actx.statement.line) ;

			if ( actx.statement.assemblyCallbackMethod != null )
				Reflection.method(actx.statement.assemblyCallbackMethod).in(actx.statement.assemblyCallbackObject).invoke() ;
		}

		return this ;
	}


	@Log(Level.INFO)
	XA generate() throws IOException {

		actx.phase = Generate ;
//		System.err.println(actx.symbols.keySet()) ;

		for ( final Statement statement : actx.statements ) {
			actx.statement = statement ;

			if ( actx.statement.assemblyEnable() && (actx.statement.block != null) && (actx.statement.bytes != null) && (actx.statement.bytes.length > 0) )
				actx.statement.block.fillBytes(actx.statement.loc, actx.statement.bytes) ;
		}

		return this ;
	}


	@Log(Level.INFO)
	XA extrude() throws IOException {

		actx.phase = Extrude ;

		if ( actx.cmdLine.hasOption(CommandLineOption_Binary) ) {

			final String extruderClassname = extruders.get(actx.decodedArgs.get(CommandLineOption_Format)).getName() ;
			final Class<? extends iExtruder> extruderClass = Reflection.type(extruderClassname).loadAs(iExtruder.class) ;
			final iExtruder extruder = Reflection.constructor().in(extruderClass).newInstance() ;

			try ( FileWriter fileWriter = new FileWriter(actx.decodedArgs.get(CommandLineOption_Binary)); PrintWriter out = new PrintWriter(fileWriter); ) {
				extruder.extrude(out, actx) ;
			}
		}

		Console.info("") ;
		Console.info(actx.decodedArgs.get(CommandLineOption_Binary)) ;
		Console.info(FileUtils.readFileToString(new File(actx.decodedArgs.get(CommandLineOption_Binary)), Charset.defaultCharset())) ;

		return this ;
	}


	@Log(Level.INFO)
	XA list() throws IOException {

		actx.phase = List ;

		if ( actx.cmdLine.hasOption(CommandLineOption_List) ) {

			final String extruderClassname = extruders.get(AssemblyLister.class.getSimpleName()).getName() ;
			final Class<? extends iExtruder> extruderClass = Reflection.type(extruderClassname).loadAs(iExtruder.class) ;
			final iExtruder lister = Reflection.constructor().in(extruderClass).newInstance() ;

			try ( FileWriter fileWriter = new FileWriter(actx.decodedArgs.get(CommandLineOption_List)); PrintWriter out = new PrintWriter(fileWriter); ) {
				lister.extrude(out, actx) ;
			}

			Console.info("") ;
			Console.info(actx.decodedArgs.get(CommandLineOption_List)) ;
			Console.info(FileUtils.readFileToString(new File(actx.decodedArgs.get(CommandLineOption_List)), Charset.defaultCharset())) ;
		}

		return this ;
	}


	@Log(Level.INFO)
	XA xref() throws IOException {

		actx.phase = XRef ;

		if ( actx.cmdLine.hasOption(CommandLineOption_XRef) ) {

			final String extruderClassname = extruders.get(XRefLister.class.getSimpleName()).getName() ;
			final Class<? extends iExtruder> extruderClass = Reflection.type(extruderClassname).loadAs(iExtruder.class) ;
			final iExtruder lister = Reflection.constructor().in(extruderClass).newInstance() ;

			try ( FileWriter fileWriter = new FileWriter(actx.decodedArgs.get(CommandLineOption_XRef)); PrintWriter out = new PrintWriter(fileWriter); ) {
				lister.extrude(out, actx) ;
			}

			Console.info("") ;
			Console.info(actx.decodedArgs.get(CommandLineOption_XRef)) ;
			Console.info(FileUtils.readFileToString(new File(actx.decodedArgs.get(CommandLineOption_XRef)), Charset.defaultCharset())) ;
		}

		return this ;
	}


	//
	//
	//

	public static void main(final String[] args) throws Exception {

		// Parse and process command line arguments ...

		final CommandLine cmdLine = parseCommandLine(args, CommandLineOptions) ;

		if ( cmdLine.hasOption(CommandLineOption_Help) )
			new HelpFormatter().printHelp("xa [options] file\n options:", CommandLineOptions) ;

		else if ( cmdLine.hasOption(CommandLineOption_Version) )
			Console.warn("v0.0.1") ;

		else {
			final XA $ = new XA() ;
			$.actx.cmdLine = cmdLine ;
			$.props = loadXAProperties() ;

			{
				final String path = $.props.getProperty("XA.processor.scan.classpath") ;
				$.processors = scanAndLoadByAnnotationType(path, aProcessor.class.getName(), $.processors) ;

				Logger.debug("processors: {}", $.processors) ;
			}

			{
				final String path = $.props.getProperty("XA.extruder.scan.classpath") ;
				$.extruders = scanAndLoadByAnnotationType(path, aExtruder.class.getName(), $.extruders) ;

				Logger.debug("extruders: {}", $.extruders) ;
			}

			{
				final String path = $.props.getProperty("XA.directive.scan.classpath") ;
				$.actx.directives = scanAndInstantiateByAnnotationType(path, aDirective.class.getName(), iDirective.class, $.actx, $.actx.directives) ;

				Logger.debug("directives: {}", $.actx.directives) ;
			}


			Map<String, aProcessor> srcFNExts = buildSourceFilenameExtensions($.processors) ;

			$.actx.decodedArgs = decodeCommandLine($.actx.cmdLine, srcFNExts, $.props, $.actx.decodedArgs) ;


			//

			Console.info("\nUsage ...") ;
			for ( final Entry<String, String> arg : $.actx.decodedArgs.entrySet() )
				Console.info("    -" + arg.getKey() + " " + StringUtils.trimToEmpty(arg.getValue())) ;

			$.init().parse().assemble().generate().extrude().list().xref() ;

			Console.info("Done.") ;
		}
	}


	static Map<String, aProcessor> buildSourceFilenameExtensions(Map<String, Class<Class<? extends iProcessor>>> processors) {

		Map<String, aProcessor> srcFNExts = new HashMap<>() ;

		for ( String id : processors.keySet() ) {
			aProcessor processor = (aProcessor) processors.get(id).getAnnotation(aProcessor.class) ;
			for ( String fnExt : processor.extensions() )
				srcFNExts.put(fnExt.toLowerCase(), processor) ;
		}

		Logger.warn("srcFNExts: {}", srcFNExts) ;


		return srcFNExts ;
	}


	static Properties loadXAProperties() throws IOException {

		final String rSpec = "/" + (XA.class.getName()).replace(".", "/") + ".properties" ;
		Properties props = Props.merge(XA.class, new Properties(), rSpec) ;

		Logger.debug("props @{}: {}", rSpec, props) ;


		return props ;
	}


	static <T> Map<String, T> scanAndInstantiateByAnnotationType(final String path, final String annotation, final Class<T> clazz, final AssemblyContext actx,
			Map<String, T> annotations) {

		Logger.info("Scanning classpath '{}.*' for {} ...", path, annotation) ;

		if ( annotations == null )
			annotations = new HashMap<>() ;

		try {
			@SuppressWarnings("unchecked")
			final Set<Class<?>> _annotations = new Reflections(path).getTypesAnnotatedWith((Class<? extends Annotation>) Class.forName(annotation)) ;
			Logger.info("found {} annotations in classpath: |{}|", _annotations.size(), _annotations) ;

			for ( final Class<?> _annotation : _annotations )
				for ( final Annotation a : _annotation.getAnnotations() )

					if ( a.annotationType().getName().equals(annotation) ) {

						final String _classname = _annotation.getName() ;
						final Class<?> _clazz = Reflection.type(_classname).loadAs(clazz) ;
						@SuppressWarnings("unchecked")
						final T _object = (T) Reflection.constructor().withParameterTypes(AssemblyContext.class).in(_clazz).newInstance(actx) ;

						annotations.put(_annotation.getSimpleName(), _object) ;
					}
		}
		catch ( IllegalArgumentException | SecurityException | ClassNotFoundException e ) {
			e.printStackTrace() ;
		}


		return annotations ;
	}


	@SuppressWarnings("unchecked")
	static <T> Map<String, T> scanAndLoadByAnnotationType(final String path, final String annotation, Map<String, T> annotations) {

		Logger.info("Scanning classpath '{}.*' for {} ...", path, annotation) ;

		if ( annotations == null )
			annotations = new HashMap<>() ;

		try {
			final Set<Class<?>> _annotations = new Reflections(path).getTypesAnnotatedWith((Class<? extends Annotation>) Class.forName(annotation)) ;
			Logger.info("found {} annotations in classpath: |{}|", _annotations.size(), _annotations) ;

			for ( final Class<?> _annotation : _annotations )
				for ( final Annotation a : _annotation.getAnnotations() )

					if ( a.annotationType().getName().equals(annotation) )
						annotations.put(_annotation.getSimpleName(), (T) _annotation) ;
		}
		catch ( IllegalArgumentException | SecurityException | ClassNotFoundException e ) {
			e.printStackTrace() ;
		}


		return annotations ;
	}


	//
	//
	//

	static CommandLine parseCommandLine(final String[] args, final Options options) throws ParseException {

		final String[] _args = Arrays.copyOf(args, args.length + 1) ;
		_args[(args.length > 0 ? args.length - 1 : 0)] = "" ;
		_args[args.length] = (args.length > 0 ? args[args.length - 1] : "") ;

		Logger.warn("_args: {}", Arrays.asList(_args)) ;

		return new DefaultParser().parse(options, _args) ;
	}


	static Map<String, String> decodeCommandLine(final CommandLine cmdLine, Map<String, aProcessor> srcFNExts, final Properties props,
			Map<String, String> decodedArgs) {

		if ( decodedArgs == null )
			decodedArgs = new HashMap<>() ;


		decodedArgs = decodeCommandLineArg_Processor(cmdLine, props, decodedArgs) ;

		decodedArgs = decodeCommandLineArg_Source(Lists.last(cmdLine.getArgList()), srcFNExts, cmdLine, props, decodedArgs) ;

		decodedArgs = decodeCommandLineArg_Binary(decodedArgs.get("source"), cmdLine, props, decodedArgs) ;

		decodedArgs = decodeCommandLineArg_Format(cmdLine, props, decodedArgs) ;

		decodedArgs = decodeCommandLineArg_List(decodedArgs.get("source"), cmdLine, props, decodedArgs) ;

		decodedArgs = decodeCommandLineArg_XRef(decodedArgs.get("source"), cmdLine, props, decodedArgs) ;


		Logger.debug("args: {}", Arrays.asList(decodedArgs)) ;

		return decodedArgs ;
	}


	static Map<String, String> decodeCommandLineArg_Binary(String srcFSpec, CommandLine cmdLine, Properties props, Map<String, String> decodedArgs) {

		if ( cmdLine.hasOption(CommandLineOption_Binary) ) {

			final String bin = StringUtils.trimToEmpty(cmdLine.getOptionValue(CommandLineOption_Binary)) ;

			if ( bin.isEmpty() ) {
				final String srcDSpec = FilenameUtils.getFullPath(srcFSpec) ;
				final String srcFNSpec = FilenameUtils.getName(srcFSpec) ;
				final String[] srcFNParts = srcFNSpec.split("\\.") ;

				decodedArgs.put(CommandLineOption_Binary, srcDSpec + srcFNParts[0] + ".bin") ;
			}
			else
				decodedArgs.put(CommandLineOption_Binary, bin) ;
		}


		return decodedArgs ;
	}


	static Map<String, String> decodeCommandLineArg_Format(CommandLine cmdLine, Properties props, Map<String, String> decodedArgs) {

		String format = StringUtils.trimToEmpty(cmdLine.getOptionValue(CommandLineOption_Format)) ;

		if ( format.isEmpty() )
			format = props.getProperty("XA.extruder.default") ;

		decodedArgs.put(CommandLineOption_Format, format) ;


		return decodedArgs ;
	}


	static Map<String, String> decodeCommandLineArg_List(String srcFSpec, CommandLine cmdLine, Properties props, Map<String, String> decodedArgs) {

		if ( cmdLine.hasOption(CommandLineOption_List) ) {

			final String list = StringUtils.trimToEmpty(cmdLine.getOptionValue(CommandLineOption_List)) ;

			if ( list.isEmpty() ) {
				final String srcDSpec = FilenameUtils.getFullPath(srcFSpec) ;
				final String srcFNSpec = FilenameUtils.getName(srcFSpec) ;
				final String[] srcFNParts = srcFNSpec.split("\\.") ;

				decodedArgs.put(CommandLineOption_List, srcDSpec + srcFNParts[0] + ".lis") ;
			}
			else
				decodedArgs.put(CommandLineOption_List, list) ;
		}


		return decodedArgs ;
	}


	static Map<String, String> decodeCommandLineArg_Processor(CommandLine cmdLine, Properties props, Map<String, String> decodedArgs) {

		String processor = StringUtils.trimToEmpty(cmdLine.getOptionValue(CommandLineOption_Processor)) ;

		decodedArgs.put(CommandLineOption_Processor, processor) ;


		return decodedArgs ;
	}


	static Map<String, String> decodeCommandLineArg_Source(String srcFSpec, Map<String, aProcessor> srcFNExts, CommandLine cmdLine, final Properties props,
			Map<String, String> decodedArgs) {


		if ( StringUtils.trimToEmpty(srcFSpec).isEmpty() )
			throw new IllegalArgumentException("Must specify source file for assembly.") ;

		if ( FilenameUtils.getName(srcFSpec).isEmpty() )
			throw new IllegalArgumentException("") ;

		if ( FilenameUtils.getBaseName(srcFSpec).isEmpty() )
			throw new IllegalArgumentException("") ;

		if ( FilenameUtils.getExtension(srcFSpec).isEmpty() )
			throw new IllegalArgumentException("") ;


		if ( !cmdLine.hasOption(CommandLineOption_Processor) ) {

			String srcFNExt = FilenameUtils.getExtension(srcFSpec) ;
			String processor = (srcFNExts.containsKey(srcFNExt.toLowerCase()) //
					? srcFNExts.get(srcFNExt.toLowerCase()).listener().getSimpleName() //
					: props.getProperty("XA.processor.default") //
			) ;

			decodedArgs.put(CommandLineOption_Processor, processor) ;
		}

		decodedArgs.put("source", srcFSpec) ;


		return decodedArgs ;
	}


	static Map<String, String> decodeCommandLineArg_XRef(String srcFSpec, CommandLine cmdLine, Properties props, Map<String, String> decodedArgs) {

		if ( cmdLine.hasOption(CommandLineOption_XRef) ) {
			final String list = StringUtils.trimToEmpty(cmdLine.getOptionValue(CommandLineOption_XRef)) ;

			if ( list.isEmpty() ) {
				final String srcDSpec = FilenameUtils.getFullPath(srcFSpec) ;
				final String srcFNSpec = FilenameUtils.getName(srcFSpec) ;
				final String[] srcFNParts = srcFNSpec.split("\\.") ;

				decodedArgs.put(CommandLineOption_XRef, srcDSpec + srcFNParts[0] + ".xref") ;
			}
			else
				decodedArgs.put(CommandLineOption_XRef, list) ;
		}


		return decodedArgs ;
	}
}
