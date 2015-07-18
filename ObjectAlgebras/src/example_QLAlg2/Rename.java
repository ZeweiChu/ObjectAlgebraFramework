package example_QLAlg2;

import java.util.List;

import trees.QLAlg;

//BEGIN_QL_TRANSFORM_ALG
class Rename<E, S, F> implements QLAlg<E, S, F> {
	private QLAlg<E, S, F> alg;
	private String from, to;
	public Rename(QLAlg<E, S, F> alg, String from, String to) {
		this.alg = alg; 
		this.from = from; 
		this.to = to;
	}	
	public F Form(String id, List<S> stmts) {
		return alg.Form(id, stmts);
	}
	public S If(E c, S t) {
		return alg.If(c, t);
	}
	public S Question(String n, String l, String t) {
		n = n.equals(from) ? to : n;
		return alg.Question(n, l, t);
	}
	public E Lit(int n) { 
		return alg.Lit(n); 
	}
	public E Var(String x) {
		x = x.equals(from) ? to : x;
		return alg.Var(x);
	}
	public E GEq(E l, E r) {
		return alg.GEq(l, r);
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
