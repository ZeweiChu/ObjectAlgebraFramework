package query;

import java.util.List;
import library.Monoid;
import trees.StatAlg;

public class StatAlgQuery<R> implements StatAlg<R, R> {

	private Monoid<R> m;

	public StatAlgQuery(Monoid<R> m) { this.m = m; }

	public R Assign(java.lang.String p0, R p1) {
		R res = m.empty();
		res = m.join(res, p1);
		return res;
	}

	public R Seq(R p0, R p1) {
		R res = m.empty();
		res = m.join(res, p0);
		res = m.join(res, p1);
		return res;
	}

}