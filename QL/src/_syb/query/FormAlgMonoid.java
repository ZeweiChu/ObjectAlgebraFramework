package _syb.query;

import java.util.List;

import library.Monoid;
import ql_obj_alg.syntax.IFormAlg;

public interface FormAlgMonoid<T> extends IFormAlg<T, T, T> {
	Monoid<T> m();

	@Override
	default T form(String id, List<T> statements) {
		T r = m().empty();
		for (T t: statements) {
			r = m().join(r, t);
		}
		return r;
	}
	

}
