import java.util.*;


public class BaseNode extends HeadNode {
	public List<IRNode> NodeList = new ArrayList<IRNode>();
	public Stack<String> valueStack = new Stack<String>();
	public Stack<String> opStack = new Stack<String>();
	
	public BaseNode() {	
		SemanticHandler.getCurrentList().add(this);
		SemanticHandler.currentBaseNode = this;
		SemanticHandler.currentIRList = this.NodeList;
	}
	
	public void printNode() {
		for (IRNode node : NodeList) {
			node.printNode();
		}
	}
	
	public void addVal(String value) {
		this.valueStack.push(value);
	}
	
	public void addOp(String op) {
		this.opStack.push(op);
	}
	
	public void startFunc(String id) {
		
	}
	
	public void endFunc() {
		
	}
	
	public void finishBase() {
		//parse through stacks and build IR list
		
		
		this.valueStack = null;
		this.opStack = null;
	}
	
	public int getPrec(String op) {
		if (op.equals("+") || op.equals("-"))
			return 0;
		else
			return 1;
	}
}
