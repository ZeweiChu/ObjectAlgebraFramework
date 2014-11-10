package test;

import library.Monoid;
import query.SybAlgQuery;

public class StringQuerySybAlgebra extends SybAlgQuery<String>{
	public StringQuerySybAlgebra(Monoid<String> m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String P(String name, String address){
		return name;
	}
}
