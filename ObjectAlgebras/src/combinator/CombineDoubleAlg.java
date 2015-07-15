package combinator;

import java.util.ArrayList;
import java.util.List;
import library.Pair;
import trees.DoubleAlg;

public class CombineDoubleAlg<A0, B0>
	implements DoubleAlg<Pair<A0, B0>> {

	public DoubleAlg<A0> alg1;
	public DoubleAlg<B0> alg2;

	public CombineDoubleAlg(DoubleAlg<A0> _alg1, DoubleAlg<B0> _alg2) {
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

	public Pair<A0, B0> Double(Pair<A0, B0> p0) {
		return new Pair<A0, B0>(alg1.Double(p0.a()), alg2.Double(p0.b()));
	}

}