package transform;

import java.util.function.Function;
import java.util.List;
import java.util.ArrayList;
import NewExample.CompanyAlg;

public interface G_CompanyAlgTransform<A, B0, B1, B2, B3, B4, B5> extends CompanyAlg<Function<A, B0>, Function<A, B1>, Function<A, B2>, Function<A, B3>, Function<A, B4>, Function<A, B5>> {

	CompanyAlg<B0, B1, B2, B3, B4, B5> companyAlg();

	default <B> List<B> substListCompanyAlg(List<Function<A, B>> list, A acc) {
		List<B> res = new ArrayList<B>();
		for (Function<A, B> i : list)
			res.add(i.apply(acc));
		return res;
	}

	@Override
	default Function<A, B0> C(List<Function<A, B1>> p0) {
		return acc -> companyAlg().C(substListCompanyAlg(p0, acc));
	}

	@Override
	default Function<A, B1> D(Function<A, B3> p0, List<Function<A, B2>> p1) {
		return acc -> companyAlg().D(p0.apply(acc), substListCompanyAlg(p1, acc));
	}

	@Override
	default Function<A, B2> DU(Function<A, B1> p0) {
		return acc -> companyAlg().DU(p0.apply(acc));
	}

	@Override
	default Function<A, B3> E(Function<A, B4> p0, Function<A, B5> p1) {
		return acc -> companyAlg().E(p0.apply(acc), p1.apply(acc));
	}

	@Override
	default Function<A, B4> P() {
		return acc -> companyAlg().P();
	}

	@Override
	default Function<A, B2> PU(Function<A, B3> p0) {
		return acc -> companyAlg().PU(p0.apply(acc));
	}

	@Override
	default Function<A, B5> S(NewExample.Ref<java.lang.Double> p0) {
		return acc -> companyAlg().S(p0);
	}

}