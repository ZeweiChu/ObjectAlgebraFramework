package example_QLAlg;

import trees.QLAlg;
import util.QLAlgTrans;

public class SubstVar<E, S, F> extends QLAlgTrans<E, S, F> {

	String x; E e;
	
	public SubstVar(QLAlg<E, S, F> alg, String x, E e) {
		super(alg);
		this.x = x;
		this.e = e;
	}
	
	public E var(String varName) {
		return varName.equals(x) ? e : qLAlg().var(varName);
	}

}


/*
class SubstVar<E, S, F> implements QLAlg<E, S, F> {
	
	private QLAlg<E, S, F> alg;
	private String x;
	private E e;

	public F form(String id, List<S> statements) {
		return alg.form(id, statements);
	}

	...
	
	public E var(String varName) {
		return varName.equals(x) ? e : alg.var(varName);
	}
	
}
*/
