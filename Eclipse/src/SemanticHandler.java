import java.util.*;



public class SemanticHandler {
	public static Stack<List<HeadNode>> SemanticStack = new Stack<List<HeadNode>>();
	public static List<IRNode> currentIRList;
	public static List<HeadNode> rootList = new ArrayList<HeadNode>();
	
	
	
	//public static Stack<SemanticActionTree> SemanticStack = new Stack<SemanticActionTree>();
	
	private static int label = 0;
	public static String expr1;
	public static String expr2;
	public static String compop;
	public SemanticHandler() {
		
	}
	
	public static List<HeadNode> getCurrentList() {
		return SemanticStack.peek();
	}

	
	public static void pushList(List<HeadNode> list) {
		SemanticStack.push(list);
	}
	
	public static void popList() {
		SemanticStack.pop();
	}
	
	public static void printOffset(int num) {
		for (int i = 0; i < num; i++) {
			System.out.print("\t");
		}
	}
	
	public static void printFriendly(HeadNode node, int offset) {
		printOffset(offset);
		if (node instanceof BaseNode) {
			System.out.print("Base - Length: ");
			int len = ((BaseNode) node).NodeList.size();
			System.out.print(Integer.toString(len)+"\n");
			for (IRNode irNode : ((BaseNode) node).NodeList) {
				printOffset(offset);
				irNode.printNode();
			}
		}
		else if (node instanceof IfNode) {
			System.out.print("If - IF/Else's: ");
			int len = ((IfNode) node).ifBodyList.size();
			System.out.print(Integer.toString(len)+"\n");
			for (IfBodyNode bodyNode : ((IfNode) node).ifBodyList) {
				printFriendly(bodyNode, offset + 1);
			}
		}
		else if (node instanceof IfBodyNode) {
			System.out.print("IFBody - Length: ");
			int len = ((IfBodyNode) node).headNodes.size();
			System.out.print(Integer.toString(len)+"\n");
			for (HeadNode headNode : ((IfBodyNode) node).headNodes) {
				printFriendly(headNode,offset + 1);
			}
		}
		
		
		/*for (int i = 0; i < sizeTree; i++) {
			SemanticNode tempNode = tree.SemanticList.get(i);
			System.out.print("\n");
			for (int k = 0; k < offset; k++) {
				System.out.print("\t");
			}
			
			System.out.print(tempNode.type);
			if (tempNode.type == SemanticNode.SemanticType.IF) {
				int sizeElse = tempNode.bodyThenList.size();
				System.out.print("Length: "+Integer.toString(tempNode.bodyThenList.size()));
				for (int j = 0; j < sizeElse; j++) {
					printfriendly(tempNode.bodyThenList.get(j),offset + 1);
				}
			}
			else if (tempNode.type == SemanticNode.SemanticType.ELSEIF) {
				int sizeElse = tempNode.bodyThenList.size();
				System.out.print("Length: "+Integer.toString(tempNode.bodyThenList.size()));
				for (int j = 0; j < sizeElse; j++) {
					printfriendly(tempNode.bodyThenList.get(j),offset + 1);
				}
			}
			
			
		}*/
	
	}
	
	public static void printIRCode() {
		System.out.println("SemanticList:");
		for (HeadNode node : rootList) {
			printFriendly(node,0);
		}
		System.out.println("");
		for (HeadNode node : rootList) {
			node.printNode();
		}
	}
	
	public static IfNode getParentIf() {
		return (IfNode)SemanticHandler.getCurrentList().get(SemanticHandler.getCurrentList().size() - 1);
	}
	
	public static void addIF(IfBodyNode node) {
		// gen condition
		
		//genCondition(newIf.condition);
		//newIf.bodyThenList.add(newTree);
		//SemanticStack.push(newTree);
		//SemanticHandler.getCurrentTree().addNode(newIf);
	}
	
	
	public static void addIfBody() {
		//LinkedList<Object> test;
		//popTree();
		//SemanticNode parentIf = getCurrentTree().SemanticList.getLast();
		//SemanticActionTree newTree = new SemanticActionTree();
		//SemanticNode newelseIf = new SemanticNode(SemanticNode.SemanticType.ELSEIF); // adding new node adds it to base tree
		//newelseIf.jumpOutStart = parentIf.jumpOutStart;
		//genCondition(newelseIf.condition);
		//SemanticStack.push(newTree);
		//SemanticHandler.getCurrentTree().addNode(newelseIf);
		//parentIf.bodyThenList.add(newTree);
		
		
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
	
	public static void genIfLabels(IfNode ifNode) {
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
		popList();
		IfNode parentIf = getParentIf();
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
