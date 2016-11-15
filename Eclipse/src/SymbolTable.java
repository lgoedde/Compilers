import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;
import java.lang.Exception.*;


public class SymbolTable
{
	public static Stack<Scope> ScopeStack = new Stack<Scope>(); 
	public static Scope currentScope;
	private static int block_number = 1;
	public static Scope globalScope;

	public SymbolTable() {
	
	}

	public static void pushScope(Scope scope) {
		ScopeStack.push(scope);
		if (scope.name == "GLOBAL")
			globalScope = scope;
		currentScope = getCurrentScope();
	}

	public static void pushBlock() {
		pushScope(new Scope("BLOCK "+Integer.toString(block_number++)));
	}

	public static void popScope() {
		Scope tempScope = ScopeStack.pop();
	}

	public static Scope getCurrentScope() {
		return ScopeStack.peek();
	}

	public static String getSymbolType(String identifier) {
		if (globalScope.symbolLookUp.containsKey(identifier)) {
			return globalScope.symbolTable.get((Integer)globalScope.symbolLookUp.get(identifier)).type;
		}
		if (currentScope.symbolLookUp.containsKey(identifier)) {
			return currentScope.symbolTable.get((Integer)currentScope.symbolLookUp.get(identifier)).type;
		}
	 	return "";
	}



}