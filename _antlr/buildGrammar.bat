
SET uPfam=6502
IF NOT "%1"=="" SET uPfam=%1

SET uP=MOS6502
IF NOT "%2"=="" SET uP=%2

SET uPver=v4
IF NOT "%3"=="" SET uPver=%3

rem SET dirVer=v4
rem IF NOT "%4"=="" SET dirVer=%4

rem SET exprVer=v3
rem IF NOT "%5"=="" SET exprVer=%5

SET assVer=v1
IF NOT "%5"=="" SET assVer=%5

SET sourceDir="H:\_cwg-base__Home\Computer Archtecture\Retro Computing\xa\grammars"
SET targetDir="H:\_cwg-base__Home\Computer Archtecture\Retro Computing\xa\__eclipse\xa\target\generated-sources\antlr4"


COPY %sourceDir%\processors\_%uPfam%\%uP%_%uPver%.g4 %uP%_.g4
rem COPY %sourceDir%\_directives\Directives_%dirVer%.g4 Directives.g4
rem COPY %sourceDir%\_expressions\Expressions_%exprVer%.g4 Expressions.g4
COPY %sourceDir%\_assemblies\Assembly_%assVer%.g4 Assembly.g4


java org.antlr.v4.Tool  -o %targetDir%\xcom\retro\xa\antlr\processors\%uP%  -package xcom.retro.xa.antlr.processors.%uP% %uP%_.g4
rem java org.antlr.v4.Tool  -o %targetDir%\xcom\retro\xa\antlr  -package xcom.retro.xa.antlr  Directives.g4
rem java org.antlr.v4.Tool  -o %targetDir%\xcom\retro\xa\antlr  -package xcom.retro.xa.antlr  Expressions.g4
java org.antlr.v4.Tool  -o %targetDir%\xcom\retro\xa\antlr  -package xcom.retro.xa.antlr  Assembly.g4


DEL /Q %targetDir%\*.*
DEL *.g4
