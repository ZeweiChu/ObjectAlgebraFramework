package test;

import query.SybAlgQuery;
import library.Monoid;

//BEGIN_QUERY_WITH_OAFRAMEWORK
public class FloatQuerySybAlgebra extends SybAlgQuery<Float> {
	public FloatQuerySybAlgebra(Monoid<Float> m) {
		super(m);
	}
	public Float S(float p0){return p0;}
}
//END_QUERY_WITH_OAFRAMEWORK