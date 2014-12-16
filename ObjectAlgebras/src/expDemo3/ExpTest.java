package expDemo3;

import java.util.Set;

/*Using manual queries and transformations: Implement generic queries and transformations for the language 
 * (but do not use our framework). Then implement freeVars as a query and substVars as a transformation.
 */
public class ExpTest {
	
//BEGIN_GEN_EXP
static <Exp> Exp genExp(ExpAlg<Exp> alg) {
	return alg.Add(alg.Var("x"), alg.Add(alg.Var("y"), alg.Lit(2)));
}
//END_GEN_EXP
		
	public static void main(String[] args) {
		
//BEGIN_CLIENTCODE_FREEVARS
FreeVars alg = new FreeVars(){};
System.out.println(genExp(alg));
//END_CLIENTCODE_FREEVARS
		
		
		/*
		SubstVarExpAlg<Set<String>> substVarsExpAlg = new SubstVarExpAlg<Set<String>>() {
			public ExpAlg<Set<String>> expAlg() { return alg; }
			public String x() { return "var1"; }
			public Set<String> e() { return expAlg().Var("var4"); }
		};
		Set<String> res = genExp(substVarsExpAlg);
		for (String s: res) System.out.println(s);
		*/
		
	}
}
