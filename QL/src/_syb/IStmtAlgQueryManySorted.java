package _syb;

import library.Monoid;
import ql_obj_alg.syntax.IStmtAlg;

public class IStmtAlgQueryManySorted<E, S> implements IStmtAlg<E, S> {
	private Monoid<S> m;

	public Monoid<S> m() {
		return m;
	}

	public IStmtAlgQueryManySorted(Monoid<S> m) {
		this.m = m;
	}

	public S iff(E p0, java.util.List<S> p1) {
		S res = m.empty();
		res = m.join(res, m.fold(p1));
		return res;
	}

	public S iffelse(E p0, java.util.List<S> p1, java.util.List<S> p2) {
		S res = m.empty();
		res = m.join(res, m.fold(p1));
		res = m.join(res, m.fold(p2));
		return res;
	}

	public S question(java.lang.String p0, java.lang.String p1,
			ql_obj_alg.check.types.Type p2) {
		S res = m.empty();
		return res;
	}

	public S question(java.lang.String p0, java.lang.String p1,
			ql_obj_alg.check.types.Type p2, E p3) {
		S res = m.empty();
		return res;
	}
}
