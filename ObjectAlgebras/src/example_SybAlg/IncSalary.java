package example_SybAlg;

import transform.SybAlgTransform;
import trees.SybAlg;

//BEGIN_TRANSFORM_WITH_OAFRAMEWORK
public class IncSalary implements SybAlgTransform<Float, Float, Float, Float, Float, Float> {
	private SybAlg<Float, Float, Float, Float, Float, Float> alg;
	@Override
	public SybAlg<Float,Float,Float,Float,Float,Float> sybAlg() {return alg;};
	public IncSalary(SybAlg<Float, Float, Float, Float, Float, Float> alg) {this.alg=alg;}
	@Override
	public Float S(float salary) {return alg.S(1.1f * salary);}
}
//END_TRANSFORM_WITH_OAFRAMEWORK