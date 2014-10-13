package query;

import library.Monoid;
import trees.StmAlg;

public class QueryStmAlg<R> implements StmAlg<R,R,R> {
	private Monoid<R> m;
	public Monoid<R> m() { return m; }
	public QueryStmAlg(Monoid<R> m) {
		this.m = m;
	}
	public  R EAdd(R p0, R p1) {
		R res = m.empty();
		res = m.join(res, p0);
		res = m.join(res, p1);
		return res;
	}
	public  R EInt(int p0) {
		R res = m.empty();
		return res;
	}
	public  R EStm(R p0) {
		R res = m.empty();
		res = m.join(res, p0);
		return res;
	}
	public  R EVar(java.lang.String p0) {
		R res = m.empty();
		return res;
	}
	public  R SAss(java.lang.String p0, R p1) {
		R res = m.empty();
		res = m.join(res, p1);
		return res;
	}
	public  R SBlock(java.util.List<R> p0) {
		R res = m.empty();
		res = m.join(res, m.fold(p0));
		return res;
	}
	public  R SDecl(R p0, java.lang.String p1) {
		R res = m.empty();
		res = m.join(res, p0);
		return res;
	}
	public  R SReturn(R p0) {
		R res = m.empty();
		res = m.join(res, p0);
		return res;
	}
	public  R TFloat() {
		R res = m.empty();
		return res;
	}
	public  R TInt() {
		R res = m.empty();
		return res;
	}
}