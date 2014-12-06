package expDemo2;

public interface SubstVarsExpAlg<Exp> extends ExpAlg<Exp> {
	ExpAlg<Exp> expAlg();
	String getVar();
	Exp getExp();
	default Exp Var(String s) {
		return s.equals(getVar()) ? getExp() : expAlg().Var(s);
	}
	default Exp Lit(int i) {
		return expAlg().Lit(i);
	}
	default Exp Add(Exp e1, Exp e2) {
		return expAlg().Add(e1, e2);
	}
}
