package debruijn;

import static debruijn.Util.cons;

import java.util.List;
import java.util.function.Function;

import transform.G_ExpAlgTransform;
import transform.G_LamAlgTransform;

public interface DeBruijn<E> extends G_ExpAlgTransform<List<String>, E>, G_LamAlgTransform<List<String>, E> {
	@Override 
	default Function<List<String>, E> Var(String p0) {
		return (xs) -> expAlg().Var("" + xs.indexOf(p0));
	}
	
	@Override 
	default Function<List<String>, E> Lambda(String x, Function<List<String>, E> e) {
		return (xs) -> lamAlg().Lambda("", e.apply(cons(x, xs)));
	}
}
