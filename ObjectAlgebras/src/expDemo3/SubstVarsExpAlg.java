package expDemo3;

public interface SubstVarsExpAlg<Exp> extends ExpAlgTransform<Exp> {
	String getVar();
	Exp getExp();
	default Exp Var(String s) {
		return s.equals(getVar()) ? getExp() : expAlg().Var(s);
	}
}
