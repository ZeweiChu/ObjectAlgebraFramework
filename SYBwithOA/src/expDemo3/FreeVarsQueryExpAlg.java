package expDemo3;

//BEGIN_FREEVARS_QUERY
public class FreeVarsQueryExpAlg extends QueryExpAlg<String[]>{
	public FreeVarsQueryExpAlg(Monoid<String[]> m) {super(m);}
	public String[] Var(String s){return new String[]{s};}
}
//END_FREEVARS_QUERY
