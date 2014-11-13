package example_SybAlg;

import library.Monoid;
import query.SybAlgQuery;

public class FloatQuery extends SybAlgQuery<Float> {

	public FloatQuery(Monoid<Float> m) {
		super(m);
	}
	
	@Override
	public Float S(float p0) {
		return p0; 
	}

}
