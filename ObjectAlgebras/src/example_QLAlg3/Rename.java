package example_QLAlg3;

import trees.QLAlg;
import util.QLAlgTrans;

//BEGIN_QL_TRANSFORM_WITH_OAFRAMEWORK
class Rename<E, S, F> extends QLAlgTrans<E, S, F> {	
	public Rename(QLAlg<E, S, F> alg) { super(alg); }	
	public S question(String n, String l, String t) {
		return qLAlg().question(n + "_", l, t);
	}	
	public E var(String x) {
		return qLAlg().var(x + "_");
	}
}
//END_QL_TRANSFORM_WITH_OAFRAMEWORK
