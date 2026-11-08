

package xcom.retro.xa.directives.dir ;


import static xcom.retro.xa.Identifier.IdentifierTypes.* ;
import static xcom.retro.xa.XA.logEvent ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_formatQualifiedID ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_parsedText ;

import java.util.Map ;

import org.antlr.v4.runtime.ParserRuleContext ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.Identifier ;
import xcom.retro.xa.Operand ;
import xcom.retro.xa.api.annotations.aDirective ;
import xcom.retro.xa.api.interfaces.iDirective ;
import xcom.retro.xa.api.interfaces.iSource ;
import xcom.utils4j.data.structured.map.Maps ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
@aDirective
public class EQU implements iDirective {


	static final Identifier $Identifier = new Identifier(null) ;

	//
	// Attributes ...
	//

	AssemblyContext actx ;


	//
	// Constructor(s) ...
	//

	public EQU(final AssemblyContext actx) {
		this.actx = actx ;
	}


	//
	// Implements iDirective ...
	//

	@Override
	public void parse(final ParserRuleContext pctx) {

		logEvent(actx.$args(), actx.phase(), "args", "(args) {}: args: {}", this.getClass(), actx.statement().operands()) ;

		final Map<String, Identifier> _ids = actx.identifiers() ;
		final iSource _src = actx.source().peek() ;

		final Map<String, Operand> _operands = actx.statement().operands() ;
		final Operand _operand1 = Maps.firstEntryValue(_operands) ;
		final String _id = ASMB_parsedText(pctx.getParent().getChild(0).getChild(0)) ;
		final String _idFQN = ASMB_formatQualifiedID(_id, _src.qualifier()).toLowerCase() ;

		logEvent(actx.$args(), actx.phase(), "EQU", "(EQU) {}: operand1: {}", this.getClass(), _operand1) ;
		logEvent(actx.$args(), actx.phase(), "EQU", "(EQU) {}: identifier: {}, {}", this.getClass(), _id, _idFQN) ;

		_ids.get(_idFQN).type(Value) ;
		logEvent(actx.$args(), actx.phase(), "ids", "(ids) {}: update identifier-type: {}: {}", this.getClass(), _idFQN, _ids.get(_idFQN).type()) ;

		final byte[] operand1_value = _operand1.assignment().eval(_ids).value() ;
		logEvent(actx.$args(), actx.phase(), "EQU", "(EQU) {}: value: {}", this.getClass(), operand1_value) ;

		_ids.get(_idFQN).value(operand1_value) ;
		logEvent(actx.$args(), actx.phase(), "ids", "(ids) {}: update identifier-value: {}: {}", this.getClass(), _idFQN, _ids.get(_idFQN).value()) ;
	}
}
