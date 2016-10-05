import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;

public class Symbol {
	public String identifier;
	public String type;
	public String value;

	public Symbol(String identifier, String type, String value) {
		this.identifier = identifier;
		this.type = type;
		if (type == "STRING") {
			this.value = " value "+value;
		}
		else {
			this.value = "";
		}
	}

	public Symbol () {
		this.identifier = "";
		this.type = "";
		this.value = "";
	}
}

