import java.util.*;

public class IfBodyNode extends HeadNode {
	public List<HeadNode> headNodes = new ArrayList<HeadNode>();
	public List<IRNode> conditionSetUp = new ArrayList<IRNode>();
	public IRNode condition = new IRNode();
	public IRNode label = new IRNode();
	public IRNode jumpOut = new IRNode();
	
	public IfBodyNode(boolean first) {
		if (!first) {
			SemanticHandler.popList();
		}
		IfNode parent = SemanticHandler.getParentIf();
		parent.ifBodyList.add(this);
		//jumpOut = parent.jumpOut;
		SemanticHandler.currentIRList = conditionSetUp;
		SemanticHandler.genCondition(condition,true);
		SemanticHandler.pushList(this.headNodes);
	}
	
	public void printNode() {
		if (label != null)
			label.printNode();
		for (IRNode node : conditionSetUp) {
			node.printNode();
		}
		condition.printNode();
		for (HeadNode node : headNodes) {
			node.printNode();
		}
		
		jumpOut.printNode();
	}
	

}
