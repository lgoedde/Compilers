import java.util.*;




public class SemanticHandler {
	public static Stack<List<HeadNode>> SemanticStack = new Stack<List<HeadNode>>();
	public static List<IRNode> currentIRList;
	public List<HeadNode> rootList;// = new ArrayList<HeadNode>();
	public static BaseNode currentBaseNode;

	public static List<Function> FunctionList = new ArrayList<Function>(); // Iterate through to print each functions semantic handler
	public static Function currentFunction;
	public static ConditionSetUp conditionSetUp;
	
	public static List<IRNode> functionIRNodes = new ArrayList<IRNode>();

	public static IRNode.IROpcode lastOpCode;

	private static int label = 0;
	public static String expr1;
	public static String expr2;
	public static String compop;
	
	public static List<String> globalVars = new ArrayList<String>();
	
	public static int nodenum = 0;
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
	
	public static IRNode findTarget(String name) {
		for (IRNode temp : functionIRNodes)  {
			if (temp.Result != null && temp.Opcode == IRNode.IROpcode.LABEL && temp.Result.equals(name)) {
				return temp;
			}
		}
		return null;
	}
	
	
	public static void genCFG() {
		int listSize = functionIRNodes.size();
		
		
		IRNode succ;
		
		for (int i = 0; i < listSize; i++) {
			IRNode curr = functionIRNodes.get(i);
			switch (curr.Opcode) {
			case GT :
			case GE :
			case LT :
			case LE :
			case NE :
			case EQ :
				// Taken branch
				succ = functionIRNodes.get(i + 1);
				curr.succ.add(i + 1);
				succ.prec.add(i);
				// Not taken branch
				succ = findTarget(curr.Result);
				succ.prec.add(i);
				curr.succ.add(functionIRNodes.indexOf(succ));
				break;
			case JUMP :
				succ = findTarget(curr.Result);
				succ.prec.add(i);
				curr.succ.add(functionIRNodes.indexOf(succ));
				break;
			case RET :
				curr.succ.clear();
				break;
			default :
				curr.succ.add(i + 1);
				succ = functionIRNodes.get(i + 1);
				succ.prec.add(i);
				break;
			}
		
		}
	}
	
	
	public static List<String> getInSet(List<String> outSet, String kill, String gen1, String gen2) {
		List<String> result = new ArrayList<String>();
		for (String val : outSet) {
			result.add(val);
		}
		
		if (gen1 != null && gen1.equals("$GLOBAL")) {
			for (String val : globalVars) {
				addUnique(result,val);
			}
			return result;
		}
		
		if (kill != null)
			result.remove(kill);
		addUnique(result,gen1);
		addUnique(result,gen2);
		return result;
	}
	
	public static boolean updateIn(IRNode node) {
		List<String> newList = null;
		switch (node.Opcode) {
		case STOREI :
		case STOREF :
			newList = getInSet(node.liveOut,node.Result,node.Op1,null);
			break;
		case ADDI :
		case ADDF :
		case SUBI :
		case SUBF :
		case MULTI :
		case MULTF :
		case DIVI :
		case DIVF :
			newList = getInSet(node.liveOut,node.Result,node.Op1,node.Op2);
			break;
		case READI :
		case READF :
			newList = getInSet(node.liveOut,node.Result,null,null);
			break;
		case WRITES :
		case WRITEI :
		case WRITEF :
			newList = getInSet(node.liveOut,null,node.Result,null);
			break;
		case GT :
		case GE :
		case LT :
		case LE :
		case NE :
		case EQ :
			newList = getInSet(node.liveOut,null,node.Op1,node.Op2);
			break;
		case JUMP :
		case LABEL :
			newList = getInSet(node.liveOut,null,null,null);
			break;
		case PUSH :
			newList = getInSet(node.liveOut,null,node.Op1,null);
			break;
		case POP :
			newList = getInSet(node.liveOut,node.Result,null,null);
			break;
		case JSR :
			newList = getInSet(node.liveOut,null,null,null);
			break;
		case LINK :
		case RET :
			newList = getInSet(node.liveOut,null,null,null);
			break;
		}
		
		for (String val : newList) {
			if (! node.liveIn.contains(val)) {
				node.liveIn = newList;
				return true;
			}
		}
		if (newList.size() != node.liveIn.size()) {
			node.liveIn = newList;
			return true;
		}
		return false;
	}
	
	public static void updateOut(IRNode node) {
		if (node.Opcode == IRNode.IROpcode.RET)
			return;
		node.liveOut.clear();
		for (int i : node.succ) {
			IRNode successor = functionIRNodes.get(i);
			updateIn(successor);
			for (String val : successor.liveIn) {
				addUnique(node.liveOut, val);
			}
		}
		boolean changed = updateIn(node);
		if (changed || node.liveIn.size() == 0) {
			for (int precessor : node.prec) {
				updateOut(functionIRNodes.get(precessor));
			}
		}
		
	}
	
	public static void Liveness() {
		
		for (IRNode node : functionIRNodes) {
			if (node.Opcode == IRNode.IROpcode.RET) {
				node.liveIn.clear();
				node.liveOut.clear();
				updateOut(functionIRNodes.get(node.prec.get(0)));
			}
		}
	}
	
	public static void copyList(List<String> src, List<String> dest) {
		if (src == null || dest == null)
			return;
		for (String val : src) {
			dest.add(val);
		}
		
	}
	
	public static void addUnique(List<String> list,String value) {
		if (value == null)
			return;
		if (isNumeric(value))
			return;
		
		for (String val : list) {
			if (val.equals(value)) {
				return;
			}
		}
		list.add(value);
		return;
	}
	
