import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;

public class ExpressionEval {

	public static int regNum = 0;

	public ExpressionEval() {

	}


	public static String SimplifyExpression(String expression) {
		String simplified;
		System.out.print("expr: "+expression);
		expression = expression.replaceAll("\\s+","");
		expression = expression.replace(";","");
		System.out.print("\ttrim: "+expression);
		simplified = SimplifyParenthesis(expression);
		System.out.print("\tparen: "+simplified);
		simplified = SimpMult(simplified);
		System.out.print("\tmult: "+simplified);
		simplified = SimpAdd(simplified);
		System.out.println("\tfinish: "+simplified);
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
				while (!stack.empty() && (pop = (char)stack.pop()) != '(') {
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
		//System.out.println("tokens mult: "+tokens);
		String token;
		String type;
		String result;
		String op1;
		String op2;
		Stack stack = new Stack();
		for (int i = 0; i < tokens.size(); i++) {
			token = tokens.get(i);
			if (token.equals("*") || token.equals("/")) {
				op1 = (String)stack.pop();
				op2 = tokens.get(++i);
				type = getType(op1,op2);
				result = "$T"+Integer.toString(regNum++);
				stack.push(result);
				if (token.equals("*")) {
					if (type.equals("FLOAT")) 
						IRList.NodeList.add(new IRNode("MULTF ",op1+" ",op2+" ",result));
					else
						IRList.NodeList.add(new IRNode("MULTI ",op1+" ",op2+" ",result));
				}
				else if (token.equals("/")) {
					if (type.equals("FLOAT")) 
						IRList.NodeList.add(new IRNode("DIVF ",op1+" ",op2+" ",result));
					else
						IRList.NodeList.add(new IRNode("DIVI ",op1+" ",op2+" ",result));
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
		Stack stack = new Stack();
		for (int i = 0; i < tokens.size(); i++) {
			token = tokens.get(i);

			if (token.equals("+") || token.equals("-")) {
				op1 = (String)stack.pop();
				op2 = tokens.get(++i);
				type = getType(op1,op2);
				result = "$T"+Integer.toString(regNum++);
				stack.push(result);
				if (token.equals("+")) {
					if (type.equals("FLOAT")) 
						IRList.NodeList.add(new IRNode("ADDF ",op1+" ",op2+" ",result));
					else
						IRList.NodeList.add(new IRNode("ADDI ",op1+" ",op2+" ",result));
				}
				else if (token.equals("-")) {
					if (type.equals("FLOAT")) 
						IRList.NodeList.add(new IRNode("SUBF ",op1+" ",op2+" ",result));
					else
						IRList.NodeList.add(new IRNode("SUBI ",op1+" ",op2+" ",result));
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

	private static List<String> GetTokens(String expr) {
		List<String> strArr = new ArrayList<String>();
		String temp = "";
		for(int i = 0; i < expr.length(); i++) {
			char c = expr.charAt(i);
			if (c == '+' || c == '-' || c == '/' || c == '*') {
				//System.out.println("add token: "+temp);
				strArr.add(temp);
				strArr.add(Character.toString(c));
				temp = "";
			}
			else {
				temp = temp + c;
			}
		}
		//System.out.println("add token: "+temp);

		strArr.add(temp);
		return strArr;
	}

	private static String getType(String op1, String op2) {
		if (op1.charAt(0) == '$' || op2.charAt(0) == '$')
			return "FLOAT";
		if (op1.indexOf('.') != -1 || op2.indexOf('.') != -1)
			return "FLOAT";
		String type1;
		String type2;
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