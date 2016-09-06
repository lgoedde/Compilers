//Created by Michael Baio and Lucas Goedde

import java.io.*;
import org.antlr.v4.runtime.*;
import java.util.Arrays.*;
//import java.lang;

public class Micro {

    public static void main(String[] args) throws Exception{
    	String fileName = args[0]; //May want to remove this and put files in top level dir
		//System.out.println(fileName);
		try
		{
			//int 01;
			//System.out.println();
			ANTLRFileStream fileStream = new ANTLRFileStream(fileName);
			MicroLexer lexer = new MicroLexer(fileStream);
		
			Token tempToken;
			String type = null;
			while((tempToken = lexer.nextToken()).getType() != lexer.EOF) {
				switch (tempToken.getType()) {
					case MicroLexer.IDENTIFIER:
						type = "IDENTIFIER";
						break;
					case MicroLexer.INTLITERAL:
						type = "INTLITERAL";
						break;	
					case MicroLexer.FLOATLITERAL:
						type = "FLOATLITERAL";
						break;
					case MicroLexer.STRINGLITERAL:
						type = "STRINGLITERAL";
						break;
					case MicroLexer.COMMENT:
						type = "COMMENT";
						break;
					case MicroLexer.KEYWORD:
						type = "KEYWORD";
						break;
					case MicroLexer.OPERATOR:
						type = "OPERATOR";
						break;
					case MicroLexer.WHITESPACE:
						type = "WHITESPACE";
						break;
					default: type = ""; break;
				}
				if (type != "COMMENT" && type != "WHITESPACE")
				{
					System.out.println("Token Type: "+type+"\n"+"Value: "+tempToken.getText());
				}
			}

			//System.out.println(MicroLexer.getTokenNames());
			//System.out.println(MicroLexer.getRuleNames());

		}
		catch(Exception e)
		{
			System.out.println(e);
		}

    }
}
