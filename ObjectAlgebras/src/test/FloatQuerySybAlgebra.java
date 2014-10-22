package test;

import query.SybAlgQuery;
import library.Monoid;

public class FloatQuerySybAlgebra extends SybAlgQuery<Float> {

	public FloatQuerySybAlgebra(Monoid<Float> m) {
		super(m);
	}
	
	@Override
	public Float S(float p0){
		return p0;
	}

}
