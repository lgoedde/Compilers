import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;

public class IRList {
	public static LinkedList<IRNode> NodeList = new LinkedList<IRNode>();

	public IRList() {
	}

	public static void addNode(IRNode node) {
		if (node != null)
			NodeList.add(node);
	}

	public static void printList() {
		System.out.println(";IR code");
		int listSize = getSize();
		for (int i = 0; i < listSize; i++) 
		{
			NodeList.get(i).printNode();
		}
	}

	private static int getSize() {
		return NodeList.size();
	}

	public static void addAssignment(String id, String expr) {
		System.out.print("ID: "+id);
		System.out.println("\texpr: "+expr);
		String simplified = ExpressionEval.SimplifyExpression(expr);
		NodeList.add(new IRNode("STOREI ",simplified," ","$T"+Integer.toString(ExpressionEval.regNum++)));
	}

	public static void addRead(String idList) {
		String type;
		if (idList.indexOf(',') != -1) {
			String[] parts = idList.split(",");
			for (String part : parts) {
				if ((type = SymbolTable.getSymbolType(part)) != "") {
					if (type.equals("INT")) {
						IRNode tempNode = new IRNode("READI"," ","",part);
						NodeList.add(tempNode);
					}
					else if(type.equals("FLOAT")) {
						IRNode tempNode = new IRNode("READF"," ","",part);
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
					IRNode tempNode = new IRNode("READI"," ","",idList);
					NodeList.add(tempNode);
				}
				else if(type.equals("FLOAT")) {
					IRNode tempNode = new IRNode("READF"," ","",idList);
					NodeList.add(tempNode);
				}
			}
			else {
				// USING VARIABLE NOT DECLARED
			}
		}
	}

	public static void addWrite(String idList) {
	String type;
		if (idList.indexOf(',') != -1) {
			String[] parts = idList.split(",");
			for (String part : parts) {
				if ((type = SymbolTable.getSymbolType(part)) != "") {
					if (type.equals("INT")) {
						IRNode tempNode = new IRNode("WRITEI"," ","",part);
						NodeList.add(tempNode);
					}
					else if(type.equals("FLOAT")) {
						IRNode tempNode = new IRNode("WRITEF"," ","",part);
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
					IRNode tempNode = new IRNode("WRITEI"," ","",idList);
					NodeList.add(tempNode);
				}
				else if(type.equals("FLOAT")) {
					IRNode tempNode = new IRNode("WRITEF"," ","",idList);
					NodeList.add(tempNode);
				}
			}
			else {
				// USING VARIABLE NOT DECLARED
			}
		}
	}
}