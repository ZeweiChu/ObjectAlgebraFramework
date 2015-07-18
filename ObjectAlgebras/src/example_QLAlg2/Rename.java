package example_QLAlg2;

import java.util.List;

import trees.QLAlg;

//BEGIN_QL_TRANSFORM_ALG
class Rename<E, S, F> implements QLAlg<E, S, F> {
	private QLAlg<E, S, F> alg;
	private String from, to;
	public Rename(QLAlg<E, S, F> alg, String from, String to) {
		this.alg = alg; this.from = from; this.to = to;
	}	
	public F Form(String id, List<S> stmts) {
		return alg.Form(id, stmts);
	}
	public S If(E cond, S then) {
		return alg.If(cond, then);
	}
	public S Question(String id, String lbl,
			String type) {
		String newN = id.equals(from) ? to : id;
		return alg.Question(newN, lbl, type);
	}
	public E Lit(int x) { return alg.Lit(x); }
	public E Var(String name) {
		String newN = name.equals(from) ? to : name;
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
	String from, to;	
	
	F Form(String n, List<S> b) {
		return alg.Form(n, b);
	}
	S Question(String n, String l, String t) {
		n = n.equals(from) ? to : n;
		return alg.Question(n, l, t);
	}
	...
	E Var(String x) {
		x = x.equals(from) ? to : x;
		return alg.Var(x);
	}
}
//END_QL_TRANSFORM_ALG_SIMP

*/
