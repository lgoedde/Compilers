import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;

public class Symbol {
	public static String typeIn;
	public String type;
	public String value;
	public String irReg;

	public Symbol(String value) {
		this.type = typeIn;
		this.value = value;
		this.irReg = null;
	}

	public Symbol (int depth) {
		this.irReg = "";
		this.type = "";
		this.value = "";
	}
	
	public Symbol clone() {
		Symbol newSymb = new Symbol(null);
		newSymb.type = this.type;
		newSymb.value = this.value;
		newSymb.irReg = this.irReg;
		return newSymb;
	}
}

