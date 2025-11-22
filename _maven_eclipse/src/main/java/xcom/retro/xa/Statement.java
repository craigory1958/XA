

package xcom.retro.xa ;


import java.util.LinkedHashMap ;
import java.util.Map ;

import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.utils4j.logging.aspects.api.annotations.Log ;


public class Statement {

	//@formatter:off

	boolean assemblyEnable ;
	public boolean assemblyEnable() { return assemblyEnable ; }

	Map<String, Operand> operands ;
	public Map<String, Operand> operands() { return operands ; }

	String assemblyCallbackMethod ;
	public Statement assemblyCallbackMethod(final String assemblyCallbackMethod) {
		this.assemblyCallbackMethod = assemblyCallbackMethod ;
		return this ;
	}

	Object assemblyCallbackObject ;
	public Statement assemblyCallbackObject(final Object assemblyCallbackObject) {
		this.assemblyCallbackObject = assemblyCallbackObject ;
		return this ;
	}

	ByteBlock block ;

	byte[] bytes ;
	public byte[] bytes() { return bytes ; }
	public Statement bytes(final byte[] bytes) {
		this.bytes = bytes ;
		return this ;
	}

	Moniker label ;
	public Moniker label() { return label ; }
	public Statement label(final Moniker label) {
		this.label = label ;
		return this ;
	}

	String line ;
	public String line() { return line ; }

	boolean list ;
	public boolean list() { return list ; }
	public Statement list(final boolean list) {
		this.list = list ;
		return this ;
	}

	int ln ;
	public int ln() { return ln ; }
	public Statement ln(final int ln) {
		this.ln = ln ;
		return this ;
	}

	int sourceLN ;
	public int sourceLN() { return sourceLN ; }
	public Statement sourceLN(final int sourceLN) {
		this.sourceLN = sourceLN ;
		return this ;
	}

	int loc ;
	public int loc() { return loc ; }

	Map<String, Parameter> parameters ;
	public Map<String, Parameter> parameters() { return parameters ; }

	ParserRuleContext pctx ;
	public ParserRuleContext pctx() { return pctx ; }
	public Statement pctx(final ParserRuleContext pctx) {
		this.pctx = pctx ;
		return this ;
	}

	Integer sourceID ;
	public Integer sourceID() { return sourceID ; }

	//@formatter:on


	@Log
	public Statement(final int sourceID, final int sourceLN, final String line, final int lc, final boolean list, final boolean assembleEnable) {

		this.assemblyEnable = assembleEnable ;
		loc = lc ;
		this.line = line ;
		this.list = list ;
		this.sourceLN = sourceLN ;
		this.sourceID = sourceID ;

		operands = new LinkedHashMap<>() ;
		parameters = new LinkedHashMap<>() ;
	}
}
