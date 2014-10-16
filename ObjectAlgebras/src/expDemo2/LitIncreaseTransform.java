package expDemo2;

//BEGIN_TRANSFORM
public class LitIncreaseTransform implements ExpAlg<G_Exp>{
	@Override
	public G_Exp Add(G_Exp e1, G_Exp e2) {
		return new G_Exp() {
			@Override
			public <Exp> Exp accept(ExpAlg<Exp> alg) {
				return alg.Add(e1.accept(alg), e2.accept(alg));
			}
		};
	}
	@Override
	public G_Exp Lit(int i) {
		return new G_Exp() {
			@Override
			public <Exp> Exp accept(ExpAlg<Exp> alg) {
				return alg.Lit(i*2);
			}
		};
	}
	@Override
	public G_Exp Var(String s) {
		return new G_Exp() {
			@Override
			public <Exp> Exp accept(ExpAlg<Exp> alg) {
				return alg.Var(s);
			}
		};
	}
}
//END_TRANSFORM