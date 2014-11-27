package example_SybAlg;

import library.Monoid;
import monoid.StringMonoid;
import query.SybAlgQuery;

public class StringQuery implements SybAlgQuery<String> {
	public Monoid<String> m() {return new StringMonoid();}
	public String P(String name, String address) {return name;}
}
