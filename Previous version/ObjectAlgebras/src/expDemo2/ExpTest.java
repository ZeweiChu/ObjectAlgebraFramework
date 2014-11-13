package expDemo2;

/*Directly: Just implement freeVars and substVars directly 
 * without using queries/transformations or our framework;
 */
public class ExpTest {
	
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
		
		SubstVarsExpAlg substVarsExpAlg = new SubstVarsExpAlg("var1", gExp);
		FreeVarsExpAlg alg = new FreeVarsExpAlg(){};
		String[] res = genExp(substVarsExpAlg).accept(alg);
		for (String s: res) System.out.println(s);
		
		LitIncreaseTransform litIncreaseTransform = new LitIncreaseTransform();
		LitQueryExpAlg litQueryExpAlg = new LitQueryExpAlg();
		Integer intRes = genExp(substVarsExpAlg).accept(litIncreaseTransform).accept(litIncreaseTransform).accept(litIncreaseTransform).accept(litQueryExpAlg);
		System.out.println("Total Value: " + intRes);
	}
}
