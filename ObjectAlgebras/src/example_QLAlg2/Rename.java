package example_QLAlg2;

import java.util.List;

import trees.QLAlg;

//BEGIN_QL_TRANSFORM_ALG
class Rename<E, S, F> implements QLAlg<E, S, F> {
	private QLAlg<E, S, F> alg;	
	public Rename(QLAlg<E, S, F> alg) {
		this.alg = alg;
	}	
	public F Form(String id, List<S> stmts) {
		return alg.Form(id, stmts);
	}
	public S If(E cond, S then) {
		return alg.If(cond, then);
	}
	public S Question(String id, String lbl,
			String type) {
		return alg.Question(id + "_", lbl, type);
	}
	public E Lit(int x) { return alg.Lit(x); }
	public E Var(String name) {
		return alg.Var(name + "_");
	}
	public E GEq(E lhs, E rhs) {
		return alg.GEq(lhs, rhs);
	}
}
//END_QL_TRANSFORM_ALG

/*

//BEGIN_QL_TRANSFORM_ALG_SIMP
class Rename<E, S, F> implements QLAlg<E, S, F> {
	private QLAlg<E, S, F> alg;	
	public Rename(QLAlg<E, S, F> alg) {
		this.alg = alg;
	}
	public F Form(String n, List<S> b) {
		return alg.form(n, b);
	}
	public S Question(String n, String l, String t) {
		return alg.question(n + "_", l, t);
	}
	...
	public E Var(String x) {
		return alg.var(x+ "_");
	}
}
//END_QL_TRANSFORM_ALG_SIMP

*/
