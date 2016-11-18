import java.util.*;

public class Function {
	public static HashMap<String,Function> TableLookUp = new HashMap<String,Function>();
	public static Stack<HashMap<String,Symbol>> symbolLookUp = new Stack<HashMap<String,Symbol>>();
	public SemanticHandler semanticHandler = new SemanticHandler();
	public int locals = 0;
	public int params = 0;
	
	public Function(String name) {
		if (!SemanticHandler.SemanticStack.empty()) {
			SemanticHandler.SemanticStack.clear();
		}
		//System.out.println("Entered Function: "+name);
		SemanticHandler.currentFunction = this;
		TableLookUp.put(name, this);
		SemanticHandler.FunctionList.add(this);
		List<HeadNode> rootList = new ArrayList<HeadNode>();
		SemanticHandler.pushList(rootList);
		semanticHandler.rootList = rootList;
		symbolLookUp.push(new HashMap<String,Symbol>());
		//test();
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
	
	public static void addSymbol(String identifier, Symbol tsymbol) {
		//TODO handle if identifier is comma separated values)
		if (getSymbol(identifier) != null) {
			System.out.println("DECLARATION ERROR " + identifier);
			System.exit(1);
		}	
		//System.out.println("New Symbol: "+identifier);
		symbolLookUp.peek().put(identifier, tsymbol);
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
