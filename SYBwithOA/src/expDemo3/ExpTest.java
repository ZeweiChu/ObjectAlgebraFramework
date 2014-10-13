package expDemo3;

/*Using manual queries and transformations: Implement generic queries and transformations for the language 
 * (but do not use our framework). Then implement freeVars as a query and substVars as a transformation.
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
		
		SubstVarsTransform substVarsExpAlg = new SubstVarsTransform("var1", gExp);
		FreeVarsQueryExpAlg alg = new FreeVarsQueryExpAlg(new FreeVarsMonoid());
		String[] res = genExp(substVarsExpAlg).accept(alg);
		for (String s: res) System.out.println(s);
		
		LitIncreaseTransform litIncreaseTransform = new LitIncreaseTransform();
		LitQueryExpAlg litQueryExpAlg = new LitQueryExpAlg(new LitMonoid());
		Integer intRes = genExp(substVarsExpAlg).accept(litIncreaseTransform).accept(litIncreaseTransform).accept(litIncreaseTransform).accept(litQueryExpAlg);
		System.out.println("Total Value: " + intRes);
	}
}
