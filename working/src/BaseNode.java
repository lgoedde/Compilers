import java.util.*;


public class BaseNode extends HeadNode {
	public List<IRNode> NodeList = new ArrayList<IRNode>();
	public Stack<stackVal> valueStack = new Stack<stackVal>();
	public Stack<String> opStack = new Stack<String>();
	public String lastRegVal;
	
	public BaseNode() {	
		SemanticHandler.getCurrentList().add(this);
		SemanticHandler.currentBaseNode = this;
		//SemanticHandler.currentIRList = this.NodeList;
	}
	
	public BaseNode(int a) {
		SemanticHandler.currentBaseNode = this;
		if (a == 1) {
			SemanticHandler.conditionSetUp.leftSetUp = this;
		}
		else {
			SemanticHandler.conditionSetUp.rightSetUp = this;
		}
	}
	
	public void printNode() {
		for (IRNode node : NodeList) {
			node.printNode();
		}
	}
	
	public void addInt(String value) {
		//System.out.println("int: "+value);
		this.valueStack.push(new stackVal("INT",value));
	}
	
	public void addFloat(String value) {
		//System.out.println("float: "+value);
		this.valueStack.push(new stackVal("FLOAT",value));
	}
	
	public void addId(String value) {
		//System.out.println("id: "+value);
		String type = null;
		Symbol temp = Function.getSymbol(value);
		if (temp != null) {
			type = temp.type;
			value = temp.irReg;
		}
		else {
			System.out.println("Using variable not in scope!");
			System.exit(1);
		}
		this.valueStack.push(new stackVal(type,value));
	}
	
	public void evalOp(String op) {
		stackVal val2 = this.valueStack.pop();
		val2.value = checkVal(val2.value,val2.type);
		stackVal val1 = this.valueStack.pop();
		val1.value = checkVal(val1.value,val1.type);
		String resType = val1.type.equals("FLOAT") || val2.type.equals("FLOAT") ? "FLOAT" : "INT";
		String resReg = Function.GetNextReg(resType);
		if (op.equals("*")) {
			if (resType.equals("FLOAT"))
				NodeList.add(new IRNode(IRNode.IROpcode.MULTF,val1.value,val2.value,resReg));
			else
				NodeList.add(new IRNode(IRNode.IROpcode.MULTI,val1.value,val2.value,resReg));
		}
		else if  (op.equals("/")) {
			if (resType.equals("FLOAT"))
				NodeList.add(new IRNode(IRNode.IROpcode.DIVF,val1.value,val2.value,resReg));
			else
				NodeList.add(new IRNode(IRNode.IROpcode.DIVI,val1.value,val2.value,resReg));
		}
		else if (op.equals("+")) {
			if (resType.equals("FLOAT"))
				NodeList.add(new IRNode(IRNode.IROpcode.ADDF,val1.value,val2.value,resReg));
			else
				NodeList.add(new IRNode(IRNode.IROpcode.ADDI,val1.value,val2.value,resReg));
		}
		else if (op.equals("-")) {
			if (resType.equals("FLOAT"))
				NodeList.add(new IRNode(IRNode.IROpcode.SUBF,val1.value,val2.value,resReg));
			else
				NodeList.add(new IRNode(IRNode.IROpcode.SUBI,val1.value,val2.value,resReg));
		}
		else {
			System.out.println("Weird op for evalOp");
			return;
		}
		valueStack.push(new stackVal(resType,resReg));
	}
	
	public String checkVal(String value, String type) {
		String newVal = value;
		if (isNumeric(value)) {
			newVal = Function.GetNextReg(type);
			IRNode tempNode = new IRNode(null,value,null,newVal);
			if (type.equals("INT")) {
				tempNode.Opcode = IRNode.IROpcode.STOREI;
			}
			else {
				tempNode.Opcode = IRNode.IROpcode.STOREF;
			}
			NodeList.add(tempNode);
		}
		return newVal;
	}
	
	public boolean isNumeric(String s) {  
    	return s.matches("[-+]?\\d*\\.?\\d+");  
	}
	
