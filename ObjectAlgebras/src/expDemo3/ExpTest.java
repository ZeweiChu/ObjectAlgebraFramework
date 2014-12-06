package expDemo3;

/*Using manual queries and transformations: Implement generic queries and transformations for the language 
 * (but do not use our framework). Then implement freeVars as a query and substVars as a transformation.
 */
public class ExpTest {
	static <Exp> Exp genExp(ExpAlg<Exp> alg){
		return alg.Add(alg.Add(alg.Lit(2), alg.Var("var1")),alg.Add(alg.Add(alg.Var("var2"), alg.Var("var3")), alg.Lit(5)));
	}
		
	public static void main(String[] args) {		
		FreeVarsExpAlg alg = new FreeVarsExpAlg(){};
		SubstVarsExpAlg<String[]> substVarsExpAlg = new SubstVarsExpAlg<String[]>() {
			public ExpAlg<String[]> expAlg() { return alg; }
			public String getVar() { return "var1"; }
			public String[] getExp() { return expAlg().Var("var4"); }
		};
		String[] res = genExp(substVarsExpAlg);
		for (String s: res) System.out.println(s);
	}
}
