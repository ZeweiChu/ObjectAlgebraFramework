package OOExp;

interface ExpAlg<A> {
	A lit(int x);
	A add(A e1, A e2);
}

class Factory implements ExpAlg<Exp> {
	public Exp lit(int x) {
		return new Lit(x);
	}

	public Exp add(Exp e1, Exp e2) {
		return new Add(e1, e2);
	}
}

/* Rectroactive Implementation of IPrint for expressions */

//BEGIN__RETROACTIVE
interface IPrint {
	String print();
}
class Print implements ExpAlg<IPrint> {
	public IPrint lit(final int x) {
		return new IPrint() {
			public String print() {
				return new Integer(x).toString();
		}};
	}
	public IPrint add(final IPrint e1, final IPrint e2) {
		return new IPrint() {
			public String print() {
				return e1.print() + " + " + e2.print();
		}};
}}
//END__RETROACTIVE

/* Client */

public class Retroactive {
	static <A> A exp(ExpAlg<A> v) {
		return v.add(v.lit(3), v.lit(4));
	}
	
	public static void main(String args[]) {
		Factory base = new Factory();
		Print print = new Print();
		
		int x = exp(base).eval();
		String s = exp(print).print();
		
		System.out.println("Exp: " + s + "; evaluates to: " + x);
	}
}