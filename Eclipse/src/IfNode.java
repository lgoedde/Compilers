import java.util.*;

public class IfNode extends HeadNode {
	public List<IfBodyNode> ifBodyList = new ArrayList<IfBodyNode>();
	public IRNode outLabel = new IRNode();
	public IRNode jumpOut = new IRNode();
	
	public IfNode() {
		SemanticHandler.getCurrentList().add(this);
	}
	
	public void printNode() {
		for (IfBodyNode node : ifBodyList) {
			node.printNode();
		}
		outLabel.printNode();
	}
}
