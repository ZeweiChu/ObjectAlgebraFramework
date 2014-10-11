package expDemo4;

import library.Monoid;
import query.QueryExpAlg;

public class LitQueryExpAlg extends QueryExpAlg<Integer>{

	public LitQueryExpAlg(Monoid<Integer> m) {
		super(m);
	}

	public Integer Lit(int i){
		return i;
	}
}
