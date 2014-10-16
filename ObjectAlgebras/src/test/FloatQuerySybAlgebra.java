package test;

import library.Monoid;
import query.QuerySybAlg;

public class FloatQuerySybAlgebra extends QuerySybAlg<Float> {

	public FloatQuerySybAlgebra(Monoid<Float> m) {
		super(m);
	}
	
	@Override
	public Float S(float p0){
		return p0;
	}

}
