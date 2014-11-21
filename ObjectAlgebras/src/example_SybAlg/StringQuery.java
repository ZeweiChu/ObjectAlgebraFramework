package example_SybAlg;

import library.Monoid;
import query.SybAlgQuery;

public class StringQuery implements SybAlgQuery<String> {
	private Monoid<String> m;
	public StringQuery(Monoid<String> m) {this.m = m;}
	public Monoid<String> m() {return m;}
	public String P(String name, String address) {return name;}
}
