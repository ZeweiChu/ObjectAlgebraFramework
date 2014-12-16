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

	static void TestFreeVars() {	
		
//BEGIN_CLIENTCODE_FREEVARS
FreeVars alg = new FreeVars(){};
System.out.println(genExp(alg));
//END_CLIENTCODE_FREEVARS

	}
	
	static void TestSubstVar() {
		
//BEGIN_CLIENTCODE_SUBSTVAR
SubstVar<Set<String>> subst = new SubstVar<Set<String>>() {
	public ExpAlg<Set<String>> expAlg() {return new FreeVars(){};}
	public String x() {return "x";}
	public Set<String> e() {return expAlg().Var("z");}
};
System.out.println(genExp(subst));
//END_CLIENTCODE_SUBSTVAR

	}
		
	public static void main(String[] args) {
		TestFreeVars();
		TestSubstVar();		
	}
}
