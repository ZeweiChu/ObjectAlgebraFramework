package expDemo1;

import generic.G_Exp;
import trees.ExpAlg;

public class SubstVarsExpAlg1 implements ExpAlg<G_Exp>{
	private String s;
	private G_Exp gExp;
	
	public SubstVarsExpAlg1(String s, G_Exp gExp){
		this.s = s;
		this.gExp = gExp;
	}
	
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
				return alg.Lit(i);
			}
		};
	}
	@Override
	public G_Exp Var(String ss) {
		return new G_Exp() {
			@Override
			public <Exp> Exp accept(ExpAlg<Exp> alg) {
				if (ss.equals(s)) return gExp.accept(alg); 
				else return alg.Var(ss);
			}
		};
	}
}
