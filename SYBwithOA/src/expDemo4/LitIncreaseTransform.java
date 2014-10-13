package expDemo4;

import generic.G_Exp;
import transform.ExpAlgTransform;
import trees.ExpAlg;

public class LitIncreaseTransform implements ExpAlgTransform{
	@Override
	public G_Exp Lit(int p0){
		return new G_Exp(){
			@Override
			public <Exp> Exp accept(ExpAlg<Exp> alg) {
				return alg.Lit(p0*2);
			}
		};
	}
}
