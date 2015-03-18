package util;

import transform.FJAlgTransform;
import trees.FJAlg;

public class FJAlgTrans<A0, A1, A2, A3, A4, A5, A6, A7> implements FJAlgTransform<A0, A1, A2, A3, A4, A5, A6, A7> {

	private FJAlg<A0, A1, A2, A3, A4, A5, A6, A7> alg;

	public FJAlgTrans(FJAlg<A0, A1, A2, A3, A4, A5, A6, A7> alg) {this.alg = alg;}

	public FJAlg<A0, A1, A2, A3, A4, A5, A6, A7> fJAlg() {return alg;}

}