// Generated from /home/craigory1958/_cwg-base__Home/Computer-Architecture/Retro-Computing/XA/grammars/__workspace/Assembly.g4 by ANTLR 4.13.2
package xcom.retro.xa.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AssemblyParser}.
 */
public interface AssemblyListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#directive}.
	 * @param ctx the parse tree
	 */
	void enterDirective(AssemblyParser.DirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#directive}.
	 * @param ctx the parse tree
	 */
	void exitDirective(AssemblyParser.DirectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#assembler}.
	 * @param ctx the parse tree
	 */
	void enterAssembler(AssemblyParser.AssemblerContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#assembler}.
	 * @param ctx the parse tree
	 */
	void exitAssembler(AssemblyParser.AssemblerContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#macro}.
	 * @param ctx the parse tree
	 */
	void enterMacro(AssemblyParser.MacroContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#macro}.
	 * @param ctx the parse tree
	 */
	void exitMacro(AssemblyParser.MacroContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#struct}.
	 * @param ctx the parse tree
	 */
	void enterStruct(AssemblyParser.StructContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#struct}.
	 * @param ctx the parse tree
	 */
	void exitStruct(AssemblyParser.StructContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#invocation}.
	 * @param ctx the parse tree
	 */
	void enterInvocation(AssemblyParser.InvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#invocation}.
	 * @param ctx the parse tree
	 */
	void exitInvocation(AssemblyParser.InvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#optionList}.
	 * @param ctx the parse tree
	 */
	void enterOptionList(AssemblyParser.OptionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#optionList}.
	 * @param ctx the parse tree
	 */
	void exitOptionList(AssemblyParser.OptionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(AssemblyParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(AssemblyParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(AssemblyParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(AssemblyParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(AssemblyParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(AssemblyParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(AssemblyParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(AssemblyParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(AssemblyParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(AssemblyParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#qualifiedSymbol}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedSymbol(AssemblyParser.QualifiedSymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#qualifiedSymbol}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedSymbol(AssemblyParser.QualifiedSymbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#symbol}.
	 * @param ctx the parse tree
	 */
	void enterSymbol(AssemblyParser.SymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#symbol}.
	 * @param ctx the parse tree
	 */
	void exitSymbol(AssemblyParser.SymbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(AssemblyParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(AssemblyParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#scopedLabel}.
	 * @param ctx the parse tree
	 */
	void enterScopedLabel(AssemblyParser.ScopedLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#scopedLabel}.
	 * @param ctx the parse tree
	 */
	void exitScopedLabel(AssemblyParser.ScopedLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(AssemblyParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(AssemblyParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(AssemblyParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(AssemblyParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#org}.
	 * @param ctx the parse tree
	 */
	void enterOrg(AssemblyParser.OrgContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#org}.
	 * @param ctx the parse tree
	 */
	void exitOrg(AssemblyParser.OrgContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#dottedIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterDottedIdentifier(AssemblyParser.DottedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#dottedIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitDottedIdentifier(AssemblyParser.DottedIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#qualifiedIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedIdentifier(AssemblyParser.QualifiedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#qualifiedIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedIdentifier(AssemblyParser.QualifiedIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#scopedIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterScopedIdentifier(AssemblyParser.ScopedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#scopedIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitScopedIdentifier(AssemblyParser.ScopedIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(AssemblyParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(AssemblyParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#binary}.
	 * @param ctx the parse tree
	 */
	void enterBinary(AssemblyParser.BinaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#binary}.
	 * @param ctx the parse tree
	 */
	void exitBinary(AssemblyParser.BinaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(AssemblyParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(AssemblyParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(AssemblyParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(AssemblyParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#integerAdd}.
	 * @param ctx the parse tree
	 */
	void enterIntegerAdd(AssemblyParser.IntegerAddContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#integerAdd}.
	 * @param ctx the parse tree
	 */
	void exitIntegerAdd(AssemblyParser.IntegerAddContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#integerSubtract}.
	 * @param ctx the parse tree
	 */
	void enterIntegerSubtract(AssemblyParser.IntegerSubtractContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#integerSubtract}.
	 * @param ctx the parse tree
	 */
	void exitIntegerSubtract(AssemblyParser.IntegerSubtractContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#integerMultiply}.
	 * @param ctx the parse tree
	 */
	void enterIntegerMultiply(AssemblyParser.IntegerMultiplyContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#integerMultiply}.
	 * @param ctx the parse tree
	 */
	void exitIntegerMultiply(AssemblyParser.IntegerMultiplyContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#integerDivide}.
	 * @param ctx the parse tree
	 */
	void enterIntegerDivide(AssemblyParser.IntegerDivideContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#integerDivide}.
	 * @param ctx the parse tree
	 */
	void exitIntegerDivide(AssemblyParser.IntegerDivideContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#bitwiseShiftLeft}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseShiftLeft(AssemblyParser.BitwiseShiftLeftContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#bitwiseShiftLeft}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseShiftLeft(AssemblyParser.BitwiseShiftLeftContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#bitwiseShiftRight}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseShiftRight(AssemblyParser.BitwiseShiftRightContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#bitwiseShiftRight}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseShiftRight(AssemblyParser.BitwiseShiftRightContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#bitwiseAnd}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseAnd(AssemblyParser.BitwiseAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#bitwiseAnd}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseAnd(AssemblyParser.BitwiseAndContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#bitwiseOr}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseOr(AssemblyParser.BitwiseOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#bitwiseOr}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseOr(AssemblyParser.BitwiseOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#logicalAnd}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAnd(AssemblyParser.LogicalAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#logicalAnd}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAnd(AssemblyParser.LogicalAndContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#logicalOr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOr(AssemblyParser.LogicalOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#logicalOr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOr(AssemblyParser.LogicalOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#equal}.
	 * @param ctx the parse tree
	 */
	void enterEqual(AssemblyParser.EqualContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#equal}.
	 * @param ctx the parse tree
	 */
	void exitEqual(AssemblyParser.EqualContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#notEqual}.
	 * @param ctx the parse tree
	 */
	void enterNotEqual(AssemblyParser.NotEqualContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#notEqual}.
	 * @param ctx the parse tree
	 */
	void exitNotEqual(AssemblyParser.NotEqualContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#lessThan}.
	 * @param ctx the parse tree
	 */
	void enterLessThan(AssemblyParser.LessThanContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#lessThan}.
	 * @param ctx the parse tree
	 */
	void exitLessThan(AssemblyParser.LessThanContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#greaterThan}.
	 * @param ctx the parse tree
	 */
	void enterGreaterThan(AssemblyParser.GreaterThanContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#greaterThan}.
	 * @param ctx the parse tree
	 */
	void exitGreaterThan(AssemblyParser.GreaterThanContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#plus}.
	 * @param ctx the parse tree
	 */
	void enterPlus(AssemblyParser.PlusContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#plus}.
	 * @param ctx the parse tree
	 */
	void exitPlus(AssemblyParser.PlusContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#minus}.
	 * @param ctx the parse tree
	 */
	void enterMinus(AssemblyParser.MinusContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#minus}.
	 * @param ctx the parse tree
	 */
	void exitMinus(AssemblyParser.MinusContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#highByte}.
	 * @param ctx the parse tree
	 */
	void enterHighByte(AssemblyParser.HighByteContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#highByte}.
	 * @param ctx the parse tree
	 */
	void exitHighByte(AssemblyParser.HighByteContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#lowByte}.
	 * @param ctx the parse tree
	 */
	void enterLowByte(AssemblyParser.LowByteContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#lowByte}.
	 * @param ctx the parse tree
	 */
	void exitLowByte(AssemblyParser.LowByteContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#logicalNot}.
	 * @param ctx the parse tree
	 */
	void enterLogicalNot(AssemblyParser.LogicalNotContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#logicalNot}.
	 * @param ctx the parse tree
	 */
	void exitLogicalNot(AssemblyParser.LogicalNotContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(AssemblyParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(AssemblyParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteral(AssemblyParser.NumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteral(AssemblyParser.NumericLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#alphanumericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterAlphanumericLiteral(AssemblyParser.AlphanumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#alphanumericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitAlphanumericLiteral(AssemblyParser.AlphanumericLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#binaryLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBinaryLiteral(AssemblyParser.BinaryLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#binaryLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBinaryLiteral(AssemblyParser.BinaryLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#octalLiteral}.
	 * @param ctx the parse tree
	 */
	void enterOctalLiteral(AssemblyParser.OctalLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#octalLiteral}.
	 * @param ctx the parse tree
	 */
	void exitOctalLiteral(AssemblyParser.OctalLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#decimalLiteral}.
	 * @param ctx the parse tree
	 */
	void enterDecimalLiteral(AssemblyParser.DecimalLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#decimalLiteral}.
	 * @param ctx the parse tree
	 */
	void exitDecimalLiteral(AssemblyParser.DecimalLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#hexLiteral}.
	 * @param ctx the parse tree
	 */
	void enterHexLiteral(AssemblyParser.HexLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#hexLiteral}.
	 * @param ctx the parse tree
	 */
	void exitHexLiteral(AssemblyParser.HexLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#characterLiteral}.
	 * @param ctx the parse tree
	 */
	void enterCharacterLiteral(AssemblyParser.CharacterLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#characterLiteral}.
	 * @param ctx the parse tree
	 */
	void exitCharacterLiteral(AssemblyParser.CharacterLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(AssemblyParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(AssemblyParser.StringLiteralContext ctx);
}