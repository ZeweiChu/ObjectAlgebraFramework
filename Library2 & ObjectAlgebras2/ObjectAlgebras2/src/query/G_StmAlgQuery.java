package query;

import library.Monoid;
import trees.StmAlg;

public class G_StmAlgQuery<A0, A1, A2> implements StmAlg<A0, A1, A2> {

	Monoid<A0> m0;
	Monoid<A1> m1;
	Monoid<A2> m2;

	public G_StmAlgQuery(Monoid<A0> m0, Monoid<A1> m1, Monoid<A2> m2) {
		this.m0 = m0;
		this.m1 = m1;
		this.m2 = m2;
	}

	@Override
	public A1 EAdd(A1 p0, A1 p1) {
		A1 res = m1.empty();
		res = m1.join(res, p0);
		res = m1.join(res, p1);
		return res;
	}

	@Override
	public A1 EInt(int p0) {
		A1 res = m1.empty();
		return res;
	}

	@Override
	public A1 EStm(A0 p0) {
		A1 res = m1.empty();
		return res;
	}

	@Override
	public A1 EVar(java.lang.String p0) {
		A1 res = m1.empty();
		return res;
	}

	@Override
	public A0 SAss(java.lang.String p0, A1 p1) {
		A0 res = m0.empty();
		return res;
	}

	@Override
	public A0 SBlock(java.util.List<A0> p0) {
		A0 res = m0.empty();
		res = m0.join(res, m0.fold(p0));
		return res;
	}

	@Override
	public A0 SDecl(A2 p0, java.lang.String p1) {
		A0 res = m0.empty();
		return res;
	}

	@Override
	public A0 SReturn(A1 p0) {
		A0 res = m0.empty();
		return res;
	}

	@Override
	public A2 TFloat() {
		A2 res = m2.empty();
		return res;
	}

	@Override
	public A2 TInt() {
		A2 res = m2.empty();
		return res;
	}

}