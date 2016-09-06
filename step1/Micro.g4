grammar Micro;

KEYWORD: 'PROGRAM' | 'BEGIN' | 'END' | 'FUNCTION' | 'READ' | 'WRITE' | 'IF' | 'ELSIF' | 'ENDIF' | 'DO' | 'WHILE' | 'CONTINUE' | 'BREAK' | 'RETURN' | 'INT' | 'VOID' | 'STRING' | 'FLOAT' | 'TRUE' | 'FALSE' ;

OPERATOR: ':='|'+'|'-'|'*'|'/'|'='|'!='|'<'|'>'|'('|')'|';'|','|'<='|'>=';

IDENTIFIER: ([a-zA-Z])([a-zA-Z0-9])*;

INTLITERAL:	[0-9]+|[0];

FLOATLITERAL: [0-9]*'.'[0-9]+;

STRINGLITERAL: '"'('\\"' | ~('\n'|'\r'))*? '"';

WHITESPACE: [\r\t\n' ']+;

COMMENT: '-''-'(.)*?'\n';

testsocompiles: 'blah';
