
public class TinyInstr {
	public enum TinyOpcode {
		var,
		str,
		label,
		move,
		addi,
		addr,
		subi,
		subr,
		muli,
		mulr,
		divi,
		divr,
		inci,
		deci,
		cmpi,
		push,
		pop,
		jsr,
		ret,
		link,
		unlnk,
		cmpr,
		jmp,
		jgt,
		jlt,
		jge,
		jle,
		jeq,
		jne,
		sys,
		end
	}
	
	public TinyOpcode opcode;
	public String op1;
	public String op2;
	
	public TinyInstr(TinyOpcode opcode, String op1, String op2) {
		this.opcode = opcode;
		this.op1 = op1;
		this.op2 = op2;
	}
	
	public void printInstr() {
		System.out.print(opcode+" ");
		if (op1 != null)
			System.out.print(op1+" ");
		if (op2 != null)
			System.out.print(op2);
		System.out.print("\n");
	}
}
