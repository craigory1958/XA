

package xcom.retro.xa.processors ;


import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.antlr.processors.Z80.Z80_Lexer ;
import xcom.retro.xa.antlr.processors.Z80.Z80_Parser ;
import xcom.retro.xa.api.annotations.aProcessor ;
import xcom.retro.xa.api.interfaces.iProcessor ;
import xcom.retro.xa.assembly.AssemblyListener ;


@aProcessor(lexar = Z80_Lexer.class, parser = Z80_Parser.class, listener = Z80.class, extensions = { "z80", "asm", })
public class Z80 extends AssemblyListener implements iProcessor {

	enum Opcodes {
		;

		byte[] bytes ;


		Opcodes(final byte... bytes) {
			this.bytes = bytes ;
		}
	}


	//
	//
	//

	public Z80(final AssemblyContext actx) {
		super(actx) ;
	}
}
