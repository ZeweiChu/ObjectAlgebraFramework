package example_object_algebras;

public class Test {

	<E> E makeExp(ExpAlg<E> alg) {
		return alg.Add(alg.Lit(2), alg.Lit(3));
	}
	
	<E> E makeMul(MulAlg<E> alg) {
		return alg.Mul(alg.Add(alg.Lit(2), alg.Lit(3)), alg.Lit(4));
	}
	
	void go() {
		println(makeExp(new Eval()));
		println(makeExp(new Print()));
		println(makeMul(new MulEval()));
	}
	
	void println(Object o) {
		System.out.println(o);
	}
	
	public static void main(String[] args) {
		Test t = new Test();
		t.go();
	}
	
}
