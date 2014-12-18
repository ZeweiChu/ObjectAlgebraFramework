package expDemo3;

//BEGIN_UNIQUEVARS
public interface Unique<E> extends ExpAlgTransform<E> {
	int nextInt();
	default E Var(String s) { return expAlg().Var(s + "#" + nextInt()); }
}
//END_UNIQUEVARS
