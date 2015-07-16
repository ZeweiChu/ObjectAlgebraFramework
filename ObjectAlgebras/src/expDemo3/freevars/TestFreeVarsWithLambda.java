package expDemo3.freevars;

import java.util.Set;

import library.Monoid;
import monoid.SetMonoid;

public class TestFreeVarsWithLambda {

	interface FreeVarsExpWithLambda extends FreeVars, FreeVarsWithLambda {
		
	}
	
	public static void main(String[] args) {
		FreeVarsExpWithLambda fv = new FreeVarsExpWithLambda() { 
			public Monoid<Set<String>> m() { return new SetMonoid<>(); }
		};
		Set<String> term = fv.Lam("x", fv.Add(fv.Var("x"), fv.Var("y")));
		System.out.println(term);
	}
}
