package query;

import library.Monoid;
import trees.ExpAlg;

public class G_ExpAlgQuery<A0> implements ExpAlg<A0> {

	private Monoid<A0> m0;

	public G_ExpAlgQuery(Monoid<A0> m0) {
		this.m0 = m0;
	}

	@Override
	public A0 Add(A0 p0, A0 p1) {
		A0 res = m0.empty();
		res = m0.join(res, p0);
		res = m0.join(res, p1);
		return res;
	}

	@Override
	public A0 Lit(int p0) {
		A0 res = m0.empty();
		return res;
	}

	@Override
	public A0 Var(java.lang.String p0) {
		A0 res = m0.empty();
		return res;
	}

}