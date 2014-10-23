package _syb;

import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IStmtAlg;

public interface G_IStmtAlg_S {
	<E,S> S accept(IExpAlg<E> expAlg, IStmtAlg<E,S> alg);
}