package _syb.trafo;

import ql_obj_alg.syntax.IExpAlg;

public interface ExpAlgId<E> extends IExpAlg<E> {

	IExpAlg<E> alg();
	
	@Override
	default E bracket(E e) {
		return alg().bracket(e);
	}

	@Override
	default E lit(int x) {
		return alg().lit(x);
	}

	@Override
	default E bool(boolean b) {
		return alg().bool(b);
	}

	@Override
	default E string(String s) {
		return alg().string(s);
	}

	@Override
	default E var(String varName) {
		return alg().var(varName);
	}

	@Override
	default E not(E exp) {
		return alg().not(exp);
	}

	@Override
	default E mul(E lhs, E rhs) {
		return alg().mul(lhs, rhs);
	}

	@Override
	default E div(E lhs, E rhs) {
		return alg().div(lhs, rhs);
	}

	@Override
	default E add(E lhs, E rhs) {
		return alg().add(lhs, rhs);
	}

	@Override
	default E sub(E lhs, E rhs) {
		return alg().sub(lhs, rhs);
	}

	@Override
	default E eq(E lhs, E rhs) {
		return alg().eq(lhs, rhs);
	}

	@Override
	default E neq(E lhs, E rhs) {
		return alg().neq(lhs, rhs);
	}

	@Override
	default E lt(E lhs, E rhs) {
		return alg().lt(lhs, rhs);
	}

	@Override
	default E leq(E lhs, E rhs) {
		return alg().leq(lhs, rhs);
	}

	@Override
	default E gt(E lhs, E rhs) {
		return alg().gt(lhs, rhs);
	}

	@Override
	default E geq(E lhs, E rhs) {
		return alg().geq(lhs, rhs);
	}

	@Override
	default E and(E lhs, E rhs) {
		return alg().and(lhs, rhs);
	}

	@Override
	default E or(E lhs, E rhs) {
		return alg().or(lhs, rhs);
	}

}
