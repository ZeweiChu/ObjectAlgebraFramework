package example_SybAlg;

import transform.SybAlgTransform;
import trees.SybAlg;

//BEGIN_TRANSFORM_WITH_OAFRAMEWORK
public class Rename extends SybAlgTransform<String, String, String, String, String, String> {

	public Rename(SybAlg<String, String, String, String, String, String> alg) {
		super(alg);
	}
	
	public String P(String name, String address) {
		return alg.P("_" + name, address);
	}

}
//END_TRANSFORM_WITH_OAFRAMEWORK