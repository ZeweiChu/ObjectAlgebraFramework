package test;

import library.Monoid;
import query.QuerySybAlg;

public class StringQuerySybAlgebra extends QuerySybAlg<String>{
	public StringQuerySybAlgebra(Monoid<String> m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public
	 String P(String name, String address){
		return name;
	}
}
