package _syb.trafo;

import java.util.List;

import ql_obj_alg.syntax.IUnlessAlg;

public interface UnlessAlgId<E, S> extends IUnlessAlg<E, S> {

	IUnlessAlg<E, S> unlessAlg();
	
	@Override
	default S unless(E cond, List<S> body) {
		return unlessAlg().unless(cond, body);
	}

}
