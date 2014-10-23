package _syb;

import generic.G_IExpAlg_E;
import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IStmtAlg;
import ql_obj_alg.syntax.IUnlessAlg;

public interface IUnlessAlgTransformManySorted extends IUnlessAlg<G_IExpAlg_E, G_IUnlessAlg_S> {
	@Override
	default G_IUnlessAlg_S unless(G_IExpAlg_E p0, java.util.List<G_IUnlessAlg_S> p1) {
		return new G_IUnlessAlg_S() {
			@Override
			public <E, S> S accept(IExpAlg<E> expAlg, IStmtAlg<E, S> stmtAlg, IUnlessAlg<E, S> alg) {
				java.util.List<S> gp1 = new java.util.ArrayList<S>();
				for (G_IUnlessAlg_S s: p1) {
					gp1.add(s.accept(expAlg, stmtAlg, alg));
				}
				return alg.unless(p0.accept(expAlg), gp1);
			}
		};
	}
}