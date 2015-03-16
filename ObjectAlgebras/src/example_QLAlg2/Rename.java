package example_QLAlg2;

import java.util.List;

import trees.QLAlg;

//BEGIN_QL_TRANSFORM_ALG
class Rename<E, S, F> implements QLAlg<E, S, F> {
	private QLAlg<E, S, F> alg;	
	public Rename(QLAlg<E, S, F> alg) {
		this.alg = alg;
	}	
	public F form(String id, List<S> stmts) {
		return alg.form(id, stmts);
	}
	public S iff(E cond, S then) {
		return alg.iff(cond, then);
	}
	public S question(String id, String lbl,
			String type) {
		return alg.question(id + "_", lbl, type);
	}
	public E lit(int x) { return alg.lit(x); }
	public E var(String name) {
		return alg.var(name + "_");
	}
	public E geq(E lhs, E rhs) {
		return alg.geq(lhs, rhs);
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
	public E var(String name) {
		return alg.var(name + "_");
	}
	...
}
//END_QL_TRANSFORM_ALG_SIMP

*/
