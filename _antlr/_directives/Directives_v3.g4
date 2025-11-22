 

grammar Directives ;

options { 
	caseInsensitive = true;	
}

import Expressions ;


directive:  assembler | macro | struct | invocation ;

assembler:  '.' Directives argumentList? ;
macro: '.' symbol  '.macro' optionList? ;
struct: '.' symbol '.struct' optionList? ;
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

symbol:  Directives | Identifier IdentifierExtensiom* ;
ideogram:  '.' symbol ;


label: Identifier ;
scopedLabel: '@'? Identifier ;


Directives:  
	'align' | 
	'byte' | 
	'end' | 'endif' | 'endmacro' | 'endstruct' | 'equ' | 
	'if' |'ifdef' | 'ifndef' | 'include' | 
	'list' |
	'nolist' |
	'org' | 
	'page' |
	'segment' |
	'word' 
	;
