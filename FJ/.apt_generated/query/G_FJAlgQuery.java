package query;

import library.Monoid;
import trees.FJAlg;

public interface G_FJAlgQuery<A0, A1, A2, A3, A4, A5, A6, A7> extends FJAlg<A0, A1, A2, A3, A4, A5, A6, A7> {

	Monoid<A0> mJ();
	Monoid<A1> mT();
	Monoid<A2> mN();
	Monoid<A3> mL();
	Monoid<A4> mK();
	Monoid<A5> mM();
	Monoid<A6> mE();
	Monoid<A7> mX();

	@Override
	default A6 Cast(A2 p0, A6 p1) {
		A6 res = mE().empty();
		res = mE().join(res, p1);
		return res;
	}

	@Override
	default A3 ClassDec(java.lang.String p0, java.util.List<manual.Pair<X,N>> p1, A2 p2, java.util.List<manual.Pair<T,java.lang.String>> p3, A4 p4, java.util.List<A5> p5) {
		A3 res = mL().empty();
		return res;
	}

	@Override
	default A2 ClassType(java.lang.String p0, java.util.List<A1> p1) {
		A2 res = mN().empty();
		return res;
	}

	@Override
	default A0 Code(java.util.List<A3> p0) {
		A0 res = mJ().empty();
		return res;
	}

	@Override
	default A4 ConstrDec(java.lang.String p0, java.util.List<manual.Pair<T,java.lang.String>> p1) {
		A4 res = mK().empty();
		return res;
	}

	@Override
	default A6 FieldAccess(A6 p0, java.lang.String p1) {
		A6 res = mE().empty();
		res = mE().join(res, p0);
		return res;
	}

	@Override
	default A5 MethodDec(java.util.List<manual.Pair<X,N>> p0, A1 p1, java.lang.String p2, java.util.List<manual.Pair<T,java.lang.String>> p3, A6 p4) {
		A5 res = mM().empty();
		return res;
	}

	@Override
	default A6 MethodInvoke(A6 p0, java.lang.String p1, java.util.List<A1> p2, java.util.List<A6> p3) {
		A6 res = mE().empty();
		res = mE().join(res, p0);
		res = mE().join(res, mE().fold(p3));
		return res;
	}

	@Override
	default A6 ObjectCreate(A2 p0, java.util.List<A6> p1) {
		A6 res = mE().empty();
		res = mE().join(res, mE().fold(p1));
		return res;
	}

	@Override
	default A7 TVar(java.lang.String p0) {
		A7 res = mX().empty();
		return res;
	}

	@Override
	default A1 TypeN(A2 p0) {
		A1 res = mT().empty();
		return res;
	}

	@Override
	default A1 TypeX(A7 p0) {
		A1 res = mT().empty();
		return res;
	}

	@Override
	default A6 Var(java.lang.String p0) {
		A6 res = mE().empty();
		return res;
	}

}