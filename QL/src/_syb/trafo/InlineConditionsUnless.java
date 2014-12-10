package _syb.trafo;

import java.util.function.Function;

import transform.G_IExpAlgTransform;
import transform.G_IUnlessAlgTransform;

//BEGIN_INLINECONDS_UNLESS
public interface InlineConditionsUnless<E, S> extends 
  G_IUnlessAlgTransform<E, E, S>, G_IExpAlgTransform<E, E> { 
	default Function<E, S> unless(Function<E, E> p0, Function<E, S> p1) {
		return (guard) -> {
			return p1.apply(iExpAlg().and(guard, iExpAlg().not(p0.apply(guard))));
		};
	}
}
//END_INLINECONDS_UNLESS