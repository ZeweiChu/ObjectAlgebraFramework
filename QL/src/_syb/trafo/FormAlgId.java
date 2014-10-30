package _syb.trafo;

import java.util.List;

import ql_obj_alg.syntax.IFormAlg;

public interface FormAlgId<E, S, F> extends IFormAlg<E, S, F> {
	IFormAlg<E, S, F> formAlg();

	@Override
	default F form(String id, List<S> statements) {
		return formAlg().form(id, statements);
	}

}
