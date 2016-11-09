import java.util.*;

public class WhileNode extends HeadNode {
	public List<HeadNode> headNodes = new ArrayList<HeadNode>();
	public List<IRNode> conditionSetUp = new ArrayList<IRNode>();
	public IRNode condition = new IRNode();
	public IRNode labelTop = new IRNode();
	//public IRNode labelOut = new IRNode();
	//public IRNode jumpTop = new IRNode();
	
	
	public WhileNode() {
		SemanticHandler.getCurrentList().add(this);
		SemanticHandler.pushList(this.headNodes);
	}
	
	public void printNode() {
		for (IRNode node : conditionSetUp) {
			node.printNode();
		}
		labelTop.printNode();
		for (HeadNode node : headNodes) {
			node.printNode();
		}
		condition.printNode();
		//jumpTop.printNode();
	}
}
