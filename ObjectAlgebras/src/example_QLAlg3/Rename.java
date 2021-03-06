package example_QLAlg3;

import trees.QLAlg;
import util.QLAlgTrans;

//BEGIN_QL_TRANSFORM_WITH_OAFRAMEWORK
class Rename<E, S, F> extends QLAlgTrans<E, S, F> {
	String from, to;
	Rename(QLAlg<E,S,F> alg, String from, String to) {
		super(alg); 
		this.from = from; 
		this.to = to;
	}
	public S Question(String n, String l, String t) {
		n = n.equals(from) ? to : n;
		return qLAlg().Question(n, l, t);
	}
	public E Var(String x) {
		x = x.equals(from) ? to : x;
		return qLAlg().Var(x);
	}
}
//END_QL_TRANSFORM_WITH_OAFRAMEWORK
