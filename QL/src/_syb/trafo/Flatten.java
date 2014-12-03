package _syb.trafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import ql_obj_alg.check.types.Type;
import transform.G_IExpAlgTransform;
import transform.G_IFormAlgTransform;
import transform.G_IStmtAlgTransform;

public interface Flatten<E, S> extends
	G_IFormAlgTransform<E, Function<E, List<S>>, E, List<S>>,
	G_IStmtAlgTransform<E, E, List<S>>, 
	G_IExpAlgTransform<E, E> {

	// It's not structure shy, it would be, for instance, if we'd have a block() constructor...
	
	@Override 
	default Function<E, List<S>> iff(Function<E, E> p0, List<Function<E, List<S>>> p1) {
		return iffelse(p0, p1, Collections.emptyList());
	}
	
	@Override 
	default Function<E, List<S>> iffelse(Function<E,E> p0, List<Function<E, List<S>>> p1,List<Function<E, List<S>>> p2) {
		return (guard) -> {
			E conj1 =  iExpAlg().and(guard, p0.apply(guard));
			List<S> ss = new ArrayList<>();
			for (Function<E, List<S>> f: p1) {
				ss.addAll(f.apply(conj1));
			}
			E conj2 = iExpAlg().and(guard, iExpAlg().not(p0.apply(guard)));
			for (Function<E, List<S>> f: p2) {
				ss.addAll(f.apply(conj2));
			}
			return ss;
		};
	}
	

	@Override
	default Function<E, List<S>> question(String id, String label, Type type) {
		return (guard) -> {
			return Collections.singletonList(iStmtAlg().iff(guard, Collections.singletonList(iStmtAlg().question(id, label, type))));
		};
	}
	
	@Override
	default Function<E, List<S>> question(String id, String label, Type type, Function<E,E> exp) {
		return (guard) -> {
			return Collections.singletonList(iStmtAlg().iff(guard, Collections.singletonList(iStmtAlg().question(id, label, type, exp.apply(guard)))));
		};
	}
}
