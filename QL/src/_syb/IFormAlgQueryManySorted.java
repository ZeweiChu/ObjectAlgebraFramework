package _syb;

import library.Monoid;
import ql_obj_alg.syntax.IFormAlg;

// Note, we have to unify S and F here for the monoid to work...
public class IFormAlgQueryManySorted<E, S> implements IFormAlg<E, S, S> {
	private Monoid<S> m;

	public Monoid<S> m() {
		return m;
	}

	public IFormAlgQueryManySorted(Monoid<S> m) {
		this.m = m;
	}

	public S form(java.lang.String p0, java.util.List<S> p1) {
		S res = m.empty();
		res = m.join(res, m.fold(p1));
		return res;
	}
}
