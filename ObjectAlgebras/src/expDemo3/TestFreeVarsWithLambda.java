package expDemo3;

import java.util.Set;

public class TestFreeVarsWithLambda {

	public static void main(String[] args) {
		FreeVarsWithLambdas.FreeVarsWithLambda fv = new FreeVarsWithLambdas.FreeVarsWithLambda() { 
			public Monoid<Set<String>> m() { return new SetMonoid<>(); }
		};
		Set<String> term = fv.Lam("x", fv.Add(fv.Var("x"), fv.Var("y")));
		System.out.println(term);
	}
}
