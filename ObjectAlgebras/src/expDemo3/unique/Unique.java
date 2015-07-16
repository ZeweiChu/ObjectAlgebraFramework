package expDemo3.unique;

import transform.ExpAlgTransform;

interface Unique<E> extends ExpAlgTransform<E> {
	int nextInt();
	default E Var(String s) {
		return expAlg().Var(s + nextInt());
	}
}
