package example_DoubleAlg1;

import trees.DoubleAlg;
import trees.ExpAlg;

//BEGIN_DESUGAR_DOUBLE
interface Desugar<Exp> extends ExpAlg<Exp>, DoubleAlg<Exp> {
	ExpAlg<Exp> expAlg();
	default Exp Var(String s) {
		return expAlg().Var(s);
	}
	default Exp Lit(int i) {
		return expAlg().Lit(i);
	}
	default Exp Add(Exp e1, Exp e2) {
		return expAlg().Add(e1, e2);
	}
	default Exp Double(Exp e) {
		return expAlg().Add(e, e);
	}
}
//END_DESUGAR_DOUBLE
