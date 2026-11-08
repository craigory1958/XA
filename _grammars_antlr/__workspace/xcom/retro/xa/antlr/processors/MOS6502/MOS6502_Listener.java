// Generated from /home/craigory1958/_cwg-base__Home/Computer-Architecture/Retro-Computing/XA/grammars/__workspace/MOS6502_.g4 by ANTLR 4.13.2
package xcom.retro.xa.antlr.processors.MOS6502;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MOS6502_Parser}.
 */
public interface MOS6502_Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterAssembly(MOS6502_Parser.AssemblyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitAssembly(MOS6502_Parser.AssemblyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MOS6502_Parser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MOS6502_Parser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#eol}.
	 * @param ctx the parse tree
	 */
	void enterEol(MOS6502_Parser.EolContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#eol}.
	 * @param ctx the parse tree
	 */
	void exitEol(MOS6502_Parser.EolContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(MOS6502_Parser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(MOS6502_Parser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#branchOpcode}.
	 * @param ctx the parse tree
	 */
	void enterBranchOpcode(MOS6502_Parser.BranchOpcodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#branchOpcode}.
	 * @param ctx the parse tree
	 */
	void exitBranchOpcode(MOS6502_Parser.BranchOpcodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#opcode}.
	 * @param ctx the parse tree
	 */
	void enterOpcode(MOS6502_Parser.OpcodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#opcode}.
	 * @param ctx the parse tree
	 */
	void exitOpcode(MOS6502_Parser.OpcodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#branchAddressingMode}.
	 * @param ctx the parse tree
	 */
	void enterBranchAddressingMode(MOS6502_Parser.BranchAddressingModeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#branchAddressingMode}.
	 * @param ctx the parse tree
	 */
	void exitBranchAddressingMode(MOS6502_Parser.BranchAddressingModeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#addressingMode}.
	 * @param ctx the parse tree
	 */
	void enterAddressingMode(MOS6502_Parser.AddressingModeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#addressingMode}.
	 * @param ctx the parse tree
	 */
	void exitAddressingMode(MOS6502_Parser.AddressingModeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#absolute}.
	 * @param ctx the parse tree
	 */
	void enterAbsolute(MOS6502_Parser.AbsoluteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#absolute}.
	 * @param ctx the parse tree
	 */
	void exitAbsolute(MOS6502_Parser.AbsoluteContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#accumulator}.
	 * @param ctx the parse tree
	 */
	void enterAccumulator(MOS6502_Parser.AccumulatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#accumulator}.
	 * @param ctx the parse tree
	 */
	void exitAccumulator(MOS6502_Parser.AccumulatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#immediate}.
	 * @param ctx the parse tree
	 */
	void enterImmediate(MOS6502_Parser.ImmediateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#immediate}.
	 * @param ctx the parse tree
	 */
	void exitImmediate(MOS6502_Parser.ImmediateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#implied}.
	 * @param ctx the parse tree
	 */
	void enterImplied(MOS6502_Parser.ImpliedContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#implied}.
	 * @param ctx the parse tree
	 */
	void exitImplied(MOS6502_Parser.ImpliedContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#indexedX}.
	 * @param ctx the parse tree
	 */
	void enterIndexedX(MOS6502_Parser.IndexedXContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#indexedX}.
	 * @param ctx the parse tree
	 */
	void exitIndexedX(MOS6502_Parser.IndexedXContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#indexedY}.
	 * @param ctx the parse tree
	 */
	void enterIndexedY(MOS6502_Parser.IndexedYContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#indexedY}.
	 * @param ctx the parse tree
	 */
	void exitIndexedY(MOS6502_Parser.IndexedYContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#indirect}.
	 * @param ctx the parse tree
	 */
	void enterIndirect(MOS6502_Parser.IndirectContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#indirect}.
	 * @param ctx the parse tree
	 */
	void exitIndirect(MOS6502_Parser.IndirectContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#zpIndirectX}.
	 * @param ctx the parse tree
	 */
	void enterZpIndirectX(MOS6502_Parser.ZpIndirectXContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#zpIndirectX}.
	 * @param ctx the parse tree
	 */
	void exitZpIndirectX(MOS6502_Parser.ZpIndirectXContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#zpIndirectY}.
	 * @param ctx the parse tree
	 */
	void enterZpIndirectY(MOS6502_Parser.ZpIndirectYContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#zpIndirectY}.
	 * @param ctx the parse tree
	 */
	void exitZpIndirectY(MOS6502_Parser.ZpIndirectYContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#relative}.
	 * @param ctx the parse tree
	 */
	void enterRelative(MOS6502_Parser.RelativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#relative}.
	 * @param ctx the parse tree
	 */
	void exitRelative(MOS6502_Parser.RelativeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#directive}.
	 * @param ctx the parse tree
	 */
	void enterDirective(MOS6502_Parser.DirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#directive}.
	 * @param ctx the parse tree
	 */
	void exitDirective(MOS6502_Parser.DirectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#assembler}.
	 * @param ctx the parse tree
	 */
	void enterAssembler(MOS6502_Parser.AssemblerContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#assembler}.
	 * @param ctx the parse tree
	 */
	void exitAssembler(MOS6502_Parser.AssemblerContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#macro}.
	 * @param ctx the parse tree
	 */
	void enterMacro(MOS6502_Parser.MacroContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#macro}.
	 * @param ctx the parse tree
	 */
	void exitMacro(MOS6502_Parser.MacroContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#struct}.
	 * @param ctx the parse tree
	 */
	void enterStruct(MOS6502_Parser.StructContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#struct}.
	 * @param ctx the parse tree
	 */
	void exitStruct(MOS6502_Parser.StructContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#invocation}.
	 * @param ctx the parse tree
	 */
	void enterInvocation(MOS6502_Parser.InvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#invocation}.
	 * @param ctx the parse tree
	 */
	void exitInvocation(MOS6502_Parser.InvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#optionList}.
	 * @param ctx the parse tree
	 */
	void enterOptionList(MOS6502_Parser.OptionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#optionList}.
	 * @param ctx the parse tree
	 */
	void exitOptionList(MOS6502_Parser.OptionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(MOS6502_Parser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(MOS6502_Parser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(MOS6502_Parser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(MOS6502_Parser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(MOS6502_Parser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(MOS6502_Parser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(MOS6502_Parser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(MOS6502_Parser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(MOS6502_Parser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(MOS6502_Parser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#qualifiedSymbol}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedSymbol(MOS6502_Parser.QualifiedSymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#qualifiedSymbol}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedSymbol(MOS6502_Parser.QualifiedSymbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#symbol}.
	 * @param ctx the parse tree
	 */
	void enterSymbol(MOS6502_Parser.SymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#symbol}.
	 * @param ctx the parse tree
	 */
	void exitSymbol(MOS6502_Parser.SymbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(MOS6502_Parser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(MOS6502_Parser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#scopedLabel}.
	 * @param ctx the parse tree
	 */
	void enterScopedLabel(MOS6502_Parser.ScopedLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#scopedLabel}.
	 * @param ctx the parse tree
	 */
	void exitScopedLabel(MOS6502_Parser.ScopedLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(MOS6502_Parser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(MOS6502_Parser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(MOS6502_Parser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(MOS6502_Parser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#org}.
	 * @param ctx the parse tree
	 */
	void enterOrg(MOS6502_Parser.OrgContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#org}.
	 * @param ctx the parse tree
	 */
	void exitOrg(MOS6502_Parser.OrgContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#dottedIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterDottedIdentifier(MOS6502_Parser.DottedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#dottedIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitDottedIdentifier(MOS6502_Parser.DottedIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#qualifiedIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedIdentifier(MOS6502_Parser.QualifiedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#qualifiedIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedIdentifier(MOS6502_Parser.QualifiedIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#scopedIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterScopedIdentifier(MOS6502_Parser.ScopedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#scopedIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitScopedIdentifier(MOS6502_Parser.ScopedIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(MOS6502_Parser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(MOS6502_Parser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#binary}.
	 * @param ctx the parse tree
	 */
	void enterBinary(MOS6502_Parser.BinaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#binary}.
	 * @param ctx the parse tree
	 */
	void exitBinary(MOS6502_Parser.BinaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(MOS6502_Parser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(MOS6502_Parser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(MOS6502_Parser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(MOS6502_Parser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#integerAdd}.
	 * @param ctx the parse tree
	 */
	void enterIntegerAdd(MOS6502_Parser.IntegerAddContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#integerAdd}.
	 * @param ctx the parse tree
	 */
	void exitIntegerAdd(MOS6502_Parser.IntegerAddContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#integerSubtract}.
	 * @param ctx the parse tree
	 */
	void enterIntegerSubtract(MOS6502_Parser.IntegerSubtractContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#integerSubtract}.
	 * @param ctx the parse tree
	 */
	void exitIntegerSubtract(MOS6502_Parser.IntegerSubtractContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#integerMultiply}.
	 * @param ctx the parse tree
	 */
	void enterIntegerMultiply(MOS6502_Parser.IntegerMultiplyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#integerMultiply}.
	 * @param ctx the parse tree
	 */
	void exitIntegerMultiply(MOS6502_Parser.IntegerMultiplyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#integerDivide}.
	 * @param ctx the parse tree
	 */
	void enterIntegerDivide(MOS6502_Parser.IntegerDivideContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#integerDivide}.
	 * @param ctx the parse tree
	 */
	void exitIntegerDivide(MOS6502_Parser.IntegerDivideContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#bitwiseShiftLeft}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseShiftLeft(MOS6502_Parser.BitwiseShiftLeftContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#bitwiseShiftLeft}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseShiftLeft(MOS6502_Parser.BitwiseShiftLeftContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#bitwiseShiftRight}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseShiftRight(MOS6502_Parser.BitwiseShiftRightContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#bitwiseShiftRight}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseShiftRight(MOS6502_Parser.BitwiseShiftRightContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#bitwiseAnd}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseAnd(MOS6502_Parser.BitwiseAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#bitwiseAnd}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseAnd(MOS6502_Parser.BitwiseAndContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#bitwiseOr}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseOr(MOS6502_Parser.BitwiseOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#bitwiseOr}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseOr(MOS6502_Parser.BitwiseOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#logicalAnd}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAnd(MOS6502_Parser.LogicalAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#logicalAnd}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAnd(MOS6502_Parser.LogicalAndContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#logicalOr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOr(MOS6502_Parser.LogicalOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#logicalOr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOr(MOS6502_Parser.LogicalOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#equal}.
	 * @param ctx the parse tree
	 */
	void enterEqual(MOS6502_Parser.EqualContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#equal}.
	 * @param ctx the parse tree
	 */
	void exitEqual(MOS6502_Parser.EqualContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#notEqual}.
	 * @param ctx the parse tree
	 */
	void enterNotEqual(MOS6502_Parser.NotEqualContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#notEqual}.
	 * @param ctx the parse tree
	 */
	void exitNotEqual(MOS6502_Parser.NotEqualContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#lessThan}.
	 * @param ctx the parse tree
	 */
	void enterLessThan(MOS6502_Parser.LessThanContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#lessThan}.
	 * @param ctx the parse tree
	 */
	void exitLessThan(MOS6502_Parser.LessThanContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#greaterThan}.
	 * @param ctx the parse tree
	 */
	void enterGreaterThan(MOS6502_Parser.GreaterThanContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#greaterThan}.
	 * @param ctx the parse tree
	 */
	void exitGreaterThan(MOS6502_Parser.GreaterThanContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#plus}.
	 * @param ctx the parse tree
	 */
	void enterPlus(MOS6502_Parser.PlusContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#plus}.
	 * @param ctx the parse tree
	 */
	void exitPlus(MOS6502_Parser.PlusContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#minus}.
	 * @param ctx the parse tree
	 */
	void enterMinus(MOS6502_Parser.MinusContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#minus}.
	 * @param ctx the parse tree
	 */
	void exitMinus(MOS6502_Parser.MinusContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#highByte}.
	 * @param ctx the parse tree
	 */
	void enterHighByte(MOS6502_Parser.HighByteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#highByte}.
	 * @param ctx the parse tree
	 */
	void exitHighByte(MOS6502_Parser.HighByteContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#lowByte}.
	 * @param ctx the parse tree
	 */
	void enterLowByte(MOS6502_Parser.LowByteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#lowByte}.
	 * @param ctx the parse tree
	 */
	void exitLowByte(MOS6502_Parser.LowByteContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#logicalNot}.
	 * @param ctx the parse tree
	 */
	void enterLogicalNot(MOS6502_Parser.LogicalNotContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#logicalNot}.
	 * @param ctx the parse tree
	 */
	void exitLogicalNot(MOS6502_Parser.LogicalNotContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(MOS6502_Parser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(MOS6502_Parser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteral(MOS6502_Parser.NumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteral(MOS6502_Parser.NumericLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#alphanumericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterAlphanumericLiteral(MOS6502_Parser.AlphanumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#alphanumericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitAlphanumericLiteral(MOS6502_Parser.AlphanumericLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#binaryLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBinaryLiteral(MOS6502_Parser.BinaryLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#binaryLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBinaryLiteral(MOS6502_Parser.BinaryLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#octalLiteral}.
	 * @param ctx the parse tree
	 */
	void enterOctalLiteral(MOS6502_Parser.OctalLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#octalLiteral}.
	 * @param ctx the parse tree
	 */
	void exitOctalLiteral(MOS6502_Parser.OctalLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#decimalLiteral}.
	 * @param ctx the parse tree
	 */
	void enterDecimalLiteral(MOS6502_Parser.DecimalLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#decimalLiteral}.
	 * @param ctx the parse tree
	 */
	void exitDecimalLiteral(MOS6502_Parser.DecimalLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#hexLiteral}.
	 * @param ctx the parse tree
	 */
	void enterHexLiteral(MOS6502_Parser.HexLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#hexLiteral}.
	 * @param ctx the parse tree
	 */
	void exitHexLiteral(MOS6502_Parser.HexLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#characterLiteral}.
	 * @param ctx the parse tree
	 */
	void enterCharacterLiteral(MOS6502_Parser.CharacterLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#characterLiteral}.
	 * @param ctx the parse tree
	 */
	void exitCharacterLiteral(MOS6502_Parser.CharacterLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MOS6502_Parser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(MOS6502_Parser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MOS6502_Parser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(MOS6502_Parser.StringLiteralContext ctx);
}