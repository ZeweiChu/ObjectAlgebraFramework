package example_StmAlg;

import library.Monoid;
import query.StmAlgQuery;

public class StringQuery extends StmAlgQuery<String> {

	public StringQuery(Monoid<String> m) {
		super(m);
	}
	
	@Override
	public String SDecl(String t, String v){
		return v;
	};

}
