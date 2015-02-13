package transform;

import NewExample.CompanyAlg;

public interface CompanyAlgTransform<A0, A1, A2, A3, A4, A5> extends CompanyAlg<A0, A1, A2, A3, A4, A5> {

	CompanyAlg<A0, A1, A2, A3, A4, A5> companyAlg();

	@Override
	default A0 C(java.util.List<A1> p0) {
		return companyAlg().C(p0);
	}

	@Override
	default A1 D(A3 p0, java.util.List<A2> p1) {
		return companyAlg().D(p0, p1);
	}

	@Override
	default A2 DU(A1 p0) {
		return companyAlg().DU(p0);
	}

	@Override
	default A3 E(A4 p0, A5 p1) {
		return companyAlg().E(p0, p1);
	}

	@Override
	default A4 P() {
		return companyAlg().P();
	}

	@Override
	default A2 PU(A3 p0) {
		return companyAlg().PU(p0);
	}

	@Override
	default A5 S(NewExample.Ref<java.lang.Double> p0) {
		return companyAlg().S(p0);
	}

}