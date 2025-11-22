 

grammar MOS6502_;

options { 
	caseInsensitive = true;	
}

import Assembly ;


assembly:  ( statement eol )* EOF ;
statement:  ( label | scopedLabel )? ( instruction |  directive )? ;


//
// Instructions
//

instruction:  ( branchOpcode branchAddressingMode ) | ( opcode addressingMode ) ;

branchOpcode:  BranchOpcodes ;
opcode:  Opcodes ;

branchAddressingMode:  relative ;
addressingMode:  absolute | accumulator | immediate | implied | indexedX | indexedY | indirect | zpIndirectX | zpIndirectY | relative ;

absolute:  argument ;
accumulator:  'A' ;
immediate:  '#' argument ; 
implied: ;
indexedX:  argument ',' 'X' ;
indexedY:  argument ',' 'Y' ; 
indirect:  '[' argument ']' ;
zpIndirectX:  '[' argument ',' 'X' ']' ;
zpIndirectY:  '[' argument ']' ',' 'Y' ;
relative:  argument ;


BranchOpcodes:  
	'BCC' | 'BCS' | 'BEQ' | 'BMI' | 'BNE' | 'BPL' | 'BRA' | 'BVC' | 'BVS'
	 ;

Opcodes:  
	'ADC' | 'AND' | 'ASL' | 
	'BIT' | 'BRK' |
	'CLC' | 'CLD' | 'CLI' | 'CLV' | 'CMP' | 'CPX' | 'CPY' | 
 	'DEC' | 'DEX' | 'DEY' | 
	'EOR' | 
	'INC' | 'INX' | 'INY' | 
	'JMP' | 'JSR' | 
	'LDA' | 'LDY' | 'LDX' | 'LSR' | 
	'NOP' | 
	'ORA' | 
	'PHA' | 'PHX' | 'PHY' | 'PHP' | 'PLA' | 'PLP' | 'PLY' | 
	'ROL' | 'ROR' | 'RTI' | 'RTS' | 
	'SBC' | 'SEC' | 'SED' | 'SEI' | 'STA' | 'STX' | 'STY' | 'STZ' | 
	'TAX' | 'TAY' | 'TSX' | 'TXA' | 'TXS' | 'TYA'
	;


//
//
//

eol:  EOL ;


Comment:  ';' ~ [\r\n]* -> skip ;

EOL:  [\r\n]+ ;

WS:  [ \t] -> skip ;

ANY: . ;
