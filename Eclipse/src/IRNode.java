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
		WRITEF,
		WRITES,
		PUSH,
		POP,
		JSR,
		LINK,
		RET,
	}

	public IROpcode Opcode;
	public String Op1 = null;
	public String Op2 = null;
	public String Result;
	public String typeBranch = null;
	public List<String> liveOut = new ArrayList<String>();
	public List<String> liveIn = new ArrayList<String>();
	public List<Integer> prec = new ArrayList<Integer>();
	public List<Integer> succ = new ArrayList<Integer>();


	public IRNode(IROpcode opcode, String op1, String op2, String result){
		this.Opcode = opcode;
		this.Op1 = op1;
		this.Op2 = op2;
		this.Result = result;


	}

	public IRNode() {

	}

	public void printNode() {
		if (this.Opcode != null) {
			//System.out.print(SemanticHandler.nodenum++);
			System.out.print(";"+this.Opcode+" ");
			if (this.Op1 != null)
				System.out.print(this.Op1+" ");
			if (this.Op2 != null)
				System.out.print(this.Op2+" ");
			if (this.Result != null)
				System.out.print(this.Result);
			//if (this.liveIn != null)
				//System.out.println(this.liveIn);
			//if (this.liveOut != null)
				//System.out.print(" "+this.liveOut);
			//if (this.succ != null)
				//System.out.println("Succ: "+this.succ);
			//if (this.succ != null)
				//System.out.println("Prec: "+this.prec);
			System.out.print("\n");
		}
	}
}