	public static boolean isNumeric(String s) {
    	return s.matches("[-+]?\\d*\\.?\\d+");
	}

	public static void genFunctionList(HeadNode node) {
		if (node instanceof BaseNode) {
			for (IRNode irNode : ((BaseNode) node).NodeList) {
				if (irNode.Opcode != null)
					functionIRNodes.add(irNode);
			}
		}
		else if (node instanceof IfNode) {
			for (IfBodyNode bodyNode : ((IfNode) node).ifBodyList) {
				genFunctionList(bodyNode);
			}
			if (((IfNode) node).outLabel.Opcode != null)
				functionIRNodes.add(((IfNode) node).outLabel);
		}
		else if (node instanceof IfBodyNode) {
			if (((IfBodyNode) node).label.Opcode != null)
				functionIRNodes.add(((IfBodyNode)node).label);
			if (((IfBodyNode)node).conditionSetUp.leftSetUp != null) {
				for (IRNode irNode : ((IfBodyNode)node).conditionSetUp.leftSetUp.NodeList) {
					if (irNode.Opcode != null)
						functionIRNodes.add(irNode);
				}
			}
			if (((IfBodyNode)node).conditionSetUp.rightSetUp != null) {
				for (IRNode irNode : ((IfBodyNode)node).conditionSetUp.rightSetUp.NodeList) {
					if (irNode.Opcode != null)
						functionIRNodes.add(irNode);
				}
			}
			if (((IfBodyNode) node).conditionSetUp.condition.Opcode != null)
				functionIRNodes.add(((IfBodyNode)node).conditionSetUp.condition);
			for (HeadNode headNode : ((IfBodyNode) node).headNodes) {
				genFunctionList(headNode);
			}
			if (((IfBodyNode) node).jumpOut.Opcode != null)
				functionIRNodes.add(((IfBodyNode)node).jumpOut);

		}
		else if (node instanceof WhileNode) {
			if (((WhileNode) node).conditionSetUp.leftSetUp != null) {
			for (IRNode irNode : ((WhileNode)node).conditionSetUp.leftSetUp.NodeList) {
				if (irNode.Opcode != null)
				functionIRNodes.add(irNode);
			}
			}
			if (((WhileNode) node).conditionSetUp.rightSetUp != null) {
			for (IRNode irNode : ((WhileNode)node).conditionSetUp.rightSetUp.NodeList) {
				if (irNode.Opcode != null)
				functionIRNodes.add(irNode);
			}
			}
			if (((WhileNode) node).labelTop.Opcode != null)
			functionIRNodes.add(((WhileNode) node).labelTop);
			for (HeadNode headNode : ((WhileNode) node).headNodes) {
				genFunctionList(headNode);
			}
			if (((WhileNode) node).conditionSetUp.condition.Opcode != null)
			functionIRNodes.add(((WhileNode)node).conditionSetUp.condition);
			//TinyGeneration.printTiny(((WhileNode)node).jumpTop);
		}

	}

	public static void printIRCode() {
		System.out.println(";IR code");


		for(Function func : FunctionList) {
			for (HeadNode node : func.semanticHandler.rootList) {
				node.printNode();
			}
			if (lastOpCode != IRNode.IROpcode.RET) {
				//BaseNode temp = new BaseNode(2);
				//temp.NodeList.add(new IRNode(IRNode.IROpcode.RET,null,null,null));
				//func.semanticHandler.rootList.add(temp);
				System.out.println(";RET");
			}
			System.out.println("");
		}
		System.out.println(";tiny code");
		HashMap<String,Symbol> globals = Function.symbolLookUp.pop();
		for (String key : globals.keySet()) {
			globalVars.add(key);
			Symbol temp = globals.get(key);
			if (temp.type.equals("STRING"))
				System.out.println("str "+key+" "+temp.value);
			else
				System.out.println("var "+key);
		}
		System.out.println("push");
		System.out.println("push r0");
		System.out.println("push r1");
		System.out.println("push r2");
		System.out.println("push r3");
		System.out.println("jsr main");
		System.out.println("sys halt");

		//Iterate through each function
		for(Function func : FunctionList) {
			TinyGeneration.paramLength = Function.ParamLookUp.get(func.name);
			TinyGeneration.numLocals = Function.LocalLookUp.get(func.name);
			TinyGeneration.numTemps = Function.TempLookUp.get(func.name);
			TinyGeneration.resetRegisterStack();
			functionIRNodes.clear();
			for (HeadNode node : func.semanticHandler.rootList) {
				genFunctionList(node);
			}
			if (functionIRNodes.get(functionIRNodes.size() - 1).Opcode != IRNode.IROpcode.RET)
				functionIRNodes.add(new IRNode(IRNode.IROpcode.RET,null,null,null));
			genCFG();
			nodenum = 0;
			Liveness();
			
			for (IRNode node : functionIRNodes) {
				//node.printNode();
				TinyGeneration.printTiny(node);
			}
			
			
			
			
		}
		int listSize = TinyGeneration.TinyList.size();
		for (int i = 0; i < listSize; i++)
		{
			TinyGeneration.TinyList.get(i).printInstr();
		}
		System.out.println("end");
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
		conditionNode.typeBranch = BaseNode.condType;
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
	
	public static void setWhile() {
		List<HeadNode> temp = SemanticStack.pop();
		conditionSetUp = ((WhileNode)SemanticHandler.getCurrentList().get(SemanticHandler.getCurrentList().size() - 1)).conditionSetUp;
		SemanticStack.push(temp);
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
