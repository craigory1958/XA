 

grammar Expressions ;

options { 
	caseInsensitive = true;	
}


expr:  term ( ( binary | comparison ) term)* ;

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
