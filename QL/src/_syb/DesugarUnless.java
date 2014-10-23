package _syb;

import generic.G_IExpAlg_E;

import java.util.List;

import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IStmtAlg;
import ql_obj_alg.syntax.IUnlessAlg;

public class DesugarUnless {
	
	static class Desugar implements IUnlessAlgTransformManySorted {
		@Override
		public G_IUnlessAlg_S unless(G_IExpAlg_E p0, List<G_IUnlessAlg_S> p1) {
			return new G_IUnlessAlg_S() {
				
				@Override
				public <E, S> S accept(IExpAlg<E> expAlg, IStmtAlg<E, S> stmtAlg, IUnlessAlg<E, S> alg) {
					java.util.List<S> gp1 = new java.util.ArrayList<S>();
					for (G_IUnlessAlg_S s: p1) {
						gp1.add(s.accept(expAlg, stmtAlg, alg));
					}
					return stmtAlg.iff(expAlg.not(p0.accept(expAlg)), gp1);
				}
			};
		}
	}

}
