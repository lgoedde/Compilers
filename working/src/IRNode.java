import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;

public class IRNode {
	
	public enum IROpcode {
		ADDI,
		ADDF,
		SUBI,
		SUBF,
		MULTI,
		MULTF,
		DIVI,
		DIVF,
		STOREI,
		STOREF,
		GT,
		GE,
		LT,
		LE,
		NE,
		EQ,
		JUMP,
		LABEL,
		READI,
		READF,
		WRITEI,
		WRITEF
	}

	public IROpcode Opcode;
	public String Op1;
	public String Op2;
	public String Result;

	public IRNode(IROpcode opcode, String op1, String op2, String result){
		this.Opcode = opcode;
		this.Op1 = op1;
		this.Op2 = op2;
		this.Result = result;
	}

	public IRNode() {

	}

	public void printNode() {
		System.out.println(";"+this.Opcode+this.Op1+this.Op2+this.Result);
	}
}