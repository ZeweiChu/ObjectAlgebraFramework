package query;

import java.util.List;
import library.Monoid;
import trees.LamAlg;

public class LamAlgQuery<R> implements LamAlg<R> {

	private Monoid<R> m;

	public LamAlgQuery(Monoid<R> m) { this.m = m; }

	public R Apply(R p0, R p1) {
		R res = m.empty();
		res = m.join(res, p0);
		res = m.join(res, p1);
		return res;
	}

	public R Lambda(java.lang.String p0, R p1) {
		R res = m.empty();
		res = m.join(res, p1);
		return res;
	}

}