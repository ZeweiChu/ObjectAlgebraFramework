package expDemo4;
import query.ExpAlgQuery;
import library.Monoid;

public class FreeVarsQueryExpAlg implements ExpAlgQuery<String[]> {
	private Monoid<String[]> m;
	public FreeVarsQueryExpAlg(Monoid<String[]> m) {this.m = m;}
	public Monoid<String[]> m() {return m;}
	public String[] Var(String s) {return new String[]{s};}
}
