package test;

import library.Monoid;
import query.QueryStmAlg;
public class StringQueryStmAlgebra extends QueryStmAlg<String>{
	
	public StringQueryStmAlgebra(Monoid<String> m) {
		super(m);
	}

	@Override
	public String SDecl(String t, String v){
		return v;
	};
}
