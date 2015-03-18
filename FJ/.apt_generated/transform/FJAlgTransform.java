package transform;

import trees.FJAlg;

public interface FJAlgTransform<A0, A1, A2, A3, A4, A5, A6, A7> extends FJAlg<A0, A1, A2, A3, A4, A5, A6, A7> {

	FJAlg<A0, A1, A2, A3, A4, A5, A6, A7> fJAlg();

	@Override
	default A6 Cast(A2 p0, A6 p1) {
		return fJAlg().Cast(p0, p1);
	}

	@Override
	default A3 ClassDec(java.lang.String p0, java.util.List<manual.Pair<X,N>> p1, A2 p2, java.util.List<manual.Pair<T,java.lang.String>> p3, A4 p4, java.util.List<A5> p5) {
		return fJAlg().ClassDec(p0, p1, p2, p3, p4, p5);
	}

	@Override
	default A2 ClassType(java.lang.String p0, java.util.List<A1> p1) {
		return fJAlg().ClassType(p0, p1);
	}

	@Override
	default A0 Code(java.util.List<A3> p0) {
		return fJAlg().Code(p0);
	}

	@Override
	default A4 ConstrDec(java.lang.String p0, java.util.List<manual.Pair<T,java.lang.String>> p1) {
		return fJAlg().ConstrDec(p0, p1);
	}

	@Override
	default A6 FieldAccess(A6 p0, java.lang.String p1) {
		return fJAlg().FieldAccess(p0, p1);
	}

	@Override
	default A5 MethodDec(java.util.List<manual.Pair<X,N>> p0, A1 p1, java.lang.String p2, java.util.List<manual.Pair<T,java.lang.String>> p3, A6 p4) {
		return fJAlg().MethodDec(p0, p1, p2, p3, p4);
	}

	@Override
	default A6 MethodInvoke(A6 p0, java.lang.String p1, java.util.List<A1> p2, java.util.List<A6> p3) {
		return fJAlg().MethodInvoke(p0, p1, p2, p3);
	}

	@Override
	default A6 ObjectCreate(A2 p0, java.util.List<A6> p1) {
		return fJAlg().ObjectCreate(p0, p1);
	}

	@Override
	default A7 TVar(java.lang.String p0) {
		return fJAlg().TVar(p0);
	}

	@Override
	default A1 TypeN(A2 p0) {
		return fJAlg().TypeN(p0);
	}

	@Override
	default A1 TypeX(A7 p0) {
		return fJAlg().TypeX(p0);
	}

	@Override
	default A6 Var(java.lang.String p0) {
		return fJAlg().Var(p0);
	}

}