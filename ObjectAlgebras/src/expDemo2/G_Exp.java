package expDemo2;
//BEGIN_GENERIC_VISITABLE
public interface G_Exp {
	<Exp> Exp accept(ExpAlg<Exp> alg);
}
//END_GENERIC_VISITABLE