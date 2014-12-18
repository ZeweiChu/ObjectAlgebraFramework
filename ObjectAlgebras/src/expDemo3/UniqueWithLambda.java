package expDemo3;

//BEGIN_EXTEND_UNIQUEVARS
public interface UniqueWithLambda<E> extends Unique<E>, LamAlgTransform<E> {
	default E Lam(String x, E e) { return lamAlg().Lam(x + "#" + nextInt(), e); }
}
//END_EXTEND_UNIQUEVARS

