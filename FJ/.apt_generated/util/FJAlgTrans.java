package util;

import transform.FJAlgTransform;
import trees.FJAlg;

public class FJAlgTrans<A0, A1, A2, A3, A4, A5, A6, A7, A8, A9> implements FJAlgTransform<A0, A1, A2, A3, A4, A5, A6, A7, A8, A9> {

	private FJAlg<A0, A1, A2, A3, A4, A5, A6, A7, A8, A9> alg;

	public FJAlgTrans(FJAlg<A0, A1, A2, A3, A4, A5, A6, A7, A8, A9> alg) {this.alg = alg;}

	public FJAlg<A0, A1, A2, A3, A4, A5, A6, A7, A8, A9> fJAlg() {return alg;}

}