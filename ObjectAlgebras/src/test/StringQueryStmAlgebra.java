package test;

import library.Monoid;
import query.StmAlgQuery;
public class StringQueryStmAlgebra extends StmAlgQuery<String>{
	
	public StringQueryStmAlgebra(Monoid<String> m) {
		super(m);
	}

	@Override
	public String SDecl(String t, String v){
		return v;
	};
}
