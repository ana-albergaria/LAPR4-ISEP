// Generated from C:/Users/Ana Albergaria/OneDrive - Instituto Superior de Engenharia do Porto/Ano 2/lei21_22_s4_2df_01/base.core/src/main/java/eapli/base/surveymanagement/antlr\questionnaire.g4 by ANTLR 4.10.1
package eapli.base.surveymanagement.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class questionnaireParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, DECIMALS_ALLOWED=5, SINGLE_CHOICE_WITH_INPUT=6, 
		MULTIPLE_CHOICE_WITH_INPUT=7, FREE_TEXT=8, NUMERIC=9, SINGLE_CHOICE=10, 
		MULTIPLE_CHOICE=11, SORTING_OPTION=12, SCALING_OPTION=13, MANDATORY=14, 
		OPTIONAL=15, CONDITION_DEPENDENT=16, DIGITO=17, PALAVRA=18, PARENTESIS_DIREITO=19, 
		PARENTESIS_ESQUERDO=20, VIRGULA=21, ESPACO=22, DOIS_PONTOS=23, PONTO_INTERROGACAO=24, 
		PONTO_EXCLAMACAO=25, NEWLINE=26, WS=27;
	public static final int
		RULE_alfanumerico = 0, RULE_frase = 1, RULE_title = 2, RULE_message = 3, 
		RULE_survey = 4, RULE_questionnaire_id = 5, RULE_section = 6, RULE_numeric_id = 7, 
		RULE_obligatoriness = 8, RULE_repeatability = 9, RULE_question = 10, RULE_option = 11, 
		RULE_question_text = 12, RULE_type = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"alfanumerico", "frase", "title", "message", "survey", "questionnaire_id", 
			"section", "numeric_id", "obligatoriness", "repeatability", "question", 
			"option", "question_text", "type"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Section Obligatoriness: '", "'Repeatability: '", "'Type: '", 
			"'Scale: '", "'Decimal numbers are allowed!'", "'single choice with input'", 
			"'multiple choice with input'", "'free text'", "'numeric'", "'single choice'", 
			"'multiple choice'", "'sorting option'", "'scaling option'", "'mandatory'", 
			"'optional'", "'condition dependent'", null, null, "')'", "'('", "','", 
			"' '", "':'", "'?'", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "DECIMALS_ALLOWED", "SINGLE_CHOICE_WITH_INPUT", 
			"MULTIPLE_CHOICE_WITH_INPUT", "FREE_TEXT", "NUMERIC", "SINGLE_CHOICE", 
			"MULTIPLE_CHOICE", "SORTING_OPTION", "SCALING_OPTION", "MANDATORY", "OPTIONAL", 
			"CONDITION_DEPENDENT", "DIGITO", "PALAVRA", "PARENTESIS_DIREITO", "PARENTESIS_ESQUERDO", 
			"VIRGULA", "ESPACO", "DOIS_PONTOS", "PONTO_INTERROGACAO", "PONTO_EXCLAMACAO", 
			"NEWLINE", "WS"
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
	public String getGrammarFileName() { return "questionnaire.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public questionnaireParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class AlfanumericoContext extends ParserRuleContext {
		public TerminalNode PALAVRA() { return getToken(questionnaireParser.PALAVRA, 0); }
		public TerminalNode DIGITO() { return getToken(questionnaireParser.DIGITO, 0); }
		public AlfanumericoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alfanumerico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterAlfanumerico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitAlfanumerico(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitAlfanumerico(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlfanumericoContext alfanumerico() throws RecognitionException {
		AlfanumericoContext _localctx = new AlfanumericoContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_alfanumerico);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			_la = _input.LA(1);
			if ( !(_la==DIGITO || _la==PALAVRA) ) {
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

	public static class FraseContext extends ParserRuleContext {
		public List<TerminalNode> PALAVRA() { return getTokens(questionnaireParser.PALAVRA); }
		public TerminalNode PALAVRA(int i) {
			return getToken(questionnaireParser.PALAVRA, i);
		}
		public List<TerminalNode> ESPACO() { return getTokens(questionnaireParser.ESPACO); }
		public TerminalNode ESPACO(int i) {
			return getToken(questionnaireParser.ESPACO, i);
		}
		public List<TerminalNode> VIRGULA() { return getTokens(questionnaireParser.VIRGULA); }
		public TerminalNode VIRGULA(int i) {
			return getToken(questionnaireParser.VIRGULA, i);
		}
		public List<TerminalNode> DIGITO() { return getTokens(questionnaireParser.DIGITO); }
		public TerminalNode DIGITO(int i) {
			return getToken(questionnaireParser.DIGITO, i);
		}
		public FraseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_frase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterFrase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitFrase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitFrase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FraseContext frase() throws RecognitionException {
		FraseContext _localctx = new FraseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_frase);
		int _la;
		try {
			setState(66);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				match(PALAVRA);
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==VIRGULA || _la==ESPACO) {
					{
					{
					setState(32);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==VIRGULA) {
						{
						setState(31);
						match(VIRGULA);
						}
					}

					setState(34);
					match(ESPACO);
					setState(36); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(35);
						_la = _input.LA(1);
						if ( !(_la==DIGITO || _la==PALAVRA) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						setState(38); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==DIGITO || _la==PALAVRA );
					}
					}
					setState(44);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(46); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(45);
					match(DIGITO);
					}
					}
					setState(48); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DIGITO );
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==VIRGULA || _la==ESPACO) {
					{
					{
					setState(51);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==VIRGULA) {
						{
						setState(50);
						match(VIRGULA);
						}
					}

					setState(53);
					match(ESPACO);
					setState(55); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(54);
						_la = _input.LA(1);
						if ( !(_la==DIGITO || _la==PALAVRA) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						setState(57); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==DIGITO || _la==PALAVRA );
					}
					}
					setState(63);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(64);
				match(PALAVRA);
				setState(65);
				match(VIRGULA);
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

	public static class TitleContext extends ParserRuleContext {
		public TitleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_title; }
	 
		public TitleContext() { }
		public void copyFrom(TitleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LengthTitleContext extends TitleContext {
		public FraseContext frase() {
			return getRuleContext(FraseContext.class,0);
		}
		public LengthTitleContext(TitleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterLengthTitle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitLengthTitle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitLengthTitle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TitleContext title() throws RecognitionException {
		TitleContext _localctx = new TitleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_title);
		try {
			_localctx = new LengthTitleContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			frase();
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

	public static class MessageContext extends ParserRuleContext {
		public List<FraseContext> frase() {
			return getRuleContexts(FraseContext.class);
		}
		public FraseContext frase(int i) {
			return getRuleContext(FraseContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(questionnaireParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(questionnaireParser.NEWLINE, i);
		}
		public MessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_message; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageContext message() throws RecognitionException {
		MessageContext _localctx = new MessageContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_message);
		int _la;
		try {
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				frase();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(74); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(71);
					frase();
					setState(72);
					match(NEWLINE);
					}
					}
					setState(76); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DIGITO || _la==PALAVRA );
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

	public static class SurveyContext extends ParserRuleContext {
		public Questionnaire_idContext questionnaire_id() {
			return getRuleContext(Questionnaire_idContext.class,0);
		}
		public TerminalNode ESPACO() { return getToken(questionnaireParser.ESPACO, 0); }
		public TitleContext title() {
			return getRuleContext(TitleContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(questionnaireParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(questionnaireParser.NEWLINE, i);
		}
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
		}
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public SurveyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_survey; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterSurvey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitSurvey(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitSurvey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SurveyContext survey() throws RecognitionException {
		SurveyContext _localctx = new SurveyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_survey);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			questionnaire_id();
			setState(81);
			match(ESPACO);
			setState(82);
			title();
			setState(83);
			match(NEWLINE);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DIGITO || _la==PALAVRA) {
				{
				setState(84);
				message();
				}
			}

			setState(89); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(87);
					match(NEWLINE);
					setState(88);
					section();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(91); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(93);
			match(NEWLINE);
			setState(94);
			match(NEWLINE);
			setState(95);
			message();
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

	public static class Questionnaire_idContext extends ParserRuleContext {
		public List<AlfanumericoContext> alfanumerico() {
			return getRuleContexts(AlfanumericoContext.class);
		}
		public AlfanumericoContext alfanumerico(int i) {
			return getRuleContext(AlfanumericoContext.class,i);
		}
		public Questionnaire_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionnaire_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterQuestionnaire_id(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitQuestionnaire_id(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitQuestionnaire_id(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Questionnaire_idContext questionnaire_id() throws RecognitionException {
		Questionnaire_idContext _localctx = new Questionnaire_idContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_questionnaire_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(97);
				alfanumerico();
				}
				}
				setState(100); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGITO || _la==PALAVRA );
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

	public static class SectionContext extends ParserRuleContext {
		public Numeric_idContext numeric_id() {
			return getRuleContext(Numeric_idContext.class,0);
		}
		public TitleContext title() {
			return getRuleContext(TitleContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(questionnaireParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(questionnaireParser.NEWLINE, i);
		}
		public ObligatorinessContext obligatoriness() {
			return getRuleContext(ObligatorinessContext.class,0);
		}
		public MessageContext message() {
			return getRuleContext(MessageContext.class,0);
		}
		public RepeatabilityContext repeatability() {
			return getRuleContext(RepeatabilityContext.class,0);
		}
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			numeric_id();
			setState(103);
			title();
			setState(104);
			match(NEWLINE);
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DIGITO || _la==PALAVRA) {
				{
				setState(105);
				message();
				}
			}

			setState(108);
			match(T__0);
			setState(109);
			obligatoriness();
			setState(113);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(110);
				match(NEWLINE);
				setState(111);
				match(T__1);
				setState(112);
				repeatability();
				}
				break;
			}
			setState(115);
			match(NEWLINE);
			setState(117); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(116);
				question();
				}
				}
				setState(119); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGITO );
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

	public static class Numeric_idContext extends ParserRuleContext {
		public List<TerminalNode> DIGITO() { return getTokens(questionnaireParser.DIGITO); }
		public TerminalNode DIGITO(int i) {
			return getToken(questionnaireParser.DIGITO, i);
		}
		public Numeric_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterNumeric_id(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitNumeric_id(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitNumeric_id(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numeric_idContext numeric_id() throws RecognitionException {
		Numeric_idContext _localctx = new Numeric_idContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_numeric_id);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(122); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(121);
					match(DIGITO);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(124); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class ObligatorinessContext extends ParserRuleContext {
		public TerminalNode MANDATORY() { return getToken(questionnaireParser.MANDATORY, 0); }
		public TerminalNode OPTIONAL() { return getToken(questionnaireParser.OPTIONAL, 0); }
		public TerminalNode CONDITION_DEPENDENT() { return getToken(questionnaireParser.CONDITION_DEPENDENT, 0); }
		public TerminalNode DOIS_PONTOS() { return getToken(questionnaireParser.DOIS_PONTOS, 0); }
		public TerminalNode ESPACO() { return getToken(questionnaireParser.ESPACO, 0); }
		public FraseContext frase() {
			return getRuleContext(FraseContext.class,0);
		}
		public ObligatorinessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obligatoriness; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterObligatoriness(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitObligatoriness(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitObligatoriness(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObligatorinessContext obligatoriness() throws RecognitionException {
		ObligatorinessContext _localctx = new ObligatorinessContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_obligatoriness);
		try {
			setState(132);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MANDATORY:
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				match(MANDATORY);
				}
				break;
			case OPTIONAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				match(OPTIONAL);
				}
				break;
			case CONDITION_DEPENDENT:
				enterOuterAlt(_localctx, 3);
				{
				setState(128);
				match(CONDITION_DEPENDENT);
				setState(129);
				match(DOIS_PONTOS);
				setState(130);
				match(ESPACO);
				setState(131);
				frase();
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

	public static class RepeatabilityContext extends ParserRuleContext {
		public List<TerminalNode> DIGITO() { return getTokens(questionnaireParser.DIGITO); }
		public TerminalNode DIGITO(int i) {
			return getToken(questionnaireParser.DIGITO, i);
		}
		public RepeatabilityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeatability; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterRepeatability(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitRepeatability(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitRepeatability(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepeatabilityContext repeatability() throws RecognitionException {
		RepeatabilityContext _localctx = new RepeatabilityContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_repeatability);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(134);
				match(DIGITO);
				}
				}
				setState(137); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGITO );
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

	public static class QuestionContext extends ParserRuleContext {
		public Numeric_idContext numeric_id() {
			return getRuleContext(Numeric_idContext.class,0);
		}
		public Question_textContext question_text() {
			return getRuleContext(Question_textContext.class,0);
		}
		public TerminalNode PARENTESIS_ESQUERDO() { return getToken(questionnaireParser.PARENTESIS_ESQUERDO, 0); }
		public ObligatorinessContext obligatoriness() {
			return getRuleContext(ObligatorinessContext.class,0);
		}
		public TerminalNode PARENTESIS_DIREITO() { return getToken(questionnaireParser.PARENTESIS_DIREITO, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(questionnaireParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(questionnaireParser.NEWLINE, i);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			numeric_id();
			setState(140);
			question_text();
			setState(141);
			match(PARENTESIS_ESQUERDO);
			setState(142);
			obligatoriness();
			setState(143);
			match(PARENTESIS_DIREITO);
			setState(146);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(144);
				match(NEWLINE);
				setState(145);
				message();
				}
				break;
			}
			setState(148);
			match(NEWLINE);
			setState(149);
			match(T__2);
			setState(150);
			type();
			setState(151);
			match(NEWLINE);
			setState(155);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(152);
				message();
				setState(153);
				match(NEWLINE);
				}
				break;
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

	public static class OptionContext extends ParserRuleContext {
		public Numeric_idContext numeric_id() {
			return getRuleContext(Numeric_idContext.class,0);
		}
		public TerminalNode PARENTESIS_DIREITO() { return getToken(questionnaireParser.PARENTESIS_DIREITO, 0); }
		public FraseContext frase() {
			return getRuleContext(FraseContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(questionnaireParser.NEWLINE, 0); }
		public TerminalNode DOIS_PONTOS() { return getToken(questionnaireParser.DOIS_PONTOS, 0); }
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			numeric_id();
			setState(158);
			match(PARENTESIS_DIREITO);
			setState(159);
			frase();
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOIS_PONTOS) {
				{
				setState(160);
				match(DOIS_PONTOS);
				}
			}

			setState(163);
			match(NEWLINE);
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

	public static class Question_textContext extends ParserRuleContext {
		public FraseContext frase() {
			return getRuleContext(FraseContext.class,0);
		}
		public TerminalNode PONTO_INTERROGACAO() { return getToken(questionnaireParser.PONTO_INTERROGACAO, 0); }
		public Question_textContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterQuestion_text(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitQuestion_text(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitQuestion_text(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Question_textContext question_text() throws RecognitionException {
		Question_textContext _localctx = new Question_textContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_question_text);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			frase();
			setState(166);
			match(PONTO_INTERROGACAO);
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode FREE_TEXT() { return getToken(questionnaireParser.FREE_TEXT, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(questionnaireParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(questionnaireParser.NEWLINE, i);
		}
		public TerminalNode NUMERIC() { return getToken(questionnaireParser.NUMERIC, 0); }
		public TerminalNode ESPACO() { return getToken(questionnaireParser.ESPACO, 0); }
		public TerminalNode PARENTESIS_ESQUERDO() { return getToken(questionnaireParser.PARENTESIS_ESQUERDO, 0); }
		public TerminalNode DECIMALS_ALLOWED() { return getToken(questionnaireParser.DECIMALS_ALLOWED, 0); }
		public TerminalNode PARENTESIS_DIREITO() { return getToken(questionnaireParser.PARENTESIS_DIREITO, 0); }
		public TerminalNode SINGLE_CHOICE() { return getToken(questionnaireParser.SINGLE_CHOICE, 0); }
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public TerminalNode MULTIPLE_CHOICE() { return getToken(questionnaireParser.MULTIPLE_CHOICE, 0); }
		public TerminalNode SINGLE_CHOICE_WITH_INPUT() { return getToken(questionnaireParser.SINGLE_CHOICE_WITH_INPUT, 0); }
		public TerminalNode MULTIPLE_CHOICE_WITH_INPUT() { return getToken(questionnaireParser.MULTIPLE_CHOICE_WITH_INPUT, 0); }
		public TerminalNode SORTING_OPTION() { return getToken(questionnaireParser.SORTING_OPTION, 0); }
		public TerminalNode SCALING_OPTION() { return getToken(questionnaireParser.SCALING_OPTION, 0); }
		public FraseContext frase() {
			return getRuleContext(FraseContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_type);
		int _la;
		try {
			setState(223);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FREE_TEXT:
				enterOuterAlt(_localctx, 1);
				{
				setState(168);
				match(FREE_TEXT);
				setState(169);
				match(NEWLINE);
				}
				break;
			case NUMERIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(170);
				match(NUMERIC);
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ESPACO) {
					{
					setState(171);
					match(ESPACO);
					setState(172);
					match(PARENTESIS_ESQUERDO);
					setState(173);
					match(DECIMALS_ALLOWED);
					setState(174);
					match(PARENTESIS_DIREITO);
					}
				}

				setState(177);
				match(NEWLINE);
				}
				break;
			case SINGLE_CHOICE:
				enterOuterAlt(_localctx, 3);
				{
				setState(178);
				match(SINGLE_CHOICE);
				setState(179);
				match(NEWLINE);
				setState(181); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(180);
					option();
					}
					}
					setState(183); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DIGITO );
				}
				break;
			case MULTIPLE_CHOICE:
				enterOuterAlt(_localctx, 4);
				{
				setState(185);
				match(MULTIPLE_CHOICE);
				setState(186);
				match(NEWLINE);
				setState(188); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(187);
					option();
					}
					}
					setState(190); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DIGITO );
				}
				break;
			case SINGLE_CHOICE_WITH_INPUT:
				enterOuterAlt(_localctx, 5);
				{
				setState(192);
				match(SINGLE_CHOICE_WITH_INPUT);
				setState(193);
				match(NEWLINE);
				setState(195); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(194);
					option();
					}
					}
					setState(197); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DIGITO );
				}
				break;
			case MULTIPLE_CHOICE_WITH_INPUT:
				enterOuterAlt(_localctx, 6);
				{
				setState(199);
				match(MULTIPLE_CHOICE_WITH_INPUT);
				setState(200);
				match(NEWLINE);
				setState(202); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(201);
					option();
					}
					}
					setState(204); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DIGITO );
				}
				break;
			case SORTING_OPTION:
				enterOuterAlt(_localctx, 7);
				{
				setState(206);
				match(SORTING_OPTION);
				setState(207);
				match(NEWLINE);
				setState(209); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(208);
					option();
					}
					}
					setState(211); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DIGITO );
				}
				break;
			case SCALING_OPTION:
				enterOuterAlt(_localctx, 8);
				{
				setState(213);
				match(SCALING_OPTION);
				setState(214);
				match(NEWLINE);
				setState(215);
				match(T__3);
				setState(216);
				frase();
				setState(217);
				match(NEWLINE);
				setState(219); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(218);
					option();
					}
					}
					setState(221); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DIGITO );
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

	public static final String _serializedATN =
		"\u0004\u0001\u001b\u00e2\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0003\u0001!\b\u0001\u0001\u0001\u0001\u0001\u0004\u0001%\b\u0001"+
		"\u000b\u0001\f\u0001&\u0005\u0001)\b\u0001\n\u0001\f\u0001,\t\u0001\u0001"+
		"\u0001\u0004\u0001/\b\u0001\u000b\u0001\f\u00010\u0001\u0001\u0003\u0001"+
		"4\b\u0001\u0001\u0001\u0001\u0001\u0004\u00018\b\u0001\u000b\u0001\f\u0001"+
		"9\u0005\u0001<\b\u0001\n\u0001\f\u0001?\t\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001C\b\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0004\u0003K\b\u0003\u000b\u0003\f\u0003L\u0003"+
		"\u0003O\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004V\b\u0004\u0001\u0004\u0001\u0004\u0004\u0004Z\b\u0004"+
		"\u000b\u0004\f\u0004[\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0004\u0005c\b\u0005\u000b\u0005\f\u0005d\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0003\u0006k\b\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006r\b\u0006\u0001"+
		"\u0006\u0001\u0006\u0004\u0006v\b\u0006\u000b\u0006\f\u0006w\u0001\u0007"+
		"\u0004\u0007{\b\u0007\u000b\u0007\f\u0007|\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0003\b\u0085\b\b\u0001\t\u0004\t\u0088\b\t\u000b\t"+
		"\f\t\u0089\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\n\u0093\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\n\u009c\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u00a2\b\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\r"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00b0\b\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0004\r\u00b6\b\r\u000b\r\f\r\u00b7\u0001\r"+
		"\u0001\r\u0001\r\u0004\r\u00bd\b\r\u000b\r\f\r\u00be\u0001\r\u0001\r\u0001"+
		"\r\u0004\r\u00c4\b\r\u000b\r\f\r\u00c5\u0001\r\u0001\r\u0001\r\u0004\r"+
		"\u00cb\b\r\u000b\r\f\r\u00cc\u0001\r\u0001\r\u0001\r\u0004\r\u00d2\b\r"+
		"\u000b\r\f\r\u00d3\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0004"+
		"\r\u00dc\b\r\u000b\r\f\r\u00dd\u0003\r\u00e0\b\r\u0001\r\u0000\u0000\u000e"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u0000\u0001\u0001\u0000\u0011\u0012\u00f9\u0000\u001c\u0001\u0000\u0000"+
		"\u0000\u0002B\u0001\u0000\u0000\u0000\u0004D\u0001\u0000\u0000\u0000\u0006"+
		"N\u0001\u0000\u0000\u0000\bP\u0001\u0000\u0000\u0000\nb\u0001\u0000\u0000"+
		"\u0000\ff\u0001\u0000\u0000\u0000\u000ez\u0001\u0000\u0000\u0000\u0010"+
		"\u0084\u0001\u0000\u0000\u0000\u0012\u0087\u0001\u0000\u0000\u0000\u0014"+
		"\u008b\u0001\u0000\u0000\u0000\u0016\u009d\u0001\u0000\u0000\u0000\u0018"+
		"\u00a5\u0001\u0000\u0000\u0000\u001a\u00df\u0001\u0000\u0000\u0000\u001c"+
		"\u001d\u0007\u0000\u0000\u0000\u001d\u0001\u0001\u0000\u0000\u0000\u001e"+
		"*\u0005\u0012\u0000\u0000\u001f!\u0005\u0015\u0000\u0000 \u001f\u0001"+
		"\u0000\u0000\u0000 !\u0001\u0000\u0000\u0000!\"\u0001\u0000\u0000\u0000"+
		"\"$\u0005\u0016\u0000\u0000#%\u0007\u0000\u0000\u0000$#\u0001\u0000\u0000"+
		"\u0000%&\u0001\u0000\u0000\u0000&$\u0001\u0000\u0000\u0000&\'\u0001\u0000"+
		"\u0000\u0000\')\u0001\u0000\u0000\u0000( \u0001\u0000\u0000\u0000),\u0001"+
		"\u0000\u0000\u0000*(\u0001\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000"+
		"+C\u0001\u0000\u0000\u0000,*\u0001\u0000\u0000\u0000-/\u0005\u0011\u0000"+
		"\u0000.-\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000\u00000.\u0001\u0000"+
		"\u0000\u000001\u0001\u0000\u0000\u00001=\u0001\u0000\u0000\u000024\u0005"+
		"\u0015\u0000\u000032\u0001\u0000\u0000\u000034\u0001\u0000\u0000\u0000"+
		"45\u0001\u0000\u0000\u000057\u0005\u0016\u0000\u000068\u0007\u0000\u0000"+
		"\u000076\u0001\u0000\u0000\u000089\u0001\u0000\u0000\u000097\u0001\u0000"+
		"\u0000\u00009:\u0001\u0000\u0000\u0000:<\u0001\u0000\u0000\u0000;3\u0001"+
		"\u0000\u0000\u0000<?\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000"+
		"=>\u0001\u0000\u0000\u0000>C\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000"+
		"\u0000@A\u0005\u0012\u0000\u0000AC\u0005\u0015\u0000\u0000B\u001e\u0001"+
		"\u0000\u0000\u0000B.\u0001\u0000\u0000\u0000B@\u0001\u0000\u0000\u0000"+
		"C\u0003\u0001\u0000\u0000\u0000DE\u0003\u0002\u0001\u0000E\u0005\u0001"+
		"\u0000\u0000\u0000FO\u0003\u0002\u0001\u0000GH\u0003\u0002\u0001\u0000"+
		"HI\u0005\u001a\u0000\u0000IK\u0001\u0000\u0000\u0000JG\u0001\u0000\u0000"+
		"\u0000KL\u0001\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000LM\u0001\u0000"+
		"\u0000\u0000MO\u0001\u0000\u0000\u0000NF\u0001\u0000\u0000\u0000NJ\u0001"+
		"\u0000\u0000\u0000O\u0007\u0001\u0000\u0000\u0000PQ\u0003\n\u0005\u0000"+
		"QR\u0005\u0016\u0000\u0000RS\u0003\u0004\u0002\u0000SU\u0005\u001a\u0000"+
		"\u0000TV\u0003\u0006\u0003\u0000UT\u0001\u0000\u0000\u0000UV\u0001\u0000"+
		"\u0000\u0000VY\u0001\u0000\u0000\u0000WX\u0005\u001a\u0000\u0000XZ\u0003"+
		"\f\u0006\u0000YW\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000[Y\u0001"+
		"\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000"+
		"]^\u0005\u001a\u0000\u0000^_\u0005\u001a\u0000\u0000_`\u0003\u0006\u0003"+
		"\u0000`\t\u0001\u0000\u0000\u0000ac\u0003\u0000\u0000\u0000ba\u0001\u0000"+
		"\u0000\u0000cd\u0001\u0000\u0000\u0000db\u0001\u0000\u0000\u0000de\u0001"+
		"\u0000\u0000\u0000e\u000b\u0001\u0000\u0000\u0000fg\u0003\u000e\u0007"+
		"\u0000gh\u0003\u0004\u0002\u0000hj\u0005\u001a\u0000\u0000ik\u0003\u0006"+
		"\u0003\u0000ji\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000kl\u0001"+
		"\u0000\u0000\u0000lm\u0005\u0001\u0000\u0000mq\u0003\u0010\b\u0000no\u0005"+
		"\u001a\u0000\u0000op\u0005\u0002\u0000\u0000pr\u0003\u0012\t\u0000qn\u0001"+
		"\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000"+
		"su\u0005\u001a\u0000\u0000tv\u0003\u0014\n\u0000ut\u0001\u0000\u0000\u0000"+
		"vw\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000"+
		"\u0000x\r\u0001\u0000\u0000\u0000y{\u0005\u0011\u0000\u0000zy\u0001\u0000"+
		"\u0000\u0000{|\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000|}\u0001"+
		"\u0000\u0000\u0000}\u000f\u0001\u0000\u0000\u0000~\u0085\u0005\u000e\u0000"+
		"\u0000\u007f\u0085\u0005\u000f\u0000\u0000\u0080\u0081\u0005\u0010\u0000"+
		"\u0000\u0081\u0082\u0005\u0017\u0000\u0000\u0082\u0083\u0005\u0016\u0000"+
		"\u0000\u0083\u0085\u0003\u0002\u0001\u0000\u0084~\u0001\u0000\u0000\u0000"+
		"\u0084\u007f\u0001\u0000\u0000\u0000\u0084\u0080\u0001\u0000\u0000\u0000"+
		"\u0085\u0011\u0001\u0000\u0000\u0000\u0086\u0088\u0005\u0011\u0000\u0000"+
		"\u0087\u0086\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000"+
		"\u0089\u0087\u0001\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000"+
		"\u008a\u0013\u0001\u0000\u0000\u0000\u008b\u008c\u0003\u000e\u0007\u0000"+
		"\u008c\u008d\u0003\u0018\f\u0000\u008d\u008e\u0005\u0014\u0000\u0000\u008e"+
		"\u008f\u0003\u0010\b\u0000\u008f\u0092\u0005\u0013\u0000\u0000\u0090\u0091"+
		"\u0005\u001a\u0000\u0000\u0091\u0093\u0003\u0006\u0003\u0000\u0092\u0090"+
		"\u0001\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0094"+
		"\u0001\u0000\u0000\u0000\u0094\u0095\u0005\u001a\u0000\u0000\u0095\u0096"+
		"\u0005\u0003\u0000\u0000\u0096\u0097\u0003\u001a\r\u0000\u0097\u009b\u0005"+
		"\u001a\u0000\u0000\u0098\u0099\u0003\u0006\u0003\u0000\u0099\u009a\u0005"+
		"\u001a\u0000\u0000\u009a\u009c\u0001\u0000\u0000\u0000\u009b\u0098\u0001"+
		"\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c\u0015\u0001"+
		"\u0000\u0000\u0000\u009d\u009e\u0003\u000e\u0007\u0000\u009e\u009f\u0005"+
		"\u0013\u0000\u0000\u009f\u00a1\u0003\u0002\u0001\u0000\u00a0\u00a2\u0005"+
		"\u0017\u0000\u0000\u00a1\u00a0\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001"+
		"\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a4\u0005"+
		"\u001a\u0000\u0000\u00a4\u0017\u0001\u0000\u0000\u0000\u00a5\u00a6\u0003"+
		"\u0002\u0001\u0000\u00a6\u00a7\u0005\u0018\u0000\u0000\u00a7\u0019\u0001"+
		"\u0000\u0000\u0000\u00a8\u00a9\u0005\b\u0000\u0000\u00a9\u00e0\u0005\u001a"+
		"\u0000\u0000\u00aa\u00af\u0005\t\u0000\u0000\u00ab\u00ac\u0005\u0016\u0000"+
		"\u0000\u00ac\u00ad\u0005\u0014\u0000\u0000\u00ad\u00ae\u0005\u0005\u0000"+
		"\u0000\u00ae\u00b0\u0005\u0013\u0000\u0000\u00af\u00ab\u0001\u0000\u0000"+
		"\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000"+
		"\u0000\u00b1\u00e0\u0005\u001a\u0000\u0000\u00b2\u00b3\u0005\n\u0000\u0000"+
		"\u00b3\u00b5\u0005\u001a\u0000\u0000\u00b4\u00b6\u0003\u0016\u000b\u0000"+
		"\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b5\u0001\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000"+
		"\u00b8\u00e0\u0001\u0000\u0000\u0000\u00b9\u00ba\u0005\u000b\u0000\u0000"+
		"\u00ba\u00bc\u0005\u001a\u0000\u0000\u00bb\u00bd\u0003\u0016\u000b\u0000"+
		"\u00bc\u00bb\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000"+
		"\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000\u0000"+
		"\u00bf\u00e0\u0001\u0000\u0000\u0000\u00c0\u00c1\u0005\u0006\u0000\u0000"+
		"\u00c1\u00c3\u0005\u001a\u0000\u0000\u00c2\u00c4\u0003\u0016\u000b\u0000"+
		"\u00c3\u00c2\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000"+
		"\u00c5\u00c3\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000\u0000\u0000"+
		"\u00c6\u00e0\u0001\u0000\u0000\u0000\u00c7\u00c8\u0005\u0007\u0000\u0000"+
		"\u00c8\u00ca\u0005\u001a\u0000\u0000\u00c9\u00cb\u0003\u0016\u000b\u0000"+
		"\u00ca\u00c9\u0001\u0000\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000\u0000"+
		"\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000"+
		"\u00cd\u00e0\u0001\u0000\u0000\u0000\u00ce\u00cf\u0005\f\u0000\u0000\u00cf"+
		"\u00d1\u0005\u001a\u0000\u0000\u00d0\u00d2\u0003\u0016\u000b\u0000\u00d1"+
		"\u00d0\u0001\u0000\u0000\u0000\u00d2\u00d3\u0001\u0000\u0000\u0000\u00d3"+
		"\u00d1\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000\u00d4"+
		"\u00e0\u0001\u0000\u0000\u0000\u00d5\u00d6\u0005\r\u0000\u0000\u00d6\u00d7"+
		"\u0005\u001a\u0000\u0000\u00d7\u00d8\u0005\u0004\u0000\u0000\u00d8\u00d9"+
		"\u0003\u0002\u0001\u0000\u00d9\u00db\u0005\u001a\u0000\u0000\u00da\u00dc"+
		"\u0003\u0016\u000b\u0000\u00db\u00da\u0001\u0000\u0000\u0000\u00dc\u00dd"+
		"\u0001\u0000\u0000\u0000\u00dd\u00db\u0001\u0000\u0000\u0000\u00dd\u00de"+
		"\u0001\u0000\u0000\u0000\u00de\u00e0\u0001\u0000\u0000\u0000\u00df\u00a8"+
		"\u0001\u0000\u0000\u0000\u00df\u00aa\u0001\u0000\u0000\u0000\u00df\u00b2"+
		"\u0001\u0000\u0000\u0000\u00df\u00b9\u0001\u0000\u0000\u0000\u00df\u00c0"+
		"\u0001\u0000\u0000\u0000\u00df\u00c7\u0001\u0000\u0000\u0000\u00df\u00ce"+
		"\u0001\u0000\u0000\u0000\u00df\u00d5\u0001\u0000\u0000\u0000\u00e0\u001b"+
		"\u0001\u0000\u0000\u0000\u001e &*039=BLNU[djqw|\u0084\u0089\u0092\u009b"+
		"\u00a1\u00af\u00b7\u00be\u00c5\u00cc\u00d3\u00dd\u00df";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}