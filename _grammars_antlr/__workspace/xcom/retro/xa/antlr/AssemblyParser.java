// Generated from /home/craigory1958/_cwg-base__Home/Computer-Architecture/Retro-Computing/XA/grammars/__workspace/Assembly.g4 by ANTLR 4.13.2
package xcom.retro.xa.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class AssemblyParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, Directives=29, BinaryLiteral=30, 
		OctalLiteral=31, DecimalLiteral=32, HexLiteral=33, CharacterLiteral=34, 
		StringLiteral=35, Identifier=36, IdentifierExtension=37;
	public static final int
		RULE_directive = 0, RULE_assembler = 1, RULE_macro = 2, RULE_struct = 3, 
		RULE_invocation = 4, RULE_optionList = 5, RULE_option = 6, RULE_parameterList = 7, 
		RULE_parameter = 8, RULE_assignment = 9, RULE_argument = 10, RULE_qualifiedSymbol = 11, 
		RULE_symbol = 12, RULE_label = 13, RULE_scopedLabel = 14, RULE_expr = 15, 
		RULE_term = 16, RULE_org = 17, RULE_dottedIdentifier = 18, RULE_qualifiedIdentifier = 19, 
		RULE_scopedIdentifier = 20, RULE_identifier = 21, RULE_binary = 22, RULE_comparison = 23, 
		RULE_unary = 24, RULE_integerAdd = 25, RULE_integerSubtract = 26, RULE_integerMultiply = 27, 
		RULE_integerDivide = 28, RULE_bitwiseShiftLeft = 29, RULE_bitwiseShiftRight = 30, 
		RULE_bitwiseAnd = 31, RULE_bitwiseOr = 32, RULE_logicalAnd = 33, RULE_logicalOr = 34, 
		RULE_equal = 35, RULE_notEqual = 36, RULE_lessThan = 37, RULE_greaterThan = 38, 
		RULE_plus = 39, RULE_minus = 40, RULE_highByte = 41, RULE_lowByte = 42, 
		RULE_logicalNot = 43, RULE_literal = 44, RULE_numericLiteral = 45, RULE_alphanumericLiteral = 46, 
		RULE_binaryLiteral = 47, RULE_octalLiteral = 48, RULE_decimalLiteral = 49, 
		RULE_hexLiteral = 50, RULE_characterLiteral = 51, RULE_stringLiteral = 52;
	private static String[] makeRuleNames() {
		return new String[] {
			"directive", "assembler", "macro", "struct", "invocation", "optionList", 
			"option", "parameterList", "parameter", "assignment", "argument", "qualifiedSymbol", 
			"symbol", "label", "scopedLabel", "expr", "term", "org", "dottedIdentifier", 
			"qualifiedIdentifier", "scopedIdentifier", "identifier", "binary", "comparison", 
			"unary", "integerAdd", "integerSubtract", "integerMultiply", "integerDivide", 
			"bitwiseShiftLeft", "bitwiseShiftRight", "bitwiseAnd", "bitwiseOr", "logicalAnd", 
			"logicalOr", "equal", "notEqual", "lessThan", "greaterThan", "plus", 
			"minus", "highByte", "lowByte", "logicalNot", "literal", "numericLiteral", 
			"alphanumericLiteral", "binaryLiteral", "octalLiteral", "decimalLiteral", 
			"hexLiteral", "characterLiteral", "stringLiteral"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'.macro'", "'.struct'", "','", "'='", "':'", "'@'", "'('", 
			"')'", "'*'", "'${'", "'}'", "'<='", "'>='", "'+'", "'-'", "'/'", "'<<'", 
			"'>>'", "'&'", "'|'", "'&&'", "'||'", "'=='", "'!='", "'<'", "'>'", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "Directives", "BinaryLiteral", "OctalLiteral", 
			"DecimalLiteral", "HexLiteral", "CharacterLiteral", "StringLiteral", 
			"Identifier", "IdentifierExtension"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Assembly.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AssemblyParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DirectiveContext extends ParserRuleContext {
		public AssemblerContext assembler() {
			return getRuleContext(AssemblerContext.class,0);
		}
		public MacroContext macro() {
			return getRuleContext(MacroContext.class,0);
		}
		public StructContext struct() {
			return getRuleContext(StructContext.class,0);
		}
		public InvocationContext invocation() {
			return getRuleContext(InvocationContext.class,0);
		}
		public DirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitDirective(this);
		}
	}

	public final DirectiveContext directive() throws RecognitionException {
		DirectiveContext _localctx = new DirectiveContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_directive);
		try {
			setState(110);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				assembler();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				macro();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(108);
				struct();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(109);
				invocation();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssemblerContext extends ParserRuleContext {
		public TerminalNode Directives() { return getToken(AssemblyParser.Directives, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public AssemblerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assembler; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterAssembler(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitAssembler(this);
		}
	}

	public final AssemblerContext assembler() throws RecognitionException {
		AssemblerContext _localctx = new AssemblerContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_assembler);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(T__0);
			setState(113);
			match(Directives);
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 137371946386L) != 0)) {
				{
				setState(114);
				parameterList();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MacroContext extends ParserRuleContext {
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public OptionListContext optionList() {
			return getRuleContext(OptionListContext.class,0);
		}
		public MacroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterMacro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitMacro(this);
		}
	}

	public final MacroContext macro() throws RecognitionException {
		MacroContext _localctx = new MacroContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_macro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			symbol();
			setState(118);
			match(T__1);
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69256349696L) != 0)) {
				{
				setState(119);
				optionList();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StructContext extends ParserRuleContext {
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public OptionListContext optionList() {
			return getRuleContext(OptionListContext.class,0);
		}
		public StructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitStruct(this);
		}
	}

	public final StructContext struct() throws RecognitionException {
		StructContext _localctx = new StructContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_struct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			symbol();
			setState(123);
			match(T__2);
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69256349696L) != 0)) {
				{
				setState(124);
				optionList();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InvocationContext extends ParserRuleContext {
		public QualifiedSymbolContext qualifiedSymbol() {
			return getRuleContext(QualifiedSymbolContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public InvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_invocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterInvocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitInvocation(this);
		}
	}

	public final InvocationContext invocation() throws RecognitionException {
		InvocationContext _localctx = new InvocationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_invocation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			qualifiedSymbol();
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 137371946386L) != 0)) {
				{
				setState(128);
				parameterList();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OptionListContext extends ParserRuleContext {
		public OptionContext option() {
			return getRuleContext(OptionContext.class,0);
		}
		public OptionListContext optionList() {
			return getRuleContext(OptionListContext.class,0);
		}
		public OptionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterOptionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitOptionList(this);
		}
	}

	public final OptionListContext optionList() throws RecognitionException {
		OptionListContext _localctx = new OptionListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_optionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			option();
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(132);
				match(T__3);
				setState(133);
				optionList();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OptionContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitOption(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			identifier();
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(137);
				assignment();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterListContext extends ParserRuleContext {
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitParameterList(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_parameterList);
		int _la;
		try {
			setState(149);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__6:
			case T__7:
			case T__9:
			case T__10:
			case T__14:
			case T__15:
			case T__25:
			case T__26:
			case T__27:
			case Directives:
			case BinaryLiteral:
			case OctalLiteral:
			case DecimalLiteral:
			case HexLiteral:
			case CharacterLiteral:
			case StringLiteral:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(140);
				parameter();
				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(141);
					match(T__3);
					setState(142);
					parameterList();
					}
				}

				}
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(145);
				match(T__3);
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 137371946386L) != 0)) {
					{
					setState(146);
					parameterList();
					}
				}

				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public DottedIdentifierContext dottedIdentifier() {
			return getRuleContext(DottedIdentifierContext.class,0);
		}
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitParameter(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_parameter);
		try {
			setState(159);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(151);
				identifier();
				setState(152);
				assignment();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(154);
				dottedIdentifier();
				setState(155);
				assignment();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(157);
				dottedIdentifier();
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(158);
				argument();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentContext extends ParserRuleContext {
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(T__4);
			setState(162);
			argument();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitArgument(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QualifiedSymbolContext extends ParserRuleContext {
		public List<SymbolContext> symbol() {
			return getRuleContexts(SymbolContext.class);
		}
		public SymbolContext symbol(int i) {
			return getRuleContext(SymbolContext.class,i);
		}
		public QualifiedSymbolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedSymbol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterQualifiedSymbol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitQualifiedSymbol(this);
		}
	}

	public final QualifiedSymbolContext qualifiedSymbol() throws RecognitionException {
		QualifiedSymbolContext _localctx = new QualifiedSymbolContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_qualifiedSymbol);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(166);
					symbol();
					setState(167);
					match(T__5);
					}
					} 
				}
				setState(173);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			setState(174);
			symbol();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SymbolContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(AssemblyParser.Identifier, 0); }
		public TerminalNode Directives() { return getToken(AssemblyParser.Directives, 0); }
		public SymbolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterSymbol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitSymbol(this);
		}
	}

	public final SymbolContext symbol() throws RecognitionException {
		SymbolContext _localctx = new SymbolContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_symbol);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(T__0);
			setState(177);
			_la = _input.LA(1);
			if ( !(_la==Directives || _la==Identifier) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LabelContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(AssemblyParser.Identifier, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitLabel(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScopedLabelContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(AssemblyParser.Identifier, 0); }
		public ScopedLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scopedLabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterScopedLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitScopedLabel(this);
		}
	}

	public final ScopedLabelContext scopedLabel() throws RecognitionException {
		ScopedLabelContext _localctx = new ScopedLabelContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_scopedLabel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			match(T__6);
			setState(182);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<BinaryContext> binary() {
			return getRuleContexts(BinaryContext.class);
		}
		public BinaryContext binary(int i) {
			return getRuleContext(BinaryContext.class,i);
		}
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			term();
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 268428288L) != 0)) {
				{
				{
				setState(187);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__9:
				case T__14:
				case T__15:
				case T__16:
				case T__17:
				case T__18:
				case T__19:
				case T__20:
				case T__21:
				case T__22:
					{
					setState(185);
					binary();
					}
					break;
				case T__12:
				case T__13:
				case T__23:
				case T__24:
				case T__25:
				case T__26:
					{
					setState(186);
					comparison();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(189);
				term();
				}
				}
				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public OrgContext org() {
			return getRuleContext(OrgContext.class,0);
		}
		public ScopedIdentifierContext scopedIdentifier() {
			return getRuleContext(ScopedIdentifierContext.class,0);
		}
		public QualifiedIdentifierContext qualifiedIdentifier() {
			return getRuleContext(QualifiedIdentifierContext.class,0);
		}
		public DottedIdentifierContext dottedIdentifier() {
			return getRuleContext(DottedIdentifierContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_term);
		try {
			setState(208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(196);
				org();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(197);
				scopedIdentifier();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(198);
				qualifiedIdentifier();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(199);
				dottedIdentifier();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(200);
				literal();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(201);
				match(T__7);
				setState(202);
				expr();
				setState(203);
				match(T__8);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(205);
				unary();
				setState(206);
				term();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OrgContext extends ParserRuleContext {
		public OrgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_org; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterOrg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitOrg(this);
		}
	}

	public final OrgContext org() throws RecognitionException {
		OrgContext _localctx = new OrgContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_org);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DottedIdentifierContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public DottedIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dottedIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterDottedIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitDottedIdentifier(this);
		}
	}

	public final DottedIdentifierContext dottedIdentifier() throws RecognitionException {
		DottedIdentifierContext _localctx = new DottedIdentifierContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_dottedIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(T__0);
			setState(213);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QualifiedIdentifierContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<DottedIdentifierContext> dottedIdentifier() {
			return getRuleContexts(DottedIdentifierContext.class);
		}
		public DottedIdentifierContext dottedIdentifier(int i) {
			return getRuleContext(DottedIdentifierContext.class,i);
		}
		public QualifiedIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterQualifiedIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitQualifiedIdentifier(this);
		}
	}

	public final QualifiedIdentifierContext qualifiedIdentifier() throws RecognitionException {
		QualifiedIdentifierContext _localctx = new QualifiedIdentifierContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_qualifiedIdentifier);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(215);
				dottedIdentifier();
				setState(216);
				match(T__5);
				}
				}
				setState(222);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(228);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(223);
					identifier();
					setState(224);
					match(T__5);
					}
					} 
				}
				setState(230);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			setState(231);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScopedIdentifierContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ScopedIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scopedIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterScopedIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitScopedIdentifier(this);
		}
	}

	public final ScopedIdentifierContext scopedIdentifier() throws RecognitionException {
		ScopedIdentifierContext _localctx = new ScopedIdentifierContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_scopedIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(T__6);
			setState(234);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(AssemblyParser.Identifier, 0); }
		public TerminalNode Directives() { return getToken(AssemblyParser.Directives, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_identifier);
		try {
			setState(241);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(236);
				match(Identifier);
				}
				break;
			case Directives:
				enterOuterAlt(_localctx, 2);
				{
				setState(237);
				match(Directives);
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(238);
				match(T__10);
				setState(239);
				match(Identifier);
				setState(240);
				match(T__11);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BinaryContext extends ParserRuleContext {
		public IntegerAddContext integerAdd() {
			return getRuleContext(IntegerAddContext.class,0);
		}
		public IntegerSubtractContext integerSubtract() {
			return getRuleContext(IntegerSubtractContext.class,0);
		}
		public IntegerMultiplyContext integerMultiply() {
			return getRuleContext(IntegerMultiplyContext.class,0);
		}
		public IntegerDivideContext integerDivide() {
			return getRuleContext(IntegerDivideContext.class,0);
		}
		public BitwiseShiftLeftContext bitwiseShiftLeft() {
			return getRuleContext(BitwiseShiftLeftContext.class,0);
		}
		public BitwiseShiftRightContext bitwiseShiftRight() {
			return getRuleContext(BitwiseShiftRightContext.class,0);
		}
		public BitwiseAndContext bitwiseAnd() {
			return getRuleContext(BitwiseAndContext.class,0);
		}
		public BitwiseOrContext bitwiseOr() {
			return getRuleContext(BitwiseOrContext.class,0);
		}
		public LogicalAndContext logicalAnd() {
			return getRuleContext(LogicalAndContext.class,0);
		}
		public LogicalOrContext logicalOr() {
			return getRuleContext(LogicalOrContext.class,0);
		}
		public BinaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterBinary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitBinary(this);
		}
	}

	public final BinaryContext binary() throws RecognitionException {
		BinaryContext _localctx = new BinaryContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_binary);
		try {
			setState(253);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__14:
				enterOuterAlt(_localctx, 1);
				{
				setState(243);
				integerAdd();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 2);
				{
				setState(244);
				integerSubtract();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 3);
				{
				setState(245);
				integerMultiply();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 4);
				{
				setState(246);
				integerDivide();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 5);
				{
				setState(247);
				bitwiseShiftLeft();
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 6);
				{
				setState(248);
				bitwiseShiftRight();
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 7);
				{
				setState(249);
				bitwiseAnd();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 8);
				{
				setState(250);
				bitwiseOr();
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 9);
				{
				setState(251);
				logicalAnd();
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 10);
				{
				setState(252);
				logicalOr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonContext extends ParserRuleContext {
		public EqualContext equal() {
			return getRuleContext(EqualContext.class,0);
		}
		public NotEqualContext notEqual() {
			return getRuleContext(NotEqualContext.class,0);
		}
		public LessThanContext lessThan() {
			return getRuleContext(LessThanContext.class,0);
		}
		public GreaterThanContext greaterThan() {
			return getRuleContext(GreaterThanContext.class,0);
		}
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitComparison(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_comparison);
		try {
			setState(261);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__23:
				enterOuterAlt(_localctx, 1);
				{
				setState(255);
				equal();
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 2);
				{
				setState(256);
				notEqual();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 3);
				{
				setState(257);
				lessThan();
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 4);
				{
				setState(258);
				greaterThan();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 5);
				{
				setState(259);
				match(T__12);
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 6);
				{
				setState(260);
				match(T__13);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryContext extends ParserRuleContext {
		public PlusContext plus() {
			return getRuleContext(PlusContext.class,0);
		}
		public MinusContext minus() {
			return getRuleContext(MinusContext.class,0);
		}
		public HighByteContext highByte() {
			return getRuleContext(HighByteContext.class,0);
		}
		public LowByteContext lowByte() {
			return getRuleContext(LowByteContext.class,0);
		}
		public LogicalNotContext logicalNot() {
			return getRuleContext(LogicalNotContext.class,0);
		}
		public UnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitUnary(this);
		}
	}

	public final UnaryContext unary() throws RecognitionException {
		UnaryContext _localctx = new UnaryContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_unary);
		try {
			setState(268);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__14:
				enterOuterAlt(_localctx, 1);
				{
				setState(263);
				plus();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 2);
				{
				setState(264);
				minus();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 3);
				{
				setState(265);
				highByte();
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 4);
				{
				setState(266);
				lowByte();
				}
				break;
			case T__27:
				enterOuterAlt(_localctx, 5);
				{
				setState(267);
				logicalNot();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IntegerAddContext extends ParserRuleContext {
		public IntegerAddContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerAdd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterIntegerAdd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitIntegerAdd(this);
		}
	}

	public final IntegerAddContext integerAdd() throws RecognitionException {
		IntegerAddContext _localctx = new IntegerAddContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_integerAdd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			match(T__14);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IntegerSubtractContext extends ParserRuleContext {
		public IntegerSubtractContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerSubtract; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterIntegerSubtract(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitIntegerSubtract(this);
		}
	}

	public final IntegerSubtractContext integerSubtract() throws RecognitionException {
		IntegerSubtractContext _localctx = new IntegerSubtractContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_integerSubtract);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			match(T__15);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IntegerMultiplyContext extends ParserRuleContext {
		public IntegerMultiplyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerMultiply; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterIntegerMultiply(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitIntegerMultiply(this);
		}
	}

	public final IntegerMultiplyContext integerMultiply() throws RecognitionException {
		IntegerMultiplyContext _localctx = new IntegerMultiplyContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_integerMultiply);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IntegerDivideContext extends ParserRuleContext {
		public IntegerDivideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerDivide; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterIntegerDivide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitIntegerDivide(this);
		}
	}

	public final IntegerDivideContext integerDivide() throws RecognitionException {
		IntegerDivideContext _localctx = new IntegerDivideContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_integerDivide);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			match(T__16);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BitwiseShiftLeftContext extends ParserRuleContext {
		public BitwiseShiftLeftContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitwiseShiftLeft; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterBitwiseShiftLeft(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitBitwiseShiftLeft(this);
		}
	}

	public final BitwiseShiftLeftContext bitwiseShiftLeft() throws RecognitionException {
		BitwiseShiftLeftContext _localctx = new BitwiseShiftLeftContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_bitwiseShiftLeft);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BitwiseShiftRightContext extends ParserRuleContext {
		public BitwiseShiftRightContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitwiseShiftRight; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterBitwiseShiftRight(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitBitwiseShiftRight(this);
		}
	}

	public final BitwiseShiftRightContext bitwiseShiftRight() throws RecognitionException {
		BitwiseShiftRightContext _localctx = new BitwiseShiftRightContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_bitwiseShiftRight);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			match(T__18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BitwiseAndContext extends ParserRuleContext {
		public BitwiseAndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitwiseAnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterBitwiseAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitBitwiseAnd(this);
		}
	}

	public final BitwiseAndContext bitwiseAnd() throws RecognitionException {
		BitwiseAndContext _localctx = new BitwiseAndContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_bitwiseAnd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BitwiseOrContext extends ParserRuleContext {
		public BitwiseOrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitwiseOr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterBitwiseOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitBitwiseOr(this);
		}
	}

	public final BitwiseOrContext bitwiseOr() throws RecognitionException {
		BitwiseOrContext _localctx = new BitwiseOrContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_bitwiseOr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			match(T__20);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogicalAndContext extends ParserRuleContext {
		public LogicalAndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalAnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterLogicalAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitLogicalAnd(this);
		}
	}

	public final LogicalAndContext logicalAnd() throws RecognitionException {
		LogicalAndContext _localctx = new LogicalAndContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_logicalAnd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(T__21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogicalOrContext extends ParserRuleContext {
		public LogicalOrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterLogicalOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitLogicalOr(this);
		}
	}

	public final LogicalOrContext logicalOr() throws RecognitionException {
		LogicalOrContext _localctx = new LogicalOrContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_logicalOr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			match(T__22);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EqualContext extends ParserRuleContext {
		public EqualContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitEqual(this);
		}
	}

	public final EqualContext equal() throws RecognitionException {
		EqualContext _localctx = new EqualContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_equal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			match(T__23);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NotEqualContext extends ParserRuleContext {
		public NotEqualContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notEqual; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterNotEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitNotEqual(this);
		}
	}

	public final NotEqualContext notEqual() throws RecognitionException {
		NotEqualContext _localctx = new NotEqualContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_notEqual);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			match(T__24);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LessThanContext extends ParserRuleContext {
		public LessThanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lessThan; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterLessThan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitLessThan(this);
		}
	}

	public final LessThanContext lessThan() throws RecognitionException {
		LessThanContext _localctx = new LessThanContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_lessThan);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			match(T__25);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GreaterThanContext extends ParserRuleContext {
		public GreaterThanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_greaterThan; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterGreaterThan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitGreaterThan(this);
		}
	}

	public final GreaterThanContext greaterThan() throws RecognitionException {
		GreaterThanContext _localctx = new GreaterThanContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_greaterThan);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(T__26);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PlusContext extends ParserRuleContext {
		public PlusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plus; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterPlus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitPlus(this);
		}
	}

	public final PlusContext plus() throws RecognitionException {
		PlusContext _localctx = new PlusContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_plus);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			match(T__14);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MinusContext extends ParserRuleContext {
		public MinusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_minus; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitMinus(this);
		}
	}

	public final MinusContext minus() throws RecognitionException {
		MinusContext _localctx = new MinusContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_minus);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			match(T__15);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HighByteContext extends ParserRuleContext {
		public HighByteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_highByte; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterHighByte(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitHighByte(this);
		}
	}

	public final HighByteContext highByte() throws RecognitionException {
		HighByteContext _localctx = new HighByteContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_highByte);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			match(T__25);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LowByteContext extends ParserRuleContext {
		public LowByteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lowByte; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterLowByte(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitLowByte(this);
		}
	}

	public final LowByteContext lowByte() throws RecognitionException {
		LowByteContext _localctx = new LowByteContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_lowByte);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			match(T__26);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogicalNotContext extends ParserRuleContext {
		public LogicalNotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalNot; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterLogicalNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitLogicalNot(this);
		}
	}

	public final LogicalNotContext logicalNot() throws RecognitionException {
		LogicalNotContext _localctx = new LogicalNotContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_logicalNot);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			match(T__27);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public AlphanumericLiteralContext alphanumericLiteral() {
			return getRuleContext(AlphanumericLiteralContext.class,0);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_literal);
		try {
			setState(310);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BinaryLiteral:
			case OctalLiteral:
			case DecimalLiteral:
			case HexLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(308);
				numericLiteral();
				}
				break;
			case CharacterLiteral:
			case StringLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(309);
				alphanumericLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumericLiteralContext extends ParserRuleContext {
		public BinaryLiteralContext binaryLiteral() {
			return getRuleContext(BinaryLiteralContext.class,0);
		}
		public OctalLiteralContext octalLiteral() {
			return getRuleContext(OctalLiteralContext.class,0);
		}
		public DecimalLiteralContext decimalLiteral() {
			return getRuleContext(DecimalLiteralContext.class,0);
		}
		public HexLiteralContext hexLiteral() {
			return getRuleContext(HexLiteralContext.class,0);
		}
		public NumericLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterNumericLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitNumericLiteral(this);
		}
	}

	public final NumericLiteralContext numericLiteral() throws RecognitionException {
		NumericLiteralContext _localctx = new NumericLiteralContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_numericLiteral);
		try {
			setState(316);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BinaryLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(312);
				binaryLiteral();
				}
				break;
			case OctalLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(313);
				octalLiteral();
				}
				break;
			case DecimalLiteral:
				enterOuterAlt(_localctx, 3);
				{
				setState(314);
				decimalLiteral();
				}
				break;
			case HexLiteral:
				enterOuterAlt(_localctx, 4);
				{
				setState(315);
				hexLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AlphanumericLiteralContext extends ParserRuleContext {
		public CharacterLiteralContext characterLiteral() {
			return getRuleContext(CharacterLiteralContext.class,0);
		}
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public AlphanumericLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alphanumericLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterAlphanumericLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitAlphanumericLiteral(this);
		}
	}

	public final AlphanumericLiteralContext alphanumericLiteral() throws RecognitionException {
		AlphanumericLiteralContext _localctx = new AlphanumericLiteralContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_alphanumericLiteral);
		try {
			setState(320);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CharacterLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(318);
				characterLiteral();
				}
				break;
			case StringLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(319);
				stringLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BinaryLiteralContext extends ParserRuleContext {
		public TerminalNode BinaryLiteral() { return getToken(AssemblyParser.BinaryLiteral, 0); }
		public BinaryLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterBinaryLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitBinaryLiteral(this);
		}
	}

	public final BinaryLiteralContext binaryLiteral() throws RecognitionException {
		BinaryLiteralContext _localctx = new BinaryLiteralContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_binaryLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
			match(BinaryLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OctalLiteralContext extends ParserRuleContext {
		public TerminalNode OctalLiteral() { return getToken(AssemblyParser.OctalLiteral, 0); }
		public OctalLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_octalLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterOctalLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitOctalLiteral(this);
		}
	}

	public final OctalLiteralContext octalLiteral() throws RecognitionException {
		OctalLiteralContext _localctx = new OctalLiteralContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_octalLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			match(OctalLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DecimalLiteralContext extends ParserRuleContext {
		public TerminalNode DecimalLiteral() { return getToken(AssemblyParser.DecimalLiteral, 0); }
		public DecimalLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimalLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterDecimalLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitDecimalLiteral(this);
		}
	}

	public final DecimalLiteralContext decimalLiteral() throws RecognitionException {
		DecimalLiteralContext _localctx = new DecimalLiteralContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_decimalLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			match(DecimalLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HexLiteralContext extends ParserRuleContext {
		public TerminalNode HexLiteral() { return getToken(AssemblyParser.HexLiteral, 0); }
		public HexLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hexLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterHexLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitHexLiteral(this);
		}
	}

	public final HexLiteralContext hexLiteral() throws RecognitionException {
		HexLiteralContext _localctx = new HexLiteralContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_hexLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			match(HexLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CharacterLiteralContext extends ParserRuleContext {
		public TerminalNode CharacterLiteral() { return getToken(AssemblyParser.CharacterLiteral, 0); }
		public CharacterLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_characterLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterCharacterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitCharacterLiteral(this);
		}
	}

	public final CharacterLiteralContext characterLiteral() throws RecognitionException {
		CharacterLiteralContext _localctx = new CharacterLiteralContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_characterLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			match(CharacterLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringLiteralContext extends ParserRuleContext {
		public TerminalNode StringLiteral() { return getToken(AssemblyParser.StringLiteral, 0); }
		public StringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitStringLiteral(this);
		}
	}

	public final StringLiteralContext stringLiteral() throws RecognitionException {
		StringLiteralContext _localctx = new StringLiteralContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_stringLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(332);
			match(StringLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001%\u014f\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0003\u0000o\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001t\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"y\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003~\b\u0003\u0001"+
		"\u0004\u0001\u0004\u0003\u0004\u0082\b\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005\u0087\b\u0005\u0001\u0006\u0001\u0006\u0003\u0006\u008b"+
		"\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0090\b\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007\u0094\b\u0007\u0003\u0007\u0096\b"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0003\b\u00a0\b\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0005\u000b\u00aa\b\u000b\n\u000b\f\u000b\u00ad"+
		"\t\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u00bc\b\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u00c0\b"+
		"\u000f\n\u000f\f\u000f\u00c3\t\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00d1\b\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0005\u0013\u00db\b\u0013\n\u0013\f\u0013\u00de\t\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u00e3\b\u0013\n\u0013\f\u0013"+
		"\u00e6\t\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015"+
		"\u00f2\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016"+
		"\u00fe\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0003\u0017\u0106\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0003\u0018\u010d\b\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c"+
		"\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f"+
		"\u0001 \u0001 \u0001!\u0001!\u0001\"\u0001\"\u0001#\u0001#\u0001$\u0001"+
		"$\u0001%\u0001%\u0001&\u0001&\u0001\'\u0001\'\u0001(\u0001(\u0001)\u0001"+
		")\u0001*\u0001*\u0001+\u0001+\u0001,\u0001,\u0003,\u0137\b,\u0001-\u0001"+
		"-\u0001-\u0001-\u0003-\u013d\b-\u0001.\u0001.\u0003.\u0141\b.\u0001/\u0001"+
		"/\u00010\u00010\u00011\u00011\u00012\u00012\u00013\u00013\u00014\u0001"+
		"4\u00014\u0000\u00005\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\"+
		"^`bdfh\u0000\u0001\u0002\u0000\u001d\u001d$$\u014c\u0000n\u0001\u0000"+
		"\u0000\u0000\u0002p\u0001\u0000\u0000\u0000\u0004u\u0001\u0000\u0000\u0000"+
		"\u0006z\u0001\u0000\u0000\u0000\b\u007f\u0001\u0000\u0000\u0000\n\u0083"+
		"\u0001\u0000\u0000\u0000\f\u0088\u0001\u0000\u0000\u0000\u000e\u0095\u0001"+
		"\u0000\u0000\u0000\u0010\u009f\u0001\u0000\u0000\u0000\u0012\u00a1\u0001"+
		"\u0000\u0000\u0000\u0014\u00a4\u0001\u0000\u0000\u0000\u0016\u00ab\u0001"+
		"\u0000\u0000\u0000\u0018\u00b0\u0001\u0000\u0000\u0000\u001a\u00b3\u0001"+
		"\u0000\u0000\u0000\u001c\u00b5\u0001\u0000\u0000\u0000\u001e\u00b8\u0001"+
		"\u0000\u0000\u0000 \u00d0\u0001\u0000\u0000\u0000\"\u00d2\u0001\u0000"+
		"\u0000\u0000$\u00d4\u0001\u0000\u0000\u0000&\u00dc\u0001\u0000\u0000\u0000"+
		"(\u00e9\u0001\u0000\u0000\u0000*\u00f1\u0001\u0000\u0000\u0000,\u00fd"+
		"\u0001\u0000\u0000\u0000.\u0105\u0001\u0000\u0000\u00000\u010c\u0001\u0000"+
		"\u0000\u00002\u010e\u0001\u0000\u0000\u00004\u0110\u0001\u0000\u0000\u0000"+
		"6\u0112\u0001\u0000\u0000\u00008\u0114\u0001\u0000\u0000\u0000:\u0116"+
		"\u0001\u0000\u0000\u0000<\u0118\u0001\u0000\u0000\u0000>\u011a\u0001\u0000"+
		"\u0000\u0000@\u011c\u0001\u0000\u0000\u0000B\u011e\u0001\u0000\u0000\u0000"+
		"D\u0120\u0001\u0000\u0000\u0000F\u0122\u0001\u0000\u0000\u0000H\u0124"+
		"\u0001\u0000\u0000\u0000J\u0126\u0001\u0000\u0000\u0000L\u0128\u0001\u0000"+
		"\u0000\u0000N\u012a\u0001\u0000\u0000\u0000P\u012c\u0001\u0000\u0000\u0000"+
		"R\u012e\u0001\u0000\u0000\u0000T\u0130\u0001\u0000\u0000\u0000V\u0132"+
		"\u0001\u0000\u0000\u0000X\u0136\u0001\u0000\u0000\u0000Z\u013c\u0001\u0000"+
		"\u0000\u0000\\\u0140\u0001\u0000\u0000\u0000^\u0142\u0001\u0000\u0000"+
		"\u0000`\u0144\u0001\u0000\u0000\u0000b\u0146\u0001\u0000\u0000\u0000d"+
		"\u0148\u0001\u0000\u0000\u0000f\u014a\u0001\u0000\u0000\u0000h\u014c\u0001"+
		"\u0000\u0000\u0000jo\u0003\u0002\u0001\u0000ko\u0003\u0004\u0002\u0000"+
		"lo\u0003\u0006\u0003\u0000mo\u0003\b\u0004\u0000nj\u0001\u0000\u0000\u0000"+
		"nk\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000nm\u0001\u0000\u0000"+
		"\u0000o\u0001\u0001\u0000\u0000\u0000pq\u0005\u0001\u0000\u0000qs\u0005"+
		"\u001d\u0000\u0000rt\u0003\u000e\u0007\u0000sr\u0001\u0000\u0000\u0000"+
		"st\u0001\u0000\u0000\u0000t\u0003\u0001\u0000\u0000\u0000uv\u0003\u0018"+
		"\f\u0000vx\u0005\u0002\u0000\u0000wy\u0003\n\u0005\u0000xw\u0001\u0000"+
		"\u0000\u0000xy\u0001\u0000\u0000\u0000y\u0005\u0001\u0000\u0000\u0000"+
		"z{\u0003\u0018\f\u0000{}\u0005\u0003\u0000\u0000|~\u0003\n\u0005\u0000"+
		"}|\u0001\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~\u0007\u0001\u0000"+
		"\u0000\u0000\u007f\u0081\u0003\u0016\u000b\u0000\u0080\u0082\u0003\u000e"+
		"\u0007\u0000\u0081\u0080\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000"+
		"\u0000\u0000\u0082\t\u0001\u0000\u0000\u0000\u0083\u0086\u0003\f\u0006"+
		"\u0000\u0084\u0085\u0005\u0004\u0000\u0000\u0085\u0087\u0003\n\u0005\u0000"+
		"\u0086\u0084\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000"+
		"\u0087\u000b\u0001\u0000\u0000\u0000\u0088\u008a\u0003*\u0015\u0000\u0089"+
		"\u008b\u0003\u0012\t\u0000\u008a\u0089\u0001\u0000\u0000\u0000\u008a\u008b"+
		"\u0001\u0000\u0000\u0000\u008b\r\u0001\u0000\u0000\u0000\u008c\u008f\u0003"+
		"\u0010\b\u0000\u008d\u008e\u0005\u0004\u0000\u0000\u008e\u0090\u0003\u000e"+
		"\u0007\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000"+
		"\u0000\u0000\u0090\u0096\u0001\u0000\u0000\u0000\u0091\u0093\u0005\u0004"+
		"\u0000\u0000\u0092\u0094\u0003\u000e\u0007\u0000\u0093\u0092\u0001\u0000"+
		"\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000\u0094\u0096\u0001\u0000"+
		"\u0000\u0000\u0095\u008c\u0001\u0000\u0000\u0000\u0095\u0091\u0001\u0000"+
		"\u0000\u0000\u0096\u000f\u0001\u0000\u0000\u0000\u0097\u0098\u0003*\u0015"+
		"\u0000\u0098\u0099\u0003\u0012\t\u0000\u0099\u00a0\u0001\u0000\u0000\u0000"+
		"\u009a\u009b\u0003$\u0012\u0000\u009b\u009c\u0003\u0012\t\u0000\u009c"+
		"\u00a0\u0001\u0000\u0000\u0000\u009d\u00a0\u0003$\u0012\u0000\u009e\u00a0"+
		"\u0003\u0014\n\u0000\u009f\u0097\u0001\u0000\u0000\u0000\u009f\u009a\u0001"+
		"\u0000\u0000\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u009f\u009e\u0001"+
		"\u0000\u0000\u0000\u00a0\u0011\u0001\u0000\u0000\u0000\u00a1\u00a2\u0005"+
		"\u0005\u0000\u0000\u00a2\u00a3\u0003\u0014\n\u0000\u00a3\u0013\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a5\u0003\u001e\u000f\u0000\u00a5\u0015\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a7\u0003\u0018\f\u0000\u00a7\u00a8\u0005\u0006\u0000"+
		"\u0000\u00a8\u00aa\u0001\u0000\u0000\u0000\u00a9\u00a6\u0001\u0000\u0000"+
		"\u0000\u00aa\u00ad\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000"+
		"\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u00ae\u0001\u0000\u0000"+
		"\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ae\u00af\u0003\u0018\f\u0000"+
		"\u00af\u0017\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005\u0001\u0000\u0000"+
		"\u00b1\u00b2\u0007\u0000\u0000\u0000\u00b2\u0019\u0001\u0000\u0000\u0000"+
		"\u00b3\u00b4\u0005$\u0000\u0000\u00b4\u001b\u0001\u0000\u0000\u0000\u00b5"+
		"\u00b6\u0005\u0007\u0000\u0000\u00b6\u00b7\u0005$\u0000\u0000\u00b7\u001d"+
		"\u0001\u0000\u0000\u0000\u00b8\u00c1\u0003 \u0010\u0000\u00b9\u00bc\u0003"+
		",\u0016\u0000\u00ba\u00bc\u0003.\u0017\u0000\u00bb\u00b9\u0001\u0000\u0000"+
		"\u0000\u00bb\u00ba\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001\u0000\u0000"+
		"\u0000\u00bd\u00be\u0003 \u0010\u0000\u00be\u00c0\u0001\u0000\u0000\u0000"+
		"\u00bf\u00bb\u0001\u0000\u0000\u0000\u00c0\u00c3\u0001\u0000\u0000\u0000"+
		"\u00c1\u00bf\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000"+
		"\u00c2\u001f\u0001\u0000\u0000\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000"+
		"\u00c4\u00d1\u0003\"\u0011\u0000\u00c5\u00d1\u0003(\u0014\u0000\u00c6"+
		"\u00d1\u0003&\u0013\u0000\u00c7\u00d1\u0003$\u0012\u0000\u00c8\u00d1\u0003"+
		"X,\u0000\u00c9\u00ca\u0005\b\u0000\u0000\u00ca\u00cb\u0003\u001e\u000f"+
		"\u0000\u00cb\u00cc\u0005\t\u0000\u0000\u00cc\u00d1\u0001\u0000\u0000\u0000"+
		"\u00cd\u00ce\u00030\u0018\u0000\u00ce\u00cf\u0003 \u0010\u0000\u00cf\u00d1"+
		"\u0001\u0000\u0000\u0000\u00d0\u00c4\u0001\u0000\u0000\u0000\u00d0\u00c5"+
		"\u0001\u0000\u0000\u0000\u00d0\u00c6\u0001\u0000\u0000\u0000\u00d0\u00c7"+
		"\u0001\u0000\u0000\u0000\u00d0\u00c8\u0001\u0000\u0000\u0000\u00d0\u00c9"+
		"\u0001\u0000\u0000\u0000\u00d0\u00cd\u0001\u0000\u0000\u0000\u00d1!\u0001"+
		"\u0000\u0000\u0000\u00d2\u00d3\u0005\n\u0000\u0000\u00d3#\u0001\u0000"+
		"\u0000\u0000\u00d4\u00d5\u0005\u0001\u0000\u0000\u00d5\u00d6\u0003*\u0015"+
		"\u0000\u00d6%\u0001\u0000\u0000\u0000\u00d7\u00d8\u0003$\u0012\u0000\u00d8"+
		"\u00d9\u0005\u0006\u0000\u0000\u00d9\u00db\u0001\u0000\u0000\u0000\u00da"+
		"\u00d7\u0001\u0000\u0000\u0000\u00db\u00de\u0001\u0000\u0000\u0000\u00dc"+
		"\u00da\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000\u0000\u00dd"+
		"\u00e4\u0001\u0000\u0000\u0000\u00de\u00dc\u0001\u0000\u0000\u0000\u00df"+
		"\u00e0\u0003*\u0015\u0000\u00e0\u00e1\u0005\u0006\u0000\u0000\u00e1\u00e3"+
		"\u0001\u0000\u0000\u0000\u00e2\u00df\u0001\u0000\u0000\u0000\u00e3\u00e6"+
		"\u0001\u0000\u0000\u0000\u00e4\u00e2\u0001\u0000\u0000\u0000\u00e4\u00e5"+
		"\u0001\u0000\u0000\u0000\u00e5\u00e7\u0001\u0000\u0000\u0000\u00e6\u00e4"+
		"\u0001\u0000\u0000\u0000\u00e7\u00e8\u0003*\u0015\u0000\u00e8\'\u0001"+
		"\u0000\u0000\u0000\u00e9\u00ea\u0005\u0007\u0000\u0000\u00ea\u00eb\u0003"+
		"*\u0015\u0000\u00eb)\u0001\u0000\u0000\u0000\u00ec\u00f2\u0005$\u0000"+
		"\u0000\u00ed\u00f2\u0005\u001d\u0000\u0000\u00ee\u00ef\u0005\u000b\u0000"+
		"\u0000\u00ef\u00f0\u0005$\u0000\u0000\u00f0\u00f2\u0005\f\u0000\u0000"+
		"\u00f1\u00ec\u0001\u0000\u0000\u0000\u00f1\u00ed\u0001\u0000\u0000\u0000"+
		"\u00f1\u00ee\u0001\u0000\u0000\u0000\u00f2+\u0001\u0000\u0000\u0000\u00f3"+
		"\u00fe\u00032\u0019\u0000\u00f4\u00fe\u00034\u001a\u0000\u00f5\u00fe\u0003"+
		"6\u001b\u0000\u00f6\u00fe\u00038\u001c\u0000\u00f7\u00fe\u0003:\u001d"+
		"\u0000\u00f8\u00fe\u0003<\u001e\u0000\u00f9\u00fe\u0003>\u001f\u0000\u00fa"+
		"\u00fe\u0003@ \u0000\u00fb\u00fe\u0003B!\u0000\u00fc\u00fe\u0003D\"\u0000"+
		"\u00fd\u00f3\u0001\u0000\u0000\u0000\u00fd\u00f4\u0001\u0000\u0000\u0000"+
		"\u00fd\u00f5\u0001\u0000\u0000\u0000\u00fd\u00f6\u0001\u0000\u0000\u0000"+
		"\u00fd\u00f7\u0001\u0000\u0000\u0000\u00fd\u00f8\u0001\u0000\u0000\u0000"+
		"\u00fd\u00f9\u0001\u0000\u0000\u0000\u00fd\u00fa\u0001\u0000\u0000\u0000"+
		"\u00fd\u00fb\u0001\u0000\u0000\u0000\u00fd\u00fc\u0001\u0000\u0000\u0000"+
		"\u00fe-\u0001\u0000\u0000\u0000\u00ff\u0106\u0003F#\u0000\u0100\u0106"+
		"\u0003H$\u0000\u0101\u0106\u0003J%\u0000\u0102\u0106\u0003L&\u0000\u0103"+
		"\u0106\u0005\r\u0000\u0000\u0104\u0106\u0005\u000e\u0000\u0000\u0105\u00ff"+
		"\u0001\u0000\u0000\u0000\u0105\u0100\u0001\u0000\u0000\u0000\u0105\u0101"+
		"\u0001\u0000\u0000\u0000\u0105\u0102\u0001\u0000\u0000\u0000\u0105\u0103"+
		"\u0001\u0000\u0000\u0000\u0105\u0104\u0001\u0000\u0000\u0000\u0106/\u0001"+
		"\u0000\u0000\u0000\u0107\u010d\u0003N\'\u0000\u0108\u010d\u0003P(\u0000"+
		"\u0109\u010d\u0003R)\u0000\u010a\u010d\u0003T*\u0000\u010b\u010d\u0003"+
		"V+\u0000\u010c\u0107\u0001\u0000\u0000\u0000\u010c\u0108\u0001\u0000\u0000"+
		"\u0000\u010c\u0109\u0001\u0000\u0000\u0000\u010c\u010a\u0001\u0000\u0000"+
		"\u0000\u010c\u010b\u0001\u0000\u0000\u0000\u010d1\u0001\u0000\u0000\u0000"+
		"\u010e\u010f\u0005\u000f\u0000\u0000\u010f3\u0001\u0000\u0000\u0000\u0110"+
		"\u0111\u0005\u0010\u0000\u0000\u01115\u0001\u0000\u0000\u0000\u0112\u0113"+
		"\u0005\n\u0000\u0000\u01137\u0001\u0000\u0000\u0000\u0114\u0115\u0005"+
		"\u0011\u0000\u0000\u01159\u0001\u0000\u0000\u0000\u0116\u0117\u0005\u0012"+
		"\u0000\u0000\u0117;\u0001\u0000\u0000\u0000\u0118\u0119\u0005\u0013\u0000"+
		"\u0000\u0119=\u0001\u0000\u0000\u0000\u011a\u011b\u0005\u0014\u0000\u0000"+
		"\u011b?\u0001\u0000\u0000\u0000\u011c\u011d\u0005\u0015\u0000\u0000\u011d"+
		"A\u0001\u0000\u0000\u0000\u011e\u011f\u0005\u0016\u0000\u0000\u011fC\u0001"+
		"\u0000\u0000\u0000\u0120\u0121\u0005\u0017\u0000\u0000\u0121E\u0001\u0000"+
		"\u0000\u0000\u0122\u0123\u0005\u0018\u0000\u0000\u0123G\u0001\u0000\u0000"+
		"\u0000\u0124\u0125\u0005\u0019\u0000\u0000\u0125I\u0001\u0000\u0000\u0000"+
		"\u0126\u0127\u0005\u001a\u0000\u0000\u0127K\u0001\u0000\u0000\u0000\u0128"+
		"\u0129\u0005\u001b\u0000\u0000\u0129M\u0001\u0000\u0000\u0000\u012a\u012b"+
		"\u0005\u000f\u0000\u0000\u012bO\u0001\u0000\u0000\u0000\u012c\u012d\u0005"+
		"\u0010\u0000\u0000\u012dQ\u0001\u0000\u0000\u0000\u012e\u012f\u0005\u001a"+
		"\u0000\u0000\u012fS\u0001\u0000\u0000\u0000\u0130\u0131\u0005\u001b\u0000"+
		"\u0000\u0131U\u0001\u0000\u0000\u0000\u0132\u0133\u0005\u001c\u0000\u0000"+
		"\u0133W\u0001\u0000\u0000\u0000\u0134\u0137\u0003Z-\u0000\u0135\u0137"+
		"\u0003\\.\u0000\u0136\u0134\u0001\u0000\u0000\u0000\u0136\u0135\u0001"+
		"\u0000\u0000\u0000\u0137Y\u0001\u0000\u0000\u0000\u0138\u013d\u0003^/"+
		"\u0000\u0139\u013d\u0003`0\u0000\u013a\u013d\u0003b1\u0000\u013b\u013d"+
		"\u0003d2\u0000\u013c\u0138\u0001\u0000\u0000\u0000\u013c\u0139\u0001\u0000"+
		"\u0000\u0000\u013c\u013a\u0001\u0000\u0000\u0000\u013c\u013b\u0001\u0000"+
		"\u0000\u0000\u013d[\u0001\u0000\u0000\u0000\u013e\u0141\u0003f3\u0000"+
		"\u013f\u0141\u0003h4\u0000\u0140\u013e\u0001\u0000\u0000\u0000\u0140\u013f"+
		"\u0001\u0000\u0000\u0000\u0141]\u0001\u0000\u0000\u0000\u0142\u0143\u0005"+
		"\u001e\u0000\u0000\u0143_\u0001\u0000\u0000\u0000\u0144\u0145\u0005\u001f"+
		"\u0000\u0000\u0145a\u0001\u0000\u0000\u0000\u0146\u0147\u0005 \u0000\u0000"+
		"\u0147c\u0001\u0000\u0000\u0000\u0148\u0149\u0005!\u0000\u0000\u0149e"+
		"\u0001\u0000\u0000\u0000\u014a\u014b\u0005\"\u0000\u0000\u014bg\u0001"+
		"\u0000\u0000\u0000\u014c\u014d\u0005#\u0000\u0000\u014di\u0001\u0000\u0000"+
		"\u0000\u0018nsx}\u0081\u0086\u008a\u008f\u0093\u0095\u009f\u00ab\u00bb"+
		"\u00c1\u00d0\u00dc\u00e4\u00f1\u00fd\u0105\u010c\u0136\u013c\u0140";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}