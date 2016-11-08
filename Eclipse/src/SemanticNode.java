import java.util.LinkedList;


public class SemanticNode {
	public enum SemanticType {
		BASE,
		IF,
		ELSEIF,
		WHILE
	}
	public SemanticType type;
	public IRList IRNodes = null;
	public IRNode elseOutLabel = null; //else for if, out for while
	public IRNode outStartLabel = null;// out for if, start for while
	public IRNode condition = null;
	public IRNode jumpOutStart = null;//jump command for out for if, start for while
	public LinkedList<SemanticActionTree> bodyThenList = null;
	//public SemanticActionTree elseList = null;
	
	public SemanticNode(SemanticType type) {
		this.type = type;
		if (type == SemanticType.BASE) {
			IRNodes = new IRList();
			SemanticHandler.getCurrentTree().addNode(this);
			SemanticHandler.currentIRList = this.IRNodes;
		}
		else if (type == SemanticType.IF) {
			elseOutLabel = new IRNode();
			outStartLabel = new IRNode();			
			jumpOutStart = new IRNode();
			condition = new IRNode();
			bodyThenList = new LinkedList<SemanticActionTree>();
			SemanticHandler.getCurrentTree().addNode(this);
			//elseList = new SemanticActionTree();	
		}
		else if (type == SemanticType.ELSEIF) {
			elseOutLabel = new IRNode();
			outStartLabel = new IRNode();
			jumpOutStart = new IRNode();
			condition = new IRNode();
			//bodyThenList = new LinkedList<SemanticActionTree>();
			//elseList = new SemanticActionTree();	
		}
		//SemanticHandler.getCurrentTree().addNode(this);
		//SemanticHandler.currentIRList = this.IRNodes;
	}
}
