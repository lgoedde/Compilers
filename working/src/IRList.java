import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;

public class IRList {
	public LinkedList<IRNode> NodeList = new LinkedList<IRNode>();

	public IRList() {
	}

	public void addNode(IRNode node) {
		if (node != null)
			this.NodeList.add(node);
	}

	public void printList() {
		//System.out.println(";IR code");
		int listSize = this.NodeList.size();
		for (int i = 0; i < listSize; i++) 
		{
			this.NodeList.get(i).printNode();
		}
	}


	
}