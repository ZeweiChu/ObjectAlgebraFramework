package example_QLAlg2;

import java.util.List;

import trees.QLAlg;

//BEGIN_QL_TRANSFORM_ALG
class Rename<E, S, F> implements QLAlg<E, S, F> {
	private QLAlg<E, S, F> alg;
	private String n1, n2;
	public Rename(QLAlg<E, S, F> alg, String n1, String n2) {
		this.alg = alg; this.n1 = n1; this.n2 = n2;
	}	
	public F Form(String id, List<S> stmts) {
		return alg.Form(id, stmts);
	}
	public S If(E cond, S then) {
		return alg.If(cond, then);
	}
	public S Question(String id, String lbl,
			String type) {
		String newN = id.equals(n1) ? n2 : id;
		return alg.Question(newN, lbl, type);
	}
	public E Lit(int x) { return alg.Lit(x); }
	public E Var(String name) {
		String newN = name.equals(n1) ? n2 : name;
		return alg.Var(newN);
	}
	public E GEq(E lhs, E rhs) {
		return alg.GEq(lhs, rhs);
	}
}
//END_QL_TRANSFORM_ALG

/*

//BEGIN_QL_TRANSFORM_ALG_SIMP
class Rename<E, S, F> implements QLAlg<E, S, F> {
	QLAlg<E, S, F> alg;
	String n1, n2;	
	
	F Form(String n, List<S> b) {
		return alg.Form(n, b);
	}
	S Question(String n, String l, String t) {
		String newN = n.equals(n1) ? n2 : n;
		return alg.Question(newN, l, t);
	}
	...
	E Var(String x) {
		String newN = x.equals(n1) ? n2 : x;
		return alg.Var(newN);
	}
}
//END_QL_TRANSFORM_ALG_SIMP

*/
