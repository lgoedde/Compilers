import java.util.*;

public class IfBodyNode extends HeadNode {
	public List<HeadNode> headNodes = new ArrayList<HeadNode>();
	public ConditionSetUp conditionSetUp = new ConditionSetUp();
	//public IRNode condition = new IRNode();
	public IRNode label = new IRNode();
	public IRNode jumpOut = new IRNode();
	
	public IfBodyNode(boolean first) {
		if (!first) {
			SemanticHandler.popList();
		}
		IfNode parent = SemanticHandler.getParentIf();
		parent.ifBodyList.add(this);
		//jumpOut = parent.jumpOut;
		//SemanticHandler.currentIRList = conditionSetUp;
		SemanticHandler.conditionSetUp = this.conditionSetUp;
		SemanticHandler.genCondition(conditionSetUp.condition,true);
		SemanticHandler.pushList(this.headNodes);
	}
	
	public void printNode() {
		if (label != null)
			label.printNode();
		conditionSetUp.leftSetUp.printNode();
		conditionSetUp.rightSetUp.printNode();
		conditionSetUp.condition.printNode();
		for (HeadNode node : headNodes) {
			node.printNode();
		}
		
		jumpOut.printNode();
	}
	

}
