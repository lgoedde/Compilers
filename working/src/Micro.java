//Created by Michael Baio and Lucas Goedde

import java.io.*;
import org.antlr.v4.runtime.*;
import java.util.Arrays.*;
//import java.lang;
//TEST COMMENT 4
public class Micro {

    public static void main(String[] args) throws Exception{
    	//get the current file
    	String fileName = args[0]; 
    	//fileName = "testcases/step5/input/test_if.micro";
    	//System.setIn(new FileInputStream("testcases/step5/input/step4_testcase.input"));
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
			SemanticHandler.printIRCode();
			//System.out.println("Accepted");
		}

		catch (Exception e) {
			//System.out.println("Not Accepted");

		}

		//took out the old stuff for step1

    }
}
