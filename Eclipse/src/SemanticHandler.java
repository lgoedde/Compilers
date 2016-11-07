import java.util.*;



public class SemanticHandler {
	public static Stack<SemanticActionTree> SemanticStack = new Stack<SemanticActionTree>();
	public static IRList currentIRList;
	private static int label = 0;
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
	
	public static void addIF(String cond) {
		// gen condition
		SemanticNode newIf = new SemanticNode(SemanticNode.SemanticType.IF);
		genCondition(cond, newIf.condition);
		newIf.bodyThenList.add(new SemanticActionTree());
		SemanticStack.push(newIf.bodyThenList.getLast());
	}
	
	
	public static void addElseIF(String cond) {
		popTree();
		SemanticNode parentIf = getCurrentTree().SemanticList.getLast();
		SemanticActionTree newTree = new SemanticActionTree();
		SemanticNode newelseIf = new SemanticNode(SemanticNode.SemanticType.ELSEIF);
		newelseIf.jumpOutStart = parentIf.jumpOutStart;
		genCondition(cond, newelseIf.condition);
		newTree.SemanticList.add(newelseIf);
		parentIf.bodyThenList.add(newTree);
		SemanticStack.push(newTree);
		
	}
	
	public static void genCondition(String cond, IRNode conditionNode) {
		String[] parts = cond.split(" ");
		if (parts[1].equals("TRUE")) {
			conditionNode.Opcode = null;
			return;
		}
			
		if (parts[1].equals("FALSE")) {
			conditionNode.Opcode = IRNode.IROpcode.JUMP;
			return;
		}
			
		String expr1 = ExpressionEval.SimplifyExpression(parts[0]);
		String expr2 = ExpressionEval.SimplifyExpression(parts[2]);
		conditionNode.Op1 = expr1;
		conditionNode.Op2 = expr2;
		if (parts[1].equals("<"))
			conditionNode.Opcode = IRNode.IROpcode.GE;
		else if (parts[1].equals(">"))
			conditionNode.Opcode = IRNode.IROpcode.LE;	
		else if (parts[1].equals("="))
			conditionNode.Opcode = IRNode.IROpcode.NE;
		else if (parts[1].equals("!="))
			conditionNode.Opcode = IRNode.IROpcode.EQ;
		else if (parts[1].equals("<="))
			conditionNode.Opcode = IRNode.IROpcode.GT;
		else if (parts[1].equals(">="))
			conditionNode.Opcode = IRNode.IROpcode.LT;
	}
	
	public static void genIfLabels(SemanticNode ifNode) {
		int listLen = ifNode.bodyThenList.size();
		String outLabel = "label"+Integer.toString(label);
		ifNode.bodyThenList.getFirst().SemanticList.getFirst().condition.Result = outLabel;
		ifNode.bodyThenList.getFirst().SemanticList.getFirst().jumpOutStart.Result = outLabel;
		for (int i = 0; i < listLen; i++) {
			SemanticNode tempNode = ifNode.bodyThenList.get(i).SemanticList.getFirst();
			tempNode.jumpOutStart.Opcode = IRNode.IROpcode.JUMP;
			tempNode.jumpOutStart.Result = outLabel;
			tempNode.elseOutLabel.Result = "label"+Integer.toString(label + i);
			tempNode.elseOutLabel.Opcode = IRNode.IROpcode.LABEL;
			tempNode.condition.Result = "label"+Integer.toString(label + i + 1);
			if (i == listLen - 1) {
				tempNode.condition.Result = outLabel;
			}
			else if (i == 0) {
				tempNode.elseOutLabel.Opcode = null;
				tempNode.outStartLabel.Opcode = IRNode.IROpcode.LABEL;
				tempNode.outStartLabel.Result = outLabel;
			}
		}
		label += listLen;
	}
	
	public static void addendIF() {
		popTree();
		SemanticNode parentIf = getCurrentTree().SemanticList.getLast();
		genIfLabels(parentIf);
	}
	
	public static void addAssignment(String id, String expr) {
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

	public static void addRead(String idList) {
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

	public static void addWrite(String idList) {
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
