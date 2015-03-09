package example_QLAlg3;

import trees.QLAlg;
import util.QLAlgTrans;

class Renaming<E, S, F> extends QLAlgTrans<E, S, F> {
	
	public Renaming(QLAlg<E, S, F> alg) { super(alg); }
	
	public S question(String id, String label, String type) {
		return qLAlg().question(id + "_", label, type);
	}
	
	public E var(String varName) {
		return qLAlg().var(varName + "_");
	}

}
