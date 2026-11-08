

package xcom.retro.xa ;


import java.util.ArrayList ;
import java.util.HashMap ;
import java.util.List ;
import java.util.Map ;
import java.util.Stack ;

import org.antlr.v4.runtime.Lexer ;
import org.antlr.v4.runtime.Parser ;
import org.apache.commons.cli.CommandLine ;

import xcom.retro.xa.XA.AssemblyPhases ;
import xcom.retro.xa.api.interfaces.iDirective ;
import xcom.retro.xa.api.interfaces.iSource ;
import xcom.retro.xa.directives.dir.MACRO ;
import xcom.retro.xa.directives.dir.STRUCT ;


public class AssemblyContext {

	//
	// Attributes ...
	//

	//@formatter:off

	XA$Args $args ;
	public XA$Args $args() { return $args ; }

	/**
	 * Track block is assembled
	 */
	Stack<Boolean> assemble ;
	public Stack<Boolean> assemble() { return assemble ; }

	CommandLine cmd ;

	Map<String, Object> decodedArgs ;
	public Map<String, Object> decodedArgs() { return decodedArgs ; }

	Map<String, iDirective> directives ;
	public Map<String, iDirective> directives() { return directives ; }

	int fn ;
	public int fn() { return fn ; }
	public AssemblyContext fn(final int fn) {
		this.fn = fn ;
		return this ;
	}


	Map<String, Identifier> identifiers = new HashMap<>() {};
	public Map<String, Identifier> identifiers() { return identifiers ; }

	Lexer lexer ;
	public Lexer lexer() { return lexer ; }

	/**
	 * Track if block is listed
	 */
	boolean list ;
	public boolean list() { return list ; }
	public AssemblyContext list(final boolean list) {
		this.list = list ;
		return this ;
	}

	Map<String, MACRO> macros = new HashMap<>() ;
	public Map<String, MACRO> macros() { return macros ; }

	Parser parser ;
	public Parser parser() { return parser ; }

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

	int sn ;
	public int sn() { return sn ; }

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

	//@formatter:on


	//
	// Constructor(s) ...
	//

	public AssemblyContext() {

		list = true ;
		assemble = new Stack<>() ;
		assemble.push(true) ;

		sn = 0 ;
	}


	//
	//
	//

	public boolean assemblyEnable() {
		return assemble.peek() ;
	}
}
