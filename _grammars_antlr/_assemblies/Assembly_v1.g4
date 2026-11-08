

//
// Assembly (v1)
//

assembly:  ( statement eol )* EOF ;
statement:  ( label | scopedLabel )? ( instruction | directive )? ;

eol:  EOL ;
Comment:  ';' ~[\r\n]* -> skip ;

EOL:  [\r\n] ;
WS:  [ \t] -> skip ;
