

package xcom.retro.xa ;


import java.util.LinkedHashMap ;
import java.util.Map ;

import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.utils4j.logging.aspects.api.annotations.Log ;


public class Statement {

	//@formatter:off

	/**
	 * Designates if statement is assembled.
	 */
	boolean assemblyEnable ;
	public boolean assemblyEnable() { return assemblyEnable ; }

	/**
	 * Any operands included in the statement.
	 */
	Map<String, Operand> operands ;
	public Map<String, Operand> operands() { return operands ; }

	/**
	 * Callback method used to assemble statement opcode.
	 */
	String assemblyCallbackMethod ;
	public Statement assemblyCallbackMethod(final String assemblyCallbackMethod) {
		this.assemblyCallbackMethod = assemblyCallbackMethod ;
		return this ;
	}

	/**
	 * Callback object passed to Callback Method.
	 */
	Object assemblyCallbackObject ;
	public Statement assemblyCallbackObject(final Object assemblyCallbackObject) {
		this.assemblyCallbackObject = assemblyCallbackObject ;
		return this ;
	}

	/**
	 * 
	 */
	ByteBlock block ;
	public ByteBlock block() { return block ; }

	/**
	 * Assembled statement bytes.
	 */
	byte[] bytes ;
	public byte[] bytes() { return bytes ; }
	public Statement bytes(final byte[] bytes) {
		this.bytes = bytes ;
		return this ;
	}

	/**
	 * Source file number of original statement line.
	 */
	Integer fn ;
	public Integer fn() { return fn ; }

	/**
	 * 
	 */
	Identifier label ;
	public Identifier label() { return label ; }
	public Statement label(final Identifier label) {
		this.label = label ;
		return this ;
	}

	/**
	 * Original statement line text.
	 */
	String line ;
	public String line() { return line ; }

	/**
	 * Designates if statement is included in listings.
	 */
	boolean list ;
	public boolean list() { return list ; }
	public Statement list(final boolean list) {
		this.list = list ;
		return this ;
	}

	/**
	 * Source line number of original statement line.
	 */
	int ln ;
	public int ln() { return ln ; }
	public Statement ln(final int ln) {
		this.ln = ln ;
		return this ;
	}

	/**
	 * Memory location of assembled statement.
	 */
	int loc ;
	public int loc() { return loc ; }

	/**
	 * 
	 */
	Map<String, Parameter> parameters ;
	public Map<String, Parameter> parameters() { return parameters ; }

	/**
	 * 
	 */
	ParserRuleContext pctx ;
	public ParserRuleContext pctx() { return pctx ; }
	public Statement pctx(final ParserRuleContext pctx) {
		this.pctx = pctx ;
		return this ;
	}

	/**
	 * Ordinal number of the assembled statement.
	 */
	int sn ;
	public int sn() { return sn ; }
	public Statement sn(final int sn) {
		this.sn = sn ;
		return this ;
	}

	//@formatter:on


	@Log
	public Statement(final int fn, final int ln, final String line, final int loc, final boolean list, final boolean assembleEnable) {

		assemblyEnable = assembleEnable ;
		this.loc = loc ;
		this.line = line ;
		this.list = list ;
		this.ln = ln ;
		this.fn = fn ;

		operands = new LinkedHashMap<>() ;
		parameters = new LinkedHashMap<>() ;
	}


	//
	//
	//

	@Override
	public String toString() {
		return String.format("ln:%d, ln:%d, loc:%d", sn, ln, loc) ;
	}
}
