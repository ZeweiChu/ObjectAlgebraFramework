package expDemo4;

import query.ExpAlgQuery;
import library.Monoid;

public class LitQueryExpAlg extends ExpAlgQuery<Integer>{

	public LitQueryExpAlg(Monoid<Integer> m) {
		super(m);
	}

	public Integer Lit(int i){
		return i;
	}
}
