import java.util.*;

public class Function {
	public static HashMap<String,Function> TableLookUp = new HashMap<String,Function>();
	public static HashMap<String,Integer> ParamLookUp = new HashMap<String,Integer>();
	public static HashMap<String,Integer> LocalLookUp = new HashMap<String,Integer>();
	public static Stack<HashMap<String,Symbol>> symbolLookUp = new Stack<HashMap<String,Symbol>>();
	public SemanticHandler semanticHandler = new SemanticHandler();
	public int locals = 0;
	public int params = 0;
	public String retType;
	public String name;
	public static String currName;

	public static String scope;


	public static HashMap<String,String> regLookUp = new HashMap<String,String>();
	public static int tempReg = 0;
	public static int tempLocal = 1;
	public static int tempParam = 1;


	public Function(String name) {
		if (!SemanticHandler.SemanticStack.empty()) {
			SemanticHandler.SemanticStack.clear();
		}
		this.name = name;
		currName = name;
		ParamLookUp.put(name, 0);
		LocalLookUp.put(name, 0);
		tempReg = 0;
		tempLocal = 1;
		tempParam = 1;
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
		ParamLookUp.put(currName, tempParam-1);
		regLookUp.put(reg,type);
		return reg;
	}

	public static String getNextLocal(String type) {
		String reg = "$L"+Integer.toString(tempLocal++);
		LocalLookUp.put(currName, tempLocal-1);
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
				symbolLookUp.peek().put(id, temp);
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

}
