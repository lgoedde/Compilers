import java.io.*;
import java.util.*;


public class TinyGeneration {
	private static HashMap<String,String> IRtoTinyReg = new HashMap<String,String>();
	private static Stack<String> AvailableRegs = new Stack<String>();
	public static LinkedList<TinyInstr> TinyList = new LinkedList<TinyInstr>();
	
	public TinyGeneration() {
	}
	
	public static void printTiny() {
		resetRegisterStack();
		System.out.println(";tiny code");
		for (Symbol symbol : SymbolTable.globalScope.symbolTable) {
			if (symbol.type.equals("STRING"))
				System.out.println("str "+symbol.identifier);
			else
				System.out.println("var "+symbol.identifier);
		}
		ListIterator<IRNode> iterator = IRList.NodeList.listIterator();
		while (iterator.hasNext()) {
			IRNode ir = iterator.next();
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
				
			}
		}
		
		int listSize = TinyList.size();
		for (int i = 0; i < listSize; i++) 
		{
			TinyList.get(i).printInstr();
		}
		System.out.println("sys halt");
	}
	
	
	private static void store(String value, String dest) {
		value = convertOp(value);
		dest = convertOp(dest);
		TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,value,dest));
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
	
	private static void resetRegisterStack() {
		for (int i = 999; i >= 0; i--) {
			AvailableRegs.push("r"+Integer.toString(i));
		}
	}
}
