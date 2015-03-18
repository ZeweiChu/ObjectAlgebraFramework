package query;

import library.Monoid;
import trees.FJAlg;

public interface FJAlgQuery<R> extends FJAlg<R, R, R, R, R, R, R, R> {

	Monoid<R> m();

	default R Cast(R p0, R p1) {
		R res = m().empty();
		res = m().join(res, p0);
		res = m().join(res, p1);
		return res;
	}

	default R ClassDec(java.lang.String p0, java.util.List<manual.Pair<X,N>> p1, R p2, java.util.List<manual.Pair<T,java.lang.String>> p3, R p4, java.util.List<R> p5) {
		R res = m().empty();
		res = m().join(res, p2);
		res = m().join(res, p4);
		res = m().join(res, m().fold(p5));
		return res;
	}

	default R ClassType(java.lang.String p0, java.util.List<R> p1) {
		R res = m().empty();
		res = m().join(res, m().fold(p1));
		return res;
	}

	default R Code(java.util.List<R> p0) {
		R res = m().empty();
		res = m().join(res, m().fold(p0));
		return res;
	}

	default R ConstrDec(java.lang.String p0, java.util.List<manual.Pair<T,java.lang.String>> p1) {
		R res = m().empty();
		return res;
	}

	default R FieldAccess(R p0, java.lang.String p1) {
		R res = m().empty();
		res = m().join(res, p0);
		return res;
	}

	default R MethodDec(java.util.List<manual.Pair<X,N>> p0, R p1, java.lang.String p2, java.util.List<manual.Pair<T,java.lang.String>> p3, R p4) {
		R res = m().empty();
		res = m().join(res, p1);
		res = m().join(res, p4);
		return res;
	}

	default R MethodInvoke(R p0, java.lang.String p1, java.util.List<R> p2, java.util.List<R> p3) {
		R res = m().empty();
		res = m().join(res, p0);
		res = m().join(res, m().fold(p2));
		res = m().join(res, m().fold(p3));
		return res;
	}

	default R ObjectCreate(R p0, java.util.List<R> p1) {
		R res = m().empty();
		res = m().join(res, p0);
		res = m().join(res, m().fold(p1));
		return res;
	}

	default R TVar(java.lang.String p0) {
		R res = m().empty();
		return res;
	}

	default R TypeN(R p0) {
		R res = m().empty();
		res = m().join(res, p0);
		return res;
	}

	default R TypeX(R p0) {
		R res = m().empty();
		res = m().join(res, p0);
		return res;
	}

	default R Var(java.lang.String p0) {
		R res = m().empty();
		return res;
	}

}