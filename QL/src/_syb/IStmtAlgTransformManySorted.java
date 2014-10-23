package _syb;

import generic.G_IExpAlg_E;
import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IStmtAlg;

public interface IStmtAlgTransformManySorted extends IStmtAlg<G_IExpAlg_E, G_IStmtAlg_S> {
	@Override
	default G_IStmtAlg_S iff(G_IExpAlg_E p0, java.util.List<G_IStmtAlg_S> p1) {
		return new G_IStmtAlg_S() {
			@Override
			public <E, S> S accept(IExpAlg<E> expAlg, IStmtAlg<E, S> alg) {
				java.util.List<S> gp1 = new java.util.ArrayList<S>();
				for (G_IStmtAlg_S s : p1) {
					gp1.add(s.accept(expAlg, alg));
				}
				return alg.iff(p0.accept(expAlg), gp1);
			}
		};
	}

	@Override
	default G_IStmtAlg_S iffelse(G_IExpAlg_E p0,
			java.util.List<G_IStmtAlg_S> p1, java.util.List<G_IStmtAlg_S> p2) {
		return new G_IStmtAlg_S() {
			@Override
			public <E, S> S accept(IExpAlg<E> expAlg, IStmtAlg<E, S> alg) {
				java.util.List<S> gp1 = new java.util.ArrayList<S>();
				for (G_IStmtAlg_S s : p1) {
					gp1.add(s.accept(expAlg, alg));
				}
				java.util.List<S> gp2 = new java.util.ArrayList<S>();
				for (G_IStmtAlg_S s : p2) {
					gp2.add(s.accept(expAlg, alg));
				}
				return alg.iffelse(p0.accept(expAlg), gp1, gp2);
			}
		};
	}

	@Override
	default G_IStmtAlg_S question(java.lang.String p0, java.lang.String p1,
			ql_obj_alg.check.types.Type p2) {
		return new G_IStmtAlg_S() {
			@Override
			public <E, S> S accept(IExpAlg<E> expAlg, IStmtAlg<E, S> alg) {
				return alg.question(p0, p1, p2);
			}
		};
	}

	@Override
	default G_IStmtAlg_S question(java.lang.String p0, java.lang.String p1,
			ql_obj_alg.check.types.Type p2, G_IExpAlg_E p3) {
		return new G_IStmtAlg_S() {
			@Override
			public <E, S> S accept(IExpAlg<E> expAlg, IStmtAlg<E, S> alg) {
				return alg.question(p0, p1, p2, p3.accept(expAlg));
			}
		};
	}
}
