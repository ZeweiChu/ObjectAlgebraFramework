package util;

import transform.LamAlgTransform;
import trees.LamAlg;

public class LamAlgTrans<A0> implements LamAlgTransform<A0> {

	private LamAlg<A0> alg;

	public LamAlgTrans(LamAlg<A0> alg) {this.alg = alg;}

	public LamAlg<A0> lamAlg() {return alg;}

}