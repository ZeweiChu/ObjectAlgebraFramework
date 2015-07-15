package example_DoubleAlg2;

import trees.DoubleAlg;
import trees.ExpAlg;

public class Test {

//BEGIN_MAKE_DOUBLE_EXP
<E> E makeExp(ExpAlg<E> alg) {
	Desugar<E> d = new Desugar<E>() {
		public ExpAlg<E> expAlg() { return alg; }
		public DoubleAlg<E> doubleAlg() { return null; }
	};
	return d.Add(d.Lit(5), d.Double(d.Var("a")));
}
//END_MAKE_DOUBLE_EXP
	
	void go() {
		PrettyPrint printAlg = new PrettyPrint();
		System.out.println(makeExp(printAlg));
	}
	
	public static void main(String args[]) {
		Test t = new Test();
		t.go();
	}
	
}
