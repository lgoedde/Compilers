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
			}
			
		}
		
		int listSize = TinyList.size();
		for (int i = 0; i < listSize; i++) 
		{
			TinyList.get(i).printInstr();
		}
	}
	
	
	private static void store(String value, String dest) {
		value = convertOp(value);
		dest = convertOp(dest);
		TinyList.add(new TinyInstr(TinyInstr.TinyOpcode.move,value,dest));
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
