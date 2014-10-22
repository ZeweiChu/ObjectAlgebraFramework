package test;

import query.CombineSybAlg;
import query.QuerySybAlg;

public class StringFloatQuerySybAlgebra extends CombineSybAlg<String, String, String, String, String, String, Float, Float, Float, Float, Float, Float> {

	public StringFloatQuerySybAlgebra(QuerySybAlg<String> query1,
			QuerySybAlg<Float> query2) {
		super(query1, query2);
		// TODO Auto-generated constructor stub
	}

}
