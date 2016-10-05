import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;
import java.lang.Exception.*;


public class SymbolTable
{
	public static Stack<Scope> ScopeStack = new Stack<Scope>(); 
	public static Scope currentScope;
	private static int block_number = 1;

	public SymbolTable() {
	
	}

	public static void pushScope(Scope scope) {
		ScopeStack.push(scope);
		currentScope = getCurrentScope();
	}

	public static void pushBlock() {
		pushScope(new Scope("BLOCK "+Integer.toString(block_number++)));
	}

	public static void popScope() {
		Scope tempScope = ScopeStack.pop();
		if (tempScope.name != "GLOBAL") {
			System.out.println("");
		}
		System.out.println("Symbol table " + tempScope.name);
		tempScope.printSymbols();
	}

	public static Scope getCurrentScope() {
		return ScopeStack.peek();
	}



}