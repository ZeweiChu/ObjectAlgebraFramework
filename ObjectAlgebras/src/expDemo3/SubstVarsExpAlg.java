package expDemo3;

//BEGIN_SUBSTVARS_WITH_ID
public interface SubstVarsExpAlg<Exp> extends ExpAlgTransform<Exp> {
	String getVar(); Exp getExp();
	default Exp Var(String s) { return s.equals(getVar()) ? getExp() : expAlg().Var(s); }
}
//END_SUBSTVARS_WITH_ID
