import java.util.*;

public class WhileNode extends HeadNode {
	public List<HeadNode> headNodes = new ArrayList<HeadNode>();
	public ConditionSetUp conditionSetUp = new ConditionSetUp();
	//public IRNode condition = new IRNode();
	public IRNode labelTop = new IRNode();
	
	public WhileNode() {
		SemanticHandler.getCurrentList().add(this);
		SemanticHandler.pushList(this.headNodes);
		SemanticHandler.conditionSetUp = this.conditionSetUp;
	}
	
	public void printNode() {
		conditionSetUp.leftSetUp.printNode();
		conditionSetUp.rightSetUp.printNode();
		
		labelTop.printNode();
		for (HeadNode node : headNodes) {
			node.printNode();
		}
		conditionSetUp.condition.printNode();
	}
	
	
}
