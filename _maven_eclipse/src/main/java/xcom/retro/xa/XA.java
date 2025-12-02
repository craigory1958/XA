

package xcom.retro.xa ;


import static xcom.retro.xa.XA$Args.CommandLineOptions ;
import static xcom.retro.xa.XA$Args.XA$Arg_Binary ;
import static xcom.retro.xa.XA$Args.XA$Arg_Format ;
import static xcom.retro.xa.XA$Args.XA$Arg_List ;
import static xcom.retro.xa.XA$Args.XA$Arg_Processor ;
import static xcom.retro.xa.XA$Args.XA$Arg_XRef ;
import static xcom.retro.xa.XA$Args.decodeCommandLine ;
import static xcom.retro.xa.XA.AssemblyPhases.Assemble ;
import static xcom.retro.xa.XA.AssemblyPhases.Extrude ;
import static xcom.retro.xa.XA.AssemblyPhases.Generate ;
import static xcom.retro.xa.XA.AssemblyPhases.Init ;
import static xcom.retro.xa.XA.AssemblyPhases.List ;
import static xcom.retro.xa.XA.AssemblyPhases.Parse ;
import static xcom.retro.xa.XA.AssemblyPhases.XRef ;
import static xcom.utils4j.CLArgs.ExitPolicyTypes.ExitOnHelpOrVersion ;
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
import org.apache.commons.io.FileUtils ;
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
import xcom.utils4j.CLArgs ;
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

		CommandLine cmd ;

		Map<String, Object> decodedArgs ;
		public Map<String, Object> decodedArgs() { return decodedArgs ; }

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

		XA$Args $args ;
		public XA$Args $args() { return $args ; }

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

	static final String AppClassname = XA.class.getName() ;
	static final String AppDesc = XA.class.getSimpleName() + " - 6502 Cross Assembler" ;
	static final String AppName = XA.class.getSimpleName() ;
	static final String AppSee = "See https://github.com/craigory1958/XA" ;
	static final String AppUsage = "java -cp " + XA.class.getSimpleName().toLowerCase() + ".jar" + XA.class.getName() + "[options] src" ;
	static final String AppVersion = "v1.0.0" ;


	Properties props ;
	AssemblyContext actx ;

	Map<String, Class<Class<? extends iExtruder>>> extruders ;
	Map<String, Class<Class<? extends iProcessor>>> processors ;
	ParseTreeListener processor ;


	public XA() {
		actx = new AssemblyContext() ;
	}


	XA init() throws Exception {

		actx.phase = Init ;

		{
			aProcessor processor = (aProcessor) processors.get(actx.decodedArgs.get(XA$Arg_Processor)).getAnnotation(aProcessor.class) ;

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

	XA parse() throws IOException {  // Pass 1

		actx.phase = Parse ;

		actx.sources.add(new FileSource(actx.sources.size(), (String) actx.decodedArgs.get("source"), actx.list())) ;
		actx.source.push(Lists.last(actx.sources)) ;

		actx.segments.put("<default>", new Segment("<default>")) ;
		actx.segment = actx.segments.get("<default>") ;
		final ParseTreeWalker walker = new ParseTreeWalker() ;

		while ( !actx.source.isEmpty() ) {

			actx.list(actx.source.peek().list()) ;

			for ( String line; (line = actx.source.peek().next()) != null; ) {

				line = line.stripTrailing() ;
				actx.$args().getEventLogger("src").log(">>>{}", line) ;


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


	XA extrude() throws IOException {

		actx.phase = Extrude ;

		if ( actx.cmd.hasOption(XA$Arg_Binary) ) {

			final String extruderClassname = extruders.get(actx.decodedArgs.get(XA$Arg_Format)).getName() ;
			final Class<? extends iExtruder> extruderClass = Reflection.type(extruderClassname).loadAs(iExtruder.class) ;
			final iExtruder extruder = Reflection.constructor().in(extruderClass).newInstance() ;

			try ( FileWriter fileWriter = new FileWriter((String) actx.decodedArgs.get(XA$Arg_Binary)); PrintWriter out = new PrintWriter(fileWriter); ) {
				extruder.extrude(out, actx) ;
			}
		}

		Console.info("") ;
		Console.info((String) actx.decodedArgs.get(XA$Arg_Binary)) ;
		Console.info(FileUtils.readFileToString(new File((String) actx.decodedArgs.get(XA$Arg_Binary)), Charset.defaultCharset())) ;

		
		return this ;
	}


	XA list() throws IOException {

		actx.phase = List ;

		if ( actx.cmd.hasOption(XA$Arg_List) ) {

			final String extruderClassname = extruders.get(AssemblyLister.class.getSimpleName()).getName() ;
			final Class<? extends iExtruder> extruderClass = Reflection.type(extruderClassname).loadAs(iExtruder.class) ;
			final iExtruder lister = Reflection.constructor().in(extruderClass).newInstance() ;

			try ( FileWriter fileWriter = new FileWriter((String) actx.decodedArgs.get(XA$Arg_List)); PrintWriter out = new PrintWriter(fileWriter); ) {
				lister.extrude(out, actx) ;
			}

			Console.info("") ;
			Console.info((String) actx.decodedArgs.get(XA$Arg_List)) ;
			Console.info(FileUtils.readFileToString(new File((String) actx.decodedArgs.get(XA$Arg_List)), Charset.defaultCharset())) ;
		}

		
		return this ;
	}


	XA xref() throws IOException {

		actx.phase = XRef ;

		if ( actx.cmd.hasOption(XA$Arg_XRef) ) {

			final String extruderClassname = extruders.get(XRefLister.class.getSimpleName()).getName() ;
			final Class<? extends iExtruder> extruderClass = Reflection.type(extruderClassname).loadAs(iExtruder.class) ;
			final iExtruder lister = Reflection.constructor().in(extruderClass).newInstance() ;

			try ( FileWriter fileWriter = new FileWriter((String) actx.decodedArgs.get(XA$Arg_XRef)); PrintWriter out = new PrintWriter(fileWriter); ) {
				lister.extrude(out, actx) ;
			}

			Console.info("") ;
			Console.info((String) actx.decodedArgs.get(XA$Arg_XRef)) ;
			Console.info(FileUtils.readFileToString(new File((String) actx.decodedArgs.get(XA$Arg_XRef)), Charset.defaultCharset())) ;
		}

		
		return this ;
	}


	//
	//
	//

	public static void main(final String[] args) throws Exception {

		// Load properties ...

		Properties props = Props.load(XA.class, AppName + ".properties") ;


		// Parse and decode base command line arguments ...

		final String[] _args = Arrays.copyOf(args, args.length + 1) ;
		_args[(args.length > 0 ? args.length - 1 : 0)] = "" ;
		_args[args.length] = (args.length > 0 ? args[args.length - 1] : "") ;

		final CommandLine cmd = new DefaultParser().parse(CommandLineOptions, _args) ;

		XA$Args $args = new XA$Args(cmd, props, _args, Console) ;
		$args.loadAppProps(props, AppClassname, AppDesc, AppName, AppSee, AppUsage, AppVersion) ;

		Map<String, Object> decoded = $args.decodeCommandLineArgs(cmd, CommandLineOptions, props, ExitOnHelpOrVersion, $args.decodedArgs()) ;

		$args.getEventLogger("init").log("props: {}", props) ;
		$args.getEventLogger("init").log("args: {}", Arrays.asList(_args)) ;
		$args.getEventLogger("init").log("decodedArgs: {}", decoded) ;


		// Instantiate and process ...

		final XA $ = new XA() ;
		$.actx.cmd = cmd ;
		$.actx.$args = $args ;
		$.props = props ;

		{
			final String path = $.props.getProperty("XA.processor.scan.classpath") ;
			$.processors = scanAndLoadByAnnotationType(path, aProcessor.class.getName(), $.processors, $.actx) ;

			$args.getEventLogger("init").log("processors: {}", $.processors) ;
		}

		{
			final String path = $.props.getProperty("XA.extruder.scan.classpath") ;
			$.extruders = scanAndLoadByAnnotationType(path, aExtruder.class.getName(), $.extruders, $.actx) ;

			$args.getEventLogger("init").log("extruders: {}", $.extruders) ;
		}

		{
			final String path = $.props.getProperty("XA.directive.scan.classpath") ;
			$.actx.directives = scanAndInstantiateByAnnotationType(path, aDirective.class.getName(), iDirective.class, $.actx.directives, $.actx) ;

			$args.getEventLogger("init").log("directives: {}", $.actx.directives) ;
		}


		Map<String, aProcessor> srcFNExts = buildSourceFilenameExtensions($.processors) ;
		$args.getEventLogger("init").log("srcFNExts: {}", srcFNExts) ;


		// Decode command line arguments ...

		$.actx.decodedArgs = decodeCommandLine($.actx.cmd, srcFNExts, $.props, decoded) ;
		$args.getEventLogger("init").log("decodedArgs: {}", $.actx.decodedArgs) ;


		XA$Args.printToolUsage(AppUsage, AppDesc, $.actx.decodedArgs(), Console) ;


		//

		$.init().parse().assemble().generate().extrude().list().xref() ;

		Console.info("Done.") ;
	}


	static Map<String, aProcessor> buildSourceFilenameExtensions(Map<String, Class<Class<? extends iProcessor>>> processors) {

		Map<String, aProcessor> srcFNExts = new HashMap<>() ;

		for ( String id : processors.keySet() ) {
			aProcessor processor = (aProcessor) processors.get(id).getAnnotation(aProcessor.class) ;
			for ( String fnExt : processor.extensions() )
				srcFNExts.put(fnExt.toLowerCase(), processor) ;
		}


		return srcFNExts ;
	}


	static <T> Map<String, T> scanAndInstantiateByAnnotationType(final String path, final String annotation, final Class<T> clazz,
			Map<String, T> annotations, final AssemblyContext actx) {

		actx.$args.getEventLogger("init").log("Scanning classpath '{}.*' for {} ...", path, annotation) ;

		if ( annotations == null )
			annotations = new HashMap<>() ;

		try {
			@SuppressWarnings("unchecked")
			final Set<Class<?>> _annotations = new Reflections(path).getTypesAnnotatedWith((Class<? extends Annotation>) Class.forName(annotation)) ;
			actx.$args.getEventLogger("init").log("found {} annotations in classpath: |{}|", _annotations.size(), _annotations) ;

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
	static <T> Map<String, T> scanAndLoadByAnnotationType(final String path, final String annotation, Map<String, T> annotations, final AssemblyContext actx) {

		actx.$args.getEventLogger("init").log("Scanning classpath '{}.*' for {} ...", path, annotation) ;

		if ( annotations == null )
			annotations = new HashMap<>() ;

		try {
			final Set<Class<?>> _annotations = new Reflections(path).getTypesAnnotatedWith((Class<? extends Annotation>) Class.forName(annotation)) ;
			actx.$args.getEventLogger("init").log("found {} annotations in classpath: |{}|", _annotations.size(), _annotations) ;

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
}
