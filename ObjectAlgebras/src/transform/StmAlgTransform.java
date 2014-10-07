package transform;

import trees.StmAlg;
import generic.*;

public interface StmAlgTransform extends StmAlg<G_Stm, G_EExp, G_Typ> {
	@Override
	default G_EExp EAdd(G_EExp p0, G_EExp p1) {
		return new G_EExp() {
			@Override
			public <Stm,EExp,Typ> EExp accept(StmAlg<Stm,EExp,Typ> alg) {
				return alg.EAdd(p0.accept(alg), p1.accept(alg));
			}
		};
	}
	@Override
	default G_EExp EInt(int p0) {
		return new G_EExp() {
			@Override
			public <Stm,EExp,Typ> EExp accept(StmAlg<Stm,EExp,Typ> alg) {
				return alg.EInt(p0);
			}
		};
	}
	@Override
	default G_EExp EStm(G_Stm p0) {
		return new G_EExp() {
			@Override
			public <Stm,EExp,Typ> EExp accept(StmAlg<Stm,EExp,Typ> alg) {
				return alg.EStm(p0.accept(alg));
			}
		};
	}
	@Override
	default G_EExp EVar(java.lang.String p0) {
		return new G_EExp() {
			@Override
			public <Stm,EExp,Typ> EExp accept(StmAlg<Stm,EExp,Typ> alg) {
				return alg.EVar(p0);
			}
		};
	}
	@Override
	default G_Stm SAss(java.lang.String p0, G_EExp p1) {
		return new G_Stm() {
			@Override
			public <Stm,EExp,Typ> Stm accept(StmAlg<Stm,EExp,Typ> alg) {
				return alg.SAss(p0, p1.accept(alg));
			}
		};
	}
	@Override
	default G_Stm SBlock(java.util.List<G_Stm> p0) {
		return new G_Stm() {
			@Override
			public <Stm,EExp,Typ> Stm accept(StmAlg<Stm,EExp,Typ> alg) {
				java.util.List<Stm> gp0 = new java.util.ArrayList<Stm>();
				for (G_Stm s: p0) {
					gp0.add(s.accept(alg));
				}
				return alg.SBlock(gp0);
			}
		};
	}
	@Override
	default G_Stm SDecl(G_Typ p0, java.lang.String p1) {
		return new G_Stm() {
			@Override
			public <Stm,EExp,Typ> Stm accept(StmAlg<Stm,EExp,Typ> alg) {
				return alg.SDecl(p0.accept(alg), p1);
			}
		};
	}
	@Override
	default G_Stm SReturn(G_EExp p0) {
		return new G_Stm() {
			@Override
			public <Stm,EExp,Typ> Stm accept(StmAlg<Stm,EExp,Typ> alg) {
				return alg.SReturn(p0.accept(alg));
			}
		};
	}
	@Override
	default G_Typ TFloat() {
		return new G_Typ() {
			@Override
			public <Stm,EExp,Typ> Typ accept(StmAlg<Stm,EExp,Typ> alg) {
				return alg.TFloat();
			}
		};
	}
	@Override
	default G_Typ TInt() {
		return new G_Typ() {
			@Override
			public <Stm,EExp,Typ> Typ accept(StmAlg<Stm,EExp,Typ> alg) {
				return alg.TInt();
			}
		};
	}
}