package _syb.query;

import library.Monoid;
import ql_obj_alg.syntax.IExpAlg;

public interface ExpAlgMonoid<E> extends IExpAlg<E> {

	Monoid<E> m();
	
	@Override
	default E bracket(E e) {
		E r = m().empty();
		return m().join(r, e);
	}

	@Override
	default E lit(int x) {
		return m().empty();
	}

	@Override
	default E bool(boolean b) {
		return m().empty();
	}

	@Override
	default E string(String s) {
		return m().empty();
	}

	@Override
	default E var(String varName) {
		return m().empty();
	}

	@Override
	default E not(E exp) {
		return m().join(m().empty(), exp);
	}

	@Override
	default E mul(E lhs, E rhs) {
		return m().join(lhs, rhs);
	}

	@Override
	default E div(E lhs, E rhs) {
		return m().join(lhs, rhs);
	}

	@Override
	default E add(E lhs, E rhs) {
		return m().join(lhs, rhs);
	}

	@Override
	default E sub(E lhs, E rhs) {
		return m().join(lhs, rhs);
	}

	@Override
	default E eq(E lhs, E rhs) {
		return m().join(lhs, rhs);
	}

	@Override
	default E neq(E lhs, E rhs) {
		return m().join(lhs, rhs);
	}

	@Override
	default E lt(E lhs, E rhs) {
		return m().join(lhs, rhs);
	}

	@Override
	default E leq(E lhs, E rhs) {
		return m().join(lhs, rhs);
	}

	@Override
	default E gt(E lhs, E rhs) {
		return m().join(lhs, rhs);
	}

	@Override
	default E geq(E lhs, E rhs) {
		return m().join(lhs, rhs);
	}

	@Override
	default E and(E lhs, E rhs) {
		return m().join(lhs, rhs);
	}

	@Override
	default E or(E lhs, E rhs) {
		return m().join(lhs, rhs);
	}
}
