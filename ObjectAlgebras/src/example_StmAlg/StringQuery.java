package example_StmAlg;

import library.Monoid;
import query.StmAlgQuery;

public class StringQuery implements StmAlgQuery<String> {
	private Monoid<String> m;
	public StringQuery(Monoid<String> m) {this.m = m;}	
	public Monoid<String> m() {return m;}
	public String SDecl(String t, String v) {return v;}
}
