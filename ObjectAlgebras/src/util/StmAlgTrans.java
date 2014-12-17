package util;

import transform.StmAlgTransform;
import trees.StmAlg;

public class StmAlgTrans<A0, A1, A2> implements StmAlgTransform<A0, A1, A2> {

	private StmAlg<A0, A1, A2> alg;

	public StmAlgTrans(StmAlg<A0, A1, A2> alg) {this.alg = alg;}

	public StmAlg<A0, A1, A2> stmAlg() {return alg;}

}