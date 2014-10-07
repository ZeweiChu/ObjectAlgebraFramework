package expDemo2;

import library.Monoid;
import trees.ExpAlg;

public class QueryExpAlg2<Exp> implements ExpAlg<Exp> {
	private Monoid<Exp> m;
	public Monoid<Exp> m() { return m; }
	public QueryExpAlg2(Monoid<Exp> m) {
		this.m = m;
	}
	public Exp Add(Exp e1, Exp e2) {
		return m.join(e1, e2);
	}
	public  Exp Lit(int p0) {
		return m.empty();
	}
	public  Exp Var(String s) {
		return m.empty();
	}
}