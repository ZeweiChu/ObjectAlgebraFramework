package example_DoubleAlg2;

import transform.DoubleAlgTransform;
import transform.ExpAlgTransform;

//BEGIN_DESUGAR_DOUBLE_SHY
interface Desugar<Exp> extends
		ExpAlgTransform<Exp>, DoubleAlgTransform<Exp> {
	default Exp Double(Exp e) {
		return expAlg().Add(e, e);
	}
}
//BEGIN_DESUGAR_DOUBLE_SHY
