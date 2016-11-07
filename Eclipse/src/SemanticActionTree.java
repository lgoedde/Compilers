import java.io.*;
import java.util.*;

public class SemanticActionTree {
	public LinkedList<SemanticNode> SemanticList = new LinkedList<SemanticNode>();
	public SemanticActionTree() {
		
	}
	
	public void addNode(SemanticNode node) {
		if (node != null) 
			this.SemanticList.add(node);
	}
	
	public void printNodes() {
		int listSize = this.SemanticList.size();
		for (int i = 0; i < listSize; i++) 
		{
			SemanticNode tempNode = this.SemanticList.get(i);
			if (tempNode.type == SemanticNode.SemanticType.BASE) {
				if (tempNode.IRNodes == null)
					System.out.println("ERROR - IRLIST IS NULL FOR BASE");
				tempNode.IRNodes.printList();
			}
			else if (tempNode.type == SemanticNode.SemanticType.IF) {
				tempNode.condition.printNode();
				int elseSize = tempNode.bodyThenList.size();
				for (int j = 0; j < elseSize; j++) {
					tempNode.bodyThenList.get(j).printNodes();
					tempNode.jumpOutStart.printNode();
				}
				//tempNode.bodyThenList.printNodes();
				
				
				//tempNode.elseList.printNodes();
				tempNode.outStartLabel.printNode();
			}
			else if (tempNode.type == SemanticNode.SemanticType.ELSEIF) {
				tempNode.elseOutLabel.printNode();
				tempNode.condition.printNode();
				int elseSize = tempNode.bodyThenList.size();
				for (int j = 0; j < elseSize; j++) {
					tempNode.bodyThenList.get(j).printNodes();
					tempNode.jumpOutStart.printNode();
				}
			}
			else if (tempNode.type == SemanticNode.SemanticType.WHILE) {
				//tempNode.outStartLabel.printNode();
				tempNode.condition.printNode();
				//tempNode.bodyThenList.printNodes();
				tempNode.jumpOutStart.printNode();
				tempNode.elseOutLabel.printNode();
			}
			
		}
	}
}
