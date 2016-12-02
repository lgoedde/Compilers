import java.io.*;
import java.util.*;


public class TinyGeneration {
	private static HashMap<String,String> IRtoTinyReg = new HashMap<String,String>();
	private static Stack<String> AvailableRegs = new Stack<String>();
	public static LinkedList<TinyInstr> TinyList = new LinkedList<TinyInstr>();
	public static int paramLength;
	public static int numLocals;
	public static int numTemps;
	
	public static IRNode currentNode;
	
	public static TinyReg[] TinyRegs = { new TinyReg(),new TinyReg(),new TinyReg(),new TinyReg()};

	public static String lastLabel;

	public TinyGeneration() {
	}
	
	public static class TinyReg {
		public String value;
		public boolean dirty;
		public boolean global;
		
		public TinyReg() {	
		}
	}

	public static void printTiny(IRNode ir) {
		if (ir == null || ir.Opcode == null)
			return;
			currentNode = ir;
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
			case WRITES :
				read_write("writes",ir.Result);
				break;
			case WRITEI :
				read_write("writei",ir.Result);
				break;
			case WRITEF :
				read_write("writer",ir.Result);
				break;
			case GT :
				branchCompare(TinyInstr.TinyOpcode.jgt,ir.Op1,ir.Op2,ir.Result,ir.typeBranch);
				break;
			case GE :
				branchCompare(TinyInstr.TinyOpcode.jge,ir.Op1,ir.Op2,ir.Result,ir.typeBranch);
				break;
			case LT :
				branchCompare(TinyInstr.TinyOpcode.jlt,ir.Op1,ir.Op2,ir.Result,ir.typeBranch);
				break;
			case LE :
				branchCompare(TinyInstr.TinyOpcode.jle,ir.Op1,ir.Op2,ir.Result,ir.typeBranch);
				break;
			case NE :
				branchCompare(TinyInstr.TinyOpcode.jne,ir.Op1,ir.Op2,ir.Result,ir.typeBranch);
				break;
			case EQ :
				branchCompare(TinyInstr.TinyOpcode.jeq,ir.Op1,ir.Op2,ir.Result,ir.typeBranch);
				break;
			case JUMP :
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.jmp,null,ir.Result));
				break;
			case LABEL :
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.label,null,ir.Result));
				lastLabel = ir.Result;
				break;

			case PUSH :
				int i = testRegs(ir.Op1);
				if (i != -1) {
					TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.push,"r"+Integer.toString(i),null));
				}
				else {
					TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.push,getStackRef(ir.Op1),null));
				}
				break;
			case POP :
				int j = testRegs(ir.Result);
				if (j != -1) {
					TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.pop,"r"+Integer.toString(j),null));
				}
				else {
					TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.pop,getStackRef(ir.Result),null));
				}
				break;
			case JSR :
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.push,"r0",null));
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.push,"r1",null));
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.push,"r2",null));
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.push,"r3",null));
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.jsr,null,ir.Result));
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.pop,"r3",null));
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.pop,"r2",null));
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.pop,"r1",null));
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.pop,"r0",null));
				//TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.pop,null,null));
				break;
			case LINK :
				//numLocals = Function.LocalLookUp.get(lastLabel);
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.link,Integer.toString(numLocals + numTemps),null));
				break;
			case RET :
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.unlnk,null,null));
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.ret,null,null));
				break;
			}
			freeUnused();
	}
	
	private static boolean checkLive(String value) {
		for (String val : currentNode.liveOut) {
			if (val.equals(value))
				return true;
		}
		return false;
	}
	
	private static void freeUnused() {
		for (int i = 0; i < 4; i++) {
			if (! checkLive(TinyRegs[i].value)) {
				TinyRegs[i].value = "";
				TinyRegs[i].dirty = false;
			}
			
		}
	}
	
	private static int findBestReg(String dontTouch) {
		for (int i = 0; i < 4; i++) {
			if (! TinyRegs[i].value.equals(dontTouch) && ! checkLive(TinyRegs[i].value))
				return i;
		}
		for (int i = 0; i < 4; i++) {
			if (! TinyRegs[i].value.equals(dontTouch))
				return i;
		}
		return -1;
	}
	
	private static String[] ensure(String op1, String op2, String result,boolean checkResult) {
		int op1Reg = -1;
		int op2Reg = -1;
		
		
		if (op1 != null) {
			for (int i = 0; i < 4; i++) {
				if (TinyRegs[i].value.equals(op1))
					op1Reg = i;
			}
			if (op1Reg == -1) {
				int i = findBestReg(op2);
				op1Reg = i;
				if (TinyRegs[i].dirty)
					saveValue(TinyRegs[i].value,i);
				TinyRegs[i].value = op1;
				TinyRegs[i].dirty = false;
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,getStackRef(op1),"r"+Integer.toString(i)));
				
			}
		}
		if (op2 != null) {
			for (int i = 0; i < 4; i++) {
				if (TinyRegs[i].value.equals(op2))
					op2Reg = i;
			}
			if (op2Reg == -1) {
				int i = findBestReg(op1);
				op2Reg = i;
				if (TinyRegs[i].dirty)
					saveValue(TinyRegs[i].value,i);
				TinyRegs[i].value = op2;
				TinyRegs[i].dirty = false;
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,getStackRef(op2),"r"+Integer.toString(i)));
			
			}
		}
		if (checkResult) {
			TinyRegs[op2Reg].value = result;
			TinyRegs[op2Reg].dirty = true;
		}
		return new String[] {"r"+Integer.toString(op1Reg),"r"+Integer.toString(op2Reg)};
		
	}
	
	private static String getStackRef(String op) {
		if (op == null)
			return null;
		op = op.replaceAll(" ", "");
		if (isNumeric(op))
			return op;
		if (op.charAt(0) == '$') {
			if (op.charAt(1) == 'L') {
				String num = op.split("L")[1];
				return "$-"+num;
			}
			if (op.charAt(1) == 'R') {
				return "$"+Integer.toString(6+paramLength);
			}
			if (op.charAt(1) == 'P') {
				String num = op.split("P")[1];
				return "$"+Integer.toString(6+paramLength-Integer.parseInt(num));
			}
			if (op.charAt(1) == 'T') {
				String num = op.split("T")[1];
				return "$-"+Integer.toString(numLocals+Integer.parseInt(num));
			}
			else {
				return null;
			}
		}
		return op;
	}
		
	private static void saveValue(String value,int regInd) {
		if (isNumeric(value))
			return;
		TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,"r"+Integer.toString(regInd),getStackRef(value)));
	}

	private static void branchCompare(TinyInstr.TinyOpcode opcode, String op1, String op2, String dest, String type) {
		int op1Reg = testRegs(op1);
		if (op1Reg == -1)
			op1 = getStackRef(op1);
		else
			op1 = "r"+Integer.toString(op1Reg);
		
		int op2Reg = testRegs(op2);
		if (op2Reg == -1) {
			op2Reg = findBestReg(op1);
			if (TinyRegs[op2Reg].dirty)
				saveValue(TinyRegs[op2Reg].value,op2Reg);
			TinyRegs[op2Reg].value = op2;
			TinyRegs[op2Reg].dirty = true;
			TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,getStackRef(op2),"r"+Integer.toString(op2Reg)));
		}
		
		op2 = "r"+Integer.toString(op2Reg);
		
		TinyInstr.TinyOpcode cmpInstr = TinyInstr.TinyOpcode.cmpi;
		if (type.equals("FLOAT"))
			cmpInstr = TinyInstr.TinyOpcode.cmpr;
		TinyList.add(new TinyInstr(cmpInstr,op1,op2));
		TinyList.add(new TinyInstr(opcode,null,dest));
		
		/*op1 = convertOp(op1);
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
		TinyList.add(new TinyInstr(opcode,null,dest));*/
	}

	private static boolean testReg(String val) {
		if (val.charAt(0) != 'r')
			return false;
		if (isNumeric(val.split("r")[1]))
				return true;
		return false;
	}
	
	private static int testRegs(String value) {
		for (int i = 0; i < 4; i++) {
			if (TinyRegs[i].value.equals(value))
				return i;
		}
		return -1;
	}


	private static void store(String value, String dest) {
		int reg1 = testRegs(value);
		int reg2 = testRegs(dest);
		
		if (reg1 == -1 && reg2 == -1) {
			int temp = findBestReg(value);
			if (TinyRegs[temp].dirty)
				saveValue(TinyRegs[temp].value,temp);
			value = getStackRef(value);
			dest = getStackRef(dest);
			TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,value,"r"+Integer.toString(temp)));
			TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,"r"+Integer.toString(temp),dest));
		}
		else {
			if (reg1 != -1)
				value = "r"+Integer.toString(reg1);
			else
				value = getStackRef(value);
			if (reg2 != -1)
				dest = "r"+Integer.toString(reg2);
			else
				dest = getStackRef(value);
			TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,value,dest));
		}
		
		
		/*value = convertOp(value);
		dest = convertOp(dest);
		if (testVarName(value) && testVarName(dest) ) {
			String newReg = AvailableRegs.pop();
			TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,value,newReg));
			TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,newReg,dest));
		}
		else {
			
			TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,value,dest));
		}*/
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
		//boolean dependency = hasDependency(op1,op2,result);
		String[] regs = ensure(op1,op2,result,true);
		TinyList.add(new TinyInstr(opcode,regs[0],regs[1]));
		
		/*op1 = convertOp(op1);
		op2 = convertOp(op2);
		if (dependency) {
			result = getNewDest(result);
		}
		else {
			result = convertOp(result);
		}
		TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,op1,result));
		TinyList.add(new TinyInstr(opcode,op2,result));*/
	}

	private static boolean hasDependency(String op1, String op2, String result) {
		return op2.equals(result) && !op1.equals(result);
	}

	private static void read_write(String opcode, String value) {
		int i = testRegs(value);
		if (i == -1) {
			if (value.charAt(0) != '$') {
				TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.sys,opcode,value));
				return;
			}
			else {
				i = findBestReg(value);
				TinyRegs[i].value = value;
				TinyRegs[i].dirty = true;
				
			}
		}
		TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.sys,opcode,"r"+Integer.toString(i)));
	}
	

	private static String getNewDest(String dest) {
		String newDest = AvailableRegs.pop();
		IRtoTinyReg.put(dest, newDest);
		return newDest;
	}

	private static String convertOp(String op) {
		if (op == null)
			return null;
		op = op.replaceAll(" ", "");
		if (op.charAt(0) == '$') {
			if (op.charAt(1) == 'L') {
				String num = op.split("L")[1];
				return "$-"+num;
			}
			if (op.charAt(1) == 'R') {
				return "$"+Integer.toString(6+paramLength);
			}
			if (op.charAt(1) == 'P') {
				String num = op.split("P")[1];
				return "$"+Integer.toString(6+paramLength-Integer.parseInt(num));
			}
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
		//for (int i = 999; i >= 0; i--) {
		//	AvailableRegs.push("r"+Integer.toString(i));
		//}
		//IRtoTinyReg.clear();
		for (int i = 0; i < 4; i++) {
			TinyRegs[i].dirty = false;
			TinyRegs[i].global = false;
			TinyRegs[i].value = "";
		}
	}
}
