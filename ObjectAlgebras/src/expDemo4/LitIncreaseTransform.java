package expDemo4;

import transform.ExpAlgTransform;
import trees.ExpAlg;

class LitIncreaseTransform extends ExpAlgTransform<Integer> {

	public LitIncreaseTransform(ExpAlg<Integer> alg) {
		super(alg);
	}

	@Override
	public Integer Lit(int p0) {
		return alg.Lit(p0 * 2);
	}
	
}