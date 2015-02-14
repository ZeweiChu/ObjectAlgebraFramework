package query;

import library.Monoid;
import NewExample.CompanyAlg;

public interface CompanyAlgQuery<R> extends CompanyAlg<R, R, R, R, R, R> {

	Monoid<R> m();

	default R C(java.util.List<R> p0) {
		R res = m().empty();
		res = m().join(res, m().fold(p0));
		return res;
	}

	default R D(R p0, java.util.List<R> p1) {
		R res = m().empty();
		res = m().join(res, p0);
		res = m().join(res, m().fold(p1));
		return res;
	}

	default R DU(R p0) {
		R res = m().empty();
		res = m().join(res, p0);
		return res;
	}

	default R E(R p0, R p1) {
		R res = m().empty();
		res = m().join(res, p0);
		res = m().join(res, p1);
		return res;
	}

	default R P() {
		R res = m().empty();
		return res;
	}

	default R PU(R p0) {
		R res = m().empty();
		res = m().join(res, p0);
		return res;
	}

	default R S(NewExample.Ref<java.lang.Double> p0) {
		R res = m().empty();
		return res;
	}

}