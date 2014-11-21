package expDemo4;

import transform.ExpAlgTransform;
import trees.ExpAlg;

class LitIncreaseTransform implements ExpAlgTransform<Integer> {
	private ExpAlg<Integer> alg;
	public LitIncreaseTransform(ExpAlg<Integer> alg) {this.alg = alg;}
	public ExpAlg<Integer> expAlg() {return alg;}
	public Integer Lit(int p0) {return alg.Lit(p0 * 2);}
}