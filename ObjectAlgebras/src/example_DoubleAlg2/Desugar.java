package example_DoubleAlg2;

import transform.ExpAlgTransform;
import trees.DoubleAlg;

//BEGIN_DESUGAR_DOUBLE_SHY
interface Desugar<E> extends DoubleAlg<E>, ExpAlgTransform<E>  {
	default E Double(E e) {
		return expAlg().Add(e, e);
	}
}
//END_DESUGAR_DOUBLE_SHY
