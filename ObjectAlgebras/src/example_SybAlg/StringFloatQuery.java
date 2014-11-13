package example_SybAlg;

import trees.SybAlg;
import combinator.CombineSybAlg;

public class StringFloatQuery extends CombineSybAlg<String, String, String, String, String, String, Float, Float, Float, Float, Float, Float> {

	public StringFloatQuery(
			SybAlg<String, String, String, String, String, String> _alg1,
			SybAlg<Float, Float, Float, Float, Float, Float> _alg2) {
		super(_alg1, _alg2);
	}

}
