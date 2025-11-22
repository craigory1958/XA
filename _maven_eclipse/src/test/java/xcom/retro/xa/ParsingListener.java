

package xcom.retro.xa ;


import java.util.List ;

import org.antlr.v4.runtime.ParserRuleContext ;
import org.antlr.v4.runtime.tree.ErrorNode ;
import org.antlr.v4.runtime.tree.ParseTreeListener ;
import org.antlr.v4.runtime.tree.TerminalNode ;

import xcom.utils4j.logging.aspects.api.annotations.Log ;


public class ParsingListener implements ParseTreeListener {


	List<String> walked ;

	public List<String> walked() {
		return walked ;
	}


	@Log
	public ParsingListener() {}


	@Log
	@Override
	public void enterEveryRule(final ParserRuleContext pctx) {
		walked.add(pctx.getClass().getSimpleName().substring(0, pctx.getClass().getSimpleName().length() - 7)) ;
	}


	@Log
	@Override
	public void visitTerminal(final TerminalNode node) {}


	@Log
	@Override
	public void visitErrorNode(final ErrorNode node) {}


	@Log
	@Override
	public void exitEveryRule(final ParserRuleContext ctx) {}
}
