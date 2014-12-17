package util;

import transform.StatAlgTransform;
import trees.StatAlg;

public class StatAlgTrans<A0, A1> implements StatAlgTransform<A0, A1> {

	private StatAlg<A0, A1> alg;

	public StatAlgTrans(StatAlg<A0, A1> alg) {this.alg = alg;}

	public StatAlg<A0, A1> statAlg() {return alg;}

}