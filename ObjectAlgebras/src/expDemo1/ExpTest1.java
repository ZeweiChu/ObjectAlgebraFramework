package expDemo1;

import generic.G_Exp;
import trees.ExpAlg;

/*Directly: Just implement freeVars and substVars directly 
 * without using queries/transformations or our framework;
 */
public class ExpTest1 {
	
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
		
		SubstVarsExpAlg1 substVarsExpAlg = new SubstVarsExpAlg1("var1", gExp);
		FreeVarsExpAlg1 alg = new FreeVarsExpAlg1(){};
		String[] res = genExp(substVarsExpAlg).accept(alg).freeVars();
		for (String s: res) System.out.println(s);
	}
}
