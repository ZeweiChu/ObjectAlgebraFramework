package example_LamAlg1;

public interface LamAlg<Exp> {
	Exp Lam(String x, Exp e);
	Exp App(Exp e1, Exp e2);
}
