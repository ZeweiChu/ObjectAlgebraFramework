package example_SybAlg;

import library.Monoid;
import query.SybAlgQuery;

//BEGIN_QUERY_WITH_OAFRAMEWORK
public class FloatQuery implements SybAlgQuery<Float> {
	private Monoid<Float> m;
	public FloatQuery(Monoid<Float> m) {this.m = m;}
	public Monoid<Float> m() {return m;}
	public Float S(float p0) {return p0;}
}
//END_QUERY_WITH_OAFRAMEWORK
