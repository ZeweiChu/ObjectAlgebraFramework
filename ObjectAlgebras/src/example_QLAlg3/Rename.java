package example_QLAlg3;

import trees.QLAlg;
import util.QLAlgTrans;

//BEGIN_QL_TRANSFORM_WITH_OAFRAMEWORK
class Rename<E, S, F> extends QLAlgTrans<E, S, F> {	
	public Rename(QLAlg<E, S, F> alg) { super(alg); }	
	public S question(String id, String lbl,
			String type) {
		return qLAlg().question(id + "_", lbl, type);
	}	
	public E var(String varName) {
		return qLAlg().var(varName + "_");
	}
}
//END_QL_TRANSFORM_WITH_OAFRAMEWORK
