package expDemo2;

import java.util.List;

/*Directly: Just implement freeVars and substVars directly 
 * without using queries/transformations or our framework;
 */
public class ExpTest {
	
	static <Exp> Exp genExp(ExpAlg<Exp> alg){
		return alg.Add(alg.Add(alg.Lit(2), alg.Var("var1")),alg.Add(alg.Add(alg.Var("var2"), alg.Var("var3")), alg.Lit(5)));
	}
		
	public static void main(String[] args) {
		FreeVarsExpAlg alg = new FreeVarsExpAlg(){};
		SubstVarsExpAlg<List<String>> substVarsExpAlg = new SubstVarsExpAlg<List<String>>() {
			public ExpAlg<List<String>> expAlg() { return alg; }
			public String x() { return "var1"; }
			public List<String> e() { return expAlg().Var("var4"); }
		};
		List<String> res = genExp(substVarsExpAlg);
		for (String s: res) System.out.println(s);
	}
}
