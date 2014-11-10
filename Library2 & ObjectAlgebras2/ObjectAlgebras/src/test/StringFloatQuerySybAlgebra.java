package test;

import query.CombineSybAlg;
import trees.SybAlg;

public class StringFloatQuerySybAlgebra extends CombineSybAlg<String, String, String, String, String, String, Float, Float, Float, Float, Float, Float> {

	public StringFloatQuerySybAlgebra(SybAlg<String, String, String, String, String, String> query1, SybAlg<Float, Float, Float, Float, Float, Float> query2) {
		super(query1, query2);
	}

}
