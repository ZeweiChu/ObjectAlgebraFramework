package expDemo3.unique;

import expDemo3.LamAlgTransform;

//BEGIN_UNIQUE_WITH_LAMBDA_INDEP
public interface UniqueWithLambda<E> extends LamAlgTransform<E> {
	int nextInt();
	default E Lam(String x, E e) {
		return lamAlg().Lam(x + nextInt(), e);
	}
}
//END_UNIQUE_WITH_LAMBDA_INDEP
