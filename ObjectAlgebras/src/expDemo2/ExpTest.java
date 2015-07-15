package expDemo2;

import java.util.Set;

/*Directly: Just implement freeVars and substVars directly 
 * without using queries/transformations or our framework;
 */
public class ExpTest {
	
	static <Exp> Exp genExp(ExpAlg<Exp> alg){
		return alg.Add(alg.Add(alg.Lit(2), alg.Var("var1")),alg.Add(alg.Add(alg.Var("var2"), alg.Var("var3")), alg.Lit(5)));
	}
		
	public static void main(String[] args) {
		//BEGIN_SUBSTVAR_CLIENT
		FreeVars fv = new FreeVars() {};
		SubstVar<Set<String>> subst = 
		  new SubstVar<Set<String>>() {
			  public ExpAlg<Set<String>> expAlg() { 
			  	return fv; 
			  }
			  public String x() { return "x"; }
			  public Set<String> e() { 
			  	return fv.Add(fv.Lit(1),fv.Var("y")); 
			  }
		};
		Set<String> res = subst.Var("x");
		//END_SUBSTVAR_CLIENT
		
		System.out.println(res);
		
		SubstVar<Set<String>> subst2 = 
			  new SubstVar<Set<String>>() {
				  public ExpAlg<Set<String>> expAlg() { 
				  	return subst; 
				  }
				  public String x() { return "y"; }
				  public Set<String> e() { 
				  	return fv.Var("x"); 
				  }
			};
		Set<String> res2 = subst2.Var("y");

		System.out.println(res2);
	}
}
