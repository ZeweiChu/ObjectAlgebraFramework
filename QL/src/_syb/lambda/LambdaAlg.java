package _syb.lambda;

import com.zewei.annotation.processor.Algebra;

@Algebra
public interface LambdaAlg<E> {
	E lambda(String x, E e);
	E var(String x);
	E apply(E e1, E e2);
}
