package expDemo3;

//BEGIN_GENERIC_QUERY
public class QueryExpAlg<Exp> implements ExpAlg<Exp> {
	private Monoid<Exp> m;
	public Monoid<Exp> m() { return m; }
	public QueryExpAlg(Monoid<Exp> m) {
		this.m = m;
	}
	public Exp Add(Exp p0,Exp p1) {
		Exp res = m.empty();
		res = m.join(res, p0);
		res = m.join(res, p1);
		return res;
	}
	public Exp Lit(int p0) {
		Exp res = m.empty();
		return res;
	}
	public Exp Var(String p0) {
		Exp res = m.empty();
		return res;
	}
}
//END_GENERIC_QUERY