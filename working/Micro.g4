grammar Micro;

program           : 'PROGRAM' id 'BEGIN' pgm_body 'END' ;
id                : IDENTIFIER;
pgm_body  		  : {
						Function.addGlobal();
					} 
					decl  func_declarations ;
decl        	  : string_decl decl | var_decl decl | ;


string_decl       : 'STRING' id ':=' str ';'
					{
						Symbol.typeIn = "STRING";
						Function.addSymbol($id.text,new Symbol($str.text));
					} ;
str               : STRINGLITERAL;


var_decl 		  : var_type {
						Symbol.typeIn = $var_type.text;
					}
					id_list ';' ;
var_type          : 'FLOAT' | 'INT';
any_type          : var_type | 'VOID';
id_list           : id
			{Symbol tempSymb = new Symbol(null);
			Function.addSymbol($id.text,tempSymb);
			}
					id_tail ;
id_tail           : ',' id id_tail |  ;


param_decl_list   : param_decl param_decl_tail |  ;
param_decl        : var_type {Symbol.typeIn = $var_type.text;} id 
					{
						Function.addSymbol($id.text,new Symbol(null)); 
					};
param_decl_tail   : ',' param_decl param_decl_tail |  ;


func_declarations : func_decl  func_declarations |  ;
func_decl         : 'FUNCTION' any_type id 
					{
						Function funct = new Function($id.text);
					} 
					'('param_decl_list')' 'BEGIN' 	func_body 'END';
func_body         : decl  stmt_list {Function.popBlock();} ;


stmt_list         : stmt stmt_list |  ;
stmt              : {BaseNode newNode = new BaseNode();} base_stmt  | if_stmt | do_while_stmt;
base_stmt         :  assign_stmt | read_stmt | write_stmt | return_stmt;


assign_stmt       : assign_expr ';' ;
assign_expr       : id ':=' expr /*{SemanticHandler.addAssignment($id.text,$expr.text);}*/ ;
read_stmt         : 'READ' '(' id_list ')'';' {SemanticHandler.addRead($id_list.text);}  ;
write_stmt        : 'WRITE' '(' id_list ')' ';' {SemanticHandler.addWrite($id_list.text);} ;
return_stmt       : 'RETURN' expr ';' ;


expr              : expr_prefix factor ;
expr_prefix       : expr_prefix factor addop /*{System.out.println("addop: "+$addop.text);}*/ |  ;
factor            : factor_prefix postfix_expr /*{System.out.println("postfix_expr: "+$postfix_expr.text);}*/ ;
factor_prefix     : factor_prefix postfix_expr mulop /*{System.out.println("mulop: "+$mulop.text);}*/ |  ;
postfix_expr      : primary /*{System.out.println("Primary: "+$primary.text);}*/ | call_expr /*{System.out.println("Call: "+$call_expr.text);}*/ ;
call_expr         : id '(' expr_list ')';
expr_list         : expr expr_list_tail |  ;
expr_list_tail    : ',' expr expr_list_tail |  ;
primary           : '(' expr ')' | id /*{System.out.println("id: "+$id.text);}*/ | INTLITERAL /*{System.out.println("literal: "+$INTLITERAL.text);}*/ | FLOATLITERAL;
addop             : '+' | '-';
mulop             : '*' | '/';


if_stmt           : 'IF' '(' cond ')' 
					{
						//SemanticHandler.pushTree();
						Function.pushBlock();
						IfNode ifNode = new IfNode();
						IfBodyNode ifbNode = new IfBodyNode(true);
					} 
					decl stmt_list {Function.popBlock();} else_part {SemanticHandler.addendIF();} 'ENDIF' ;
else_part         : 'ELSIF' '(' cond ')' 
					{
						//SemanticHandler.popTree();
						//SemanticHandler.pushTree();
						Function.pushBlock();
						IfBodyNode ifbNode = new IfBodyNode(false);
					} 
					decl  stmt_list {Function.popBlock();} else_part | ;
cond              : expr {SemanticHandler.expr1 = $expr.text;} compop expr {SemanticHandler.compop = $compop.text; SemanticHandler.expr2 = $expr.text;} | 'TRUE' {SemanticHandler.expr1 = "TRUE";} | 'FALSE' {SemanticHandler.expr1 = "FALSE";} ;
compop            : '<' | '>' | '=' | '!=' | '<=' | '>=';

do_while_stmt       : 'DO' 
						{
							Function.pushBlock();
							WhileNode whileNode = new WhileNode();
						} 
						decl stmt_list 'WHILE' '(' cond ')' {Function.popBlock(); SemanticHandler.addendWhile();} ';' ;


KEYWORD: 'PROGRAM' | 'BEGIN' | 'END' | 'FUNCTION' | 'READ' | 'WRITE' | 'IF' | 'ELSIF' | 'ENDIF' | 'DO' | 'WHILE' | 'CONTINUE' | 'BREAK' | 'RETURN' | 'INT' | 'VOID' | 'STRING' | 'FLOAT' | 'TRUE' | 'FALSE' ;

OPERATOR: ':='|'+'|'-'|'*'|'/'|'='|'!='|'<'|'>'|'('|')'|';'|','|'<='|'>=';

IDENTIFIER: ([a-zA-Z])([a-zA-Z0-9])*
			{
				if(getText().length() > 31)
					throw new RuntimeException("IDENTIFIER ERROR");
			};

INTLITERAL:	[0-9]+|[0];

FLOATLITERAL: [0-9]*'.'[0-9]+;

STRINGLITERAL: '"'('\\"' | ~('\n'|'\r'))*? '"'
				{
					if(getText().length() > 81)
						throw new RuntimeException("STRINGLITERAL ERROR");	
				};

WHITESPACE: [\r\t\n' ']+ -> skip;

COMMENT: '-''-'(.)*?'\n' -> skip;

