package finalcode;

//BEGIN_EXP_ALG
interface IntAlg<A> {
	A lit(int x);
	A add(A e1, A e2);
}
//END_EXP_ALG

//BEGIN_FACTORY_EVAL
class IntFactory implements IntAlg<Exp> {
	public Exp lit(int x) {
		return new Lit(x);
	}
	public Exp add(Exp e1, Exp e2) {
		return new Add(e1, e2);
}}
//END_FACTORY_EVAL

/* Rectroactive Implementation of IPrint for expressions */

//BEGIN_RETROACTIVE
interface IPrint {
	String print();
}
class IntPrint implements IntAlg<IPrint> {
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
//END_RETROACTIVE

/* Client */

public class Retroactive {
	static <A> A exp(IntAlg<A> v) {
		return v.add(v.lit(3), v.lit(4));
	}
	
	public static void main(String args[]) {
		IntFactory base = new IntFactory();
		IntPrint print = new IntPrint();
		
		Value x = exp(base).eval();
		String s = exp(print).print();
		
		System.out.println("Exp: " + s + "; evaluates to: " + x);
	}
}