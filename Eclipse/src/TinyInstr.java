
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
	public Regs reg;
	public Regs reg2;
	
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
		//System.out.println("Before: "+reg.reg1val+reg.reg1dirty+"\t"+reg.reg2val+reg.reg2dirty+reg.reg3val+reg.reg3dirty+reg.reg4val+reg.reg4dirty);
		//System.out.println("After : "+reg2.reg1val+reg2.reg1dirty+"\t"+reg2.reg2val+reg2.reg2dirty+reg2.reg3val+reg2.reg3dirty+reg2.reg4val+reg2.reg4dirty);
		//System.out.println();
	}
	
	public class Regs {
		String reg1val;
		String reg1dirty;
		String reg2val;
		String reg2dirty;
		String reg3val;
		String reg3dirty;
		String reg4val;
		String reg4dirty;
		
	}
}
