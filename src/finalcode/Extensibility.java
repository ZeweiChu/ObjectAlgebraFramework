package finalcode;

//BEGIN_PRINT_BASE
class Print2 implements IntAlg<String> {
	public String lit(int x) {
		return new Integer(x).toString();
	}

	public String add(String e1, String e2) {
		return e1 + " + " + e2;
	}	
}
//END_PRINT_BASE

//BEGIN_EXTENSIBILITY_VAR
interface IntBoolAlg<A> extends IntAlg<A> {
	A bool(Boolean b);
	A iff(A e1, A e2, A e3);
}
//END_EXTENSIBILITY_VAR

/* Extending evaluation */

class Bool implements Exp {
	Boolean b;
	
	public Bool(Boolean b) {this.b = b;}
	
	public Value eval() {
		return new VBool(b);
	}	
}

class Iff implements Exp {
	Exp e1, e2, e3;
	
	public Iff(Exp e1, Exp e2, Exp e3) {
		this.e1 = e1;
		this.e2 = e2;
		this.e3 = e3;
	}

	public Value eval() {
		return null;
	}
}

//BEGIN_NEW_VARIANTS
/* Extended Expression Factory */
class IntBoolFactory extends IntFactory implements IntBoolAlg<Exp> {
	public Exp bool(Boolean b) {return new Bool(b);}

	public Exp iff(Exp e1, Exp e2, Exp e3) {return new Iff(e1,e2,e3);}
}

/* Extended Retroactive Implementation for Printing */
class IntBoolPrint extends IntPrint implements IntBoolAlg<IPrint> {
	public IPrint bool(final Boolean b) {
		return new IPrint() {
			public String print() {return new Boolean(b).toString();} 
		};	
	}

	public IPrint iff(final IPrint e1, final IPrint e2, final IPrint e3) {
		return 	new IPrint() {
			public String print() {
				return "if (" + e1.print() + ") then " + e2.print() + " else " + e3.print(); 
			}
		};
	}
}
//END_NEW_VARIANTS

class Test {
	<A> A exp(IntAlg<A> v) {
		return v.add(v.lit(3), v.lit(4));
	}
	
	<A> A exp2(IntBoolAlg<A> v) {
		return v.iff(v.bool(false),v.add(v.lit(3), v.lit(4)),v.lit(0));
	}
	
	void test() {
		IntPrint p = new IntPrint();
		IntBoolPrint p2 = new IntBoolPrint();
		
		exp(p2).print();
		//exp2(p).print(); // does not type-check
	}
	
}
