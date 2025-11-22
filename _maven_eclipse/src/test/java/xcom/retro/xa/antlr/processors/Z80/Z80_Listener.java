// Generated from Z80_.g4 by ANTLR 4.13.2
package xcom.retro.xa.antlr.processors.Z80;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Z80_Parser}.
 */
public interface Z80_Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterAssembly(Z80_Parser.AssemblyContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitAssembly(Z80_Parser.AssemblyContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(Z80_Parser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(Z80_Parser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(Z80_Parser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(Z80_Parser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#branchOpcode}.
	 * @param ctx the parse tree
	 */
	void enterBranchOpcode(Z80_Parser.BranchOpcodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#branchOpcode}.
	 * @param ctx the parse tree
	 */
	void exitBranchOpcode(Z80_Parser.BranchOpcodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#opcode}.
	 * @param ctx the parse tree
	 */
	void enterOpcode(Z80_Parser.OpcodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#opcode}.
	 * @param ctx the parse tree
	 */
	void exitOpcode(Z80_Parser.OpcodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#branchAddressingMode}.
	 * @param ctx the parse tree
	 */
	void enterBranchAddressingMode(Z80_Parser.BranchAddressingModeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#branchAddressingMode}.
	 * @param ctx the parse tree
	 */
	void exitBranchAddressingMode(Z80_Parser.BranchAddressingModeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#addressingMode}.
	 * @param ctx the parse tree
	 */
	void enterAddressingMode(Z80_Parser.AddressingModeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#addressingMode}.
	 * @param ctx the parse tree
	 */
	void exitAddressingMode(Z80_Parser.AddressingModeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#absolute}.
	 * @param ctx the parse tree
	 */
	void enterAbsolute(Z80_Parser.AbsoluteContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#absolute}.
	 * @param ctx the parse tree
	 */
	void exitAbsolute(Z80_Parser.AbsoluteContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#accumulator}.
	 * @param ctx the parse tree
	 */
	void enterAccumulator(Z80_Parser.AccumulatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#accumulator}.
	 * @param ctx the parse tree
	 */
	void exitAccumulator(Z80_Parser.AccumulatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#immediate}.
	 * @param ctx the parse tree
	 */
	void enterImmediate(Z80_Parser.ImmediateContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#immediate}.
	 * @param ctx the parse tree
	 */
	void exitImmediate(Z80_Parser.ImmediateContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#implied}.
	 * @param ctx the parse tree
	 */
	void enterImplied(Z80_Parser.ImpliedContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#implied}.
	 * @param ctx the parse tree
	 */
	void exitImplied(Z80_Parser.ImpliedContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#indexedX}.
	 * @param ctx the parse tree
	 */
	void enterIndexedX(Z80_Parser.IndexedXContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#indexedX}.
	 * @param ctx the parse tree
	 */
	void exitIndexedX(Z80_Parser.IndexedXContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#indexedY}.
	 * @param ctx the parse tree
	 */
	void enterIndexedY(Z80_Parser.IndexedYContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#indexedY}.
	 * @param ctx the parse tree
	 */
	void exitIndexedY(Z80_Parser.IndexedYContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#indirect}.
	 * @param ctx the parse tree
	 */
	void enterIndirect(Z80_Parser.IndirectContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#indirect}.
	 * @param ctx the parse tree
	 */
	void exitIndirect(Z80_Parser.IndirectContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#zpIndirectX}.
	 * @param ctx the parse tree
	 */
	void enterZpIndirectX(Z80_Parser.ZpIndirectXContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#zpIndirectX}.
	 * @param ctx the parse tree
	 */
	void exitZpIndirectX(Z80_Parser.ZpIndirectXContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#zpIndirectY}.
	 * @param ctx the parse tree
	 */
	void enterZpIndirectY(Z80_Parser.ZpIndirectYContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#zpIndirectY}.
	 * @param ctx the parse tree
	 */
	void exitZpIndirectY(Z80_Parser.ZpIndirectYContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#relative}.
	 * @param ctx the parse tree
	 */
	void enterRelative(Z80_Parser.RelativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#relative}.
	 * @param ctx the parse tree
	 */
	void exitRelative(Z80_Parser.RelativeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#directive}.
	 * @param ctx the parse tree
	 */
	void enterDirective(Z80_Parser.DirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#directive}.
	 * @param ctx the parse tree
	 */
	void exitDirective(Z80_Parser.DirectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#assembler}.
	 * @param ctx the parse tree
	 */
	void enterAssembler(Z80_Parser.AssemblerContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#assembler}.
	 * @param ctx the parse tree
	 */
	void exitAssembler(Z80_Parser.AssemblerContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#macro}.
	 * @param ctx the parse tree
	 */
	void enterMacro(Z80_Parser.MacroContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#macro}.
	 * @param ctx the parse tree
	 */
	void exitMacro(Z80_Parser.MacroContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#struct}.
	 * @param ctx the parse tree
	 */
	void enterStruct(Z80_Parser.StructContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#struct}.
	 * @param ctx the parse tree
	 */
	void exitStruct(Z80_Parser.StructContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#invocation}.
	 * @param ctx the parse tree
	 */
	void enterInvocation(Z80_Parser.InvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#invocation}.
	 * @param ctx the parse tree
	 */
	void exitInvocation(Z80_Parser.InvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#optionList}.
	 * @param ctx the parse tree
	 */
	void enterOptionList(Z80_Parser.OptionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#optionList}.
	 * @param ctx the parse tree
	 */
	void exitOptionList(Z80_Parser.OptionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(Z80_Parser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(Z80_Parser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(Z80_Parser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(Z80_Parser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(Z80_Parser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(Z80_Parser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(Z80_Parser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(Z80_Parser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(Z80_Parser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(Z80_Parser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#qualifiedSymbol}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedSymbol(Z80_Parser.QualifiedSymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#qualifiedSymbol}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedSymbol(Z80_Parser.QualifiedSymbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#symbol}.
	 * @param ctx the parse tree
	 */
	void enterSymbol(Z80_Parser.SymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#symbol}.
	 * @param ctx the parse tree
	 */
	void exitSymbol(Z80_Parser.SymbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(Z80_Parser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(Z80_Parser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#scopedLabel}.
	 * @param ctx the parse tree
	 */
	void enterScopedLabel(Z80_Parser.ScopedLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#scopedLabel}.
	 * @param ctx the parse tree
	 */
	void exitScopedLabel(Z80_Parser.ScopedLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(Z80_Parser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(Z80_Parser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(Z80_Parser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(Z80_Parser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#org}.
	 * @param ctx the parse tree
	 */
	void enterOrg(Z80_Parser.OrgContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#org}.
	 * @param ctx the parse tree
	 */
	void exitOrg(Z80_Parser.OrgContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#dottedIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterDottedIdentifier(Z80_Parser.DottedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#dottedIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitDottedIdentifier(Z80_Parser.DottedIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#qualifiedIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedIdentifier(Z80_Parser.QualifiedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#qualifiedIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedIdentifier(Z80_Parser.QualifiedIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#scopedIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterScopedIdentifier(Z80_Parser.ScopedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#scopedIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitScopedIdentifier(Z80_Parser.ScopedIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(Z80_Parser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(Z80_Parser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#binary}.
	 * @param ctx the parse tree
	 */
	void enterBinary(Z80_Parser.BinaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#binary}.
	 * @param ctx the parse tree
	 */
	void exitBinary(Z80_Parser.BinaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(Z80_Parser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(Z80_Parser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(Z80_Parser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(Z80_Parser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#integerAdd}.
	 * @param ctx the parse tree
	 */
	void enterIntegerAdd(Z80_Parser.IntegerAddContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#integerAdd}.
	 * @param ctx the parse tree
	 */
	void exitIntegerAdd(Z80_Parser.IntegerAddContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#integerSubtract}.
	 * @param ctx the parse tree
	 */
	void enterIntegerSubtract(Z80_Parser.IntegerSubtractContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#integerSubtract}.
	 * @param ctx the parse tree
	 */
	void exitIntegerSubtract(Z80_Parser.IntegerSubtractContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#integerMultiply}.
	 * @param ctx the parse tree
	 */
	void enterIntegerMultiply(Z80_Parser.IntegerMultiplyContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#integerMultiply}.
	 * @param ctx the parse tree
	 */
	void exitIntegerMultiply(Z80_Parser.IntegerMultiplyContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#integerDivide}.
	 * @param ctx the parse tree
	 */
	void enterIntegerDivide(Z80_Parser.IntegerDivideContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#integerDivide}.
	 * @param ctx the parse tree
	 */
	void exitIntegerDivide(Z80_Parser.IntegerDivideContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#bitwiseShiftLeft}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseShiftLeft(Z80_Parser.BitwiseShiftLeftContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#bitwiseShiftLeft}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseShiftLeft(Z80_Parser.BitwiseShiftLeftContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#bitwiseShiftRight}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseShiftRight(Z80_Parser.BitwiseShiftRightContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#bitwiseShiftRight}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseShiftRight(Z80_Parser.BitwiseShiftRightContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#bitwiseAnd}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseAnd(Z80_Parser.BitwiseAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#bitwiseAnd}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseAnd(Z80_Parser.BitwiseAndContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#bitwiseOr}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseOr(Z80_Parser.BitwiseOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#bitwiseOr}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseOr(Z80_Parser.BitwiseOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#logicalAnd}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAnd(Z80_Parser.LogicalAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#logicalAnd}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAnd(Z80_Parser.LogicalAndContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#logicalOr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOr(Z80_Parser.LogicalOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#logicalOr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOr(Z80_Parser.LogicalOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#equal}.
	 * @param ctx the parse tree
	 */
	void enterEqual(Z80_Parser.EqualContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#equal}.
	 * @param ctx the parse tree
	 */
	void exitEqual(Z80_Parser.EqualContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#notEqual}.
	 * @param ctx the parse tree
	 */
	void enterNotEqual(Z80_Parser.NotEqualContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#notEqual}.
	 * @param ctx the parse tree
	 */
	void exitNotEqual(Z80_Parser.NotEqualContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#lessThan}.
	 * @param ctx the parse tree
	 */
	void enterLessThan(Z80_Parser.LessThanContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#lessThan}.
	 * @param ctx the parse tree
	 */
	void exitLessThan(Z80_Parser.LessThanContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#greaterThan}.
	 * @param ctx the parse tree
	 */
	void enterGreaterThan(Z80_Parser.GreaterThanContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#greaterThan}.
	 * @param ctx the parse tree
	 */
	void exitGreaterThan(Z80_Parser.GreaterThanContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#plus}.
	 * @param ctx the parse tree
	 */
	void enterPlus(Z80_Parser.PlusContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#plus}.
	 * @param ctx the parse tree
	 */
	void exitPlus(Z80_Parser.PlusContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#minus}.
	 * @param ctx the parse tree
	 */
	void enterMinus(Z80_Parser.MinusContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#minus}.
	 * @param ctx the parse tree
	 */
	void exitMinus(Z80_Parser.MinusContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#highByte}.
	 * @param ctx the parse tree
	 */
	void enterHighByte(Z80_Parser.HighByteContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#highByte}.
	 * @param ctx the parse tree
	 */
	void exitHighByte(Z80_Parser.HighByteContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#lowByte}.
	 * @param ctx the parse tree
	 */
	void enterLowByte(Z80_Parser.LowByteContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#lowByte}.
	 * @param ctx the parse tree
	 */
	void exitLowByte(Z80_Parser.LowByteContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#logicalNot}.
	 * @param ctx the parse tree
	 */
	void enterLogicalNot(Z80_Parser.LogicalNotContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#logicalNot}.
	 * @param ctx the parse tree
	 */
	void exitLogicalNot(Z80_Parser.LogicalNotContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(Z80_Parser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(Z80_Parser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteral(Z80_Parser.NumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteral(Z80_Parser.NumericLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#alphanumericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterAlphanumericLiteral(Z80_Parser.AlphanumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#alphanumericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitAlphanumericLiteral(Z80_Parser.AlphanumericLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#binaryLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBinaryLiteral(Z80_Parser.BinaryLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#binaryLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBinaryLiteral(Z80_Parser.BinaryLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#octalLiteral}.
	 * @param ctx the parse tree
	 */
	void enterOctalLiteral(Z80_Parser.OctalLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#octalLiteral}.
	 * @param ctx the parse tree
	 */
	void exitOctalLiteral(Z80_Parser.OctalLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#decimalLiteral}.
	 * @param ctx the parse tree
	 */
	void enterDecimalLiteral(Z80_Parser.DecimalLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#decimalLiteral}.
	 * @param ctx the parse tree
	 */
	void exitDecimalLiteral(Z80_Parser.DecimalLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#hexLiteral}.
	 * @param ctx the parse tree
	 */
	void enterHexLiteral(Z80_Parser.HexLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#hexLiteral}.
	 * @param ctx the parse tree
	 */
	void exitHexLiteral(Z80_Parser.HexLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#characterLiteral}.
	 * @param ctx the parse tree
	 */
	void enterCharacterLiteral(Z80_Parser.CharacterLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#characterLiteral}.
	 * @param ctx the parse tree
	 */
	void exitCharacterLiteral(Z80_Parser.CharacterLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(Z80_Parser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(Z80_Parser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link Z80_Parser#eol}.
	 * @param ctx the parse tree
	 */
	void enterEol(Z80_Parser.EolContext ctx);
	/**
	 * Exit a parse tree produced by {@link Z80_Parser#eol}.
	 * @param ctx the parse tree
	 */
	void exitEol(Z80_Parser.EolContext ctx);
}