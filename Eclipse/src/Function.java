import java.util.*;

public class Function {
	public static HashMap<String,Function> TableLookUp = new HashMap<String,Function>();
	public static Stack<HashMap<String,Symbol>> symbolLookUp = new Stack<HashMap<String,Symbol>>();
	public SemanticHandler semanticHandler = new SemanticHandler();
	public int locals = 0;
	public int params = 0;
	public String retType;
	
	public static String scope;
	
	
	public static HashMap<String,String> regLookUp = new HashMap<String,String>();
	public static int tempReg = 0;
	public static int tempLocal = 0;
	public static int tempParam = 0;
	
	
	public Function(String name) {
		if (!SemanticHandler.SemanticStack.empty()) {
			SemanticHandler.SemanticStack.clear();
		}
		tempReg = 0;
		tempLocal = 0;
		tempParam = 0;
		regLookUp.clear();
		//System.out.println("Entered Function: "+name);
		SemanticHandler.currentFunction = this;
		TableLookUp.put(name, this);
		SemanticHandler.FunctionList.add(this);
		List<HeadNode> rootList = new ArrayList<HeadNode>();
		SemanticHandler.pushList(rootList);
		semanticHandler.rootList = rootList;
		symbolLookUp.push(new HashMap<String,Symbol>());
		BaseNode label = new BaseNode();
		label.NodeList.add(new IRNode(IRNode.IROpcode.LABEL,null,null,name));
		label.NodeList.add(new IRNode(IRNode.IROpcode.LINK,null,null,null));
		//test();
	}
	
	public static void endFunc() {
		BaseNode currNode = SemanticHandler.currentFunction.semanticHandler.currentBaseNode;
		int last = currNode.NodeList.size() - 1;
		if (currNode.NodeList.get(last).Opcode != IRNode.IROpcode.RET)
			currNode.NodeList.add(new IRNode(IRNode.IROpcode.RET,null,null,null));
	}
	
	public static void addGlobal() {
		symbolLookUp.push(new HashMap<String,Symbol>());
	}
	
	public static void pushBlock() {
		//System.out.println("New Block");
		symbolLookUp.push(new HashMap<String,Symbol>());
	}

	public static void popBlock() {
		//test();
		symbolLookUp.pop();
	}
	
	public static String GetNextReg(String type) {
		String reg = "$T"+Integer.toString(tempReg++);
		regLookUp.put(reg,type);
		return reg;
	}
	
	public static String getNextParam(String type) {
		String reg = "$P"+Integer.toString(tempParam++);
		regLookUp.put(reg,type);
		return reg;
	}
	
	public static String getNextLocal(String type) {
		String reg = "$L"+Integer.toString(tempLocal++);
		regLookUp.put(reg,type);
		return reg;
	}
	
	public static void addSymbol(String identifier, Symbol tsymbol) {
		List<String> ids = splitIdList(identifier);
		if (ids.size() > 1) {
			for (String id : ids) {
				if (getSymbol(id) != null) {
					System.out.println("DECLARATION ERROR " + identifier);
					System.exit(1);
				}	
				Symbol temp = tsymbol.clone();
				if (scope.equals("GLOBAL")) 
					temp.irReg = id;
				else if (scope.equals("PARAM"))
					temp.irReg = getNextParam(temp.type);
				else if (scope.equals("LOCAL"))
					temp.irReg = getNextLocal(temp.type);
				else {
					System.out.println("type not valid in add symbol");
				}
				//System.out.println("New Symbol: "+identifier);
				symbolLookUp.peek().put(identifier, temp);
			}
		}
		else {
			if (getSymbol(identifier) != null) {
				System.out.println("DECLARATION ERROR " + identifier);
				System.exit(1);
			}	
			if (scope.equals("GLOBAL")) 
				tsymbol.irReg = identifier;
			else if (scope.equals("PARAM"))
				tsymbol.irReg = getNextParam(tsymbol.type);
			else if (scope.equals("LOCAL"))
				tsymbol.irReg = getNextLocal(tsymbol.type);
			else {
				System.out.println("type not valid in add symbol");
			}
			//System.out.println("New Symbol: "+identifier);
			symbolLookUp.peek().put(identifier, tsymbol);
		}
		
		
		
		
	}
	
	public static List<String> splitIdList(String idList) {
		List<String> parts = new ArrayList<String>();
		if (idList.indexOf(',') != -1) {
			String[] partsArr = idList.split(",");
			for (String part : partsArr) {
				parts.add(part);
			}
		}
		else {
			parts.add(idList);
		}
		return parts;
	}
	
	public static Symbol getSymbol(String identifier) {
		//Iterator<HashMap<String,Symbol>> iter = symbolLookUp.iterator();

		for (HashMap<String,Symbol> temp : symbolLookUp) {
		    if (temp.containsKey(identifier)) {
		    	Symbol temp2 = temp.get(identifier);
		    	return temp2;
		    }
		}
		return null;
	}
	
	public static void test() {
		System.out.println("\tz: "+(getSymbol("z") != null ? "YES" : "NO"));
		System.out.println("\ta: "+(getSymbol("a") != null ? "YES" : "NO"));
		System.out.println("\tb: "+(getSymbol("b") != null ? "YES" : "NO"));
		System.out.println("\tyo: "+(getSymbol("yo") != null ? "YES" : "NO"));
		System.out.println("\tyo2: "+(getSymbol("yo2") != null ? "YES" : "NO"));
		System.out.println("\tc: "+(getSymbol("c") != null ? "YES" : "NO"));
		System.out.println("\td: "+(getSymbol("d") != null ? "YES" : "NO"));
	}
}
