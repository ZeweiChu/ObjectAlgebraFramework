package expDemo4;

import generic.G_ExpAlg_Exp;
import transform.ExpAlgTransform;
import trees.ExpAlg;

public class SubstVarsTransform implements ExpAlgTransform{
	private String s;
	private G_ExpAlg_Exp gExp; 
	
	public SubstVarsTransform(String s, G_ExpAlg_Exp gExp){
		this.s = s;
		this.gExp = gExp;
	}

	@Override
	public G_ExpAlg_Exp Var(String ss) {
		return new G_ExpAlg_Exp() {
			@Override
			public <Exp> Exp accept(ExpAlg<Exp> alg) {
				if (ss.equals(s)) return gExp.accept(alg);
				else return alg.Var(ss);
			}
		};
	}
	
}
