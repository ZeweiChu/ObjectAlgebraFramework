package example_SybAlg;

import library.Monoid;
import query.SybAlgQuery;

public class StringQuery extends SybAlgQuery<String> {

	public StringQuery(Monoid<String> m) {
		super(m);
	}
	
	@Override
	public String P(String name, String address) {
		return name;
	}

}
