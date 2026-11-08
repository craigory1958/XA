

package xcom.retro.xa ;


import static xcom.retro.xa.XA$Args.$Arg_Binary ;
import static xcom.retro.xa.XA$Args.$Arg_Format ;
import static xcom.retro.xa.XA$Args.$Arg_List ;
import static xcom.retro.xa.XA$Args.$Arg_Processor ;
import static xcom.retro.xa.XA$Args.$Arg_XRef ;
import static xcom.retro.xa.XA$Args.CommandLineOptions ;
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
import java.util.Arrays ;
import java.util.HashMap ;
import java.util.Map ;
import java.util.Properties ;
import java.util.Set ;

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
import org.apache.commons.lang3.StringUtils ;
import org.fest.reflect.core.Reflection ;
import org.reflections.Reflections ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import org.slf4j.spi.LoggingEventBuilder ;

import xcom.retro.xa.api.annotations.aDirective ;
import xcom.retro.xa.api.annotations.aExtruder ;
import xcom.retro.xa.api.annotations.aProcessor ;
import xcom.retro.xa.api.interfaces.iDirective ;
import xcom.retro.xa.api.interfaces.iExtruder ;
import xcom.retro.xa.api.interfaces.iProcessor ;
import xcom.retro.xa.assembly.AssemblyUtils ;
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


	private static final Logger Console = LoggerFactory.getLogger(ConsoleLoggerName) ;

	static final String AppClassname = XA.class.getName() ;
	static final String AppDesc = XA.class.getSimpleName() + " - 6502 Cross Assembler" ;
	static final String AppName = XA.class.getSimpleName() ;
	static final String AppSee = "See https://github.com/craigory1958/XA" ;
	static final String AppUsage = "java -cp " + XA.class.getSimpleName().toLowerCase() + ".jar" + XA.class.getName() + " [options] src" ;
	static final String AppVersion = "v1.0.0" ;


	Properties props ;
	AssemblyContext actx ;

	Map<String, Class<Class<? extends iExtruder>>> extruders ;
	Map<String, Class<Class<? extends iProcessor>>> processors ;
	ParseTreeListener processor ;


	public XA() {
		actx = new AssemblyContext() ;
	}


	public XA(CommandLine cmd, XA$Args $args, Map<String, Object> decoded, Properties props) {
		
		actx = new AssemblyContext() ;
		
		actx.cmd = cmd ;
		actx.$args = $args ;
		actx.decodedArgs = decoded ;
		this.props = props ;
	}


	XA init() throws Exception {

		actx.phase = Init ;

		{
			final aProcessor processor = processors.get(actx.decodedArgs.get($Arg_Processor)).getAnnotation(aProcessor.class) ;

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
		logEvent(actx.$args(), actx.phase(), Parse.name(), "\nBeginning {} phase ...\n", Parse.name()) ;

		actx.segments.put("<default>", new Segment("<default>")) ;
		actx.segment = actx.segments.get("<default>") ;

		actx.sources.add(new FileSource(actx.sources.size(), (String) actx.decodedArgs.get("source"), actx.list(), actx.segments.get("<default>"))) ;
		actx.source.push(Lists.last(actx.sources)) ;
		final ParseTreeWalker walker = new ParseTreeWalker() ;

		while ( !actx.source.isEmpty() ) {

			for ( String line; (line = actx.source.peek().next()) != null; ) {

				actx.list(actx.source.peek().list()) ;

				line = line.stripTrailing() ;
				logEvent(actx.$args(), actx.phase(), "src", ">>>{}", line) ;
				logEvent(actx.$args(), actx.phase(), "srcs", "(srcs) {}: sources: {}", this.getClass(), actx.sources()) ;
				logEvent(actx.$args(), actx.phase(), "srcs", "(srcs) {}: source: {}", this.getClass(), actx.source()) ;


				if ( actx.list() )
					actx.sn += 1 ;

				actx.statements.add( //
						new Statement( //
								actx.source.peek().fn(), //
								actx.source.peek().ln(), //
								line, //
								actx.segment.loc, //
								actx.list(), //
								actx.assemblyEnable() //
						)) ;

				actx.statement = Lists.last(actx.statements) ;
				actx.statement.sn(actx.sn) ;

				logEvent(actx.$args(), actx.phase(), "srcs", "(srcs) {}: statement: {}", this.getClass(), actx.statement) ;

				if ( !StringUtils.trimToEmpty(line).isEmpty() && !StringUtils.trimToEmpty(line).startsWith(";") ) {
					logEvent(actx.$args(), actx.phase(), "ids", "(ids) {}: identifiers: {}", this.getClass(), actx.identifiers()) ;
					logEvent(actx.$args(), actx.phase(), "MACRO", "(MACRO) {}: macros: {}", this.getClass(), actx.macros()) ;
				}


				actx.statement.pctx = AssemblyUtils.ASMB_parseStatement(line, actx.lexer, actx.parser) ;

				logEvent(actx.$args(), actx.phase(), "asmb", "(asmb) {}: parse statement w/{})", this.getClass(), processor.getClass().getSimpleName()) ;
				walker.walk(processor, actx.statement.pctx) ;

				if ( actx.statement.assemblyEnable() )
					actx.statement.block = Lists.last(actx.segment.blocks) ;

				if ( actx.statement.bytes() != null )
					logEvent(actx.$args(), actx.phase(), "bytes", "(bytes) {}: bytes: {}", this.getClass(), actx.statement.bytes()) ;
			}

			if ( actx.source.peek().segment() != null )
				actx.segment(actx.source.peek().segment()) ;

			actx.source.pop() ;
		}

		logEvent(actx.$args(), actx.phase(), "ids", "(ids) {}: identifiers: {}", this.getClass(), actx.identifiers()) ;
		logEvent(actx.$args(), actx.phase(), "MACRO", "(MACRO) {}: macros: {}", this.getClass(), actx.macros()) ;


		return this ;
	}


	XA assemble() { // Pass 2

		actx.phase = Assemble ;
		logEvent(actx.$args(), actx.phase(), Assemble.name(), "\nBeginning {} phase ...\n", Assemble.name()) ;
		logEvent(actx.$args(), actx.phase(), "ids", "(ids) {}: identifiers: {}", this.getClass(), actx.identifiers()) ;
		logEvent(actx.$args(), actx.phase(), "MACRO", "(MACRO) {}: macros: {}", this.getClass(), actx.macros()) ;

		for ( final Statement statement : actx.statements ) {

			actx.statement = statement ;
			logEvent(actx.$args(), actx.phase(), "src", ">>>{}", actx.statement.line) ;

			if ( actx.statement.assemblyCallbackMethod != null )
				Reflection.method(actx.statement.assemblyCallbackMethod).in(actx.statement.assemblyCallbackObject).invoke() ;

			if ( actx.statement.bytes() != null )
				logEvent(actx.$args(), actx.phase(), "bytes", "(bytes) {}: bytes: {}", this.getClass(), actx.statement.bytes()) ;
		}

		return this ;
	}


	XA generate() throws IOException {

		actx.phase = Generate ;
		logEvent(actx.$args(), actx.phase(), Generate.name(), "\nBeginning {} phase ...\n", Generate.name()) ;
		logEvent(actx.$args(), actx.phase(), "gen", "(gen) {}: identifiers: {}", this.getClass(), actx.identifiers) ;

		if ( actx.cmd.hasOption($Arg_Binary) )
			for ( final Statement statement : actx.statements ) {
				actx.statement = statement ;
				logEvent(actx.$args(), actx.phase(), "src", ">>>{}", actx.statement.line) ;

				if ( actx.statement.assemblyEnable() && (actx.statement.block != null) && (actx.statement.bytes != null) && (actx.statement.bytes.length > 0) )
					actx.statement.block.fillBytes(actx.statement.loc, actx.statement.bytes) ;
			}


		return this ;
	}


	XA extrude() throws IOException {

		actx.phase = Extrude ;
		logEvent(actx.$args(), actx.phase(), Extrude.name(), "\nBeginning {} phase ...\n", Extrude.name()) ;

		if ( actx.cmd.hasOption($Arg_Binary) ) {

			final String extruderClassname = extruders.get(actx.decodedArgs.get($Arg_Format)).getName() ;
			final Class<? extends iExtruder> extruderClass = Reflection.type(extruderClassname).loadAs(iExtruder.class) ;
			final iExtruder extruder = Reflection.constructor().in(extruderClass).newInstance() ;

			try ( FileWriter fileWriter = new FileWriter((String) actx.decodedArgs.get($Arg_Binary)); PrintWriter out = new PrintWriter(fileWriter); ) {
				extruder.extrude(out, actx) ;
			}

			Console.info("") ;
			Console.info((String) actx.decodedArgs.get($Arg_Binary)) ;
			Console.info(FileUtils.readFileToString(new File((String) actx.decodedArgs.get($Arg_Binary)), Charset.defaultCharset())) ;
		}


		return this ;
	}


	XA list() throws IOException {

		actx.phase = List ;
		logEvent(actx.$args(), actx.phase(), List.name(), "\nBeginning {} phase ...\n", List.name()) ;

		if ( actx.cmd.hasOption($Arg_List) ) {

			final String extruderClassname = extruders.get(AssemblyLister.class.getSimpleName()).getName() ;
			final Class<? extends iExtruder> extruderClass = Reflection.type(extruderClassname).loadAs(iExtruder.class) ;
			final iExtruder lister = Reflection.constructor().in(extruderClass).newInstance() ;

			try ( FileWriter fileWriter = new FileWriter((String) actx.decodedArgs.get($Arg_List)); PrintWriter out = new PrintWriter(fileWriter); ) {
				lister.extrude(out, actx) ;
			}

			Console.info("") ;
			Console.info((String) actx.decodedArgs.get($Arg_List)) ;
			Console.info(FileUtils.readFileToString(new File((String) actx.decodedArgs.get($Arg_List)), Charset.defaultCharset())) ;
		}


		return this ;
	}


	XA xref() throws IOException {

		actx.phase = XRef ;
		logEvent(actx.$args(), actx.phase(), XRef.name(), "\nBeginning {} phase ...\n", XRef.name()) ;

		if ( actx.cmd.hasOption($Arg_XRef) ) {

			final String extruderClassname = extruders.get(XRefLister.class.getSimpleName()).getName() ;
			final Class<? extends iExtruder> extruderClass = Reflection.type(extruderClassname).loadAs(iExtruder.class) ;
			final iExtruder lister = Reflection.constructor().in(extruderClass).newInstance() ;

			try ( FileWriter fileWriter = new FileWriter((String) actx.decodedArgs.get($Arg_XRef)); PrintWriter out = new PrintWriter(fileWriter); ) {
				lister.extrude(out, actx) ;
			}

			Console.info("") ;
			Console.info((String) actx.decodedArgs.get($Arg_XRef)) ;
			Console.info(FileUtils.readFileToString(new File((String) actx.decodedArgs.get($Arg_XRef)), Charset.defaultCharset())) ;
		}


		return this ;
	}


	//
	//
	//

	public static void main(final String[] args) throws Exception {

		// Load properties ...

		final Properties props = Props.load(XA.class, AppName + ".properties") ;


		// Parse and decode base command line arguments ...

		final String[] _args = Arrays.copyOf(args, args.length + 1) ;
		_args[(args.length > 0 ? args.length - 1 : 0)] = "" ;
		_args[args.length] = (args.length > 0 ? args[args.length - 1] : "") ;

		final CommandLine cmd = new DefaultParser().parse(CommandLineOptions, _args) ;

		final XA$Args $args = new XA$Args(cmd, props, _args, Console) ;
		$args.loadAppProps(props, AppClassname, AppDesc, AppName, AppSee, AppUsage, AppVersion) ;

		final Map<String, Object> decoded = $args.decodeCommandLineArgs(cmd, CommandLineOptions, props, ExitOnHelpOrVersion, $args.decodedArgs()) ;

		logEvent($args, Init, "init", "(init) {}: props: {}", XA.class, props) ;
		logEvent($args, Init, "init", "(init) {}: args: {}", XA.class, Arrays.asList(_args)) ;
		logEvent($args, Init, "init", "(init) {}: decodedArgs: {}", XA.class, decoded) ;


		// Instantiate and process ...

		final XA $ = new XA(cmd, $args, decoded, props) ;

		{
			final String path = $.props.getProperty("XA.processor.scan.classpath") ;
			$.processors = scanAndLoadByAnnotationType(path, aProcessor.class.getName(), $.processors, ASMB_getEventLogger($.actx.$args(), "init")) ;

			logEvent($.actx.$args(), Init, "init", "(init) {}: processors: {}", XA.class, $.processors) ;
		}

		{
			final String path = $.props.getProperty("XA.extruder.scan.classpath") ;
			$.extruders = scanAndLoadByAnnotationType(path, aExtruder.class.getName(), $.extruders, ASMB_getEventLogger($.actx.$args(), "init")) ;

			logEvent($.actx.$args(), Init, "init", "(init) {}: extruders: {}", XA.class, $.extruders) ;
		}

		{
			final String path = $.props.getProperty("XA.directive.scan.classpath") ;
			$.actx.directives = scanAndInstantiateByAnnotationType(path, aDirective.class.getName(), iDirective.class, $.actx.directives, $.actx,
					ASMB_getEventLogger($.actx.$args(), "init")) ;

			logEvent($.actx.$args(), Init, "init", "(init) {}: directives: {}", XA.class, $.actx.directives) ;
		}


		final Map<String, aProcessor> srcFNExts = buildSourceFilenameExtensions($.processors) ;
		logEvent($.actx.$args(), Init, "init", "(init) {}: srcFNExts: {}", XA.class, srcFNExts) ;


		// Decode command line arguments ...

		$.actx.decodedArgs = decodeCommandLine($.actx.cmd, srcFNExts, $.props, $.actx.decodedArgs) ;
		logEvent($.actx.$args(), Init, "init", "(init) {}: decodedArgs: {}", XA.class, $.actx.decodedArgs) ;


		CLArgs.printToolUsage(AppUsage, AppDesc, $.actx.decodedArgs(), Console) ;


		//

		$.init().parse().assemble().generate().extrude().list().xref() ;

		Console.info("Done.") ;
	}


	static Map<String, aProcessor> buildSourceFilenameExtensions(final Map<String, Class<Class<? extends iProcessor>>> processors) {

		final Map<String, aProcessor> srcFNExts = new HashMap<>() ;

		for ( final String id : processors.keySet() ) {
			final aProcessor processor = processors.get(id).getAnnotation(aProcessor.class) ;
			for ( final String fnExt : processor.extensions() )
				srcFNExts.put(fnExt.toLowerCase(), processor) ;
		}


		return srcFNExts ;
	}


	@SuppressWarnings("unchecked")
	static <T> Map<String, T> scanAndInstantiateByAnnotationType(final String path, final String annotation, final Class<T> clazz, Map<String, T> annotations,
			final AssemblyContext actx, final LoggingEventBuilder logger) {

		logger.log("Scanning classpath '{}.*' for {} ...", path, annotation) ;

		if ( annotations == null )
			annotations = new HashMap<>() ;

		try {
			final Set<Class<?>> _annotations = new Reflections(path).getTypesAnnotatedWith((Class<? extends Annotation>) Class.forName(annotation)) ;
			logger.log("found {} annotations in classpath: |{}|", _annotations.size(), _annotations) ;

			for ( final Class<?> _annotation : _annotations )
				for ( final Annotation a : _annotation.getAnnotations() )

					if ( a.annotationType().getName().equals(annotation) ) {

						final String _classname = _annotation.getName() ;
						final Class<?> _clazz = Reflection.type(_classname).loadAs(clazz) ;
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
	static <T> Map<String, T> scanAndLoadByAnnotationType(final String path, final String annotation, Map<String, T> annotations,
			final LoggingEventBuilder logger) {

		logger.log("Scanning classpath '{}.*' for {} ...", path, annotation) ;

		if ( annotations == null )
			annotations = new HashMap<>() ;

		try {
			final Set<Class<?>> _annotations = new Reflections(path).getTypesAnnotatedWith((Class<? extends Annotation>) Class.forName(annotation)) ;
			logger.log("found {} annotations in classpath: |{}|", _annotations.size(), _annotations) ;

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


	public static LoggingEventBuilder ASMB_getEventLogger(final XA$Args $args, final String event) {
		return $args.getEventLogger(event) ;
	}


	public static void logEvent(final XA$Args $args, final AssemblyPhases phase, final String event, final String format, final Object... args) {

		if ( $args.eventLoggers().containsKey(phase.name()) )
			$args.getEventLogger(event).log(format, args) ;
	}


	public static void logEvent(final XA$Args $args, final AssemblyPhases phase, final String event, final String format, final Class<?> clazz,
			final Object... args) {

		if ( $args.eventLoggers().containsKey(phase.name()) ) {

			final Object[] _args = new Object[args.length + 1] ;
			System.arraycopy(args, 0, _args, 1, args.length) ;
			_args[0] = clazz.getSimpleName() ;

			$args.getEventLogger(event).log(format, _args) ;
		}
	}
}
