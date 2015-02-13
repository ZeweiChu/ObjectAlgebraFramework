package query;

import library.Monoid;
import NewExample.CompanyAlg;

public interface G_CompanyAlgQuery<A0, A1, A2, A3, A4, A5> extends CompanyAlg<A0, A1, A2, A3, A4, A5> {

	Monoid<A0> mCompany();
	Monoid<A1> mDept();
	Monoid<A2> mUnit();
	Monoid<A3> mEmployee();
	Monoid<A4> mPerson();
	Monoid<A5> mSalary();

	@Override
	default A0 C(java.util.List<A1> p0) {
		A0 res = mCompany().empty();
		return res;
	}

	@Override
	default A1 D(A3 p0, java.util.List<A2> p1) {
		A1 res = mDept().empty();
		return res;
	}

	@Override
	default A2 DU(A1 p0) {
		A2 res = mUnit().empty();
		return res;
	}

	@Override
	default A3 E(A4 p0, A5 p1) {
		A3 res = mEmployee().empty();
		return res;
	}

	@Override
	default A4 P() {
		A4 res = mPerson().empty();
		return res;
	}

	@Override
	default A2 PU(A3 p0) {
		A2 res = mUnit().empty();
		return res;
	}

	@Override
	default A5 S(NewExample.Ref<java.lang.Double> p0) {
		A5 res = mSalary().empty();
		return res;
	}

}