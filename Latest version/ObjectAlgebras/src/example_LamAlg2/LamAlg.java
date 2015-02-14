package example_LamAlg2;

public interface LamAlg<Exp> {
	Exp Lam(String x, Exp e);
	Exp App(Exp e1, Exp e2);
}
