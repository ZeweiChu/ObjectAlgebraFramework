package example_SybAlg;

import library.Monoid;
import monoid.FloatMonoid;
import query.SybAlgQuery;

//BEGIN_QUERY_WITH_OAFRAMEWORK
class SalaryBill implements SybAlgQuery<Float> {
	public Monoid<Float> m() {return new FloatMonoid();}
	public Float S(float sal) {return sal;}
}
//END_QUERY_WITH_OAFRAMEWORK
