package example_SybAlg;

import library.Monoid;
import monoid.FloatMonoid;
import query.SybAlgQuery;

//BEGIN_QUERY_WITH_OAFRAMEWORK
public class FloatQuery implements SybAlgQuery<Float> {
	public Monoid<Float> m() {return new FloatMonoid();}
	public Float S(float p0) {return p0;}
}
//END_QUERY_WITH_OAFRAMEWORK
