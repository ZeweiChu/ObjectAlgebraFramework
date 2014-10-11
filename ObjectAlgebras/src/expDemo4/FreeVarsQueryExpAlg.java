package expDemo4;
import library.Monoid;
import query.QueryExpAlg;

public class FreeVarsQueryExpAlg extends QueryExpAlg<String[]>{

	public FreeVarsQueryExpAlg(Monoid<String[]> m) {
		super(m);
	}
	
	public String[] Var(String s){
		return new String[]{s};
	}

}
