package _syb.trafo;

import java.util.Collections;
import java.util.function.Function;

import transform.G_IExpAlgTransform;
import transform.G_IStmtAlgTransform;
import transform.G_IUnlessAlgTransform;

//BEGIN_INLINECONDS_UNLESS
public interface InlineConditionsUnless<E, S> extends G_IUnlessAlgTransform<E, E, S>, G_IStmtAlgTransform<E, E, S>, G_IExpAlgTransform<E, E> { 
	default Function<E, S> unless(Function<E, E> p0, Function<E, S> p1) {
		return (guard) -> {
			E conj =  iExpAlg().and(guard, iExpAlg().not(p0.apply(guard)));
			return iStmtAlg().block(Collections.singletonList(p1.apply(conj)));
		};
	}
}
//END_INLINECONDS_UNLESS