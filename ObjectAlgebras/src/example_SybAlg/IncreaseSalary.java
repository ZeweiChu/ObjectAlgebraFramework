package example_SybAlg;

import trees.SybAlg;
import util.SybAlgTrans;

//BEGIN_TRANSFORM_WITH_OAFRAMEWORK
class IncreaseSalary<C,D,U,E,P,S> extends SybAlgTrans<C,D,U,E,P,S> {
	IncreaseSalary(SybAlg<C,D,U,E,P,S> alg) { super(alg); }
	public S S(float salary) { return sybAlg().S(1.1f * salary); }
}
//END_TRANSFORM_WITH_OAFRAMEWORK
