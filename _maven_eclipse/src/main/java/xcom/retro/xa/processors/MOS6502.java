

package xcom.retro.xa.processors ;


import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_parsedContextName ;
import static xcom.retro.xa.assembly.AssemblyUtils.ASMB_parsedText ;
import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_lsb ;
import static xcom.retro.xa.expressions.ExpressionUtils.EXPR_msb ;

import java.util.Arrays ;
import java.util.Map ;

import org.antlr.v4.runtime.ParserRuleContext ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

import xcom.retro.xa.Operand ;
import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.antlr.processors.MOS6502.MOS6502_Lexer ;
import xcom.retro.xa.antlr.processors.MOS6502.MOS6502_Parser ;
import xcom.retro.xa.api.annotations.aProcessor ;
import xcom.retro.xa.api.interfaces.iProcessor ;
import xcom.retro.xa.assembly.AssemblyListener ;
import xcom.retro.xa.expressions.value._ValueNode ;
import xcom.utils4j.Enums ;
import xcom.utils4j.data.structured.map.Maps ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


@aProcessor(lexar = MOS6502_Lexer.class, parser = MOS6502_Parser.class, listener = MOS6502.class, extensions = { "a65", })
public class MOS6502 extends AssemblyListener implements iProcessor {

	enum Opcodes {
		ADC_zp(null, (byte) 0x65, (byte) 0x00), //
		ADC_zpIndexedX(null, (byte) 0x75, (byte) 0x00), //
		ADC_zpIndirectX(null, (byte) 0x61, (byte) 0x00), //
		ADC_zpIndirectY(null, (byte) 0x71, (byte) 0x00), //
		ADC_absolute(ADC_zp, (byte) 0x6D, (byte) 0x00, (byte) 0x00), //
		ADC_immediate(null, (byte) 0x69, (byte) 0x00), //
		ADC_indexedX(ADC_zpIndexedX, (byte) 0x7D, (byte) 0x00, (byte) 0x00), //
		ADC_indexedY(null, (byte) 0x79, (byte) 0x00, (byte) 0x00), //
		AND_zp(null, (byte) 0x25, (byte) 0x00), //
		AND_zpIndexedX(null, (byte) 0x35, (byte) 0x00), //
		AND_zpIndirectX(null, (byte) 0x21, (byte) 0x00), //
		AND_zpIndirectY(null, (byte) 0x31, (byte) 0x00), //
		AND_absolute(AND_zp, (byte) 0x2D, (byte) 0x00, (byte) 0x00), //
		AND_immediate(null, (byte) 0x29, (byte) 0x00), //
		AND_indexedX(AND_zpIndexedX, (byte) 0x3D, (byte) 0x00, (byte) 0x00), //
		AND_indexedY(null, (byte) 0x39, (byte) 0x00, (byte) 0x00), //
		ASL_zp(null, (byte) 0x06, (byte) 0x00), //
		ASL_zpIndexedX(null, (byte) 0x16, (byte) 0x00), //
		ASL_absolute(ASL_zp, (byte) 0x0E, (byte) 0x00, (byte) 0x00), //
		ASL_accumulator(null, (byte) 0x0A), //
		ASL_indexedX(ASL_zpIndexedX, (byte) 0x1E, (byte) 0x00, (byte) 0x00), //
		BCC_relative(null, (byte) 0x90, (byte) 0x00), //
		BCS_relative(null, (byte) 0xB0, (byte) 0x00), //
		BEQ_relative(null, (byte) 0xF0, (byte) 0x00), //
		BIT_zp(null, (byte) 0x24, (byte) 0x00), //
		BIT_absolute(BIT_zp, (byte) 0x2C, (byte) 0x00, (byte) 0x00), //
		BMI_relative(null, (byte) 0x30, (byte) 0x00), //
		BNE_relative(null, (byte) 0xD0, (byte) 0x00), //
		BPL_relative(null, (byte) 0x10, (byte) 0x00), //
		BRK_immediate(null, (byte) 0x00, (byte) 0x00), //
		BVC_relative(null, (byte) 0x50, (byte) 0x00), //
		BVS_relative(null, (byte) 0x70, (byte) 0x00), //
		CLC_implied(null, (byte) 0x18), //
		CLD_implied(null, (byte) 0xD8), //
		CLI_implied(null, (byte) 0x58), //
		CLV_implied(null, (byte) 0xB8), //
		CMP_zp(null, (byte) 0xC5, (byte) 0x00), //
		CMP_zpIndexedX(null, (byte) 0xD5, (byte) 0x00), //
		CMP_zpIndirectX(null, (byte) 0xC1, (byte) 0x00), //
		CMP_zpIndirectY(null, (byte) 0xD1, (byte) 0x00), //
		CMP_absolute(CMP_zp, (byte) 0xCD, (byte) 0x00, (byte) 0x00), //
		CMP_immediate(null, (byte) 0xC9, (byte) 0x00), //
		CMP_indexedX(CMP_zpIndexedX, (byte) 0xDD, (byte) 0x00, (byte) 0x00), //
		CMP_indexedY(null, (byte) 0xD9, (byte) 0x00, (byte) 0x00), //
		CPX_zp(null, (byte) 0xE4, (byte) 0x00), //
		CPX_absolute(CPX_zp, (byte) 0xEC, (byte) 0x00, (byte) 0x00), //
		CPX_immediate(null, (byte) 0xE0, (byte) 0x00), //
		CPY_zp(null, (byte) 0xC4, (byte) 0x00), //
		CPY_absolute(CPY_zp, (byte) 0xCC, (byte) 0x00, (byte) 0x00), //
		CPY_immediate(null, (byte) 0xC0, (byte) 0x00), //
		DEC_zp(null, (byte) 0xC6, (byte) 0x00), //
		DEC_zpIndexedX(null, (byte) 0xD6, (byte) 0x00), //
		DEC_absolute(DEC_zp, (byte) 0xCE, (byte) 0x00, (byte) 0x00), //
		DEC_indexedX(DEC_zpIndexedX, (byte) 0xDE, (byte) 0x00, (byte) 0x00), //
		DEX_implied(null, (byte) 0xCA), //
		DEY_implied(null, (byte) 0x88), //
		EOR_zp(null, (byte) 0x45, (byte) 0x00), //
		EOR_zpIndexedX(null, (byte) 0x55, (byte) 0x00), //
		EOR_zpIndirectX(null, (byte) 0x41, (byte) 0x00), //
		EOR_zpIndirectY(null, (byte) 0x51, (byte) 0x00), //
		EOR_absolute(EOR_zp, (byte) 0x4D, (byte) 0x00, (byte) 0x00), //
		EOR_immediate(null, (byte) 0x49, (byte) 0x00), //
		EOR_indexedX(EOR_zpIndexedX, (byte) 0x5D, (byte) 0x00, (byte) 0x00), //
		EOR_indexedY(null, (byte) 0x59, (byte) 0x00, (byte) 0x00), //
		INC_zp(null, (byte) 0xE6, (byte) 0x00), //
		INC_zpIndexedX(null, (byte) 0xF6, (byte) 0x00), //
		INC_absolute(INC_zp, (byte) 0xEE, (byte) 0x00, (byte) 0x00), //
		INC_indexedX(INC_zpIndexedX, (byte) 0xFE, (byte) 0x00, (byte) 0x00), //
		INX_implied(null, (byte) 0xE8), //
		INY_implied(null, (byte) 0xC8), //
		JMP_absolute(null, (byte) 0x4C, (byte) 0x00, (byte) 0x00), //
		JMP_indirect(null, (byte) 0x6C, (byte) 0x00, (byte) 0x00), //
		JSR_absolute(null, (byte) 0x20, (byte) 0x00, (byte) 0x00), //
		LDA_zp(null, (byte) 0xA5, (byte) 0x00), //
		LDA_zpIndexedX(null, (byte) 0xB5, (byte) 0x00), //
		LDA_zpIndirectX(null, (byte) 0xA1, (byte) 0x00), //
		LDA_zpIndirectY(null, (byte) 0xB1, (byte) 0x00), //
		LDA_absolute(LDA_zp, (byte) 0xAD, (byte) 0x00, (byte) 0x00), //
		LDA_immediate(null, (byte) 0xA9, (byte) 0x00), //
		LDA_indexedX(LDA_zpIndexedX, (byte) 0xBD, (byte) 0x00, (byte) 0x00), //
		LDA_indexedY(null, (byte) 0xB9, (byte) 0x00, (byte) 0x00), //
		LDX_zp(null, (byte) 0xA6, (byte) 0x00), //
		LDX_zpIndexedY(null, (byte) 0xB6, (byte) 0x00), //
		LDX_absolute(LDX_zp, (byte) 0xAE, (byte) 0x00, (byte) 0x00), //
		LDX_immediate(null, (byte) 0xA2, (byte) 0x00), //
		LDX_indexedY(LDX_zpIndexedY, (byte) 0xBE, (byte) 0x00, (byte) 0x00), //
		LDY_zp(null, (byte) 0xA4, (byte) 0x00), //
		LDY_zpIndexedX(null, (byte) 0xB4, (byte) 0x00), //
		LDY_absolute(LDY_zp, (byte) 0xAC, (byte) 0x00, (byte) 0x00), //
		LDY_immediate(null, (byte) 0xA0, (byte) 0x00), //
		LDY_indexedX(LDY_zpIndexedX, (byte) 0xBC, (byte) 0x00, (byte) 0x00), //
		LSR_zp(null, (byte) 0x46, (byte) 0x00), //
		LSR_zpIndexedX(null, (byte) 0x56, (byte) 0x00), //
		LSR_absolute(LSR_zp, (byte) 0x4E, (byte) 0x00, (byte) 0x00), //
		LSR_accumulator(null, (byte) 0x4A), //
		LSR_indexedX(LSR_zpIndexedX, (byte) 0x5E, (byte) 0x00, (byte) 0x00), //
		NOP_implied(null, (byte) 0xEA), //
		ORA_zp(null, (byte) 0x05, (byte) 0x00), //
		ORA_zpIndexedX(null, (byte) 0x15, (byte) 0x00), //
		ORA_zpIndirectX(null, (byte) 0x01, (byte) 0x00), //
		ORA_zpIndirectY(null, (byte) 0x11, (byte) 0x00), //
		ORA_absolute(ORA_zp, (byte) 0x0D, (byte) 0x00, (byte) 0x00), //
		ORA_immediate(null, (byte) 0x09, (byte) 0x00), //
		ORA_indexedX(ORA_zpIndexedX, (byte) 0x1D, (byte) 0x00, (byte) 0x00), //
		ORA_indexedY(null, (byte) 0x19, (byte) 0x00, (byte) 0x00), //
		PHA_implied(null, (byte) 0x48), //
		PHP_implied(null, (byte) 0x08), //
		PLA_implied(null, (byte) 0x68), //
		PLP_implied(null, (byte) 0x28), //
		ROL_zp(null, (byte) 0x26, (byte) 0x00), //
		ROL_zpIndexedX(null, (byte) 0x36, (byte) 0x00), //
		ROL_absolute(ROL_zp, (byte) 0x2E, (byte) 0x00, (byte) 0x00), //
		ROL_accumulator(null, (byte) 0x2A), //
		ROL_indexedX(ROL_zpIndexedX, (byte) 0x3E, (byte) 0x00, (byte) 0x00), //
		ROR_zp(null, (byte) 0x66, (byte) 0x00), //
		ROR_zpIndexedX(null, (byte) 0x76, (byte) 0x00), //
		ROR_absolute(ROR_zp, (byte) 0x6E, (byte) 0x00, (byte) 0x00), //
		ROR_accumulator(null, (byte) 0x6A), //
		ROR_indexedX(ROR_zpIndexedX, (byte) 0x7E, (byte) 0x00, (byte) 0x00), //
		RTI_implied(null, (byte) 0x40), //
		RTS_implied(null, (byte) 0x60), //
		SBC_zp(null, (byte) 0xE5, (byte) 0x00), //
		SBC_zpIndexedX(null, (byte) 0xF5, (byte) 0x00), //
		SBC_zpIndirectX(null, (byte) 0xE1, (byte) 0x00), //
		SBC_zpIndirectY(null, (byte) 0xF1, (byte) 0x00), //
		SBC_absolute(SBC_zp, (byte) 0xED, (byte) 0x00, (byte) 0x00), //
		SBC_immediate(null, (byte) 0xE9, (byte) 0x00), //
		SBC_indexedX(SBC_zpIndexedX, (byte) 0xFD, (byte) 0x00, (byte) 0x00), //
		SBC_indexedY(null, (byte) 0xF9, (byte) 0x00, (byte) 0x00), //
		SEC_implied(null, (byte) 0x38), //
		SED_implied(null, (byte) 0xF8), //
		SEI_implied(null, (byte) 0x78), //
		STA_zp(null, (byte) 0x85, (byte) 0x00), //
		STA_zpIndexedX(null, (byte) 0x95, (byte) 0x00), //
		STA_zpIndirectX(null, (byte) 0x81, (byte) 0x00), //
		STA_zpIndirectY(null, (byte) 0x91, (byte) 0x00), //
		STA_absolute(STA_zp, (byte) 0x8D, (byte) 0x00, (byte) 0x00), //
		STA_indexedX(STA_zpIndexedX, (byte) 0x9D, (byte) 0x00, (byte) 0x00), //
		STA_indexedY(null, (byte) 0x99, (byte) 0x00, (byte) 0x00), //
		STX_zp(null, (byte) 0x86, (byte) 0x00), //
		STX_zpIndexedY(null, (byte) 0x96, (byte) 0x00), //
		STX_absolute(STX_zp, (byte) 0x8E, (byte) 0x00, (byte) 0x00), //
		STY_zp(null, (byte) 0x84, (byte) 0x00), //
		STY_zpIndexedX(null, (byte) 0x94, (byte) 0x00), //
		STY_absolute(STY_zp, (byte) 0x8C, (byte) 0x00, (byte) 0x00), //
		TAX_implied(null, (byte) 0xAA), //
		TAY_implied(null, (byte) 0xA8), //
		TSX_implied(null, (byte) 0xBA), //
		TXA_implied(null, (byte) 0x8A), //
		TXS_implied(null, (byte) 0x9A), //
		TYA_implied(null, (byte) 0x98), //
		;

