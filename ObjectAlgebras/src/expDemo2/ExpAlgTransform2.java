package expDemo2;

import trees.ExpAlg;
import generic.*;

public interface ExpAlgTransform2 extends ExpAlg<G_Exp> {
	@Override
	default G_Exp Add(G_Exp e1, G_Exp e2) {
		return new G_Exp() {
			@Override
			public <Exp> Exp accept(ExpAlg<Exp> alg) {
				return alg.Add(e1.accept(alg), e2.accept(alg));
			}
		};
	}
	@Override
	default G_Exp Lit(int i) {
		return new G_Exp() {
			@Override
			public <Exp> Exp accept(ExpAlg<Exp> alg) {
				return alg.Lit(i);
			}
		};
	}
	@Override
	default G_Exp Var(String s) {
		return new G_Exp() {
			@Override
			public <Exp> Exp accept(ExpAlg<Exp> alg) {
				return alg.Var(s);
			}
		};
	}
}