
public class SemanticNode {
	public enum SemanticType {
		BASE,
		IF,
		BODY,
		WHILE
	}
	public SemanticType type;
	public IRList IRNodes = null;
	public IRNode elseOutLabel = null; //else for if, out for while
	public IRNode outStartLabel = null;// out for if, start for while
	public IRNode condition = null;
	public IRNode jumpOutStart = null;//out for if, start for while
	public SemanticActionTree bodyThenList = null;
	public SemanticActionTree elseList = null;
	
	public SemanticNode(SemanticType type) {
		this.type = type;
	}
}
