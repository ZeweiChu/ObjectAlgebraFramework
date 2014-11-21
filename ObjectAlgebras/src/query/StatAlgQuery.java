package query;

import library.Monoid;
import trees.StatAlg;

public interface StatAlgQuery<R> extends StatAlg<R, R> {

	Monoid<R> m();

	default R Assign(java.lang.String p0, R p1) {
		R res = m().empty();
		res = m().join(res, p1);
		return res;
	}

	default R Seq(R p0, R p1) {
		R res = m().empty();
		res = m().join(res, p0);
		res = m().join(res, p1);
		return res;
	}

}