package example_DoubleAlg1;

import trees.DoubleAlg;
import trees.ExpAlg;

//BEGIN_DESUGAR_DOUBLE
interface Desugar<Exp> extends ExpAlg<Exp>, DoubleAlg<Exp> {
	default Exp Double(Exp e) {
		return Add(e, e);
	}
}
//END_DESUGAR_DOUBLE
