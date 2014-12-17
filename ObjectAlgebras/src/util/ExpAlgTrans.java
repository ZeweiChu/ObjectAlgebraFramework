package util;

import transform.ExpAlgTransform;
import trees.ExpAlg;

public class ExpAlgTrans<A0> implements ExpAlgTransform<A0> {

	private ExpAlg<A0> alg;

	public ExpAlgTrans(ExpAlg<A0> alg) {this.alg = alg;}

	public ExpAlg<A0> expAlg() {return alg;}

}