package _syb;

import generic.G_IExpAlg_E;
import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IFormAlg;
import ql_obj_alg.syntax.IStmtAlg;

public interface IFormAlgTransformManySorted extends IFormAlg<G_IExpAlg_E, G_IStmtAlg_S, G_IFormAlg_F> {
	@Override
	default G_IFormAlg_F form(java.lang.String p0, java.util.List<G_IStmtAlg_S> p1) {
		return new G_IFormAlg_F() {
			@Override
			public <E,S,F> F accept(IExpAlg<E> expAlg, IStmtAlg<E, S> stmtAlg, IFormAlg<E,S,F> alg) {
				java.util.List<S> gp1 = new java.util.ArrayList<S>();
				for (G_IStmtAlg_S s: p1) {
					gp1.add(s.accept(expAlg, stmtAlg));
				}
				return alg.form(p0, gp1);
			}
		};
	}
}