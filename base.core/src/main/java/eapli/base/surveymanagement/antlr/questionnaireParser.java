// Generated from C:/Users/Ana Albergaria/OneDrive - Instituto Superior de Engenharia do Porto/Ano 2/lei21_22_s4_2df_01/base.core/src/main/java/eapli/base/surveymanagement/antlr\questionnaire.g4 by ANTLR 4.10.1
package eapli.base.surveymanagement.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class questionnaireParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, SINGLE_CHOICE_WITH_INPUT=4, MULTIPLE_CHOICE_WITH_INPUT=5, 
		FREE_TEXT=6, NUMERIC=7, SINGLE_CHOICE=8, MULTIPLE_CHOICE=9, SORTING_OPTION=10, 
		SCALING_OPTION=11, YES=12, NO=13, MANDATORY=14, OPTIONAL=15, CONDITION_DEPENDENT=16, 
		DIGITO=17, PALAVRA=18, PARENTESIS_DIREITO=19, PARENTESIS_ESQUERDO=20, 
		VIRGULA=21, ESPACO=22, DOIS_PONTOS=23, PONTO_INTERROGACAO=24, PONTO_EXCLAMACAO=25, 
		NEWLINE=26, WS=27;
	public static final int
		RULE_alfanumerico = 0, RULE_frase = 1, RULE_title = 2, RULE_message = 3, 
		RULE_survey = 4, RULE_questionnaire_id = 5, RULE_section = 6, RULE_numeric_id = 7, 
		RULE_obligatoriness = 8, RULE_repeatability = 9, RULE_question = 10, RULE_option = 11, 
		RULE_question_text = 12, RULE_type = 13, RULE_single_choice = 14, RULE_single_choice_input = 15, 
		RULE_multiple_choice = 16, RULE_multiple_choice_input = 17, RULE_sorting_option = 18, 
		RULE_scaling_option = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"alfanumerico", "frase", "title", "message", "survey", "questionnaire_id", 
			"section", "numeric_id", "obligatoriness", "repeatability", "question", 
			"option", "question_text", "type", "single_choice", "single_choice_input", 
			"multiple_choice", "multiple_choice_input", "sorting_option", "scaling_option"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Section Obligatoriness: '", "'Repeatability: '", "'Type: '", 
			"'single choice with input'", "'multiple choice with input'", "'free text'", 
			"'numeric'", "'single choice'", "'multiple choice'", "'sorting option'", 
			"'scaling option'", "'yes'", "'no'", "'mandatory'", "'optional'", "'condition dependent'", 
			null, null, "')'", "'('", "','", "' '", "':'", "'?'", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "SINGLE_CHOICE_WITH_INPUT", "MULTIPLE_CHOICE_WITH_INPUT", 
			"FREE_TEXT", "NUMERIC", "SINGLE_CHOICE", "MULTIPLE_CHOICE", "SORTING_OPTION", 
			"SCALING_OPTION", "YES", "NO", "MANDATORY", "OPTIONAL", "CONDITION_DEPENDENT", 
			"DIGITO", "PALAVRA", "PARENTESIS_DIREITO", "PARENTESIS_ESQUERDO", "VIRGULA", 
			"ESPACO", "DOIS_PONTOS", "PONTO_INTERROGACAO", "PONTO_EXCLAMACAO", "NEWLINE", 
			"WS"
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
			setState(40);
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(PALAVRA);
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRGULA || _la==ESPACO) {
				{
				{
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VIRGULA) {
					{
					setState(43);
					match(VIRGULA);
					}
				}

				setState(46);
				match(ESPACO);
				setState(50);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(47);
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
					}
					setState(52);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}
				}
				}
				setState(57);
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
	public static class LengthTextContext extends TitleContext {
		public FraseContext frase() {
			return getRuleContext(FraseContext.class,0);
		}
		public LengthTextContext(TitleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterLengthText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitLengthText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitLengthText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TitleContext title() throws RecognitionException {
		TitleContext _localctx = new TitleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_title);
		try {
			_localctx = new LengthTextContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
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
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				frase();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(64); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(61);
					frase();
					setState(62);
					match(NEWLINE);
					}
					}
					setState(66); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==PALAVRA );
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
			setState(70);
			questionnaire_id();
			setState(71);
			match(ESPACO);
			setState(72);
			title();
			setState(73);
			match(NEWLINE);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PALAVRA) {
				{
				setState(74);
				message();
				}
			}

			setState(79); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(77);
					match(NEWLINE);
					setState(78);
					section();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(81); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(83);
			match(NEWLINE);
			setState(84);
			match(NEWLINE);
			setState(85);
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
			setState(88); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(87);
				alfanumerico();
				}
				}
				setState(90); 
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
		public RepeatabilityContext repeatability() {
			return getRuleContext(RepeatabilityContext.class,0);
		}
		public MessageContext message() {
			return getRuleContext(MessageContext.class,0);
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
			setState(92);
			numeric_id();
			setState(93);
			title();
			setState(94);
			match(NEWLINE);
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PALAVRA) {
				{
				setState(95);
				message();
				}
			}

			setState(98);
			match(T__0);
			setState(99);
			obligatoriness();
			setState(100);
			match(NEWLINE);
			setState(101);
			match(T__1);
			setState(102);
			repeatability();
			setState(103);
			match(NEWLINE);
			setState(105); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(104);
				question();
				}
				}
				setState(107); 
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(109);
				match(DIGITO);
				}
				}
				setState(112); 
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
			setState(120);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MANDATORY:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				match(MANDATORY);
				}
				break;
			case OPTIONAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				match(OPTIONAL);
				}
				break;
			case CONDITION_DEPENDENT:
				enterOuterAlt(_localctx, 3);
				{
				setState(116);
				match(CONDITION_DEPENDENT);
				setState(117);
				match(DOIS_PONTOS);
				setState(118);
				match(ESPACO);
				setState(119);
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
		public RepeatabilityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeatability; }
	 
		public RepeatabilityContext() { }
		public void copyFrom(RepeatabilityContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RepeatabilityYesContext extends RepeatabilityContext {
		public TerminalNode YES() { return getToken(questionnaireParser.YES, 0); }
		public RepeatabilityYesContext(RepeatabilityContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterRepeatabilityYes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitRepeatabilityYes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitRepeatabilityYes(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RepeatibilityNoContext extends RepeatabilityContext {
		public TerminalNode NO() { return getToken(questionnaireParser.NO, 0); }
		public RepeatibilityNoContext(RepeatabilityContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterRepeatibilityNo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitRepeatibilityNo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitRepeatibilityNo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepeatabilityContext repeatability() throws RecognitionException {
		RepeatabilityContext _localctx = new RepeatabilityContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_repeatability);
		try {
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case YES:
				_localctx = new RepeatabilityYesContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				match(YES);
				}
				break;
			case NO:
				_localctx = new RepeatibilityNoContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				match(NO);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			numeric_id();
			setState(127);
			question_text();
			setState(128);
			match(PARENTESIS_ESQUERDO);
			setState(129);
			obligatoriness();
			setState(130);
			match(PARENTESIS_DIREITO);
			setState(133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(131);
				match(NEWLINE);
				setState(132);
				message();
				}
				break;
			}
			setState(135);
			match(NEWLINE);
			setState(136);
			match(T__2);
			setState(137);
			type();
			setState(138);
			match(NEWLINE);
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PALAVRA) {
				{
				setState(139);
				message();
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

	public static class OptionContext extends ParserRuleContext {
		public Numeric_idContext numeric_id() {
			return getRuleContext(Numeric_idContext.class,0);
		}
		public TerminalNode PARENTESIS_DIREITO() { return getToken(questionnaireParser.PARENTESIS_DIREITO, 0); }
		public FraseContext frase() {
			return getRuleContext(FraseContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(questionnaireParser.NEWLINE, 0); }
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			numeric_id();
			setState(143);
			match(PARENTESIS_DIREITO);
			setState(144);
			frase();
			setState(145);
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
			setState(147);
			frase();
			setState(148);
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
		public TerminalNode NUMERIC() { return getToken(questionnaireParser.NUMERIC, 0); }
		public TerminalNode SINGLE_CHOICE() { return getToken(questionnaireParser.SINGLE_CHOICE, 0); }
		public TerminalNode NEWLINE() { return getToken(questionnaireParser.NEWLINE, 0); }
		public Single_choiceContext single_choice() {
			return getRuleContext(Single_choiceContext.class,0);
		}
		public TerminalNode MULTIPLE_CHOICE() { return getToken(questionnaireParser.MULTIPLE_CHOICE, 0); }
		public Multiple_choiceContext multiple_choice() {
			return getRuleContext(Multiple_choiceContext.class,0);
		}
		public TerminalNode SINGLE_CHOICE_WITH_INPUT() { return getToken(questionnaireParser.SINGLE_CHOICE_WITH_INPUT, 0); }
		public Single_choice_inputContext single_choice_input() {
			return getRuleContext(Single_choice_inputContext.class,0);
		}
		public TerminalNode MULTIPLE_CHOICE_WITH_INPUT() { return getToken(questionnaireParser.MULTIPLE_CHOICE_WITH_INPUT, 0); }
		public Multiple_choice_inputContext multiple_choice_input() {
			return getRuleContext(Multiple_choice_inputContext.class,0);
		}
		public TerminalNode SORTING_OPTION() { return getToken(questionnaireParser.SORTING_OPTION, 0); }
		public Sorting_optionContext sorting_option() {
			return getRuleContext(Sorting_optionContext.class,0);
		}
		public TerminalNode SCALING_OPTION() { return getToken(questionnaireParser.SCALING_OPTION, 0); }
		public Scaling_optionContext scaling_option() {
			return getRuleContext(Scaling_optionContext.class,0);
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
		try {
			setState(170);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FREE_TEXT:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				match(FREE_TEXT);
				}
				break;
			case NUMERIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(151);
				match(NUMERIC);
				}
				break;
			case SINGLE_CHOICE:
				enterOuterAlt(_localctx, 3);
				{
				setState(152);
				match(SINGLE_CHOICE);
				setState(153);
				match(NEWLINE);
				setState(154);
				single_choice();
				}
				break;
			case MULTIPLE_CHOICE:
				enterOuterAlt(_localctx, 4);
				{
				setState(155);
				match(MULTIPLE_CHOICE);
				setState(156);
				match(NEWLINE);
				setState(157);
				multiple_choice();
				}
				break;
			case SINGLE_CHOICE_WITH_INPUT:
				enterOuterAlt(_localctx, 5);
				{
				setState(158);
				match(SINGLE_CHOICE_WITH_INPUT);
				setState(159);
				match(NEWLINE);
				setState(160);
				single_choice_input();
				}
				break;
			case MULTIPLE_CHOICE_WITH_INPUT:
				enterOuterAlt(_localctx, 6);
				{
				setState(161);
				match(MULTIPLE_CHOICE_WITH_INPUT);
				setState(162);
				match(NEWLINE);
				setState(163);
				multiple_choice_input();
				}
				break;
			case SORTING_OPTION:
				enterOuterAlt(_localctx, 7);
				{
				setState(164);
				match(SORTING_OPTION);
				setState(165);
				match(NEWLINE);
				setState(166);
				sorting_option();
				}
				break;
			case SCALING_OPTION:
				enterOuterAlt(_localctx, 8);
				{
				setState(167);
				match(SCALING_OPTION);
				setState(168);
				match(NEWLINE);
				setState(169);
				scaling_option();
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

	public static class Single_choiceContext extends ParserRuleContext {
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public Single_choiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_choice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterSingle_choice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitSingle_choice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitSingle_choice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Single_choiceContext single_choice() throws RecognitionException {
		Single_choiceContext _localctx = new Single_choiceContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_single_choice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(172);
				option();
				}
				}
				setState(175); 
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

	public static class Single_choice_inputContext extends ParserRuleContext {
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public Single_choice_inputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_choice_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterSingle_choice_input(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitSingle_choice_input(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitSingle_choice_input(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Single_choice_inputContext single_choice_input() throws RecognitionException {
		Single_choice_inputContext _localctx = new Single_choice_inputContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_single_choice_input);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(177);
				option();
				}
				}
				setState(180); 
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

	public static class Multiple_choiceContext extends ParserRuleContext {
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public Multiple_choiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_choice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterMultiple_choice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitMultiple_choice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitMultiple_choice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiple_choiceContext multiple_choice() throws RecognitionException {
		Multiple_choiceContext _localctx = new Multiple_choiceContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_multiple_choice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(182);
				option();
				}
				}
				setState(185); 
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

	public static class Multiple_choice_inputContext extends ParserRuleContext {
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public Multiple_choice_inputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_choice_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterMultiple_choice_input(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitMultiple_choice_input(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitMultiple_choice_input(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiple_choice_inputContext multiple_choice_input() throws RecognitionException {
		Multiple_choice_inputContext _localctx = new Multiple_choice_inputContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_multiple_choice_input);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
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

	public static class Sorting_optionContext extends ParserRuleContext {
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public Sorting_optionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sorting_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterSorting_option(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitSorting_option(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitSorting_option(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sorting_optionContext sorting_option() throws RecognitionException {
		Sorting_optionContext _localctx = new Sorting_optionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_sorting_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(192);
				option();
				}
				}
				setState(195); 
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

	public static class Scaling_optionContext extends ParserRuleContext {
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public Scaling_optionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scaling_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterScaling_option(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitScaling_option(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitScaling_option(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Scaling_optionContext scaling_option() throws RecognitionException {
		Scaling_optionContext _localctx = new Scaling_optionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_scaling_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(197);
				option();
				}
				}
				setState(200); 
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

	public static final String _serializedATN =
		"\u0004\u0001\u001b\u00cb\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0003\u0001-\b\u0001\u0001\u0001\u0001\u0001\u0005\u00011\b\u0001"+
		"\n\u0001\f\u00014\t\u0001\u0005\u00016\b\u0001\n\u0001\f\u00019\t\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0004\u0003A\b\u0003\u000b\u0003\f\u0003B\u0003\u0003E\b\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004L\b"+
		"\u0004\u0001\u0004\u0001\u0004\u0004\u0004P\b\u0004\u000b\u0004\f\u0004"+
		"Q\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0004\u0005"+
		"Y\b\u0005\u000b\u0005\f\u0005Z\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006a\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0004\u0006j\b\u0006\u000b"+
		"\u0006\f\u0006k\u0001\u0007\u0004\u0007o\b\u0007\u000b\u0007\f\u0007p"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\by\b\b\u0001\t"+
		"\u0001\t\u0003\t}\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0003\n\u0086\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\n\u008d\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00ab\b\r\u0001\u000e"+
		"\u0004\u000e\u00ae\b\u000e\u000b\u000e\f\u000e\u00af\u0001\u000f\u0004"+
		"\u000f\u00b3\b\u000f\u000b\u000f\f\u000f\u00b4\u0001\u0010\u0004\u0010"+
		"\u00b8\b\u0010\u000b\u0010\f\u0010\u00b9\u0001\u0011\u0004\u0011\u00bd"+
		"\b\u0011\u000b\u0011\f\u0011\u00be\u0001\u0012\u0004\u0012\u00c2\b\u0012"+
		"\u000b\u0012\f\u0012\u00c3\u0001\u0013\u0004\u0013\u00c7\b\u0013\u000b"+
		"\u0013\f\u0013\u00c8\u0001\u0013\u0000\u0000\u0014\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&\u0000"+
		"\u0001\u0001\u0000\u0011\u0012\u00d3\u0000(\u0001\u0000\u0000\u0000\u0002"+
		"*\u0001\u0000\u0000\u0000\u0004:\u0001\u0000\u0000\u0000\u0006D\u0001"+
		"\u0000\u0000\u0000\bF\u0001\u0000\u0000\u0000\nX\u0001\u0000\u0000\u0000"+
		"\f\\\u0001\u0000\u0000\u0000\u000en\u0001\u0000\u0000\u0000\u0010x\u0001"+
		"\u0000\u0000\u0000\u0012|\u0001\u0000\u0000\u0000\u0014~\u0001\u0000\u0000"+
		"\u0000\u0016\u008e\u0001\u0000\u0000\u0000\u0018\u0093\u0001\u0000\u0000"+
		"\u0000\u001a\u00aa\u0001\u0000\u0000\u0000\u001c\u00ad\u0001\u0000\u0000"+
		"\u0000\u001e\u00b2\u0001\u0000\u0000\u0000 \u00b7\u0001\u0000\u0000\u0000"+
		"\"\u00bc\u0001\u0000\u0000\u0000$\u00c1\u0001\u0000\u0000\u0000&\u00c6"+
		"\u0001\u0000\u0000\u0000()\u0007\u0000\u0000\u0000)\u0001\u0001\u0000"+
		"\u0000\u0000*7\u0005\u0012\u0000\u0000+-\u0005\u0015\u0000\u0000,+\u0001"+
		"\u0000\u0000\u0000,-\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000"+
		".2\u0005\u0016\u0000\u0000/1\u0007\u0000\u0000\u00000/\u0001\u0000\u0000"+
		"\u000014\u0001\u0000\u0000\u000020\u0001\u0000\u0000\u000023\u0001\u0000"+
		"\u0000\u000036\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u00005,\u0001"+
		"\u0000\u0000\u000069\u0001\u0000\u0000\u000075\u0001\u0000\u0000\u0000"+
		"78\u0001\u0000\u0000\u00008\u0003\u0001\u0000\u0000\u000097\u0001\u0000"+
		"\u0000\u0000:;\u0003\u0002\u0001\u0000;\u0005\u0001\u0000\u0000\u0000"+
		"<E\u0003\u0002\u0001\u0000=>\u0003\u0002\u0001\u0000>?\u0005\u001a\u0000"+
		"\u0000?A\u0001\u0000\u0000\u0000@=\u0001\u0000\u0000\u0000AB\u0001\u0000"+
		"\u0000\u0000B@\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000CE\u0001"+
		"\u0000\u0000\u0000D<\u0001\u0000\u0000\u0000D@\u0001\u0000\u0000\u0000"+
		"E\u0007\u0001\u0000\u0000\u0000FG\u0003\n\u0005\u0000GH\u0005\u0016\u0000"+
		"\u0000HI\u0003\u0004\u0002\u0000IK\u0005\u001a\u0000\u0000JL\u0003\u0006"+
		"\u0003\u0000KJ\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000LO\u0001"+
		"\u0000\u0000\u0000MN\u0005\u001a\u0000\u0000NP\u0003\f\u0006\u0000OM\u0001"+
		"\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000QO\u0001\u0000\u0000\u0000"+
		"QR\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000ST\u0005\u001a\u0000"+
		"\u0000TU\u0005\u001a\u0000\u0000UV\u0003\u0006\u0003\u0000V\t\u0001\u0000"+
		"\u0000\u0000WY\u0003\u0000\u0000\u0000XW\u0001\u0000\u0000\u0000YZ\u0001"+
		"\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000"+
		"[\u000b\u0001\u0000\u0000\u0000\\]\u0003\u000e\u0007\u0000]^\u0003\u0004"+
		"\u0002\u0000^`\u0005\u001a\u0000\u0000_a\u0003\u0006\u0003\u0000`_\u0001"+
		"\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000"+
		"bc\u0005\u0001\u0000\u0000cd\u0003\u0010\b\u0000de\u0005\u001a\u0000\u0000"+
		"ef\u0005\u0002\u0000\u0000fg\u0003\u0012\t\u0000gi\u0005\u001a\u0000\u0000"+
		"hj\u0003\u0014\n\u0000ih\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000"+
		"ki\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000l\r\u0001\u0000\u0000"+
		"\u0000mo\u0005\u0011\u0000\u0000nm\u0001\u0000\u0000\u0000op\u0001\u0000"+
		"\u0000\u0000pn\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000q\u000f"+
		"\u0001\u0000\u0000\u0000ry\u0005\u000e\u0000\u0000sy\u0005\u000f\u0000"+
		"\u0000tu\u0005\u0010\u0000\u0000uv\u0005\u0017\u0000\u0000vw\u0005\u0016"+
		"\u0000\u0000wy\u0003\u0002\u0001\u0000xr\u0001\u0000\u0000\u0000xs\u0001"+
		"\u0000\u0000\u0000xt\u0001\u0000\u0000\u0000y\u0011\u0001\u0000\u0000"+
		"\u0000z}\u0005\f\u0000\u0000{}\u0005\r\u0000\u0000|z\u0001\u0000\u0000"+
		"\u0000|{\u0001\u0000\u0000\u0000}\u0013\u0001\u0000\u0000\u0000~\u007f"+
		"\u0003\u000e\u0007\u0000\u007f\u0080\u0003\u0018\f\u0000\u0080\u0081\u0005"+
		"\u0014\u0000\u0000\u0081\u0082\u0003\u0010\b\u0000\u0082\u0085\u0005\u0013"+
		"\u0000\u0000\u0083\u0084\u0005\u001a\u0000\u0000\u0084\u0086\u0003\u0006"+
		"\u0003\u0000\u0085\u0083\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000"+
		"\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000\u0087\u0088\u0005\u001a"+
		"\u0000\u0000\u0088\u0089\u0005\u0003\u0000\u0000\u0089\u008a\u0003\u001a"+
		"\r\u0000\u008a\u008c\u0005\u001a\u0000\u0000\u008b\u008d\u0003\u0006\u0003"+
		"\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000"+
		"\u0000\u008d\u0015\u0001\u0000\u0000\u0000\u008e\u008f\u0003\u000e\u0007"+
		"\u0000\u008f\u0090\u0005\u0013\u0000\u0000\u0090\u0091\u0003\u0002\u0001"+
		"\u0000\u0091\u0092\u0005\u001a\u0000\u0000\u0092\u0017\u0001\u0000\u0000"+
		"\u0000\u0093\u0094\u0003\u0002\u0001\u0000\u0094\u0095\u0005\u0018\u0000"+
		"\u0000\u0095\u0019\u0001\u0000\u0000\u0000\u0096\u00ab\u0005\u0006\u0000"+
		"\u0000\u0097\u00ab\u0005\u0007\u0000\u0000\u0098\u0099\u0005\b\u0000\u0000"+
		"\u0099\u009a\u0005\u001a\u0000\u0000\u009a\u00ab\u0003\u001c\u000e\u0000"+
		"\u009b\u009c\u0005\t\u0000\u0000\u009c\u009d\u0005\u001a\u0000\u0000\u009d"+
		"\u00ab\u0003 \u0010\u0000\u009e\u009f\u0005\u0004\u0000\u0000\u009f\u00a0"+
		"\u0005\u001a\u0000\u0000\u00a0\u00ab\u0003\u001e\u000f\u0000\u00a1\u00a2"+
		"\u0005\u0005\u0000\u0000\u00a2\u00a3\u0005\u001a\u0000\u0000\u00a3\u00ab"+
		"\u0003\"\u0011\u0000\u00a4\u00a5\u0005\n\u0000\u0000\u00a5\u00a6\u0005"+
		"\u001a\u0000\u0000\u00a6\u00ab\u0003$\u0012\u0000\u00a7\u00a8\u0005\u000b"+
		"\u0000\u0000\u00a8\u00a9\u0005\u001a\u0000\u0000\u00a9\u00ab\u0003&\u0013"+
		"\u0000\u00aa\u0096\u0001\u0000\u0000\u0000\u00aa\u0097\u0001\u0000\u0000"+
		"\u0000\u00aa\u0098\u0001\u0000\u0000\u0000\u00aa\u009b\u0001\u0000\u0000"+
		"\u0000\u00aa\u009e\u0001\u0000\u0000\u0000\u00aa\u00a1\u0001\u0000\u0000"+
		"\u0000\u00aa\u00a4\u0001\u0000\u0000\u0000\u00aa\u00a7\u0001\u0000\u0000"+
		"\u0000\u00ab\u001b\u0001\u0000\u0000\u0000\u00ac\u00ae\u0003\u0016\u000b"+
		"\u0000\u00ad\u00ac\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000"+
		"\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000"+
		"\u0000\u00b0\u001d\u0001\u0000\u0000\u0000\u00b1\u00b3\u0003\u0016\u000b"+
		"\u0000\u00b2\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000"+
		"\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000"+
		"\u0000\u00b5\u001f\u0001\u0000\u0000\u0000\u00b6\u00b8\u0003\u0016\u000b"+
		"\u0000\u00b7\u00b6\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000"+
		"\u0000\u00b9\u00b7\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000"+
		"\u0000\u00ba!\u0001\u0000\u0000\u0000\u00bb\u00bd\u0003\u0016\u000b\u0000"+
		"\u00bc\u00bb\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000"+
		"\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000\u0000"+
		"\u00bf#\u0001\u0000\u0000\u0000\u00c0\u00c2\u0003\u0016\u000b\u0000\u00c1"+
		"\u00c0\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3"+
		"\u00c1\u0001\u0000\u0000\u0000\u00c3\u00c4\u0001\u0000\u0000\u0000\u00c4"+
		"%\u0001\u0000\u0000\u0000\u00c5\u00c7\u0003\u0016\u000b\u0000\u00c6\u00c5"+
		"\u0001\u0000\u0000\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8\u00c6"+
		"\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000\u00c9\'\u0001"+
		"\u0000\u0000\u0000\u0016,27BDKQZ`kpx|\u0085\u008c\u00aa\u00af\u00b4\u00b9"+
		"\u00be\u00c3\u00c8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}