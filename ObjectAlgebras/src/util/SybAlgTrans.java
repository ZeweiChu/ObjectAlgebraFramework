package util;

import transform.SybAlgTransform;
import trees.SybAlg;

public class SybAlgTrans<A0, A1, A2, A3, A4, A5> implements SybAlgTransform<A0, A1, A2, A3, A4, A5> {

	private SybAlg<A0, A1, A2, A3, A4, A5> alg;

	public SybAlgTrans(SybAlg<A0, A1, A2, A3, A4, A5> alg) {this.alg = alg;}

	public SybAlg<A0, A1, A2, A3, A4, A5> sybAlg() {return alg;}

}