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
		SemanticHandler.currentFunction = this;
		TableLookUp.put(name, this);
		SemanticHandler.FunctionList.add(this);
		List<HeadNode> rootList = new ArrayList<HeadNode>();
		SemanticHandler.pushList(rootList);
		semanticHandler.rootList = rootList;
		symbolLookUp.push(new HashMap<String,Symbol>());
	}
	
	public static void addGlobal() {
		symbolLookUp.push(new HashMap<String,Symbol>());
	}
	
	public static void pushBlock() {
		symbolLookUp.push(new HashMap<String,Symbol>());
	}

	public static void popBlock() {
		symbolLookUp.pop();
	}
	
	public static void addSymbol(String identifier, Symbol tsymbol) {
		if (getSymbol(identifier) != null) {
			System.out.println("DECLARATION ERROR " + identifier);
			System.exit(1);
		}	
		symbolLookUp.peek().put(identifier, tsymbol);
	}
	
	public static Symbol getSymbol(String identifier) {
		Iterator<HashMap<String,Symbol>> iter = symbolLookUp.iterator();

		while (iter.hasNext()){
		    if (iter.next().containsKey(identifier))
		    	return iter.next().get(identifier);
		}
		return null;
	}
}
