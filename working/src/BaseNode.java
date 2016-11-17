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
}
