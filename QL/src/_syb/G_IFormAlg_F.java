package _syb;

import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IFormAlg;
import ql_obj_alg.syntax.IStmtAlg;

public interface G_IFormAlg_F {
	<E, S, F> F accept(IExpAlg<E> expAlg, IStmtAlg<E, S> stmtAlg, IFormAlg<E, S, F> formAlg);
}
