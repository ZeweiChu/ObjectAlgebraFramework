package _syb.query;

import java.util.List;

import library.Monoid;
import ql_obj_alg.check.types.Type;
import ql_obj_alg.syntax.IStmtAlg;

public interface StmtAlgMonoid<T> extends IStmtAlg<T, T> {

	Monoid<T> m();
	
	@Override
	default T iff(T cond, List<T> statements) {
		T r = m().empty();
		r = m().join(r, cond);
		for (T t: statements) {
			r = m().join(r, t);
		}
		return r;
	}

	@Override
	default T iffelse(T cond, List<T> statementsIf, List<T> statementsElse) {
		T r = m().empty();
		r = m().join(r, cond);
		for (T t: statementsIf) {
			r = m().join(r, t);
		}
		for (T t: statementsElse) {
			r = m().join(r, t);
		}
		return r;
	}

	@Override
	default T question(String id, String label, Type type) {
		return m().empty();
	}

	@Override
	default T question(String id, String label, Type type, T exp) {
		return exp;
	}

}
