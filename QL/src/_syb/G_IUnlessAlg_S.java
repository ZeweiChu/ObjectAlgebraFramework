package _syb;

import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IStmtAlg;
import ql_obj_alg.syntax.IUnlessAlg;

public interface G_IUnlessAlg_S {
	<E,S> S accept(IExpAlg<E> expAlg, IStmtAlg<E, S> stmtAlg, IUnlessAlg<E,S> alg);
}