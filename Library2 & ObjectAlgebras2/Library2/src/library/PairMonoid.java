package library;

public class PairMonoid<A, B> implements Monoid<Pair<A, B>> {
	private Monoid<A> m1;
	private Monoid<B> m2;
	
	public Monoid<A> m1() { return m1; }
	public Monoid<B> m2() { return m2; }
	
	public PairMonoid(Monoid<A> m1, Monoid<B> m2) { this.m1 = m1; this.m2 = m2; }

	@Override
	public Pair<A, B> join(Pair<A, B> x, Pair<A, B> y) {
		return new Pair<A, B>(m1.join(x.a(), y.a()), m2.join(x.b(), y.b()));
	}

	@Override
	public Pair<A, B> empty() {
		return new Pair<A, B>(m1.empty(), m2.empty());
	}
}
