package expDemo2;

import trees.ExpAlg;
import generic.G_Exp;

/*Using manual queries and transformations: Implement generic queries and transformations for the language 
 * (but do not use our framework). Then implement freeVars as a query and substVars as a transformation.
 */
public class ExpTest2 {
	static <Exp> Exp genExp(ExpAlg<Exp> alg){
		return alg.Add(alg.Add(alg.Lit(2), alg.Var("var1")),alg.Add(alg.Add(alg.Var("var2"), alg.Var("var3")), alg.Lit(5)));
	}
		
	public static void main(String[] args) {	
		G_Exp gExp = new G_Exp(){
			@Override
			public <Exp> Exp accept(ExpAlg<Exp> alg) {
				return alg.Var("Var4");
			}
		};
		
		SubstVarsTransform2 substVarsExpAlg = new SubstVarsTransform2("var1", gExp);
		FreeVarsQueryExpAlg2 alg = new FreeVarsQueryExpAlg2(new FreeVarsMonoid2());
		String[] res = genExp(substVarsExpAlg).accept(alg).freeVars();
		for (String s: res) System.out.println(s);
	}
}
