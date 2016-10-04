//Created by Michael Baio and Lucas Goedde

import java.io.*;
import org.antlr.v4.runtime.*;
import java.util.Arrays.*;
//import java.lang;
//TEST COMMENT 2
public class Micro {

    public static void main(String[] args) throws Exception{
    	//get the current file
    	String fileName = args[0]; 

    	//set up all of the stuff we need to parse
		ANTLRFileStream fileStream = new ANTLRFileStream(fileName);
		MicroLexer lexer = new MicroLexer(fileStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer); 
		MicroParser parser = new MicroParser(tokens);

		//do the actual parsing
		try {
			parser.removeErrorListeners(); //keep errors from being written to command line
			parser.setErrorHandler(new BailErrorStrategy());
			parser.program();
			System.out.println("Accepted");
		}

		catch (Exception e) {
			System.out.println("Not Accepted");

		}

		//took out the old stuff for step1

    }
}
