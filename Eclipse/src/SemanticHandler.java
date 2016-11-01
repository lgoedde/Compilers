import java.util.*;


public class SemanticHandler {
	public static Stack<SemanticActionTree> SemanticStack = new Stack<SemanticActionTree>();
	public static IRList currentIRList;
	public SemanticHandler() {
		
	}
	
	public static SemanticActionTree getCurrentTree() {
		return SemanticStack.peek();
	}
	
	public static void pushTree() {
		SemanticStack.push(new SemanticActionTree());
	}
	
	public static void popTree() {
		SemanticStack.pop();
	}
	
	public static void printIRCode() {
		getCurrentTree().printNodes();
	}
	
	
	
	public void addAssignment(String id, String expr) {
		String simplified = ExpressionEval.SimplifyExpression(expr);
		String type = ExpressionEval.getType(simplified,"0");
		String result = ExpressionEval.checkOps(simplified, "").get(0);
		if (type.equals("FLOAT")) {
			currentIRList.NodeList.add(new IRNode(IRNode.IROpcode.STOREF," "+result," ",id));
		}
		else {
			currentIRList.NodeList.add(new IRNode(IRNode.IROpcode.STOREI," "+result," ",id));

		}

	}

	public void addRead(String idList) {
		String type;
		if (idList.indexOf(',') != -1) {
			String[] parts = idList.split(",");
			for (String part : parts) {
				if ((type = SymbolTable.getSymbolType(part)) != "") {
					if (type.equals("INT")) {
						IRNode tempNode = new IRNode(IRNode.IROpcode.READI," ","",part);
						currentIRList.NodeList.add(tempNode);
					}
					else if(type.equals("FLOAT")) {
						IRNode tempNode = new IRNode(IRNode.IROpcode.READF," ","",part);
						currentIRList.NodeList.add(tempNode);
					}
				}
				else {
					// USING VARIABLE NOT DECLARED
				}
			}
		}
		else {
			if ((type = SymbolTable.getSymbolType(idList)) != "") {
				if (type.equals("INT")) {
					IRNode tempNode = new IRNode(IRNode.IROpcode.READI," ","",idList);
					currentIRList.NodeList.add(tempNode);
				}
				else if(type.equals("FLOAT")) {
					IRNode tempNode = new IRNode(IRNode.IROpcode.READF," ","",idList);
					currentIRList.NodeList.add(tempNode);
				}
			}
			else {
				// USING VARIABLE NOT DECLARED
			}
		}
	}

	public void addWrite(String idList) {
	String type;
		if (idList.indexOf(',') != -1) {
			String[] parts = idList.split(",");
			for (String part : parts) {
				if ((type = SymbolTable.getSymbolType(part)) != "") {
					if (type.equals("INT")) {
						IRNode tempNode = new IRNode(IRNode.IROpcode.WRITEI," ","",part);
						currentIRList.NodeList.add(tempNode);
					}
					else if(type.equals("FLOAT")) {
						IRNode tempNode = new IRNode(IRNode.IROpcode.WRITEF," ","",part);
						currentIRList.NodeList.add(tempNode);
					}
				}
				else {
					// USING VARIABLE NOT DECLARED
				}
			}
		}
		else {
			if ((type = SymbolTable.getSymbolType(idList)) != "") {
				if (type.equals("INT")) {
					IRNode tempNode = new IRNode(IRNode.IROpcode.WRITEI," ","",idList);
					currentIRList.NodeList.add(tempNode);
				}
				else if(type.equals("FLOAT")) {
					IRNode tempNode = new IRNode(IRNode.IROpcode.WRITEF," ","",idList);
					currentIRList.NodeList.add(tempNode);
				}
			}
			else {
				// USING VARIABLE NOT DECLARED
			}
		}
	}
	
	
	

}
