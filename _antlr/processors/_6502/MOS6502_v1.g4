 

grammar MOS6502_;

options { 
	caseInsensitive = true;	
}


assembly:  ( statement eol )* EOF ;
statement:  ( instruction | label | ( label instruction ) | directive )?  ;
label:  Identifier ;


//
// Instructions
//

instruction:  ( opcodeBranch addressingModeBranch ) | ( opcode addressingMode ) ;

opcodeBranch:  OpcodeBranches ;
opcode:  Opcodes ;

addressingModeBranch:  relative ;
addressingMode:  indexedX | indexedY | zpIndirectX | zpIndirectY | immediate | absolute | accumulator | implied | indirect  ;

absolute:  argument ;
accumulator:  'A' ;
immediate:  '#' argument ; 
implied:  ;
indexedX:  argument ',' 'X' ;
indexedY:  argument ',' 'Y' ; 
indirect:  '[' argument ']' ;
zpIndirectX:  '[' argument ',' 'X' ']' ;
zpIndirectY:  '[' argument ']' ',' 'Y' ;
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
// Directives.g4
//

directive:  label? '.' ( Directives | macro ) argumentList? inject? ;
macro:  Identifier ;
inject:  Inject ;


argumentList:   argument ( ',' argumentList )? ; 
argument:  dottedIdentifier | expr ;
dottedIdentifier:  '.' Identifier ;


Directives:  'equ' | 'include' | 'macro' ;

Inject:  '->' ~ [\r\n]* ;


//
// Expresions.g4
//

expr:  term ( ( binary | comparison ) term)* ;

term:  lc | identifier | literal | '(' expr ')' | unary term ;
lc: '*' ;


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


identifier:  Identifier ;

 
BinaryLiteral: '0b' [0-1]+ ;
OctalLiteral:  '0o' [0-7]+ ;
DecimalLiteral:  [0-9]+ ;
HexLiteral:  ('$' | '0x') [0-9A-F]+ ;

CharacterLiteral:  '\'' ~ ["] ;
StringLiteral:  '"' ~ ["]* '"' ;


//
//
//

eol:  EOL ;


Identifier:  [A-Z_] [A-Z0-9_]* ;

Comment:  ';' ~ [\r\n]* -> skip ;

EOL:  [\r\n]+ ;

WS:  [ \t] -> skip ;
