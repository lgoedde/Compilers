import java.util.*;



public class SemanticHandler {
	public static Stack<List<HeadNode>> SemanticStack = new Stack<List<HeadNode>>();
	public static List<IRNode> currentIRList;
	public List<HeadNode> rootList;// = new ArrayList<HeadNode>();
	public static BaseNode currentBaseNode;
	
	public static List<Function> FunctionList = new ArrayList<Function>(); // Iterate through to print each functions semantic handler
	public static Function currentFunction;
	public static ConditionSetUp conditionSetUp;	
	
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
				System.out.print(" ");
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
		else if (node instanceof WhileNode) {
			System.out.print("While - Length: ");
			int len = ((WhileNode) node).headNodes.size();
			System.out.print(Integer.toString(len)+"\n");
			for (HeadNode headNode : ((WhileNode) node).headNodes) {
				printFriendly(headNode,offset + 1);
			}
		}
	
	}
	
	public static void printTinyCode(HeadNode node) {/*
		if (node instanceof BaseNode) {
			for (IRNode irNode : ((BaseNode) node).NodeList) {
				TinyGeneration.printTiny(irNode);
			}
		}
		else if (node instanceof IfNode) {
			for (IfBodyNode bodyNode : ((IfNode) node).ifBodyList) {
				printTinyCode(bodyNode);
			}
			TinyGeneration.printTiny(((IfNode) node).outLabel);
		}
		else if (node instanceof IfBodyNode) {
			TinyGeneration.printTiny(((IfBodyNode) node).label);
			for (IRNode irNode : ((IfBodyNode)node).conditionSetUp) {
				TinyGeneration.printTiny(irNode);
			}
			TinyGeneration.printTiny(((IfBodyNode)node).condition);
			for (HeadNode headNode : ((IfBodyNode) node).headNodes) {
				printTinyCode(headNode);
			}
			TinyGeneration.printTiny(((IfBodyNode)node).jumpOut);
			
		}
		else if (node instanceof WhileNode) {
			for (IRNode irNode : ((WhileNode)node).conditionSetUp) {
				TinyGeneration.printTiny(irNode);
			}
			TinyGeneration.printTiny(((WhileNode) node).labelTop);
			for (HeadNode headNode : ((WhileNode) node).headNodes) {
				printTinyCode(headNode);
			}
			TinyGeneration.printTiny(((WhileNode)node).condition);
			//TinyGeneration.printTiny(((WhileNode)node).jumpTop);
		}
	*/	
	}
	
	public static void printIRCode() {
		System.out.println(";IR code");
		
		for(Function func : FunctionList) {
			for (HeadNode node : func.semanticHandler.rootList) {
				node.printNode();
			}
			System.out.println("");
		}
		
		
		
		
		
		/*for (HeadNode node : rootList) {
			node.printNode();
		}
		
		TinyGeneration.resetRegisterStack();
		System.out.println(";tiny code");
		for (Symbol symbol : SymbolTable.globalScope.symbolTable) {
			if (symbol.type.equals("STRING"))
				System.out.println("str "+symbol.identifier);
			else
				System.out.println("var "+symbol.identifier);
		}
		for (HeadNode node : rootList) {
			printTinyCode(node);
		}
		int listSize = TinyGeneration.TinyList.size();
		for (int i = 0; i < listSize; i++) 
		{
			TinyGeneration.TinyList.get(i).printInstr();
		}
		System.out.println("sys halt");*/
		
	}
	
	public static IfNode getParentIf() {
		return (IfNode)SemanticHandler.getCurrentList().get(SemanticHandler.getCurrentList().size() - 1);
	}
	
	
	public static void genCondition(IRNode conditionNode, boolean ifStmt) {
		if (expr1.equals("TRUE")) {
			if (ifStmt) {
				conditionNode.Opcode = null;
			}
			else {
				conditionNode.Opcode = IRNode.IROpcode.JUMP;
			}
			return;
		}
			
		if (expr1.equals("FALSE")) {
			if (ifStmt) {
				conditionNode.Opcode = IRNode.IROpcode.JUMP;
			}
			else {
				conditionNode.Opcode = null;
			}
			return;
		}
			
		conditionNode.Op1 = expr1;
		conditionNode.Op2 = expr2;
		if (compop.equals("<")) {
			if (ifStmt) {
				conditionNode.Opcode = IRNode.IROpcode.GE;
			}
			else {
				conditionNode.Opcode = IRNode.IROpcode.LT;
			}
		}
		else if (compop.equals(">")) {
			if (ifStmt) {
				conditionNode.Opcode = IRNode.IROpcode.LE;	
			}
			else {
				conditionNode.Opcode = IRNode.IROpcode.GT;
			}
		}
		else if (compop.equals("=")){
			if (ifStmt) {
				conditionNode.Opcode = IRNode.IROpcode.NE;
			}
			else {
				conditionNode.Opcode = IRNode.IROpcode.EQ;
			}
		}
		else if (compop.equals("!=")){
			if (ifStmt) {
				conditionNode.Opcode = IRNode.IROpcode.EQ;
			}
			else {
				conditionNode.Opcode = IRNode.IROpcode.NE;
			}
		}
		else if (compop.equals("<=")){
			if (ifStmt) {
				conditionNode.Opcode = IRNode.IROpcode.GT;
			}
			else {
				conditionNode.Opcode = IRNode.IROpcode.LE;
			}
		}
		else if (compop.equals(">=")){
			if (ifStmt) {
				conditionNode.Opcode = IRNode.IROpcode.LT;
			}
			else {
				conditionNode.Opcode = IRNode.IROpcode.GE;
			}
		}
	}
	
	public static void genIfLabels(IfNode ifNode) {
		int listLen = ifNode.ifBodyList.size();
		String outLabel = "label"+Integer.toString(label);
		ifNode.outLabel.Opcode = IRNode.IROpcode.LABEL;
		ifNode.outLabel.Result = outLabel;
		if (listLen == 1) {
			IfBodyNode tempNode = ifNode.ifBodyList.get(0);
			tempNode.conditionSetUp.condition.Result = outLabel;
			tempNode.label.Opcode = null;
			tempNode.jumpOut.Opcode = null;
			label++;
			return;
		}
		for (int i = 0; i < listLen; i++) {
			IfBodyNode tempNode = ifNode.ifBodyList.get(i);
			tempNode.jumpOut.Opcode = IRNode.IROpcode.JUMP;
			tempNode.jumpOut.Result = outLabel;
			tempNode.label.Result = "label"+Integer.toString(label + i);
			tempNode.label.Opcode = IRNode.IROpcode.LABEL;
			tempNode.conditionSetUp.condition.Result = "label"+Integer.toString(label + i + 1);
			if (i == listLen - 1) {
				tempNode.conditionSetUp.condition.Result = outLabel;
				tempNode.jumpOut.Opcode = null;
			}
			else if (i == 0) {
				tempNode.label.Opcode = null;
			}
		}
		label += listLen;
	}
	
	public static void addendIF() {
		popList();
		IfNode parentIf = getParentIf();
		genIfLabels(parentIf);
	}
	
	public static void genWhileLabels(WhileNode node) {
		String labelTop = "label"+Integer.toString(label++);
		node.labelTop.Opcode = IRNode.IROpcode.LABEL;
		node.labelTop.Result = labelTop;
		node.conditionSetUp.condition.Result = labelTop;
	}
	
	public static void addendWhile() {
		popList();
		WhileNode whileNode = (WhileNode)SemanticHandler.getCurrentList().get(SemanticHandler.getCurrentList().size() - 1);
		//SemanticHandler.currentIRList = whileNode.conditionSetUp;
		SemanticHandler.genCondition(whileNode.conditionSetUp.condition,false);
		genWhileLabels(whileNode);
	}
	
	public static void addAssignment(String id, String expr) {
		String simplified = ExpressionEval.SimplifyExpression(expr);
		String type = ExpressionEval.getType(simplified,"0");
		String result = ExpressionEval.checkOps(simplified, "").get(0);
		if (type.equals("FLOAT")) {
			currentIRList.add(new IRNode(IRNode.IROpcode.STOREF,result,null,id));
		}
		else {
			currentIRList.add(new IRNode(IRNode.IROpcode.STOREI,result,null,id));

		}

	}

	public static void addRead(String idList) {
		String type;
		if (idList.indexOf(',') != -1) {
			String[] parts = idList.split(",");
			for (String part : parts) {
				if ((type = currentFunction.getSymbol(part).type) != "") {
					if (type.equals("INT")) {
						IRNode tempNode = new IRNode(IRNode.IROpcode.READI,null,null,part);
						currentIRList.add(tempNode);
					}
					else if(type.equals("FLOAT")) {
						IRNode tempNode = new IRNode(IRNode.IROpcode.READF,null,null,part);
						currentIRList.add(tempNode);
					}
				}
				else {
					// USING VARIABLE NOT DECLARED
				}
			}
		}
		else {
			if ((type = currentFunction.getSymbol(idList).type) != "") {
				if (type.equals("INT")) {
					IRNode tempNode = new IRNode(IRNode.IROpcode.READI,null,null,idList);
					currentIRList.add(tempNode);
				}
				else if(type.equals("FLOAT")) {
					IRNode tempNode = new IRNode(IRNode.IROpcode.READF,null,null,idList);
					currentIRList.add(tempNode);
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
				if ((type = currentFunction.getSymbol(part).type) != "") {
					if (type.equals("INT")) {
						IRNode tempNode = new IRNode(IRNode.IROpcode.WRITEI,null,null,part);
						currentIRList.add(tempNode);
					}
					else if(type.equals("FLOAT")) {
						IRNode tempNode = new IRNode(IRNode.IROpcode.WRITEF,null,null,part);
						currentIRList.add(tempNode);
					}
				}
				else {
					// USING VARIABLE NOT DECLARED
				}
			}
		}
		else {
			if ((type = currentFunction.getSymbol(idList).type) != "") {
				if (type.equals("INT")) {
					IRNode tempNode = new IRNode(IRNode.IROpcode.WRITEI,null,null,idList);
					currentIRList.add(tempNode);
				}
				else if(type.equals("FLOAT")) {
					IRNode tempNode = new IRNode(IRNode.IROpcode.WRITEF,null,null,idList);
					currentIRList.add(tempNode);
				}
			}
			else {
				// USING VARIABLE NOT DECLARED
			}
		}
	}
	
	
	

}