		Opcodes zpOption ;
		byte[] bytes ;


		Opcodes(final Opcodes zpOption, final byte... bytes) {

			this.zpOption = zpOption ;
			this.bytes = bytes ;
		}
	}


	//
	//
	//

	private static final Logger Logger = LoggerFactory.getLogger(MOS6502.class) ;


	//
	//
	//

	public MOS6502(final AssemblyContext actx) {
		super(actx) ;
	}


	//
	//
	//

	@Log
	public void exitInstruction(final ParserRuleContext pctx) {

		if ( actx().statement().assemblyEnable() ) {

			final Map<String, Operand> _operands = actx().statement().operands() ;
			final String _opcode = ASMB_parsedText(pctx.getChild(0)) ;
			final String _opcodeModeContext = ASMB_parsedContextName(pctx.getChild(1).getChild(0)) ;

			Opcodes opcode = Enums.valueOfIgnoreCase(Opcodes.class, _opcode + "_" + _opcodeModeContext) ;

			if ( opcode.zpOption != null )
				try {
					final Operand operand1 = Maps.firstEntryValue(_operands) ;
					final _ValueNode value = operand1.assignment().eval(actx().symbols()) ;

					if ( (value != null) && ((int) value.getValue() <= 255) )
						opcode = opcode.zpOption ;
				}
				catch ( final NullPointerException ex ) {}

			final String callback = "set" + opcode.bytes.length + "Byte" + (_opcodeModeContext.equals("Relative") ? "Relative" : "") + "Instruction" ;

			actx().statement().assemblyCallbackMethod(callback) ;
			actx().statement().assemblyCallbackObject(this) ;
			actx().statement().bytes(Arrays.copyOf(opcode.bytes, opcode.bytes.length)) ;
			actx().segment().allocateBytes(actx().statement().bytes()) ;

			Logger.debug("Assembled callback method {}", callback) ;
		}
	}