	public void addOp(String op) {
		//System.out.println("op: "+op);
		if (op.equals(")")) {
			// Eval Parenthesis
			String opPop;
			while (true) {
				opPop = this.opStack.pop();
				if (opPop.equals("("))
					break;
				evalOp(opPop);
			}
		}
		else if (op.equals("(")) {
			// ALL GOOD
			this.opStack.push(op);
		}
		else if (op.equals(",")) {
			// ALL GOOD
			this.opStack.push(op);
		}
		else { // + - * /
			if (this.opStack.size() != 0) {
				int thisPrec = getPrec(op);
				while (this.opStack.size() > 0 && getPrec(this.opStack.peek()) >= thisPrec) {
					evalOp(this.opStack.pop());
				}
			}
			this.opStack.push(op);
			
		}
		
	}
	
	public void startFunc(String id) {
		//System.out.println("funct start: "+id);
		this.opStack.push(id);
	}
	
	public void endFunc() { // do not need to convert nums to regs for paramters - push can take nums
		//System.out.println("funct end");
		Stack<stackVal> paramList = new Stack<stackVal>();
		List<String> typeList = new ArrayList<String>();
		String currOp;
		stackVal currParam;
		while (true) {
			currOp = this.opStack.pop();
			if ((int)currOp.charAt(0) >= 65 && (int)currOp.charAt(0) <= 122) {
				currParam = this.valueStack.pop();
				paramList.push(currParam);
				//MAKE FUNCTION CALL WITH CURRENT PARAM LIST, AND PUT RESULT ON VALUE STACK
				NodeList.add(new IRNode(IRNode.IROpcode.PUSH,null,null,null));
				String param;
				stackVal temp;
				while (!paramList.empty()) {
					temp = paramList.pop();
					typeList.add(temp.type);
					NodeList.add(new IRNode(IRNode.IROpcode.PUSH,temp.value,null,null));
				}
				NodeList.add(new IRNode(IRNode.IROpcode.JSR,null,null,currOp));
				NodeList.add(new IRNode(IRNode.IROpcode.POP,null,null,null));
				
				param = Function.GetNextReg(Function.TableLookUp.get(currOp).retType);
				NodeList.add(new IRNode(IRNode.IROpcode.POP,null,null,param));
				this.valueStack.push(new stackVal(Function.TableLookUp.get(currOp).retType,param));
				break;
			}
			else if (currOp.equals(",")) {
				// Add param to list
				currParam = this.valueStack.pop();
				paramList.push(currParam);
			}
			else {
				// Eval param
				evalOp(currOp);
			}
		}
		
	}
	
	public void finishBase(String id) { // id is null if return statement, otherwise id for assign
		//parse through stacks and build IR list
		//System.out.println("done: "+id);
		while (!this.opStack.empty()) {
			evalOp(this.opStack.pop());
		}
		if (this.valueStack.size() > 1) {
			System.out.println("More than 1 element in value stack?");
		}
		else if (this.valueStack.size() == 0) {
			System.out.println("Error, no values in value stack when done");
		}
		stackVal lastVal = this.valueStack.pop();
		lastVal.value = checkVal(lastVal.value, lastVal.type);
		String result;
		
		if (id == null) { //RETURN STATEMENT
			result = "$R";
		}
		else if (isNumeric(id)) {
			lastRegVal = lastVal.value; 
			if (Integer.parseInt(id) == 1) {
				SemanticHandler.expr1 = lastVal.value;
			}
			else {
				SemanticHandler.expr2 = lastVal.value;
			}
			//expr num
			return;
		}
		else { //ASSIGN
			result = Function.getSymbol(id).irReg; 
		}
		
		if (lastVal.type.equals("FLOAT")) {
			NodeList.add(new IRNode(IRNode.IROpcode.STOREF,lastVal.value,null,result));
		}
		else {
			NodeList.add(new IRNode(IRNode.IROpcode.STOREI,lastVal.value,null,result));
		}
		this.valueStack = null;
		this.opStack = null;
	}
	
	public void addRead(String idList) {
		
	}
	
	public void addWrite(String idList) {
		
	}
	
	public int getPrec(String op) {
		if (op.equals("*") || op.equals("/")) {
			return 2;
		}
		else if (op.equals("+") || op.equals("-"))
			return 1;
		else
			return 0;
	}
	

	public class stackVal
	{
		public String type;
		public String value;
		public stackVal(String type, String value) {
			this.type = type;
			this.value = value;
		}
	}
	

}
