package query;

import library.Monoid;
import trees.SybAlg;

public class G_SybAlgQuery<A0, A1, A2, A3, A4, A5> implements SybAlg<A0, A1, A2, A3, A4, A5> {

	private Monoid<A0> m0;
	private Monoid<A1> m1;
	private Monoid<A2> m2;
	private Monoid<A3> m3;
	private Monoid<A4> m4;
	private Monoid<A5> m5;

	public G_SybAlgQuery(Monoid<A0> m0, Monoid<A1> m1, Monoid<A2> m2, Monoid<A3> m3, Monoid<A4> m4, Monoid<A5> m5) {
		this.m0 = m0;
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		this.m4 = m4;
		this.m5 = m5;
	}

	@Override
	public A0 C(java.util.List<A1> p0) {
		A0 res = m0.empty();
		return res;
	}

	@Override
	public A1 D(java.lang.String p0, A3 p1, java.util.List<A2> p2) {
		A1 res = m1.empty();
		return res;
	}

	@Override
	public A2 DU(A1 p0) {
		A2 res = m2.empty();
		return res;
	}

	@Override
	public A3 E(A4 p0, A5 p1) {
		A3 res = m3.empty();
		return res;
	}

	@Override
	public A4 P(java.lang.String p0, java.lang.String p1) {
		A4 res = m4.empty();
		return res;
	}

	@Override
	public A2 PU(A3 p0) {
		A2 res = m2.empty();
		return res;
	}

	@Override
	public A5 S(float p0) {
		A5 res = m5.empty();
		return res;
	}

}