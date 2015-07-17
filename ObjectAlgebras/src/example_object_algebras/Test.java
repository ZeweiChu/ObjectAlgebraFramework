package example_object_algebras;

public class Test {

//BEGIN_BKG_EXP_EXAMPLE
<E> E makeExp(ExpAlg<E> alg) {
	return alg.Add(alg.Lit(2), alg.Lit(3));
}
//END_BKG_EXP_EXAMPLE
	
//BEGIN_BKG_MUL_EXAMPLE
<E> E makeMul(MulAlg<E> alg) {
	return alg.Mul(alg.Add(alg.Lit(2), alg.Lit(3)), alg.Lit(4));
}
//END_BKG_MUL_EXAMPLE
	
	void go() {

//BEGIN_BKG_EXPMUL_TEST
println(makeExp(new Eval()));
println(makeExp(new Print()));
println(makeMul(new MulEval()));
//END_BKG_EXPMUL_TEST

	}
	
	void println(Object o) {
		System.out.println(o);
	}
	
	public static void main(String[] args) {
		Test t = new Test();
		t.go();
	}
	
}
