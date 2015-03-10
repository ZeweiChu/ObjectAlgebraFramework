package example_QLAlg2;

import java.util.List;

import trees.QLAlg;

class Renaming<E, S, F> implements QLAlg<E, S, F> {

	private QLAlg<E, S, F> alg;
	
	public Renaming(QLAlg<E, S, F> alg) { this.alg = alg; }
	
	public F form(String id, List<S> statements) {
		return alg.form(id, statements);
	}

	public S iff(E cond, S then) {
		return alg.iff(cond, then);
	}

	public S question(String id, String label, String type) {
		return alg.question(id + "_", label, type);
	}

	public E lit(int x) {
		return alg.lit(x);
	}

	public E var(String varName) {
		return alg.var(varName + "_");
	}

	public E geq(E lhs, E rhs) {
		return alg.geq(lhs, rhs);
	}

}
