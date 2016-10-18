import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;
import java.lang.Exception.*;


public class Scope {
	public List<Symbol> symbolTable;
	public HashMap symbolLookUp;
	public String name;
	public int symbolID = 0;

	public Scope(String name) {
		this.name = name;
		this.symbolTable = new ArrayList<Symbol>();
		this.symbolLookUp = new HashMap();
	}

	public void addSymbol(Symbol tsymbol) {
		if (tsymbol.type != "STRING" && tsymbol.identifier.indexOf(',') != -1) {
			String[] parts = tsymbol.identifier.split(",");
			for (String part : parts) {
				checkAdd(new Symbol(part,tsymbol.type,tsymbol.value));
			}
		}
		else {
			checkAdd(tsymbol);
		}
	}

	private void checkAdd(Symbol tsymbol) {
		String identifier = tsymbol.identifier;
		if (symbolLookUp.containsKey(identifier)) {
				System.out.println("DECLARATION ERROR " + tsymbol.identifier);
				System.exit(1);
		}
		this.symbolLookUp.put(identifier,symbolID);
		this.symbolTable.add(symbolID++,tsymbol);
	}



	public void printSymbols() {
		for (Symbol tsymbol : this.symbolTable) {
    		System.out.println("name "+tsymbol.identifier+" type "+tsymbol.type+tsymbol.value);
    	}
	}
}