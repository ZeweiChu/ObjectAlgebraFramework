package _syb.trafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import _syb.trafo.g_stuff.G_IExpAlgTransform;
import _syb.trafo.g_stuff.G_IFormAlgTransform;
import _syb.trafo.g_stuff.G_IStmtAlgTransform;
import ql_obj_alg.check.types.Type;
import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IFormAlg;
import ql_obj_alg.syntax.IStmtAlg;

public interface Flatten<E, S> extends
	G_IFormAlgTransform<E, Function<E, List<S>>, E, List<S>>,
	G_IStmtAlgTransform<E, E, List<S>>,
	G_IExpAlgTransform<E, E> {

	
//	IStmtAlg<E, List<S>> iStmtAlg();
//	IExpAlg<E> iExpAlg();
//	IFormAlg<E, S, List<S>> iFormAlg();
	
	// It's not structure shy, it would be, for instance, if we'd have a block() constructor...
	
	@Override 
	default List<List<S>> substList(List<Function<E, List<S>>> list,E acc) {
		return null;
	}
	
	@Override 
	default Function<E, List<S>> iff(E p0, List<Function<E, List<S>>> p1) {
		return iffelse(p0, p1, Collections.emptyList());
	}
	
	
	@Override 
	default Function<E, List<S>> iffelse(E p0, List<Function<E, List<S>>> p1,List<Function<E, List<S>>> p2) {
		return (guard) -> {
			E conj1 =  iExpAlg().and(guard, p0);
			List<S> ss = new ArrayList<>();
			for (Function<E, List<S>> f: p1) {
				ss.addAll(f.apply(conj1));
			}
			E conj2 = iExpAlg().and(guard, iExpAlg().not(p0));
			for (Function<E, List<S>> f: p2) {
				ss.addAll(f.apply(conj2));
			}
			return ss;
		};
	}
	

	@Override
	default Function<E, List<S>> question(String id, String label, Type type) {
		return (guard) -> {
			return iStmtAlg().iff(guard, Collections.singletonList(iStmtAlg().question(id, label, type)));
		};
	}
	
	@Override
	default Function<E, List<S>> question(String id, String label, Type type, E exp) {
		return (guard) -> {
			return iStmtAlg().iff(guard, Collections.singletonList(iStmtAlg().question(id, label, type, exp)));
		};
	}
}
