 

grammar Directives ;

options { 
	caseInsensitive = true;	
}

import Expressions ;



directive:  label? '.' ( Directives | macro ) argumentList? inject? ;
macro:  Identifier ;
inject:  Inject ;


argumentList:   argument ( ',' argumentList )? ; 
argument:  dottedIdentifier | expr ;
dottedIdentifier:  '.' Identifier ;

label:  Identifier ;


Directives:  'equ' | 'include' | 'macro' ;

Inject:  '->' ~ [\r\n]* ;
