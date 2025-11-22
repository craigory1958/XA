 

grammar Directives ;

options { 
	caseInsensitive = true;	
}

import Expressions ;


directive:    assembler | macro | struct | invocation   ;

assembler:    '.' Directives argumentList?   ;
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
