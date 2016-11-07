import java.util.*;



public class SemanticHandler {
	public static Stack<SemanticActionTree> SemanticStack = new Stack<SemanticActionTree>();
	public static IRList currentIRList;
	private static int label = 0;
	public static String expr1;
	public static String expr2;
	public static String compop;
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
	
	public static void printfriendly(SemanticActionTree tree, int offset) {
		int sizeTree = tree.SemanticList.size();
		System.out.println(Integer.toString(sizeTree));
		for (int i = 0; i < sizeTree; i++) {
			SemanticNode tempNode = tree.SemanticList.get(i);
			System.out.print("\n");
			for (int k = 0; k < offset; k++) {
				System.out.print("\t");
			}
			
			System.out.print(tempNode.type);
			if (tempNode.bodyThenList != null) {
				int sizeElse = tempNode.bodyThenList.size();
				System.out.print("Length: "+Integer.toString(tempNode.bodyThenList.size()));
				for (int j = 0; j < sizeElse; j++) {
					printfriendly(tempNode.bodyThenList.get(j),offset + 1);
				}
			}
			
			
		}
	
	}
	
	public static void printIRCode() {
		System.out.println("SemanticActionTree:");
		printfriendly(SemanticStack.peek(),0);
		System.out.println("");
		getCurrentTree().printNodes();
	}
	
	public static void addIF() {
		// gen condition
		SemanticNode newIf = new SemanticNode(SemanticNode.SemanticType.IF);
		genCondition(newIf.condition);
		newIf.bodyThenList.add(new SemanticActionTree());
		SemanticStack.push(newIf.bodyThenList.getLast());
	}
	
	
	public static void addElseIF() {
		popTree();
		SemanticNode parentIf = getCurrentTree().SemanticList.getLast();
		SemanticActionTree newTree = new SemanticActionTree();
		SemanticNode newelseIf = new SemanticNode(SemanticNode.SemanticType.ELSEIF);
		newelseIf.jumpOutStart = parentIf.jumpOutStart;
		genCondition(newelseIf.condition);
		newTree.SemanticList.add(newelseIf);
		parentIf.bodyThenList.add(newTree);
		SemanticStack.push(newTree);
		
	}
	
	public static void genCondition(IRNode conditionNode) {
		if (expr1.equals("TRUE")) {
			conditionNode.Opcode = null;
			return;
		}
			
		if (expr1.equals("FALSE")) {
			conditionNode.Opcode = IRNode.IROpcode.JUMP;
			return;
		}
			
		expr1 = ExpressionEval.SimplifyExpression(expr1);
		expr2 = ExpressionEval.SimplifyExpression(expr2);
		conditionNode.Op1 = expr1;
		conditionNode.Op2 = expr2;
		if (compop.equals("<"))
			conditionNode.Opcode = IRNode.IROpcode.GE;
		else if (compop.equals(">"))
			conditionNode.Opcode = IRNode.IROpcode.LE;	
		else if (compop.equals("="))
			conditionNode.Opcode = IRNode.IROpcode.NE;
		else if (compop.equals("!="))
			conditionNode.Opcode = IRNode.IROpcode.EQ;
		else if (compop.equals("<="))
			conditionNode.Opcode = IRNode.IROpcode.GT;
		else if (compop.equals(">="))
			conditionNode.Opcode = IRNode.IROpcode.LT;
	}
	
	public static void genIfLabels(SemanticNode ifNode) {
		int listLen = ifNode.bodyThenList.size();
		String outLabel = "label"+Integer.toString(label);
		//ifNode.bodyThenList.getFirst().SemanticList.getFirst().condition.Result = outLabel;
		//ifNode.bodyThenList.getFirst().SemanticList.getFirst().jumpOutStart.Result = outLabel;
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
