package expDemo2;
import library.Monoid;
import query.QueryExpAlg;

public class FreeVarsQueryExpAlg2 extends QueryExpAlg<FreeVars2>{

	public FreeVarsQueryExpAlg2(Monoid<FreeVars2> m) {
		super(m);
	}
	
	public FreeVars2 Var(String s){
		return new FreeVars2(){
			@Override
			public String[] freeVars() {
				return new String[]{s};
			}
		};
	}

}
