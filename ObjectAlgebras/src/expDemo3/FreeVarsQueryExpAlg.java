package expDemo3;

public class FreeVarsQueryExpAlg extends QueryExpAlg<String[]> {
	public FreeVarsQueryExpAlg(Monoid<String[]> m) { super(m); }
	public String[] Var(String s) { return new String[]{s}; }
}
