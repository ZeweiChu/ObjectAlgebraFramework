package _syb.trafo;

import java.util.List;

public interface DesugarUnless<E, S> extends ExpAlgId<E>, StmtAlgId<E, S>, UnlessAlgId<E, S> {
	@Override 
	default S unless(E cond, List<S> body) {
		return iff(not(cond), body);
	}
}
