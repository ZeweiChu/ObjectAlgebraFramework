package combinator;

import java.util.ArrayList;
import java.util.List;
import library.Pair;
import NewExample.CompanyAlg;

public class CombineCompanyAlg<A0, A1, A2, A3, A4, A5, B0, B1, B2, B3, B4, B5>
	implements CompanyAlg<Pair<A0, B0>, Pair<A1, B1>, Pair<A2, B2>, Pair<A3, B3>, Pair<A4, B4>, Pair<A5, B5>> {

	public CompanyAlg<A0, A1, A2, A3, A4, A5> alg1;
	public CompanyAlg<B0, B1, B2, B3, B4, B5> alg2;

	public CombineCompanyAlg(CompanyAlg<A0, A1, A2, A3, A4, A5> _alg1, CompanyAlg<B0, B1, B2, B3, B4, B5> _alg2) {
		alg1 = _alg1;
		alg2 = _alg2;
	}

	private <A, B> Pair<List<A>, List<B>> getPairList(List<Pair<A, B>> l) {
		List<A> l1 = (List<A>)new ArrayList<A>();
		List<B> l2 = (List<B>)new ArrayList<B>();
		for (Pair<A, B> element : l) {
			l1.add(element.a());
			l2.add(element.b());
		}
		return new Pair<List<A>, List<B>>(l1, l2);
	}

	public Pair<A0, B0> C(List<Pair<A1, B1>> p0) {
		return new Pair<A0, B0>(alg1.C(getPairList(p0).a()), alg2.C(getPairList(p0).b()));
	}

	public Pair<A1, B1> D(Pair<A3, B3> p0, List<Pair<A2, B2>> p1) {
		return new Pair<A1, B1>(alg1.D(p0.a(), getPairList(p1).a()), alg2.D(p0.b(), getPairList(p1).b()));
	}

	public Pair<A2, B2> DU(Pair<A1, B1> p0) {
		return new Pair<A2, B2>(alg1.DU(p0.a()), alg2.DU(p0.b()));
	}

	public Pair<A3, B3> E(Pair<A4, B4> p0, Pair<A5, B5> p1) {
		return new Pair<A3, B3>(alg1.E(p0.a(), p1.a()), alg2.E(p0.b(), p1.b()));
	}

	public Pair<A4, B4> P() {
		return new Pair<A4, B4>(alg1.P(), alg2.P());
	}

	public Pair<A2, B2> PU(Pair<A3, B3> p0) {
		return new Pair<A2, B2>(alg1.PU(p0.a()), alg2.PU(p0.b()));
	}

	public Pair<A5, B5> S(NewExample.Ref<java.lang.Double> p0) {
		return new Pair<A5, B5>(alg1.S(p0), alg2.S(p0));
	}

}