 

grammar MOS6502_;

options { 
	caseInsensitive = true;	
}


assembly:  ( statement eol )* EOF ;
statement:  ( label | instruction | ( label instruction ) | directive | ( label directive ) )?  ;

label:  Identifier ;


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
implied:  ;
indexedX:  argument ',' 'X' ;
indexedY:  argument ',' 'Y' ; 
indirect:  '[' argument ']' ;
zpIndirectX:  '[' argument ',' 'X' ']' ;
zpIndirectY:  '[' argument ']' ',' 'Y'  ;
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
// Directives.g4
//

directive:  assembler | macro | invocation ;

assembler:  '.' Directives argumentList? ;
macro: '.' symbol  '.macro' optionList? ;
invocation:  '.' symbol parameterList? ;


// Assembler directive ...
argumentList:   ( argument | option ) ( ',' argumentList )? ; 
argument:  expr ;

// Macro definition ...
optionList:   option ( ',' optionList )? ; 
option: symbol assignment? ;

// Macro invocation ...
parameterList:  ( parameter ( ',' parameterList )? ) | ( ',' parameterList? ) ; 
parameter: ( symbol assignment ) | ideogram | argument ;
assignment:  '=' ( argument | ideogram ) ;

symbol:  Directives | Identifier ;
ideogram:  '.' symbol ;


Directives:  
	'align' | 
	'byte' | 
	'end' | 'endif' | 'endmacro' | 'equ' | 
	'if' |'ifdef' | 'ifndef' | 'include' | 
	'list' |
	'nolist' |
	'org' | 
	'page' |
	'segment' |
	'word' ;


//
// Expresions.g4
//

expr:  term ( ( binary | comparison ) term )* ;

term:  org | identifier | literal | '(' expr ')' | unary term ;
org: '*' ;

identifier:  Identifier ;


binary: integerAdd | integerSubtract | integerMultiply | integerDivide | bitwiseShiftLeft | bitwiseShiftRight | bitwiseAnd | bitwiseOr | logicalAnd |  logicalOr ;
comparison: equal | notEqual | lessThan | greaterThan | '<=' | '>=' ;
unary:  plus | minus | highByte | lowByte | logicalNot ;


integerAdd: '+' ;
integerSubtract: '-' ;
integerMultiply: '*' ;
integerDivide: '/' ;

bitwiseShiftLeft: '<<' ;
bitwiseShiftRight: '>>' ;
bitwiseAnd: '&' ;
bitwiseOr: '|' ;

logicalAnd: '&&' ;
logicalOr: '||' ;

equal: '==' ;
notEqual: '!=' ;
lessThan: '<' ;
greaterThan: '>' ;

plus: '+' ;
minus: '-' ;
highByte: '<' ;
lowByte: '>' ;
logicalNot: '!' ;


literal:  numericLiteral | alphanumericLiteral ; 

numericLiteral:  binaryLiteral | octalLiteral | decimalLiteral | hexLiteral ; 
alphanumericLiteral: characterLiteral | stringLiteral ; 


binaryLiteral:   BinaryLiteral ;
octalLiteral:   OctalLiteral ;
decimalLiteral:  DecimalLiteral ;
hexLiteral:   HexLiteral ;

characterLiteral:  CharacterLiteral ;
stringLiteral:  StringLiteral ;

 
BinaryLiteral: '0b' [0-1]+ ;
OctalLiteral:  '0o' [0-7]+ ;
DecimalLiteral: '0d'? [0-9]+ ;
HexLiteral:  ('$' | '0x') [0-9A-F]+ ;


CharacterLiteral:  '\'' ~["] ;
StringLiteral:  '"' ~["]* '"' ;

Identifier:  [A-Z_] [A-Z0-9_]* ;


//
//
//

eol:  EOL ;


Comment:  ';' ~[\r\n]* -> skip ;

EOL:  [\r\n] ;

WS:  [ \t] -> skip ;
