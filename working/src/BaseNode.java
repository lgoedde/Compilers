import java.util.ArrayList;
import java.util.List;


public class BaseNode extends HeadNode {
	public List<IRNode> NodeList = new ArrayList<IRNode>();
	public BaseNode() {	
		SemanticHandler.getCurrentList().add(this);
		SemanticHandler.currentIRList = this.NodeList;
	}
	
	public void printNode() {
		for (IRNode node : NodeList) {
			node.printNode();
		}
	}
}
