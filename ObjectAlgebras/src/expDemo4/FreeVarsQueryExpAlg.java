package expDemo4;
import query.ExpAlgQuery;
import library.Monoid;

public class FreeVarsQueryExpAlg extends ExpAlgQuery<String[]>{

	public FreeVarsQueryExpAlg(Monoid<String[]> m) {
		super(m);
	}
	
	public String[] Var(String s){
		return new String[]{s};
	}

}
