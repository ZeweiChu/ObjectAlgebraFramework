package _syb.trafo;

import java.util.List;

import ql_obj_alg.syntax.IUnlessAlg;

public interface UnlessAlgId<E, S> extends IUnlessAlg<E, S>, StmtAlgId<E, S> {

	// NB: refining the return type.
	@Override
	public IUnlessAlg<E, S> stmtAlg();
	
	@Override
	default S unless(E cond, List<S> body) {
		return stmtAlg().unless(cond, body);
	}

}
