// Generated from Micro.g4 by ANTLR 4.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MicroParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__32=1, T__31=2, T__30=3, T__29=4, T__28=5, T__27=6, T__26=7, T__25=8, 
		T__24=9, T__23=10, T__22=11, T__21=12, T__20=13, T__19=14, T__18=15, T__17=16, 
		T__16=17, T__15=18, T__14=19, T__13=20, T__12=21, T__11=22, T__10=23, 
		T__9=24, T__8=25, T__7=26, T__6=27, T__5=28, T__4=29, T__3=30, T__2=31, 
		T__1=32, T__0=33, KEYWORD=34, OPERATOR=35, IDENTIFIER=36, INTLITERAL=37, 
		FLOATLITERAL=38, STRINGLITERAL=39, WHITESPACE=40, COMMENT=41;
	public static final String[] tokenNames = {
		"<INVALID>", "','", "'DO'", "'*'", "'-'", "'PROGRAM'", "'('", "'READ'", 
		"'<'", "'END'", "'!='", "'<='", "'ENDIF'", "'VOID'", "'STRING'", "'IF'", 
		"'TRUE'", "'FALSE'", "'WRITE'", "')'", "'+'", "'WHILE'", "'ELSIF'", "'='", 
		"';'", "'FUNCTION'", "'>'", "'FLOAT'", "':='", "'RETURN'", "'/'", "'BEGIN'", 
		"'>='", "'INT'", "KEYWORD", "OPERATOR", "IDENTIFIER", "INTLITERAL", "FLOATLITERAL", 
		"STRINGLITERAL", "WHITESPACE", "COMMENT"
	};
	public static final int
		RULE_program = 0, RULE_id = 1, RULE_pgm_body = 2, RULE_decl = 3, RULE_string_decl = 4, 
		RULE_str = 5, RULE_var_decl = 6, RULE_var_type = 7, RULE_any_type = 8, 
		RULE_id_list = 9, RULE_id_tail = 10, RULE_param_decl_list = 11, RULE_param_decl = 12, 
		RULE_param_decl_tail = 13, RULE_func_declarations = 14, RULE_func_decl = 15, 
		RULE_func_body = 16, RULE_stmt_list = 17, RULE_stmt = 18, RULE_base_stmt = 19, 
		RULE_assign_stmt = 20, RULE_assign_expr = 21, RULE_read_stmt = 22, RULE_write_stmt = 23, 
		RULE_return_stmt = 24, RULE_expr = 25, RULE_expr_prefix = 26, RULE_factor = 27, 
		RULE_factor_prefix = 28, RULE_postfix_expr = 29, RULE_call_expr = 30, 
		RULE_expr_list = 31, RULE_expr_list_tail = 32, RULE_primary = 33, RULE_addop = 34, 
		RULE_mulop = 35, RULE_if_stmt = 36, RULE_else_part = 37, RULE_cond = 38, 
		RULE_compop = 39, RULE_do_while_stmt = 40;
	public static final String[] ruleNames = {
		"program", "id", "pgm_body", "decl", "string_decl", "str", "var_decl", 
		"var_type", "any_type", "id_list", "id_tail", "param_decl_list", "param_decl", 
		"param_decl_tail", "func_declarations", "func_decl", "func_body", "stmt_list", 
		"stmt", "base_stmt", "assign_stmt", "assign_expr", "read_stmt", "write_stmt", 
		"return_stmt", "expr", "expr_prefix", "factor", "factor_prefix", "postfix_expr", 
		"call_expr", "expr_list", "expr_list_tail", "primary", "addop", "mulop", 
		"if_stmt", "else_part", "cond", "compop", "do_while_stmt"
	};

	@Override
	public String getGrammarFileName() { return "Micro.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public MicroParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Pgm_bodyContext pgm_body() {
			return getRuleContext(Pgm_bodyContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82); match(5);
			setState(83); id();
			setState(84); match(31);
			setState(85); pgm_body();
			setState(86); match(9);
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

	public static class IdContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MicroParser.IDENTIFIER, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitId(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88); match(IDENTIFIER);
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

	public static class Pgm_bodyContext extends ParserRuleContext {
		public Func_declarationsContext func_declarations() {
			return getRuleContext(Func_declarationsContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public Pgm_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pgm_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterPgm_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitPgm_body(this);
		}
	}

	public final Pgm_bodyContext pgm_body() throws RecognitionException {
		Pgm_bodyContext _localctx = new Pgm_bodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_pgm_body);
		try {
			enterOuterAlt(_localctx, 1);
			{

									Function.scope = "GLOBAL";
									Function.addGlobal();
								
			setState(91); decl();
			setState(92); func_declarations();
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

	public static class DeclContext extends ParserRuleContext {
		public String_declContext string_decl() {
			return getRuleContext(String_declContext.class,0);
		}
		public Var_declContext var_decl() {
			return getRuleContext(Var_declContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_decl);
		try {
			setState(101);
			switch (_input.LA(1)) {
			case 14:
				enterOuterAlt(_localctx, 1);
				{
				setState(94); string_decl();
				setState(95); decl();
				}
				break;
			case 27:
			case 33:
				enterOuterAlt(_localctx, 2);
				{
				setState(97); var_decl();
				setState(98); decl();
				}
				break;
			case 2:
			case 7:
			case 9:
			case 12:
			case 15:
			case 18:
			case 21:
			case 22:
			case 25:
			case 29:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 3);
				{
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

	public static class String_declContext extends ParserRuleContext {
		public IdContext id;
		public StrContext str;
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public StrContext str() {
			return getRuleContext(StrContext.class,0);
		}
		public String_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterString_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitString_decl(this);
		}
	}

	public final String_declContext string_decl() throws RecognitionException {
		String_declContext _localctx = new String_declContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_string_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); match(14);
			setState(104); ((String_declContext)_localctx).id = id();
			setState(105); match(28);
			setState(106); ((String_declContext)_localctx).str = str();
			setState(107); match(24);

									Symbol.typeIn = "STRING";
									Function.addSymbol((((String_declContext)_localctx).id!=null?_input.getText(((String_declContext)_localctx).id.start,((String_declContext)_localctx).id.stop):null),new Symbol((((String_declContext)_localctx).str!=null?_input.getText(((String_declContext)_localctx).str.start,((String_declContext)_localctx).str.stop):null)));
								
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

	public static class StrContext extends ParserRuleContext {
		public TerminalNode STRINGLITERAL() { return getToken(MicroParser.STRINGLITERAL, 0); }
		public StrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_str; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterStr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitStr(this);
		}
	}

	public final StrContext str() throws RecognitionException {
		StrContext _localctx = new StrContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_str);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); match(STRINGLITERAL);
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

	public static class Var_declContext extends ParserRuleContext {
		public Var_typeContext var_type;
		public Id_listContext id_list;
		public Id_listContext id_list() {
			return getRuleContext(Id_listContext.class,0);
		}
		public Var_typeContext var_type() {
			return getRuleContext(Var_typeContext.class,0);
		}
		public Var_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterVar_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitVar_decl(this);
		}
	}

	public final Var_declContext var_decl() throws RecognitionException {
		Var_declContext _localctx = new Var_declContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_var_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); ((Var_declContext)_localctx).var_type = var_type();

									Symbol.typeIn = (((Var_declContext)_localctx).var_type!=null?_input.getText(((Var_declContext)_localctx).var_type.start,((Var_declContext)_localctx).var_type.stop):null);
								
			setState(114); ((Var_declContext)_localctx).id_list = id_list();
			Symbol tempSymb = new Symbol(null);
						Function.addSymbol((((Var_declContext)_localctx).id_list!=null?_input.getText(((Var_declContext)_localctx).id_list.start,((Var_declContext)_localctx).id_list.stop):null),tempSymb);
						
			setState(116); match(24);
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

	public static class Var_typeContext extends ParserRuleContext {
		public Var_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterVar_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitVar_type(this);
		}
	}

	public final Var_typeContext var_type() throws RecognitionException {
		Var_typeContext _localctx = new Var_typeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_var_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			_la = _input.LA(1);
			if ( !(_la==27 || _la==33) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class Any_typeContext extends ParserRuleContext {
		public Var_typeContext var_type() {
			return getRuleContext(Var_typeContext.class,0);
		}
		public Any_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_any_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAny_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAny_type(this);
		}
	}

	public final Any_typeContext any_type() throws RecognitionException {
		Any_typeContext _localctx = new Any_typeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_any_type);
		try {
			setState(122);
			switch (_input.LA(1)) {
			case 27:
			case 33:
				enterOuterAlt(_localctx, 1);
				{
				setState(120); var_type();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 2);
				{
				setState(121); match(13);
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

	public static class Id_listContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Id_tailContext id_tail() {
			return getRuleContext(Id_tailContext.class,0);
		}
		public Id_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterId_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitId_list(this);
		}
	}

	public final Id_listContext id_list() throws RecognitionException {
		Id_listContext _localctx = new Id_listContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_id_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124); id();
			setState(125); id_tail();
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

	public static class Id_tailContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Id_tailContext id_tail() {
			return getRuleContext(Id_tailContext.class,0);
		}
		public Id_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_tail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterId_tail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitId_tail(this);
		}
	}

	public final Id_tailContext id_tail() throws RecognitionException {
		Id_tailContext _localctx = new Id_tailContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_id_tail);
		try {
			setState(132);
			switch (_input.LA(1)) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(127); match(1);
				setState(128); id();
				setState(129); id_tail();
				}
				break;
			case 19:
			case 24:
				enterOuterAlt(_localctx, 2);
				{
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

	public static class Param_decl_listContext extends ParserRuleContext {
		public Param_decl_tailContext param_decl_tail() {
			return getRuleContext(Param_decl_tailContext.class,0);
		}
		public Param_declContext param_decl() {
			return getRuleContext(Param_declContext.class,0);
		}
		public Param_decl_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_decl_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterParam_decl_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitParam_decl_list(this);
		}
	}

	public final Param_decl_listContext param_decl_list() throws RecognitionException {
		Param_decl_listContext _localctx = new Param_decl_listContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_param_decl_list);
		try {
			setState(138);
			switch (_input.LA(1)) {
			case 27:
			case 33:
				enterOuterAlt(_localctx, 1);
				{
				setState(134); param_decl();
				setState(135); param_decl_tail();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 2);
				{
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

	public static class Param_declContext extends ParserRuleContext {
		public Var_typeContext var_type;
		public IdContext id;
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Var_typeContext var_type() {
			return getRuleContext(Var_typeContext.class,0);
		}
		public Param_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterParam_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitParam_decl(this);
		}
	}

	public final Param_declContext param_decl() throws RecognitionException {
		Param_declContext _localctx = new Param_declContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_param_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140); ((Param_declContext)_localctx).var_type = var_type();
			Symbol.typeIn = (((Param_declContext)_localctx).var_type!=null?_input.getText(((Param_declContext)_localctx).var_type.start,((Param_declContext)_localctx).var_type.stop):null);
			setState(142); ((Param_declContext)_localctx).id = id();

									Function.addSymbol((((Param_declContext)_localctx).id!=null?_input.getText(((Param_declContext)_localctx).id.start,((Param_declContext)_localctx).id.stop):null),new Symbol(null)); 
								
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

	public static class Param_decl_tailContext extends ParserRuleContext {
		public Param_decl_tailContext param_decl_tail() {
			return getRuleContext(Param_decl_tailContext.class,0);
		}
		public Param_declContext param_decl() {
			return getRuleContext(Param_declContext.class,0);
		}
		public Param_decl_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_decl_tail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterParam_decl_tail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitParam_decl_tail(this);
		}
	}

	public final Param_decl_tailContext param_decl_tail() throws RecognitionException {
		Param_decl_tailContext _localctx = new Param_decl_tailContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_param_decl_tail);
		try {
			setState(150);
			switch (_input.LA(1)) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(145); match(1);
				setState(146); param_decl();
				setState(147); param_decl_tail();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 2);
				{
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

	public static class Func_declarationsContext extends ParserRuleContext {
		public Func_declarationsContext func_declarations() {
			return getRuleContext(Func_declarationsContext.class,0);
		}
		public Func_declContext func_decl() {
			return getRuleContext(Func_declContext.class,0);
		}
		public Func_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFunc_declarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFunc_declarations(this);
		}
	}

	public final Func_declarationsContext func_declarations() throws RecognitionException {
		Func_declarationsContext _localctx = new Func_declarationsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_func_declarations);
		try {
			setState(156);
			switch (_input.LA(1)) {
			case 25:
				enterOuterAlt(_localctx, 1);
				{
				setState(152); func_decl();
				setState(153); func_declarations();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 2);
				{
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

	public static class Func_declContext extends ParserRuleContext {
		public Any_typeContext any_type;
		public IdContext id;
		public Func_bodyContext func_body() {
			return getRuleContext(Func_bodyContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Param_decl_listContext param_decl_list() {
			return getRuleContext(Param_decl_listContext.class,0);
		}
		public Any_typeContext any_type() {
			return getRuleContext(Any_typeContext.class,0);
		}
		public Func_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFunc_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFunc_decl(this);
		}
	}

	public final Func_declContext func_decl() throws RecognitionException {
		Func_declContext _localctx = new Func_declContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_func_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158); match(25);
			setState(159); ((Func_declContext)_localctx).any_type = any_type();
			setState(160); ((Func_declContext)_localctx).id = id();

									Function.scope = "PARAM";
									Function funct = new Function((((Func_declContext)_localctx).id!=null?_input.getText(((Func_declContext)_localctx).id.start,((Func_declContext)_localctx).id.stop):null));
									SemanticHandler.currentFunction.retType = (((Func_declContext)_localctx).any_type!=null?_input.getText(((Func_declContext)_localctx).any_type.start,((Func_declContext)_localctx).any_type.stop):null);
								
			setState(162); match(6);
			setState(163); param_decl_list();
			setState(164); match(19);
			setState(165); match(31);
			Function.scope = "LOCAL";
			setState(167); func_body();
			Function.endFunc();
			setState(169); match(9);
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

	public static class Func_bodyContext extends ParserRuleContext {
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public Func_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFunc_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFunc_body(this);
		}
	}

	public final Func_bodyContext func_body() throws RecognitionException {
		Func_bodyContext _localctx = new Func_bodyContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_func_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171); decl();
			setState(172); stmt_list();
			Function.popBlock();
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

	public static class Stmt_listContext extends ParserRuleContext {
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public Stmt_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterStmt_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitStmt_list(this);
		}
	}

	public final Stmt_listContext stmt_list() throws RecognitionException {
		Stmt_listContext _localctx = new Stmt_listContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_stmt_list);
		try {
			setState(179);
			switch (_input.LA(1)) {
			case 2:
			case 7:
			case 15:
			case 18:
			case 29:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(175); stmt();
				setState(176); stmt_list();
				}
				break;
			case 9:
			case 12:
			case 21:
			case 22:
				enterOuterAlt(_localctx, 2);
				{
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

	public static class StmtContext extends ParserRuleContext {
		public Base_stmtContext base_stmt() {
			return getRuleContext(Base_stmtContext.class,0);
		}
		public If_stmtContext if_stmt() {
			return getRuleContext(If_stmtContext.class,0);
		}
		public Do_while_stmtContext do_while_stmt() {
			return getRuleContext(Do_while_stmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitStmt(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_stmt);
		try {
			setState(185);
			switch (_input.LA(1)) {
			case 7:
			case 18:
			case 29:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				BaseNode newNode = new BaseNode();
				setState(182); base_stmt();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 2);
				{
				setState(183); if_stmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 3);
				{
				setState(184); do_while_stmt();
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

	public static class Base_stmtContext extends ParserRuleContext {
		public Write_stmtContext write_stmt() {
			return getRuleContext(Write_stmtContext.class,0);
		}
		public Read_stmtContext read_stmt() {
			return getRuleContext(Read_stmtContext.class,0);
		}
		public Return_stmtContext return_stmt() {
			return getRuleContext(Return_stmtContext.class,0);
		}
		public Assign_stmtContext assign_stmt() {
			return getRuleContext(Assign_stmtContext.class,0);
		}
		public Base_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterBase_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitBase_stmt(this);
		}
	}

	public final Base_stmtContext base_stmt() throws RecognitionException {
		Base_stmtContext _localctx = new Base_stmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_base_stmt);
		try {
			setState(191);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(187); assign_stmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 2);
				{
				setState(188); read_stmt();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 3);
				{
				setState(189); write_stmt();
				}
				break;
			case 29:
				enterOuterAlt(_localctx, 4);
				{
				setState(190); return_stmt();
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

	public static class Assign_stmtContext extends ParserRuleContext {
		public Assign_exprContext assign_expr() {
			return getRuleContext(Assign_exprContext.class,0);
		}
		public Assign_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAssign_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAssign_stmt(this);
		}
	}

	public final Assign_stmtContext assign_stmt() throws RecognitionException {
		Assign_stmtContext _localctx = new Assign_stmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_assign_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193); assign_expr();
			setState(194); match(24);
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

	public static class Assign_exprContext extends ParserRuleContext {
		public IdContext id;
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Assign_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAssign_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAssign_expr(this);
		}
	}

	public final Assign_exprContext assign_expr() throws RecognitionException {
		Assign_exprContext _localctx = new Assign_exprContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_assign_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196); ((Assign_exprContext)_localctx).id = id();
			setState(197); match(28);
			setState(198); expr();
			SemanticHandler.currentBaseNode.finishBase((((Assign_exprContext)_localctx).id!=null?_input.getText(((Assign_exprContext)_localctx).id.start,((Assign_exprContext)_localctx).id.stop):null));
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

	public static class Read_stmtContext extends ParserRuleContext {
		public Id_listContext id_list;
		public Id_listContext id_list() {
			return getRuleContext(Id_listContext.class,0);
		}
		public Read_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_read_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterRead_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitRead_stmt(this);
		}
	}

	public final Read_stmtContext read_stmt() throws RecognitionException {
		Read_stmtContext _localctx = new Read_stmtContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_read_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201); match(7);
			setState(202); match(6);
			setState(203); ((Read_stmtContext)_localctx).id_list = id_list();
			setState(204); match(19);
			setState(205); match(24);
			SemanticHandler.currentBaseNode.addRead((((Read_stmtContext)_localctx).id_list!=null?_input.getText(((Read_stmtContext)_localctx).id_list.start,((Read_stmtContext)_localctx).id_list.stop):null));
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

	public static class Write_stmtContext extends ParserRuleContext {
		public Id_listContext id_list;
		public Id_listContext id_list() {
			return getRuleContext(Id_listContext.class,0);
		}
		public Write_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_write_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterWrite_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitWrite_stmt(this);
		}
	}

	public final Write_stmtContext write_stmt() throws RecognitionException {
		Write_stmtContext _localctx = new Write_stmtContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_write_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208); match(18);
			setState(209); match(6);
			setState(210); ((Write_stmtContext)_localctx).id_list = id_list();
			setState(211); match(19);
			setState(212); match(24);
			SemanticHandler.currentBaseNode.addWrite((((Write_stmtContext)_localctx).id_list!=null?_input.getText(((Write_stmtContext)_localctx).id_list.start,((Write_stmtContext)_localctx).id_list.stop):null));
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

	public static class Return_stmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Return_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterReturn_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitReturn_stmt(this);
		}
	}

	public final Return_stmtContext return_stmt() throws RecognitionException {
		Return_stmtContext _localctx = new Return_stmtContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_return_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215); match(29);
			setState(216); expr();
			SemanticHandler.currentBaseNode.finishBase(null);
			setState(218); match(24);
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

	public static class ExprContext extends ParserRuleContext {
		public Expr_prefixContext expr_prefix() {
			return getRuleContext(Expr_prefixContext.class,0);
		}
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220); expr_prefix(0);
			setState(221); factor();
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

	public static class Expr_prefixContext extends ParserRuleContext {
		public int _p;
		public AddopContext addop;
		public Expr_prefixContext expr_prefix() {
			return getRuleContext(Expr_prefixContext.class,0);
		}
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public AddopContext addop() {
			return getRuleContext(AddopContext.class,0);
		}
		public Expr_prefixContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Expr_prefixContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expr_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterExpr_prefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitExpr_prefix(this);
		}
	}

	public final Expr_prefixContext expr_prefix(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr_prefixContext _localctx = new Expr_prefixContext(_ctx, _parentState, _p);
		Expr_prefixContext _prevctx = _localctx;
		int _startState = 52;
		enterRecursionRule(_localctx, RULE_expr_prefix);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			}
			_ctx.stop = _input.LT(-1);
			setState(231);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr_prefixContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_expr_prefix);
					setState(224);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(225); factor();
					setState(226); ((Expr_prefixContext)_localctx).addop = addop();
					SemanticHandler.currentBaseNode.addOp((((Expr_prefixContext)_localctx).addop!=null?_input.getText(((Expr_prefixContext)_localctx).addop.start,((Expr_prefixContext)_localctx).addop.stop):null));
					}
					} 
				}
				setState(233);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public Postfix_exprContext postfix_expr() {
			return getRuleContext(Postfix_exprContext.class,0);
		}
		public Factor_prefixContext factor_prefix() {
			return getRuleContext(Factor_prefixContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_factor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234); factor_prefix(0);
			setState(235); postfix_expr();
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

	public static class Factor_prefixContext extends ParserRuleContext {
		public int _p;
		public MulopContext mulop;
		public MulopContext mulop() {
			return getRuleContext(MulopContext.class,0);
		}
		public Postfix_exprContext postfix_expr() {
			return getRuleContext(Postfix_exprContext.class,0);
		}
		public Factor_prefixContext factor_prefix() {
			return getRuleContext(Factor_prefixContext.class,0);
		}
		public Factor_prefixContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Factor_prefixContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_factor_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFactor_prefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFactor_prefix(this);
		}
	}

	public final Factor_prefixContext factor_prefix(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Factor_prefixContext _localctx = new Factor_prefixContext(_ctx, _parentState, _p);
		Factor_prefixContext _prevctx = _localctx;
		int _startState = 56;
		enterRecursionRule(_localctx, RULE_factor_prefix);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			}
			_ctx.stop = _input.LT(-1);
			setState(245);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Factor_prefixContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_factor_prefix);
					setState(238);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(239); postfix_expr();
					setState(240); ((Factor_prefixContext)_localctx).mulop = mulop();
					SemanticHandler.currentBaseNode.addOp((((Factor_prefixContext)_localctx).mulop!=null?_input.getText(((Factor_prefixContext)_localctx).mulop.start,((Factor_prefixContext)_localctx).mulop.stop):null));
					}
					} 
				}
				setState(247);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Postfix_exprContext extends ParserRuleContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public Call_exprContext call_expr() {
			return getRuleContext(Call_exprContext.class,0);
		}
		public Postfix_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfix_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterPostfix_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitPostfix_expr(this);
		}
	}

	public final Postfix_exprContext postfix_expr() throws RecognitionException {
		Postfix_exprContext _localctx = new Postfix_exprContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_postfix_expr);
		try {
			setState(250);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(248); primary();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(249); call_expr();
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

	public static class Call_exprContext extends ParserRuleContext {
		public IdContext id;
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Expr_listContext expr_list() {
			return getRuleContext(Expr_listContext.class,0);
		}
		public Call_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterCall_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitCall_expr(this);
		}
	}

	public final Call_exprContext call_expr() throws RecognitionException {
		Call_exprContext _localctx = new Call_exprContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_call_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252); ((Call_exprContext)_localctx).id = id();
			SemanticHandler.currentBaseNode.startFunc((((Call_exprContext)_localctx).id!=null?_input.getText(((Call_exprContext)_localctx).id.start,((Call_exprContext)_localctx).id.stop):null));
			setState(254); match(6);
			setState(255); expr_list();
			setState(256); match(19);
			SemanticHandler.currentBaseNode.endFunc();
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

	public static class Expr_listContext extends ParserRuleContext {
		public Expr_list_tailContext expr_list_tail() {
			return getRuleContext(Expr_list_tailContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterExpr_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitExpr_list(this);
		}
	}

	public final Expr_listContext expr_list() throws RecognitionException {
		Expr_listContext _localctx = new Expr_listContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_expr_list);
		try {
			setState(263);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(259); expr();
				setState(260); expr_list_tail();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
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

	public static class Expr_list_tailContext extends ParserRuleContext {
		public Expr_list_tailContext expr_list_tail() {
			return getRuleContext(Expr_list_tailContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_list_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_list_tail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterExpr_list_tail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitExpr_list_tail(this);
		}
	}

	public final Expr_list_tailContext expr_list_tail() throws RecognitionException {
		Expr_list_tailContext _localctx = new Expr_list_tailContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_expr_list_tail);
		try {
			setState(271);
			switch (_input.LA(1)) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(265); match(1);
				SemanticHandler.currentBaseNode.addOp(",");
				setState(267); expr();
				setState(268); expr_list_tail();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 2);
				{
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

	public static class PrimaryContext extends ParserRuleContext {
		public IdContext id;
		public Token INTLITERAL;
		public Token FLOATLITERAL;
		public TerminalNode FLOATLITERAL() { return getToken(MicroParser.FLOATLITERAL, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode INTLITERAL() { return getToken(MicroParser.INTLITERAL, 0); }
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitPrimary(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_primary);
		try {
			setState(286);
			switch (_input.LA(1)) {
			case 6:
				enterOuterAlt(_localctx, 1);
				{
				setState(273); match(6);
				SemanticHandler.currentBaseNode.addOp("(");
				setState(275); expr();
				SemanticHandler.currentBaseNode.addOp(")");
				setState(277); match(19);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(279); ((PrimaryContext)_localctx).id = id();
				SemanticHandler.currentBaseNode.addId((((PrimaryContext)_localctx).id!=null?_input.getText(((PrimaryContext)_localctx).id.start,((PrimaryContext)_localctx).id.stop):null));
				}
				break;
			case INTLITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(282); ((PrimaryContext)_localctx).INTLITERAL = match(INTLITERAL);
				SemanticHandler.currentBaseNode.addInt((((PrimaryContext)_localctx).INTLITERAL!=null?((PrimaryContext)_localctx).INTLITERAL.getText():null));
				}
				break;
			case FLOATLITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(284); ((PrimaryContext)_localctx).FLOATLITERAL = match(FLOATLITERAL);
				SemanticHandler.currentBaseNode.addFloat((((PrimaryContext)_localctx).FLOATLITERAL!=null?((PrimaryContext)_localctx).FLOATLITERAL.getText():null));
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

	public static class AddopContext extends ParserRuleContext {
		public AddopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAddop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAddop(this);
		}
	}

	public final AddopContext addop() throws RecognitionException {
		AddopContext _localctx = new AddopContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_addop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			_la = _input.LA(1);
			if ( !(_la==4 || _la==20) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class MulopContext extends ParserRuleContext {
		public MulopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterMulop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitMulop(this);
		}
	}

	public final MulopContext mulop() throws RecognitionException {
		MulopContext _localctx = new MulopContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_mulop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			_la = _input.LA(1);
			if ( !(_la==3 || _la==30) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class If_stmtContext extends ParserRuleContext {
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public Else_partContext else_part() {
			return getRuleContext(Else_partContext.class,0);
		}
		public If_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterIf_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitIf_stmt(this);
		}
	}

	public final If_stmtContext if_stmt() throws RecognitionException {
		If_stmtContext _localctx = new If_stmtContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_if_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292); match(15);
			setState(293); match(6);
			setState(294); cond();
			setState(295); match(19);

									//SemanticHandler.pushTree();
									Function.pushBlock();
									IfNode ifNode = new IfNode();
									IfBodyNode ifbNode = new IfBodyNode(true);
								
			setState(297); decl();
			setState(298); stmt_list();
			Function.popBlock();
			setState(300); else_part();
			SemanticHandler.addendIF();
			setState(302); match(12);
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

	public static class Else_partContext extends ParserRuleContext {
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public Else_partContext else_part() {
			return getRuleContext(Else_partContext.class,0);
		}
		public Else_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterElse_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitElse_part(this);
		}
	}

	public final Else_partContext else_part() throws RecognitionException {
		Else_partContext _localctx = new Else_partContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_else_part);
		try {
			setState(315);
			switch (_input.LA(1)) {
			case 22:
				enterOuterAlt(_localctx, 1);
				{
				setState(304); match(22);
				setState(305); match(6);
				setState(306); cond();
				setState(307); match(19);

										//SemanticHandler.popTree();
										//SemanticHandler.pushTree();
										Function.pushBlock();
										IfBodyNode ifbNode = new IfBodyNode(false);
									
				setState(309); decl();
				setState(310); stmt_list();
				Function.popBlock();
				setState(312); else_part();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 2);
				{
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

	public static class CondContext extends ParserRuleContext {
		public ExprContext expr;
		public CompopContext compop;
		public CompopContext compop() {
			return getRuleContext(CompopContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitCond(this);
		}
	}

	public final CondContext cond() throws RecognitionException {
		CondContext _localctx = new CondContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_cond);
		try {
			setState(327);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(317); ((CondContext)_localctx).expr = expr();
				SemanticHandler.expr1 = (((CondContext)_localctx).expr!=null?_input.getText(((CondContext)_localctx).expr.start,((CondContext)_localctx).expr.stop):null);
				setState(319); ((CondContext)_localctx).compop = compop();
				setState(320); ((CondContext)_localctx).expr = expr();
				SemanticHandler.compop = (((CondContext)_localctx).compop!=null?_input.getText(((CondContext)_localctx).compop.start,((CondContext)_localctx).compop.stop):null); SemanticHandler.expr2 = (((CondContext)_localctx).expr!=null?_input.getText(((CondContext)_localctx).expr.start,((CondContext)_localctx).expr.stop):null);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(323); match(16);
				SemanticHandler.expr1 = "TRUE";
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(325); match(17);
				SemanticHandler.expr1 = "FALSE";
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

	public static class CompopContext extends ParserRuleContext {
		public CompopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterCompop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitCompop(this);
		}
	}

	public final CompopContext compop() throws RecognitionException {
		CompopContext _localctx = new CompopContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_compop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(329);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 8) | (1L << 10) | (1L << 11) | (1L << 23) | (1L << 26) | (1L << 32))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class Do_while_stmtContext extends ParserRuleContext {
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public Do_while_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_do_while_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterDo_while_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitDo_while_stmt(this);
		}
	}

	public final Do_while_stmtContext do_while_stmt() throws RecognitionException {
		Do_while_stmtContext _localctx = new Do_while_stmtContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_do_while_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331); match(2);

										Function.pushBlock();
										WhileNode whileNode = new WhileNode();
									
			setState(333); decl();
			setState(334); stmt_list();
			setState(335); match(21);
			setState(336); match(6);
			setState(337); cond();
			setState(338); match(19);
			Function.popBlock(); SemanticHandler.addendWhile();
			setState(340); match(24);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 26: return expr_prefix_sempred((Expr_prefixContext)_localctx, predIndex);

		case 28: return factor_prefix_sempred((Factor_prefixContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean factor_prefix_sempred(Factor_prefixContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return 2 >= _localctx._p;
		}
		return true;
	}
	private boolean expr_prefix_sempred(Expr_prefixContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 2 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3+\u0159\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5"+
		"\5h\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\n\3\n\5\n}\n\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\5\f\u0087\n"+
		"\f\3\r\3\r\3\r\3\r\5\r\u008d\n\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3"+
		"\17\3\17\3\17\5\17\u0099\n\17\3\20\3\20\3\20\3\20\5\20\u009f\n\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\5\23\u00b6\n\23\3\24\3\24\3\24\3\24\5\24"+
		"\u00bc\n\24\3\25\3\25\3\25\3\25\5\25\u00c2\n\25\3\26\3\26\3\26\3\27\3"+
		"\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3"+
		"\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\7\34\u00e8\n\34\f\34\16\34\u00eb\13\34\3\35\3\35\3"+
		"\35\3\36\3\36\3\36\3\36\3\36\3\36\7\36\u00f6\n\36\f\36\16\36\u00f9\13"+
		"\36\3\37\3\37\5\37\u00fd\n\37\3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\5!\u010a"+
		"\n!\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u0112\n\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3"+
		"#\3#\3#\3#\5#\u0121\n#\3$\3$\3%\3%\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3"+
		"&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u013e\n\'\3(\3(\3(\3"+
		"(\3(\3(\3(\3(\3(\3(\5(\u014a\n(\3)\3)\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3"+
		"*\3*\2+\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:"+
		"<>@BDFHJLNPR\2\6\4\2\35\35##\4\2\6\6\26\26\4\2\5\5  \7\2\n\n\f\r\31\31"+
		"\34\34\"\"\u0147\2T\3\2\2\2\4Z\3\2\2\2\6\\\3\2\2\2\bg\3\2\2\2\ni\3\2\2"+
		"\2\fp\3\2\2\2\16r\3\2\2\2\20x\3\2\2\2\22|\3\2\2\2\24~\3\2\2\2\26\u0086"+
		"\3\2\2\2\30\u008c\3\2\2\2\32\u008e\3\2\2\2\34\u0098\3\2\2\2\36\u009e\3"+
		"\2\2\2 \u00a0\3\2\2\2\"\u00ad\3\2\2\2$\u00b5\3\2\2\2&\u00bb\3\2\2\2(\u00c1"+
		"\3\2\2\2*\u00c3\3\2\2\2,\u00c6\3\2\2\2.\u00cb\3\2\2\2\60\u00d2\3\2\2\2"+
		"\62\u00d9\3\2\2\2\64\u00de\3\2\2\2\66\u00e1\3\2\2\28\u00ec\3\2\2\2:\u00ef"+
		"\3\2\2\2<\u00fc\3\2\2\2>\u00fe\3\2\2\2@\u0109\3\2\2\2B\u0111\3\2\2\2D"+
		"\u0120\3\2\2\2F\u0122\3\2\2\2H\u0124\3\2\2\2J\u0126\3\2\2\2L\u013d\3\2"+
		"\2\2N\u0149\3\2\2\2P\u014b\3\2\2\2R\u014d\3\2\2\2TU\7\7\2\2UV\5\4\3\2"+
		"VW\7!\2\2WX\5\6\4\2XY\7\13\2\2Y\3\3\2\2\2Z[\7&\2\2[\5\3\2\2\2\\]\b\4\1"+
		"\2]^\5\b\5\2^_\5\36\20\2_\7\3\2\2\2`a\5\n\6\2ab\5\b\5\2bh\3\2\2\2cd\5"+
		"\16\b\2de\5\b\5\2eh\3\2\2\2fh\3\2\2\2g`\3\2\2\2gc\3\2\2\2gf\3\2\2\2h\t"+
		"\3\2\2\2ij\7\20\2\2jk\5\4\3\2kl\7\36\2\2lm\5\f\7\2mn\7\32\2\2no\b\6\1"+
		"\2o\13\3\2\2\2pq\7)\2\2q\r\3\2\2\2rs\5\20\t\2st\b\b\1\2tu\5\24\13\2uv"+
		"\b\b\1\2vw\7\32\2\2w\17\3\2\2\2xy\t\2\2\2y\21\3\2\2\2z}\5\20\t\2{}\7\17"+
		"\2\2|z\3\2\2\2|{\3\2\2\2}\23\3\2\2\2~\177\5\4\3\2\177\u0080\5\26\f\2\u0080"+
		"\25\3\2\2\2\u0081\u0082\7\3\2\2\u0082\u0083\5\4\3\2\u0083\u0084\5\26\f"+
		"\2\u0084\u0087\3\2\2\2\u0085\u0087\3\2\2\2\u0086\u0081\3\2\2\2\u0086\u0085"+
		"\3\2\2\2\u0087\27\3\2\2\2\u0088\u0089\5\32\16\2\u0089\u008a\5\34\17\2"+
		"\u008a\u008d\3\2\2\2\u008b\u008d\3\2\2\2\u008c\u0088\3\2\2\2\u008c\u008b"+
		"\3\2\2\2\u008d\31\3\2\2\2\u008e\u008f\5\20\t\2\u008f\u0090\b\16\1\2\u0090"+
		"\u0091\5\4\3\2\u0091\u0092\b\16\1\2\u0092\33\3\2\2\2\u0093\u0094\7\3\2"+
		"\2\u0094\u0095\5\32\16\2\u0095\u0096\5\34\17\2\u0096\u0099\3\2\2\2\u0097"+
		"\u0099\3\2\2\2\u0098\u0093\3\2\2\2\u0098\u0097\3\2\2\2\u0099\35\3\2\2"+
		"\2\u009a\u009b\5 \21\2\u009b\u009c\5\36\20\2\u009c\u009f\3\2\2\2\u009d"+
		"\u009f\3\2\2\2\u009e\u009a\3\2\2\2\u009e\u009d\3\2\2\2\u009f\37\3\2\2"+
		"\2\u00a0\u00a1\7\33\2\2\u00a1\u00a2\5\22\n\2\u00a2\u00a3\5\4\3\2\u00a3"+
		"\u00a4\b\21\1\2\u00a4\u00a5\7\b\2\2\u00a5\u00a6\5\30\r\2\u00a6\u00a7\7"+
		"\25\2\2\u00a7\u00a8\7!\2\2\u00a8\u00a9\b\21\1\2\u00a9\u00aa\5\"\22\2\u00aa"+
		"\u00ab\b\21\1\2\u00ab\u00ac\7\13\2\2\u00ac!\3\2\2\2\u00ad\u00ae\5\b\5"+
		"\2\u00ae\u00af\5$\23\2\u00af\u00b0\b\22\1\2\u00b0#\3\2\2\2\u00b1\u00b2"+
		"\5&\24\2\u00b2\u00b3\5$\23\2\u00b3\u00b6\3\2\2\2\u00b4\u00b6\3\2\2\2\u00b5"+
		"\u00b1\3\2\2\2\u00b5\u00b4\3\2\2\2\u00b6%\3\2\2\2\u00b7\u00b8\b\24\1\2"+
		"\u00b8\u00bc\5(\25\2\u00b9\u00bc\5J&\2\u00ba\u00bc\5R*\2\u00bb\u00b7\3"+
		"\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00ba\3\2\2\2\u00bc\'\3\2\2\2\u00bd\u00c2"+
		"\5*\26\2\u00be\u00c2\5.\30\2\u00bf\u00c2\5\60\31\2\u00c0\u00c2\5\62\32"+
		"\2\u00c1\u00bd\3\2\2\2\u00c1\u00be\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c0"+
		"\3\2\2\2\u00c2)\3\2\2\2\u00c3\u00c4\5,\27\2\u00c4\u00c5\7\32\2\2\u00c5"+
		"+\3\2\2\2\u00c6\u00c7\5\4\3\2\u00c7\u00c8\7\36\2\2\u00c8\u00c9\5\64\33"+
		"\2\u00c9\u00ca\b\27\1\2\u00ca-\3\2\2\2\u00cb\u00cc\7\t\2\2\u00cc\u00cd"+
		"\7\b\2\2\u00cd\u00ce\5\24\13\2\u00ce\u00cf\7\25\2\2\u00cf\u00d0\7\32\2"+
		"\2\u00d0\u00d1\b\30\1\2\u00d1/\3\2\2\2\u00d2\u00d3\7\24\2\2\u00d3\u00d4"+
		"\7\b\2\2\u00d4\u00d5\5\24\13\2\u00d5\u00d6\7\25\2\2\u00d6\u00d7\7\32\2"+
		"\2\u00d7\u00d8\b\31\1\2\u00d8\61\3\2\2\2\u00d9\u00da\7\37\2\2\u00da\u00db"+
		"\5\64\33\2\u00db\u00dc\b\32\1\2\u00dc\u00dd\7\32\2\2\u00dd\63\3\2\2\2"+
		"\u00de\u00df\5\66\34\2\u00df\u00e0\58\35\2\u00e0\65\3\2\2\2\u00e1\u00e9"+
		"\b\34\1\2\u00e2\u00e3\6\34\2\3\u00e3\u00e4\58\35\2\u00e4\u00e5\5F$\2\u00e5"+
		"\u00e6\b\34\1\2\u00e6\u00e8\3\2\2\2\u00e7\u00e2\3\2\2\2\u00e8\u00eb\3"+
		"\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\67\3\2\2\2\u00eb"+
		"\u00e9\3\2\2\2\u00ec\u00ed\5:\36\2\u00ed\u00ee\5<\37\2\u00ee9\3\2\2\2"+
		"\u00ef\u00f7\b\36\1\2\u00f0\u00f1\6\36\3\3\u00f1\u00f2\5<\37\2\u00f2\u00f3"+
		"\5H%\2\u00f3\u00f4\b\36\1\2\u00f4\u00f6\3\2\2\2\u00f5\u00f0\3\2\2\2\u00f6"+
		"\u00f9\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8;\3\2\2\2"+
		"\u00f9\u00f7\3\2\2\2\u00fa\u00fd\5D#\2\u00fb\u00fd\5> \2\u00fc\u00fa\3"+
		"\2\2\2\u00fc\u00fb\3\2\2\2\u00fd=\3\2\2\2\u00fe\u00ff\5\4\3\2\u00ff\u0100"+
		"\b \1\2\u0100\u0101\7\b\2\2\u0101\u0102\5@!\2\u0102\u0103\7\25\2\2\u0103"+
		"\u0104\b \1\2\u0104?\3\2\2\2\u0105\u0106\5\64\33\2\u0106\u0107\5B\"\2"+
		"\u0107\u010a\3\2\2\2\u0108\u010a\3\2\2\2\u0109\u0105\3\2\2\2\u0109\u0108"+
		"\3\2\2\2\u010aA\3\2\2\2\u010b\u010c\7\3\2\2\u010c\u010d\b\"\1\2\u010d"+
		"\u010e\5\64\33\2\u010e\u010f\5B\"\2\u010f\u0112\3\2\2\2\u0110\u0112\3"+
		"\2\2\2\u0111\u010b\3\2\2\2\u0111\u0110\3\2\2\2\u0112C\3\2\2\2\u0113\u0114"+
		"\7\b\2\2\u0114\u0115\b#\1\2\u0115\u0116\5\64\33\2\u0116\u0117\b#\1\2\u0117"+
		"\u0118\7\25\2\2\u0118\u0121\3\2\2\2\u0119\u011a\5\4\3\2\u011a\u011b\b"+
		"#\1\2\u011b\u0121\3\2\2\2\u011c\u011d\7\'\2\2\u011d\u0121\b#\1\2\u011e"+
		"\u011f\7(\2\2\u011f\u0121\b#\1\2\u0120\u0113\3\2\2\2\u0120\u0119\3\2\2"+
		"\2\u0120\u011c\3\2\2\2\u0120\u011e\3\2\2\2\u0121E\3\2\2\2\u0122\u0123"+
		"\t\3\2\2\u0123G\3\2\2\2\u0124\u0125\t\4\2\2\u0125I\3\2\2\2\u0126\u0127"+
		"\7\21\2\2\u0127\u0128\7\b\2\2\u0128\u0129\5N(\2\u0129\u012a\7\25\2\2\u012a"+
		"\u012b\b&\1\2\u012b\u012c\5\b\5\2\u012c\u012d\5$\23\2\u012d\u012e\b&\1"+
		"\2\u012e\u012f\5L\'\2\u012f\u0130\b&\1\2\u0130\u0131\7\16\2\2\u0131K\3"+
		"\2\2\2\u0132\u0133\7\30\2\2\u0133\u0134\7\b\2\2\u0134\u0135\5N(\2\u0135"+
		"\u0136\7\25\2\2\u0136\u0137\b\'\1\2\u0137\u0138\5\b\5\2\u0138\u0139\5"+
		"$\23\2\u0139\u013a\b\'\1\2\u013a\u013b\5L\'\2\u013b\u013e\3\2\2\2\u013c"+
		"\u013e\3\2\2\2\u013d\u0132\3\2\2\2\u013d\u013c\3\2\2\2\u013eM\3\2\2\2"+
		"\u013f\u0140\5\64\33\2\u0140\u0141\b(\1\2\u0141\u0142\5P)\2\u0142\u0143"+
		"\5\64\33\2\u0143\u0144\b(\1\2\u0144\u014a\3\2\2\2\u0145\u0146\7\22\2\2"+
		"\u0146\u014a\b(\1\2\u0147\u0148\7\23\2\2\u0148\u014a\b(\1\2\u0149\u013f"+
		"\3\2\2\2\u0149\u0145\3\2\2\2\u0149\u0147\3\2\2\2\u014aO\3\2\2\2\u014b"+
		"\u014c\t\5\2\2\u014cQ\3\2\2\2\u014d\u014e\7\4\2\2\u014e\u014f\b*\1\2\u014f"+
		"\u0150\5\b\5\2\u0150\u0151\5$\23\2\u0151\u0152\7\27\2\2\u0152\u0153\7"+
		"\b\2\2\u0153\u0154\5N(\2\u0154\u0155\7\25\2\2\u0155\u0156\b*\1\2\u0156"+
		"\u0157\7\32\2\2\u0157S\3\2\2\2\23g|\u0086\u008c\u0098\u009e\u00b5\u00bb"+
		"\u00c1\u00e9\u00f7\u00fc\u0109\u0111\u0120\u013d\u0149";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}