package expDemo3;

public interface UniqueWithLambda<E> extends Unique<E>, LamAlgTransform<E> {
	default E Lam(String x, E e) { return lamAlg().Lam(x + "#" + nextInt(), e); }
}
