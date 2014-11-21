package expDemo4;

import query.ExpAlgQuery;
import library.Monoid;

public class LitQueryExpAlg implements ExpAlgQuery<Integer> {
	private Monoid<Integer> m;
	public LitQueryExpAlg(Monoid<Integer> m) {this.m = m;}
	public Monoid<Integer> m() {return m;}
	public Integer Lit(int i) {return i;}
}
