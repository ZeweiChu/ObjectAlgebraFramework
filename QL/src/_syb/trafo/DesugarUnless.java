package _syb.trafo;

import java.util.List;

public interface DesugarUnless<E, S, F> extends ExpAlgId<E>, StmtAlgId<E, S>, FormAlgId<E, S, F>, UnlessAlgId<E, S> {
	@Override default public S unless(E cond, List<S> body) {
		return iff(not(cond), body);
	}
}
