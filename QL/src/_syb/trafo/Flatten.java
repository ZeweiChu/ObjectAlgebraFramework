package _syb.trafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import ql_obj_alg.check.types.Type;
import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IFormAlg;
import ql_obj_alg.syntax.IStmtAlg;

public interface Flatten<E, S> extends IFormAlg<E, Function<E, List<S>>, Function<E, List<S>>>, IStmtAlg<E, Function<E, List<S>>>, IExpAlg<E> {
	
	IStmtAlg<E, S> iStmtAlg();
	IExpAlg<E> iExpAlg();
	IFormAlg<E, S, List<S>> iFormAlg();
	
	// It's not structure shy, it would be, for instance, if we'd have a block() constructor...
	
	@Override
	default Function<E, List<S>> iff(E cond, List<Function<E, List<S>>> statements) {
		return iffelse(cond, statements, Collections.emptyList());
	}

	@Override
	default Function<E, List<S>> iffelse(E cond, List<Function<E, List<S>>> statementsIf, List<Function<E, List<S>>> statementsElse) {
		return (guard) -> {
			E conj1 = and(guard, cond);
			List<S> ss = new ArrayList<>();
			for (Function<E, List<S>> f: statementsIf) {
				ss.addAll(f.apply(conj1));
			}
			E conj2 = and(guard, not(cond));
			for (Function<E, List<S>> f: statementsElse) {
				ss.addAll(f.apply(conj2));
			}
			return ss;
		};
	}

	@Override
	default Function<E, List<S>> question(String id, String label, Type type) {
		return (guard) -> {
			return Collections.singletonList(iStmtAlg().iff(guard, 
					Collections.singletonList(iStmtAlg().question(id, label, type))));
		};
	}
	
	@Override
	default Function<E, List<S>> question(String id, String label, Type type, E exp) {
		return (guard) -> {
			return Collections.singletonList(iStmtAlg().iff(guard, 
					Collections.singletonList(iStmtAlg().question(id, label, type, exp))));
		};
	}
}
