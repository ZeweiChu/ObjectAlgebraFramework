package example_QLAlg3;

import trees.QLAlg;
import util.QLAlgTrans;

//BEGIN_QL_TRANSFORM_WITH_OAFRAMEWORK
class Rename<E, S, F> extends QLAlgTrans<E, S, F> {
	String n1, n2;
	public Rename(QLAlg<E, S, F> alg, String n1, String n2) {
		super(alg); this.n1 = n1; this.n2 = n2;
	}
	public S Question(String n, String l, String t) {
		String newN = n.equals(n1) ? n2 : n;
		return qLAlg().Question(newN, l, t);
	}
	public E Var(String x) {
		String newN = x.equals(n1) ? n2 : x;
		return qLAlg().Var(newN);
	}
}
//END_QL_TRANSFORM_WITH_OAFRAMEWORK
