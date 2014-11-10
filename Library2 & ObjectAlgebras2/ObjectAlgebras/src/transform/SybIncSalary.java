package transform;

import trees.SybAlg;

//BEGIN_TRANSFORM_WITH_OAFRAMEWORK
public class SybIncSalary extends SybAlgTransform<Float, Float, Float, Float, Float, Float> {

	public SybIncSalary(SybAlg<Float, Float, Float, Float, Float, Float> alg) {
		super(alg);
	}

	public Float S(float salary) {
		return alg.S(1.1f * salary);
	}
	
}
//END_TRANSFORM_WITH_OAFRAMEWORK