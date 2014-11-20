package combinator;

import java.util.ArrayList;
import java.util.List;
import library.Pair;
import trees.LamAlg;

public class CombineLamAlg<A0, B0>
	implements LamAlg<Pair<A0, B0>> {

	public LamAlg<A0> alg1;
	public LamAlg<B0> alg2;

	public CombineLamAlg(LamAlg<A0> _alg1, LamAlg<B0> _alg2) {
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

	public Pair<A0, B0> Apply(Pair<A0, B0> p0, Pair<A0, B0> p1) {
		return new Pair<A0, B0>(alg1.Apply(p0.a(), p1.a()), alg2.Apply(p0.b(), p1.b()));
	}

	public Pair<A0, B0> Lambda(java.lang.String p0, Pair<A0, B0> p1) {
		return new Pair<A0, B0>(alg1.Lambda(p0, p1.a()), alg2.Lambda(p0, p1.b()));
	}

}