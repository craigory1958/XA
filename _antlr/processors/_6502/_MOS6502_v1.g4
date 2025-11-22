 

grammar MOS6502_;

options { 
	caseInsensitive = true;	
}

import Directives ;


assembly:  ( statement eol )* EOF ;
statement:  ( label | instruction | ( label instruction ) | directive )?  ;


//
// Instructions
//

instruction:  ( opcodeBranch addressingModeBranch ) | ( opcode addressingMode ) ;

opcodeBranch:  OpcodeBranches ;
opcode:  Opcodes ;

addressingModeBranch:  relative ;
addressingMode:  indexedX | indexedY | indirect | zpIndirectX | zpIndirectY | immediate | absolute | accumulator | implied ;

absolute:  argument ;
accumulator:  'A' ;
immediate:  '#' argument ; 
implied:  ;
indexedX:  argument ',X' ;
indexedY:  argument ',Y' ; 
indirect:  '[' argument ']' ;
zpIndirectX:  '[' argument ',X]' ;
zpIndirectY:  '[' argument '],Y' ;
relative:  argument ;


OpcodeBranches:  
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
