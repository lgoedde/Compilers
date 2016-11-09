import java.io.*;
import java.util.*;


public class TinyGeneration {
	private static HashMap<String,String> IRtoTinyReg = new HashMap<String,String>();
	private static Stack<String> AvailableRegs = new Stack<String>();
	public static LinkedList<TinyInstr> TinyList = new LinkedList<TinyInstr>();
	
	public TinyGeneration() {
	}
	
	public static void printTiny(IRNode ir) {
		if (ir == null || ir.Opcode == null)
			return;
		//ListIterator<IRNode> iterator = null; //IRList.NodeList.listIterator();
		//while (iterator.hasNext()) {
			//IRNode ir = iterator.next();
			switch(ir.Opcode) {
			case STOREI :
			case STOREF :
				store(ir.Op1,ir.Result);
				break;
			case ADDI :
				arithmetic(TinyInstr.TinyOpcode.addi, ir.Op1, ir.Op2, ir.Result);
				break;
			case ADDF :
				arithmetic(TinyInstr.TinyOpcode.addr, ir.Op1, ir.Op2, ir.Result);
				break;
			case SUBI :
				arithmetic(TinyInstr.TinyOpcode.subi, ir.Op1, ir.Op2, ir.Result);
				break;
			case SUBF :
				arithmetic(TinyInstr.TinyOpcode.subr, ir.Op1, ir.Op2, ir.Result);
				break;
			case MULTI :
				arithmetic(TinyInstr.TinyOpcode.muli, ir.Op1, ir.Op2, ir.Result);
				break;
			case MULTF :
				arithmetic(TinyInstr.TinyOpcode.mulr, ir.Op1, ir.Op2, ir.Result);
				break;
			case DIVI :
				arithmetic(TinyInstr.TinyOpcode.divi, ir.Op1, ir.Op2, ir.Result);
				break;
			case DIVF :
				arithmetic(TinyInstr.TinyOpcode.divr, ir.Op1, ir.Op2, ir.Result);
				break;
			case READI :
				read_write("readi",ir.Result);
				break;
			case READF :
				read_write("readr",ir.Result);
				break;
			case WRITEI :
				read_write("writei",ir.Result);
				break;
			case WRITEF :
				read_write("writer",ir.Result);
				break;
			case GT :
				branchCompare(TinyInstr.TinyOpcode.jgt,ir.Op1,ir.Op2,ir.Result);
				break;
			case GE :
				branchCompare(TinyInstr.TinyOpcode.jge,ir.Op1,ir.Op2,ir.Result);
				break;
			case LT :
				branchCompare(TinyInstr.TinyOpcode.jlt,ir.Op1,ir.Op2,ir.Result);
				break;
			case LE :
				branchCompare(TinyInstr.TinyOpcode.jle,ir.Op1,ir.Op2,ir.Result);
				break;
			case NE :
				branchCompare(TinyInstr.TinyOpcode.jne,ir.Op1,ir.Op2,ir.Result);
				break;
			case EQ :
				branchCompare(TinyInstr.TinyOpcode.jeq,ir.Op1,ir.Op2,ir.Result);
				break;
			case JUMP :
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.jmp,null,ir.Result));
				break;
			case LABEL :
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.label,null,ir.Result));
				break;
			}
	}
	
	private static void branchCompare(TinyInstr.TinyOpcode opcode, String op1, String op2, String dest) {
		String type = ExpressionEval.getType(op1, op2);
		op1 = convertOp(op1);
		op2 = convertOp(op2);
		if (!testReg(op2)) {
			String nextReg = AvailableRegs.pop();
			TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,op2, nextReg));
			op2 = nextReg;
		}
		TinyInstr.TinyOpcode cmpInstr = TinyInstr.TinyOpcode.cmpi;
		if (type.equals("FLOAT"))
			cmpInstr = TinyInstr.TinyOpcode.cmpr;
		TinyList.add(new TinyInstr(cmpInstr,op1,op2));
		TinyList.add(new TinyInstr(opcode,null,dest));
	}
	
	private static boolean testReg(String val) {
		if (val.charAt(0) != 'r')
			return false;
		if (isNumeric(val.split("r")[1]))
				return true;
		return false;
	}
	
	
	private static void store(String value, String dest) {
		value = convertOp(value);
		dest = convertOp(dest);
		if (testVarName(value) && testVarName(dest) ) {
			String newReg = AvailableRegs.pop();
			TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,value,newReg));
			TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,newReg,dest));
		}
		else {
			TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,value,dest));
		}
	}
	
	public static boolean isNumeric(String s) {  
    	return s.matches("[-+]?\\d*\\.?\\d+");  
	}
	
	private static boolean testVarName(String val) {
		if (isNumeric(val))
			return false;
		if (val.charAt(0) == 'r' && isNumeric(val.split("r")[1])) {
				return false;
		}
		return true;
	}
	
	private static void arithmetic(TinyInstr.TinyOpcode opcode, String op1, String op2, String result) {
		boolean dependency = hasDependency(op1,op2,result);
		op1 = convertOp(op1);
		op2 = convertOp(op2);
		if (dependency) {
			result = getNewDest(result);
		}
		else {
			result = convertOp(result);
		}
		TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,op1,result));
		TinyList.add(new TinyInstr(opcode,op2,result));
	}
	
	private static boolean hasDependency(String op1, String op2, String result) {
		return op2.equals(result) && !op1.equals(result);
	}
	
	private static void read_write(String opcode, String value) {
		TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.sys,opcode,value));
	}
	
	private static String getNewDest(String dest) {
		String newDest = AvailableRegs.pop();
		IRtoTinyReg.put(dest, newDest);
		return newDest;
	}
	
	private static String convertOp(String op) {
		op = op.replaceAll(" ", "");
		if (op.charAt(0) == '$') {
			if (IRtoTinyReg.containsKey(op)) {
				return IRtoTinyReg.get(op);
			}
			else {
				String nextReg = AvailableRegs.pop();
				IRtoTinyReg.put(op, nextReg);
				return nextReg;
			}
		}
		else
			return op;
	}
	
	public static void resetRegisterStack() {
		for (int i = 999; i >= 0; i--) {
			AvailableRegs.push("r"+Integer.toString(i));
		}
	}
}
