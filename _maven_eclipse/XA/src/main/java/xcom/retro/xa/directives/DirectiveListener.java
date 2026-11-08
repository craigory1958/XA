

package xcom.retro.xa.directives ;


import static xcom.retro.xa.XA.logEvent ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_formatQualifiedID ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_invokeMethodFromContext ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_parsedContextName ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_parsedText ;

import org.antlr.v4.runtime.ParserRuleContext ;
import org.fest.reflect.core.Reflection ;
import org.fest.reflect.exception.ReflectionError ;

import xcom.retro.xa.AssemblyContext ;
import xcom.retro.xa.antlr.AssemblyBaseListener ;
import xcom.retro.xa.api.interfaces.iDirective ;
import xcom.retro.xa.api.interfaces.iSource ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@Log
public class DirectiveListener extends AssemblyBaseListener {

	AssemblyContext actx ;


	//
	// Constructor(s) ...
	//

	public DirectiveListener(final AssemblyContext actx) {
		this.actx = actx ;
	}


	//
	//
	//

	@Override
	public void enterEveryRule(final ParserRuleContext pctx) {
		ASMB_invokeMethodFromContext(actx, "dirs", this, "enter", pctx) ;
	}

	@Override
	public void exitEveryRule(final ParserRuleContext pctx) {
		ASMB_invokeMethodFromContext(actx, "dirs", this, "exit", pctx) ;
	}

//	public void invokeMethodFromContext(final String prefix, final ParserRuleContext pctx) {
//
//		final String _methodContext = ASMB_parsedContextName(pctx) ;
//		final String method = prefix + _methodContext ;
//
//		try {
//			this.getClass().getMethod(method, ParserRuleContext.class) ;
//
//			logEvent(actx.$args(), actx.phase(), "dirs", "(dirs) {}: invoke {}({}) in {}", this.getClass(), method, pctx.getClass().getSimpleName(),
//					this.getClass()) ;
//
//			Reflection //
//					.method(method) //
//					.withParameterTypes(ParserRuleContext.class) //
//					.in(this) //
//					.invoke(pctx) ;
//		}
//		catch ( final NoSuchMethodException ex ) {}
//		catch ( final ReflectionError ex ) {
//			System.err.println("Impossible error 52: Error walking tree: Method " + method + " not found.") ;
//		}
//	}


	//
	//
	//

	public void exitDirective(final ParserRuleContext pctx) {

		try {
			final iSource _src = actx.source().peek() ;
			final String _directiveContext = ASMB_parsedContextName(pctx.getChild(0)) ;
			String _phase = actx.phase().name().toLowerCase() ;

			logEvent(actx.$args(), actx.phase(), "dirs", "(dirs) {}: source: {}", this.getClass(), _src) ;
			logEvent(actx.$args(), actx.phase(), "dirs", "(dirs) {}: directiveContext: {}", this.getClass(), _directiveContext) ;
			logEvent(actx.$args(), actx.phase(), "dirs", "(dirs) {}: phase: {}", this.getClass(), _phase) ;


			String directive = null ;
			iDirective worker = null ;
			switch ( _directiveContext ) {

				case "Assembler":
					directive = ASMB_parsedText(pctx.getChild(0).getChild(1)) ;
					worker = actx.directives().get(directive.toUpperCase()) ;
					break ;

				case "Invocation":
					directive = ASMB_parsedText(pctx.getChild(0).getChild(0)) ;

					worker = ((worker == null) && actx.macros().containsKey(directive.toUpperCase()) ? actx.macros().get(directive.toUpperCase()) : worker) ;
					worker = ((worker == null) && actx.structs().containsKey(directive.toUpperCase()) ? actx.structs().get(directive.toUpperCase()) : worker) ;

					if ( worker == null )
						directive = ASMB_formatQualifiedID(directive, _src.qualifier()) ;

					worker = ((worker == null) && actx.macros().containsKey(directive.toUpperCase()) ? actx.macros().get(directive.toUpperCase()) : worker) ;
					worker = ((worker == null) && actx.structs().containsKey(directive.toUpperCase()) ? actx.structs().get(directive.toUpperCase()) : worker) ;

					_phase = "expand" ;
					break ;

				case "Macro":
					directive = "MACRO" ;
					worker = actx.directives().get(directive.toUpperCase()) ;
					break ;

				case "Struct":
					directive = "STRUCT" ;
					worker = actx.directives().get(directive.toUpperCase()) ;
					break ;
			}

			logEvent(actx.$args(), actx.phase(), "dirs", "(dirs) {}: directive: {}", this.getClass(), directive) ;
			logEvent(actx.$args(), actx.phase(), "dirs", "(dirs) {}: worker: {}", this.getClass(), worker.getClass().getSimpleName()) ;
			logEvent(actx.$args(), actx.phase(), "dirs", "(dirs) {}: phase: {}", this.getClass(), _phase) ;

			logEvent(actx.$args(), actx.phase(), "dirs", "(dirs) {}: invoke {}({}) in {}", this.getClass(), _phase, pctx.getClass().getSimpleName(),
					worker.getClass().getSimpleName()) ;
			Reflection.method(_phase).withParameterTypes(ParserRuleContext.class).in(worker).invoke(pctx) ;

			logEvent(actx.$args(), actx.phase(), "dirs", "(dirs) {}: exitDirective {} @{}", this.getClass(), directive, _phase) ;
		}
		catch ( final ReflectionError ex ) {
			ex.printStackTrace() ;
		}
	}
}
