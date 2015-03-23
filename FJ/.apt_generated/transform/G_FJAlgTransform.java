package transform;

import java.util.function.Function;
import java.util.List;
import java.util.ArrayList;
import trees.FJAlg;

public interface G_FJAlgTransform<A, B0, B1, B2, B3, B4, B5, B6, B7, B8, B9> extends FJAlg<Function<A, B0>, Function<A, B1>, Function<A, B2>, Function<A, B3>, Function<A, B4>, Function<A, B5>, Function<A, B6>, Function<A, B7>, Function<A, B8>, Function<A, B9>> {

	FJAlg<B0, B1, B2, B3, B4, B5, B6, B7, B8, B9> fJAlg();

	default <B> List<B> substListFJAlg(List<Function<A, B>> list, A acc) {
		List<B> res = new ArrayList<B>();
		for (Function<A, B> i : list)
			res.add(i.apply(acc));
		return res;
	}

	@Override
	default Function<A, B6> Cast(Function<A, B2> p0, Function<A, B6> p1) {
		return acc -> fJAlg().Cast(p0.apply(acc), p1.apply(acc));
	}

	@Override
	default Function<A, B3> ClassDec(java.lang.String p0, List<Function<A, B8>> p1, Function<A, B2> p2, List<Function<A, B9>> p3, Function<A, B4> p4, List<Function<A, B5>> p5) {
		return acc -> fJAlg().ClassDec(p0, substListFJAlg(p1, acc), p2.apply(acc), substListFJAlg(p3, acc), p4.apply(acc), substListFJAlg(p5, acc));
	}

	@Override
	default Function<A, B2> ClassType(java.lang.String p0, List<Function<A, B1>> p1) {
		return acc -> fJAlg().ClassType(p0, substListFJAlg(p1, acc));
	}

	@Override
	default Function<A, B0> Code(List<Function<A, B3>> p0) {
		return acc -> fJAlg().Code(substListFJAlg(p0, acc));
	}

	@Override
	default Function<A, B4> ConstrDec(java.lang.String p0, List<Function<A, B9>> p1) {
		return acc -> fJAlg().ConstrDec(p0, substListFJAlg(p1, acc));
	}

	@Override
	default Function<A, B6> FieldAccess(Function<A, B6> p0, java.lang.String p1) {
		return acc -> fJAlg().FieldAccess(p0.apply(acc), p1);
	}

	@Override
	default Function<A, B5> MethodDec(List<Function<A, B8>> p0, Function<A, B1> p1, java.lang.String p2, List<Function<A, B9>> p3, Function<A, B6> p4) {
		return acc -> fJAlg().MethodDec(substListFJAlg(p0, acc), p1.apply(acc), p2, substListFJAlg(p3, acc), p4.apply(acc));
	}

	@Override
	default Function<A, B6> MethodInvoke(Function<A, B6> p0, java.lang.String p1, List<Function<A, B1>> p2, List<Function<A, B6>> p3) {
		return acc -> fJAlg().MethodInvoke(p0.apply(acc), p1, substListFJAlg(p2, acc), substListFJAlg(p3, acc));
	}

	@Override
	default Function<A, B6> ObjectCreate(Function<A, B2> p0, List<Function<A, B6>> p1) {
		return acc -> fJAlg().ObjectCreate(p0.apply(acc), substListFJAlg(p1, acc));
	}

	@Override
	default Function<A, B7> TVar(java.lang.String p0) {
		return acc -> fJAlg().TVar(p0);
	}

	@Override
	default Function<A, B1> TypeN(Function<A, B2> p0) {
		return acc -> fJAlg().TypeN(p0.apply(acc));
	}

	@Override
	default Function<A, B1> TypeX(Function<A, B7> p0) {
		return acc -> fJAlg().TypeX(p0.apply(acc));
	}

	@Override
	default Function<A, B6> Var(java.lang.String p0) {
		return acc -> fJAlg().Var(p0);
	}

}