package _syb.trafo;

import java.util.List;

public interface DesugarUnless<E, S> extends IExpAlgTransform {
	@Override 
	default S unless(E cond, List<S> body) {
		return iff(not(cond), body);
	}
}
