package substitution.manual;

import java.util.Collections;
import java.util.Set;
import java.util.function.Supplier;

import substitution.util.FreeVars;
import substitution.util.Print;
import trees.ExpAlg;
import trees.LamAlg;

public class TestSubstitution {

	static class Doit implements Substitution<Supplier<String>> {
		private Print print = new Print();

		@Override
		public ExpAlg<Supplier<String>> expAlg() {
			return print;
		}

		@Override
		public LamAlg<Supplier<String>> lamAlg() {
			return print;
		}
	}
	
	static <E, Alg extends ExpAlg<E> & LamAlg<E>> E build(Alg alg) {
		return alg.Lambda("x", alg.Add(alg.Add(alg.Lit(3), alg.Add(alg.Var("x"), alg.Lambda("x", alg.Var("y")))), alg.Add(alg.Var("y"), alg.Lambda("y", alg.Var("y")))));
	}
	
	public static void main(String[] args) {
		Doit doit = new Doit();
		Print print = new Print();
		FreeVars fv = new FreeVars();
		
		Supplier<String> org = build(print);
		System.out.println("Original: " + org.get());
		
		Subst<Supplier<String>> x = build(doit);
		Supplier<String> exp = print.Var("x");
		Set<String> fvs = fv.Var("x");
		System.out.println(fvs);
		Supplier<String> printer = x.subst("y", exp, fvs, Collections.emptyMap());
		System.out.println("[y := x]: " + printer.get());
		
		
		org = build(print);
		System.out.println("Original: " + org.get());
		
		x = build(doit);
		exp = print.Lambda("x", print.Var("x"));
		fvs = fv.Lambda("x", fv.Var("x"));
		System.out.println(fvs);
		printer = x.subst("y", exp, fvs, Collections.emptyMap());
		System.out.println("[y := x]: " + printer.get());
	}
	
}