	//
	//
	//

	@Log
	void set1ByteInstruction() {

		final byte[] bytes = actx().statement().bytes() ;
		actx().statement().bytes(bytes) ;
	}

	@Log
	void set2ByteInstruction() {

		final Map<String, Operand> _operands = actx().statement().operands() ;
		final Operand _operand1 = Maps.firstEntryValue(_operands) ;

		final byte[] bytes = actx().statement().bytes() ;
		bytes[1] = EXPR_lsb(_operand1.assignment().eval(actx().symbols()).value()) ;
		actx().statement().bytes(bytes) ;
	}

	@Log
	void set2ByteRelativeInstruction() {

		final Map<String, Operand> _operands = actx().statement().operands() ;
		final Operand _operand1 = Maps.firstEntryValue(_operands) ;

		final byte[] bytes = actx().statement().bytes() ;
		final int x = (Integer) _operand1.assignment().eval(actx().symbols()).getValue() ;
		final int y = actx().statement().loc() ;
		bytes[1] = (byte) ((x - y - 2) & 0xFF) ;
		actx().statement().bytes(bytes) ;
	}

	@Log
	void set3ByteInstruction() {

		final Map<String, Operand> _operands = actx().statement().operands() ;
		final Operand _operand1 = Maps.firstEntryValue(_operands) ;

		final byte[] bytes = actx().statement().bytes() ;
		final byte[] value = _operand1.assignment().eval(actx().symbols()).value() ;
		bytes[1] = EXPR_lsb(value) ;
		bytes[2] = EXPR_msb(value) ;
		actx().statement().bytes(bytes) ;
	}
}
