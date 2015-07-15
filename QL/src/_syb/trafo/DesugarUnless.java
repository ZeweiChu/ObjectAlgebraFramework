package _syb.trafo;

import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IStmtAlg;
import transform.IUnlessAlgTransform;

public interface DesugarUnless<E, S> extends IUnlessAlgTransform<E, S>, IExpAlg<E>, IStmtAlg<E, S> {
	@Override 
	default S unless(E cond, S body) {
		return iff(not(cond), body);
	}
}
