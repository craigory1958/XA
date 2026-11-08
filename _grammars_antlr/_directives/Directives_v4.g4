 

//
// Directives (v4)
//

directive:  assembler | macro | struct | invocation ;

assembler:  '.' Directives parameterList? ;
macro:  symbol  '.macro' optionList? ;
struct:  symbol '.struct' optionList? ;
invocation:  qualifiedSymbol  parameterList? ;


// Macro definition ...
optionList:  option ( ',' optionList )? ; 
option:  identifier assignment? ;

// Assembler Directive or Macro Invocation ...
parameterList:  ( parameter ( ',' parameterList )? ) | ( ',' parameterList? ) ; 
parameter:  ( identifier assignment ) | ( dottedIdentifier assignment ) | ( dottedIdentifier ) | argument ;


assignment:  '=' argument ;
argument:  expr ;

qualifiedSymbol:  ( symbol ':' )* symbol ;
symbol:  '.' ( Identifier | Directives ) ;


label:  Identifier ;
scopedLabel:  '@' Identifier ;


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
