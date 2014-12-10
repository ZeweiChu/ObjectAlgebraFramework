package example_LamAlg2;

import java.util.function.Function;

public interface G_ExpAlgTransform<A, B> extends ExpAlg<Function<A, B>> {

	ExpAlg<B> expAlg();

	@Override
	default Function<A, B> Add(Function<A, B> p0, Function<A, B> p1) {
		return acc -> expAlg().Add(p0.apply(acc), p1.apply(acc));
	}

	@Override
	default Function<A, B> Lit(int p0) {
		return acc -> expAlg().Lit(p0);
	}

	@Override
	default Function<A, B> Var(String p0) {
		return acc -> expAlg().Var(p0);
	}

}