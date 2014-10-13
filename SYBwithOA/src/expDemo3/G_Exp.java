package expDemo3;

//BEGIN_GENERIC_ACCEPTOR
public interface G_Exp {
	<Exp> Exp accept(ExpAlg<Exp> alg);
}
//END_GENERIC_ACCEPTOR