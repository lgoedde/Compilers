import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;
// step 5
public class ExpressionEval {

	public static int regNum = 0;
	public static HashMap regLookUp = new HashMap();

	public ExpressionEval() {
	}


	public static String SimplifyExpression(String expression) {
		String simplified;
		expression = expression.replaceAll("\\s+","");
		expression = expression.replace(";","");
		simplified = SimplifyParenthesis(expression);
		simplified = SimpMult(simplified);
		simplified = SimpAdd(simplified);
		return simplified;
	}

	private static String SimplifyParenthesis(String expr) {
		Stack stack = new Stack();
		char c;
		char pop;
		for (int i = 0; i < expr.length(); i++) {
			c = expr.charAt(i);

			if (c == ')') {
				String inner = "";
				while (!stack.empty() && (pop = (Character)stack.pop()) != '(') {
					inner = pop + inner;
				}
				inner = SimpMult(inner);
				inner = SimpAdd(inner);
				for (int j = 0; j < inner.length(); j++) {
					stack.push(inner.charAt(j));
				}
			}
			else {
				stack.push(c);
			}
		}
		String result = "";
		while (!stack.empty()) {
			result = stack.pop() + result;
		}
		return result;
	}

	private static String SimpMult (String expr) {
		List<String> tokens = GetTokens(expr);
		String token;
		String type;
		String result;
		String op1;
		String op2;
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < tokens.size(); i++) {
			token = tokens.get(i);
			if (token.equals("*") || token.equals("/")) {
				op1 = (String)stack.pop();
				op2 = tokens.get(++i);
				type = getType(op1,op2);
				result = GetNextReg(type);
				stack.push(result);
				List<String> ops = checkOps(op1,op2);
				op1 = ops.get(0);
				op2 = ops.get(1);
				if (token.equals("*")) {
					if (type.equals("FLOAT")) 
						IRList.NodeList.add(new IRNode(IRNode.IROpcode.MULTF," "+op1+" ",op2+" ",result));
					else
						IRList.NodeList.add(new IRNode(IRNode.IROpcode.MULTI," "+op1+" ",op2+" ",result));
				}
				else if (token.equals("/")) {
					if (type.equals("FLOAT")) 
						IRList.NodeList.add(new IRNode(IRNode.IROpcode.DIVF," "+op1+" ",op2+" ",result));
					else
						IRList.NodeList.add(new IRNode(IRNode.IROpcode.DIVI," "+op1+" ",op2+" ",result));
				}
			}
			else {
				stack.push(token);
			}
		}
		result = "";
		while (!stack.empty()) {
			result = stack.pop() + result;
		}
		return result;
	}

	private static String SimpAdd (String expr) {
		List<String> tokens = GetTokens(expr);
		String token;
		String type;
		String result;
		String op1;
		String op2;
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < tokens.size(); i++) {
			token = tokens.get(i);

			if (token.equals("+") || token.equals("-")) {
				op1 = (String)stack.pop();
				op2 = tokens.get(++i);
				type = getType(op1,op2);
				result = GetNextReg(type);
				stack.push(result);
				List<String> ops = checkOps(op1,op2);
				op1 = ops.get(0);
				op2 = ops.get(1);
				if (token.equals("+")) {
					if (type.equals("FLOAT")) 
						IRList.NodeList.add(new IRNode(IRNode.IROpcode.ADDF," "+op1+" ",op2+" ",result));
					else
						IRList.NodeList.add(new IRNode(IRNode.IROpcode.ADDI," "+op1+" ",op2+" ",result));
				}
				else if (token.equals("-")) {
					if (type.equals("FLOAT")) 
						IRList.NodeList.add(new IRNode(IRNode.IROpcode.SUBF," "+op1+" ",op2+" ",result));
					else
						IRList.NodeList.add(new IRNode(IRNode.IROpcode.SUBI," "+op1+" ",op2+" ",result));
				}
				
			}
			else {
				stack.push(token);
			}
		}
		result = "";
		while (!stack.empty()) {
			result = stack.pop() + result;
		}
		return result;
	}

	public static List<String> checkOps(String op1, String op2) {
		String reg;
		String res1 = op1;
		String res2 = op2;
		List<String> results = new ArrayList<String>(); 
		if (op1.indexOf('.') != -1 ) {
			reg = GetNextReg("FLOAT");
			IRList.NodeList.add(new IRNode(IRNode.IROpcode.STOREF," "+op1," ",reg));
			res1 = reg;
		}
		else if (isNumeric(op1)) {
			reg = GetNextReg("INT");
			IRList.NodeList.add(new IRNode(IRNode.IROpcode.STOREI," "+op1," ",reg));
			res1 = reg;
		}
		if (op2.indexOf('.') != -1 ) {
			reg = GetNextReg("FLOAT");
			IRList.NodeList.add(new IRNode(IRNode.IROpcode.STOREF," "+op2," ",reg));
			res2 = reg;
		}
		else if (isNumeric(op2)) {
			reg = GetNextReg("INT");
			IRList.NodeList.add(new IRNode(IRNode.IROpcode.STOREI," "+op2," ",reg));
			res2 = reg;
		}
		
		results.add(res1);
		results.add(res2);
		return results;
		
	}

	public static String GetNextReg(String type) {
		String reg = "$T"+Integer.toString(regNum++);
		regLookUp.put(reg,type);
		return reg;
	}

	private static List<String> GetTokens(String expr) {
		List<String> strArr = new ArrayList<String>();
		String temp = "";
		for(int i = 0; i < expr.length(); i++) {
			char c = expr.charAt(i);
			if (c == '+' || c == '-' || c == '/' || c == '*') {
				strArr.add(temp);
				strArr.add(Character.toString(c));
				temp = "";
			}
			else {
				temp = temp + c;
			}
		}

		strArr.add(temp);
		return strArr;
	}

	public static String getType(String op1, String op2) {
		String type1;
		String type2;
		if (op1.charAt(0) == '$') 
			type1 = (String)regLookUp.get(op1);
		if (op2.charAt(0) == '$') {
			type2 = (String)regLookUp.get(op2);
		}
		if (op1.indexOf('.') != -1 || op2.indexOf('.') != -1)
			return "FLOAT";
		
		if (!isNumeric(op1)) {
			type1 = SymbolTable.getSymbolType(op1);
		}
		else {
			type1 = "INT";
		}
		if (!isNumeric(op2)) {
			type2 = SymbolTable.getSymbolType(op2);
		}
		else {
			type2 = "INT";
		}

		if (type1.equals("") || type2.equals("")) {
			// ERROR
		}
		if (type1.equals("FLOAT") || type2.equals("FLOAT")) {
			return "FLOAT";
		}
		return "INT";
	}

	public static boolean isNumeric(String s) {  
    	return s.matches("[-+]?\\d*\\.?\\d+");  
	}


}