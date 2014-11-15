package example_SybAlg;

import library.Monoid;
import query.SybAlgQuery;

//BEGIN_QUERY_WITH_OAFRAMEWORK
public class FloatQuery extends SybAlgQuery<Float> {
	public FloatQuery(Monoid<Float> m) {super(m);}
	public Float S(float p0) {return p0;}
}
//END_QUERY_WITH_OAFRAMEWORK
