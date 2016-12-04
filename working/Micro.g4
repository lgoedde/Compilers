grammar Micro;

program           : 'PROGRAM' id 'BEGIN' pgm_body 'END' ;
id                : IDENTIFIER;
pgm_body  		  : {
						Function.scope = "GLOBAL";
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
					id_list {Symbol tempSymb = new Symbol(null);
					Function.addSymbol($id_list.text,tempSymb);
			} ';' ;
var_type          : 'FLOAT' | 'INT';
any_type          : var_type | 'VOID';
id_list           : id
			
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
						Function.scope = "PARAM";
						Function funct = new Function($id.text);
						SemanticHandler.currentFunction.retType = $any_type.text;
					} 
					'('param_decl_list')' 'BEGIN' {Function.scope = "LOCAL";}	func_body {Function.endFunc();} 'END';
func_body         : decl  stmt_list {Function.popBlock();} ;


stmt_list         : stmt stmt_list |  ;
stmt              : {BaseNode newNode = new BaseNode();} base_stmt  | if_stmt | do_while_stmt;
base_stmt         :  assign_stmt | read_stmt | write_stmt | return_stmt;


assign_stmt       : assign_expr ';' ;
assign_expr       : id ':=' expr {SemanticHandler.currentBaseNode.finishBase($id.text);} ;
read_stmt         : 'READ' '(' id_list ')'';' {SemanticHandler.currentBaseNode.addRead($id_list.text);}  ;
write_stmt        : 'WRITE' '(' id_list ')' ';' {SemanticHandler.currentBaseNode.addWrite($id_list.text);} ;
return_stmt       : 'RETURN' expr {SemanticHandler.currentBaseNode.finishBase(null);} ';' ;


expr              : expr_prefix factor ;
expr_prefix       : expr_prefix factor addop {SemanticHandler.currentBaseNode.addOp($addop.text);} |  ;
factor            : factor_prefix postfix_expr  ;
factor_prefix     : factor_prefix postfix_expr  mulop {SemanticHandler.currentBaseNode.addOp($mulop.text);} |  ;
postfix_expr      : primary  | call_expr  ;
call_expr         : id {SemanticHandler.currentBaseNode.startFunc($id.text);}'(' expr_list ')' {SemanticHandler.currentBaseNode.endFunc();} ;
expr_list         : expr expr_list_tail |  ;
expr_list_tail    : ',' {SemanticHandler.currentBaseNode.addOp(",");} expr expr_list_tail |  ;
primary           : '(' {SemanticHandler.currentBaseNode.addOp("(");} expr {SemanticHandler.currentBaseNode.addOp(")");} ')' | id {SemanticHandler.currentBaseNode.addId($id.text);} | INTLITERAL {SemanticHandler.currentBaseNode.addInt($INTLITERAL.text);} | FLOATLITERAL {SemanticHandler.currentBaseNode.addFloat($FLOATLITERAL.text);};
addop             : '+' | '-';
mulop             : '*' | '/';


if_stmt           : 'IF' 
					{
						//SemanticHandler.pushTree();
						Function.pushBlock();
						IfNode ifNode = new IfNode();
						IfBodyNode ifbNode = new IfBodyNode(true);
					} 
					'(' cond ')' {IfBodyNode.currNode.getCond();}
					
					decl stmt_list {Function.popBlock();} else_part {SemanticHandler.addendIF();} 'ENDIF' ;
else_part         : 'ELSIF' {Function.pushBlock();
						IfBodyNode ifbNode = new IfBodyNode(false); }'(' cond ')' 
					{
						//SemanticHandler.popTree();
						//SemanticHandler.pushTree();
						//Function.pushBlock();
						//IfBodyNode ifbNode = new IfBodyNode(false);
						IfBodyNode.currNode.getCond();
					} 
					decl  stmt_list {Function.popBlock();} else_part | ;
cond              : {BaseNode newNode = new BaseNode(1);} expr {SemanticHandler.currentBaseNode.finishBase("1");} compop {BaseNode newNode2 = new BaseNode(2);} expr {SemanticHandler.compop = $compop.text; SemanticHandler.currentBaseNode.finishBase("2");} | 'TRUE' {SemanticHandler.expr1 = "TRUE";} | 'FALSE' {SemanticHandler.expr1 = "FALSE";} ;
compop            : '<' | '>' | '=' | '!=' | '<=' | '>=';

do_while_stmt       : 'DO' 
						{
							Function.pushBlock();
							WhileNode whileNode = new WhileNode();
						} 
						decl stmt_list 'WHILE' {SemanticHandler.setWhile();} '(' cond ')' {Function.popBlock(); SemanticHandler.addendWhile();} ';' ;


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

