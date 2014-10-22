package transform;

import trees.StmAlg;
import generic.*;

public interface StmAlgTransform extends StmAlg<G_StmAlg_Stm, G_StmAlg_Exp, G_StmAlg_Typ> {
	@Override
	default G_StmAlg_Exp EAdd(G_StmAlg_Exp p0, G_StmAlg_Exp p1) {
		return new G_StmAlg_Exp() {
			@Override
			public <Stm,Exp,Typ> Exp accept(StmAlg<Stm,Exp,Typ> alg) {
				return alg.EAdd(p0.accept(alg), p1.accept(alg));
			}
		};
	}
	@Override
	default G_StmAlg_Exp EInt(int p0) {
		return new G_StmAlg_Exp() {
			@Override
			public <Stm,Exp,Typ> Exp accept(StmAlg<Stm,Exp,Typ> alg) {
				return alg.EInt(p0);
			}
		};
	}
	@Override
	default G_StmAlg_Exp EStm(G_StmAlg_Stm p0) {
		return new G_StmAlg_Exp() {
			@Override
			public <Stm,Exp,Typ> Exp accept(StmAlg<Stm,Exp,Typ> alg) {
				return alg.EStm(p0.accept(alg));
			}
		};
	}
	@Override
	default G_StmAlg_Exp EVar(java.lang.String p0) {
		return new G_StmAlg_Exp() {
			@Override
			public <Stm,Exp,Typ> Exp accept(StmAlg<Stm,Exp,Typ> alg) {
				return alg.EVar(p0);
			}
		};
	}
	@Override
	default G_StmAlg_Stm SAss(java.lang.String p0, G_StmAlg_Exp p1) {
		return new G_StmAlg_Stm() {
			@Override
			public <Stm,Exp,Typ> Stm accept(StmAlg<Stm,Exp,Typ> alg) {
				return alg.SAss(p0, p1.accept(alg));
			}
		};
	}
	@Override
	default G_StmAlg_Stm SBlock(java.util.List<G_StmAlg_Stm> p0) {
		return new G_StmAlg_Stm() {
			@Override
			public <Stm,Exp,Typ> Stm accept(StmAlg<Stm,Exp,Typ> alg) {
				java.util.List<Stm> gp0 = new java.util.ArrayList<Stm>();
				for (G_StmAlg_Stm s: p0) {
					gp0.add(s.accept(alg));
				}
				return alg.SBlock(gp0);
			}
		};
	}
	@Override
	default G_StmAlg_Stm SDecl(G_StmAlg_Typ p0, java.lang.String p1) {
		return new G_StmAlg_Stm() {
			@Override
			public <Stm,Exp,Typ> Stm accept(StmAlg<Stm,Exp,Typ> alg) {
				return alg.SDecl(p0.accept(alg), p1);
			}
		};
	}
	@Override
	default G_StmAlg_Stm SReturn(G_StmAlg_Exp p0) {
		return new G_StmAlg_Stm() {
			@Override
			public <Stm,Exp,Typ> Stm accept(StmAlg<Stm,Exp,Typ> alg) {
				return alg.SReturn(p0.accept(alg));
			}
		};
	}
	@Override
	default G_StmAlg_Typ TFloat() {
		return new G_StmAlg_Typ() {
			@Override
			public <Stm,Exp,Typ> Typ accept(StmAlg<Stm,Exp,Typ> alg) {
				return alg.TFloat();
			}
		};
	}
	@Override
	default G_StmAlg_Typ TInt() {
		return new G_StmAlg_Typ() {
			@Override
			public <Stm,Exp,Typ> Typ accept(StmAlg<Stm,Exp,Typ> alg) {
				return alg.TInt();
			}
		};
	}
}