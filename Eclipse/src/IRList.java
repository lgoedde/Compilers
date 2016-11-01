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


	public void addAssignment(String id, String expr) {
		String simplified = ExpressionEval.SimplifyExpression(expr);
		String type = ExpressionEval.getType(simplified,"0");
		String result = ExpressionEval.checkOps(simplified, "").get(0);
		if (type.equals("FLOAT")) {
			this.NodeList.add(new IRNode(IRNode.IROpcode.STOREF," "+result," ",id));
		}
		else {
			this.NodeList.add(new IRNode(IRNode.IROpcode.STOREI," "+result," ",id));

		}

	}

	public void addRead(String idList) {
		String type;
		if (idList.indexOf(',') != -1) {
			String[] parts = idList.split(",");
			for (String part : parts) {
				if ((type = SymbolTable.getSymbolType(part)) != "") {
					if (type.equals("INT")) {
						IRNode tempNode = new IRNode(IRNode.IROpcode.READI," ","",part);
						NodeList.add(tempNode);
					}
					else if(type.equals("FLOAT")) {
						IRNode tempNode = new IRNode(IRNode.IROpcode.READF," ","",part);
						NodeList.add(tempNode);
					}
				}
				else {
					// USING VARIABLE NOT DECLARED
				}
			}
		}
		else {
			if ((type = SymbolTable.getSymbolType(idList)) != "") {
				if (type.equals("INT")) {
					IRNode tempNode = new IRNode(IRNode.IROpcode.READI," ","",idList);
					NodeList.add(tempNode);
				}
				else if(type.equals("FLOAT")) {
					IRNode tempNode = new IRNode(IRNode.IROpcode.READF," ","",idList);
					NodeList.add(tempNode);
				}
			}
			else {
				// USING VARIABLE NOT DECLARED
			}
		}
	}

	public void addWrite(String idList) {
	String type;
		if (idList.indexOf(',') != -1) {
			String[] parts = idList.split(",");
			for (String part : parts) {
				if ((type = SymbolTable.getSymbolType(part)) != "") {
					if (type.equals("INT")) {
						IRNode tempNode = new IRNode(IRNode.IROpcode.WRITEI," ","",part);
						NodeList.add(tempNode);
					}
					else if(type.equals("FLOAT")) {
						IRNode tempNode = new IRNode(IRNode.IROpcode.WRITEF," ","",part);
						NodeList.add(tempNode);
					}
				}
				else {
					// USING VARIABLE NOT DECLARED
				}
			}
		}
		else {
			if ((type = SymbolTable.getSymbolType(idList)) != "") {
				if (type.equals("INT")) {
					IRNode tempNode = new IRNode(IRNode.IROpcode.WRITEI," ","",idList);
					NodeList.add(tempNode);
				}
				else if(type.equals("FLOAT")) {
					IRNode tempNode = new IRNode(IRNode.IROpcode.WRITEF," ","",idList);
					NodeList.add(tempNode);
				}
			}
			else {
				// USING VARIABLE NOT DECLARED
			}
		}
	}
}