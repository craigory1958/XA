 

grammar Expressions ;

options { 
	caseInsensitive = true;	
}


//
// Directives.g4
//

directive:    assembler | macro | struct | invocation   ;

assembler:    '.' Directives parameterList?   ;
macro:    '.' symbol  '.macro' optionList?   ;
struct:    '.' symbol '.struct' optionList?   ;
invocation:    qualifiedSymbol  parameterList?   ;


// Assembler directive ...
argumentList:     ( argument | option ) ( ',' argumentList )?   ; 

// Macro definition ...
optionList:     option ( ',' optionList )?   ; 
option:   Identifier assignment?   ;

// Macro invocation ...
parameterList:    ( parameter ( ',' parameterList )? ) | ( ',' parameterList? )   ; 
parameter:   ( identifier assignment ) | ( '.' symbol assignment ) | ( '.' symbol ) | argument   ;


assignment:    '=' ( argument | ( '.' symbol ) )   ;
argument:    expr   ;

symbol:     Identifier | Directives   ;
qualifiedSymbol:  ( '.' symbol ':' )* '.' symbol   ;


label:   Identifier   ;
scopedLabel:   Identifier   ;


Directives:    
	'align' | 
	'byte' | 
	'end' | 'endif' | 'endmacro' | 'endstruct' | 'equ' | 
	'if' | 'ifdef' | 'ifndef' | 'include' | 
	'list' |
	'nolist' |
	'org' | 
	'page' |
	'segment' |
	'word'   
	;


//
// Expresions.g4
//

expr:    term ( ( binary | comparison ) term )*   ;

term:    org | ( identifier | scopedIdentifier | qualifiedIdentifier )  | literal | '(' expr ')' | unary term   ;
org:   '*'   ;

identifier:    Identifier | Directives   ;
qualifiedIdentifier:  ( '.' symbol ':' )* Identifier    ;
scopedIdentifier:    '@' Identifier   ;


binary:   integerAdd | integerSubtract | integerMultiply | integerDivide | bitwiseShiftLeft | bitwiseShiftRight | bitwiseAnd | bitwiseOr | logicalAnd |  logicalOr   ;
comparison:   equal | notEqual | lessThan | greaterThan | '<=' | '>='   ;
unary:    plus | minus | highByte | lowByte | logicalNot   ;


integerAdd:   '+'   ;
integerSubtract:   '-'   ;
integerMultiply:   '*'   ;
integerDivide:   '/'   ;

bitwiseShiftLeft:   '<<'   ;
bitwiseShiftRight:   '>>'   ;
bitwiseAnd:   '&'   ;
bitwiseOr:   '|'   ;

logicalAnd:   '&&'   ;
logicalOr:   '||'   ;

equal:   '=='   ;
notEqual:   '!='   ;
lessThan:   '<'   ;
greaterThan:   '>'   ;

plus:   '+'   ;
minus:   '-'   ;
highByte:   '<'   ;
lowByte:   '>'   ;
logicalNot:   '!'   ;


literal:    numericLiteral | alphanumericLiteral   ; 

numericLiteral:    binaryLiteral | octalLiteral | decimalLiteral | hexLiteral   ; 
alphanumericLiteral:   characterLiteral | stringLiteral   ; 


binaryLiteral:     BinaryLiteral   ;
octalLiteral:     OctalLiteral   ;
decimalLiteral:    DecimalLiteral   ;
hexLiteral:     HexLiteral   ;

characterLiteral:    CharacterLiteral   ;
stringLiteral:    StringLiteral   ;

 
BinaryLiteral:   '0b' [0-1]+   ;
OctalLiteral:    '0o' [0-7]+   ;
DecimalLiteral:   '0d'? [0-9]+   ;
HexLiteral:    ('$' | '0x') [0-9A-F]+   ;


CharacterLiteral:    '\'' ~["]   ;
StringLiteral:    '"' ~["]* '"'   ;

Identifier:    [A-Z_] [A-Z0-9_]*   ;
IdentifierExtension:    ':  ' '.'? [A-Z_] [A-Z0-9_]*   ;
