package combinator;

import java.util.ArrayList;
import java.util.List;
import library.Pair;
import trees.FJAlg;

public class CombineFJAlg<A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, B0, B1, B2, B3, B4, B5, B6, B7, B8, B9>
	implements FJAlg<Pair<A0, B0>, Pair<A1, B1>, Pair<A2, B2>, Pair<A3, B3>, Pair<A4, B4>, Pair<A5, B5>, Pair<A6, B6>, Pair<A7, B7>, Pair<A8, B8>, Pair<A9, B9>> {

	public FJAlg<A0, A1, A2, A3, A4, A5, A6, A7, A8, A9> alg1;
	public FJAlg<B0, B1, B2, B3, B4, B5, B6, B7, B8, B9> alg2;

	public CombineFJAlg(FJAlg<A0, A1, A2, A3, A4, A5, A6, A7, A8, A9> _alg1, FJAlg<B0, B1, B2, B3, B4, B5, B6, B7, B8, B9> _alg2) {
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

	public Pair<A6, B6> Cast(Pair<A2, B2> p0, Pair<A6, B6> p1) {
		return new Pair<A6, B6>(alg1.Cast(p0.a(), p1.a()), alg2.Cast(p0.b(), p1.b()));
	}

	public Pair<A3, B3> ClassDec(java.lang.String p0, List<Pair<A8, B8>> p1, Pair<A2, B2> p2, List<Pair<A9, B9>> p3, Pair<A4, B4> p4, List<Pair<A5, B5>> p5) {
		return new Pair<A3, B3>(alg1.ClassDec(p0, getPairList(p1).a(), p2.a(), getPairList(p3).a(), p4.a(), getPairList(p5).a()), alg2.ClassDec(p0, getPairList(p1).b(), p2.b(), getPairList(p3).b(), p4.b(), getPairList(p5).b()));
	}

	public Pair<A2, B2> ClassType(java.lang.String p0, List<Pair<A1, B1>> p1) {
		return new Pair<A2, B2>(alg1.ClassType(p0, getPairList(p1).a()), alg2.ClassType(p0, getPairList(p1).b()));
	}

	public Pair<A0, B0> Code(List<Pair<A3, B3>> p0) {
		return new Pair<A0, B0>(alg1.Code(getPairList(p0).a()), alg2.Code(getPairList(p0).b()));
	}

	public Pair<A4, B4> ConstrDec(java.lang.String p0, List<Pair<A9, B9>> p1) {
		return new Pair<A4, B4>(alg1.ConstrDec(p0, getPairList(p1).a()), alg2.ConstrDec(p0, getPairList(p1).b()));
	}

	public Pair<A6, B6> FieldAccess(Pair<A6, B6> p0, java.lang.String p1) {
		return new Pair<A6, B6>(alg1.FieldAccess(p0.a(), p1), alg2.FieldAccess(p0.b(), p1));
	}

	public Pair<A5, B5> MethodDec(List<Pair<A8, B8>> p0, Pair<A1, B1> p1, java.lang.String p2, List<Pair<A9, B9>> p3, Pair<A6, B6> p4) {
		return new Pair<A5, B5>(alg1.MethodDec(getPairList(p0).a(), p1.a(), p2, getPairList(p3).a(), p4.a()), alg2.MethodDec(getPairList(p0).b(), p1.b(), p2, getPairList(p3).b(), p4.b()));
	}

	public Pair<A6, B6> MethodInvoke(Pair<A6, B6> p0, java.lang.String p1, List<Pair<A1, B1>> p2, List<Pair<A6, B6>> p3) {
		return new Pair<A6, B6>(alg1.MethodInvoke(p0.a(), p1, getPairList(p2).a(), getPairList(p3).a()), alg2.MethodInvoke(p0.b(), p1, getPairList(p2).b(), getPairList(p3).b()));
	}

	public Pair<A6, B6> ObjectCreate(Pair<A2, B2> p0, List<Pair<A6, B6>> p1) {
		return new Pair<A6, B6>(alg1.ObjectCreate(p0.a(), getPairList(p1).a()), alg2.ObjectCreate(p0.b(), getPairList(p1).b()));
	}

	public Pair<A7, B7> TVar(java.lang.String p0) {
		return new Pair<A7, B7>(alg1.TVar(p0), alg2.TVar(p0));
	}

	public Pair<A1, B1> TypeN(Pair<A2, B2> p0) {
		return new Pair<A1, B1>(alg1.TypeN(p0.a()), alg2.TypeN(p0.b()));
	}

	public Pair<A1, B1> TypeX(Pair<A7, B7> p0) {
		return new Pair<A1, B1>(alg1.TypeX(p0.a()), alg2.TypeX(p0.b()));
	}

	public Pair<A6, B6> Var(java.lang.String p0) {
		return new Pair<A6, B6>(alg1.Var(p0), alg2.Var(p0));
	}

}