package expDemo3;
import library.Monoid;
import query.QueryExpAlg;

public class StringArrayQueryExpAlg3 extends QueryExpAlg<String[]>{

	public StringArrayQueryExpAlg3(Monoid<String[]> m) {
		super(m);
	}
	
	public String[] Var(String s){
		return new String[]{s};
	}

}
