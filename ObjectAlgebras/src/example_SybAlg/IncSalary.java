package example_SybAlg;

import transform.SybAlgTransform;
import trees.SybAlg;

//BEGIN_TRANSFORM_WITH_OAFRAMEWORK
public class IncSalary extends SybAlgTransform<Float, Float, Float, Float, Float, Float> {
	public IncSalary(SybAlg<Float, Float, Float, Float, Float, Float> alg) {super(alg);}
	public Float S(float salary) {return alg.S(1.1f * salary);}
}
//END_TRANSFORM_WITH_OAFRAMEWORK