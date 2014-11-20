package combinator;

import java.util.ArrayList;
import java.util.List;
import library.Pair;
import trees.StatAlg;

public class CombineStatAlg<A0, A1, B0, B1>
	implements StatAlg<Pair<A0, B0>, Pair<A1, B1>> {

	public StatAlg<A0, A1> alg1;
	public StatAlg<B0, B1> alg2;

	public CombineStatAlg(StatAlg<A0, A1> _alg1, StatAlg<B0, B1> _alg2) {
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

	public Pair<A1, B1> Assign(java.lang.String p0, Pair<A0, B0> p1) {
		return new Pair<A1, B1>(alg1.Assign(p0, p1.a()), alg2.Assign(p0, p1.b()));
	}

	public Pair<A1, B1> Seq(Pair<A1, B1> p0, Pair<A1, B1> p1) {
		return new Pair<A1, B1>(alg1.Seq(p0.a(), p1.a()), alg2.Seq(p0.b(), p1.b()));
	}

}