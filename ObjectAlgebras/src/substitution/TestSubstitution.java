package substitution;


import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

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
	
	static <E, Alg extends ExpAlg<E> & LamAlg<E>> E buildSrc(Alg alg) {
		return alg.Lam("x", alg.Add(alg.Add(alg.Lit(3), alg.Add(alg.Var("x"), alg.Lam("x", alg.Var("y")))), alg.Add(alg.Var("y"), alg.Lam("y", alg.Var("y")))));
	}
	
	
	static <E, Alg extends ExpAlg<E> & LamAlg<E>> E replacement1(Alg alg) {
		return alg.Var("x");
	}

	static <E, Alg extends ExpAlg<E> & LamAlg<E>> E replacement2(Alg alg) {
		return alg.Lam("x", alg.Var("x"));
	}

	static void example(Set<String> fvs, Supplier<String> org, Function<SubstArgs<Supplier<String>>, Supplier<String>> src, Supplier<String> repl) {
		System.out.println("Original: " + org.get());
		SubstArgs<Supplier<String>> args = new SubstArgs<Supplier<String>>(repl, "y", fvs, Collections.emptyMap());
		Supplier<String> printer = src.apply(args);
		System.out.println("[y := x]: " + printer.get());
	}
	
	static void example1(Doit doit, Print print, FreeVars fv) {
		Supplier<String> org = buildSrc(print);
		Function<SubstArgs<Supplier<String>>, Supplier<String>> src = buildSrc(doit);
		Supplier<String> exp = replacement1(print);
		Set<String> fvs = replacement1(fv);
		example(fvs, org, src, exp);
	}
	
	static void example2(Doit doit, Print print, FreeVars fv) {
		Supplier<String> org = buildSrc(print);
		Function<SubstArgs<Supplier<String>>, Supplier<String>> src = buildSrc(doit);
		Supplier<String> exp = replacement2(print);
		Set<String> fvs = replacement2(fv);
		example(fvs, org, src, exp);
	}
	
	
	
	public static void main(String[] args_) {
		Doit doit = new Doit();
		Print print = new Print();
		FreeVars fv = new FreeVars();		
		example1(doit, print, fv);
		example2(doit, print, fv);
	}
	
	static <E, Alg extends ExpAlg<E> & LamAlg<E>> E bruno(Alg alg) {
		//(\x . \y . \x . x + y) [y -> x]
		return alg.Lam("x", alg.Lam("y", alg.Lam("x", alg.Add(alg.Var("x"), alg.Var("y")))));
	}
	
	
	@Test
	public void faultyExample() {
		Doit doit = new Doit();
		Print print = new Print();
		FreeVars fv = new FreeVars();
		
		Function<SubstArgs<Supplier<String>>, Supplier<String>> src = bruno(doit);
		
		//(\x . \y . \x . x + y) [y -> x]
		Supplier<String> exp = print.Var("x");
		Set<String> fvs = fv.Var("x");
		
		SubstArgs<Supplier<String>> args = new SubstArgs<Supplier<String>>(exp, "y", fvs, Collections.emptyMap());
		Supplier<String> printer = src.apply(args);
		//            (lambda (x)  (lambda (y) (lambda (x) (+ x y))))
		assertEquals("(lambda (x_) (lambda (y) (lambda (x) (+ x y))))", printer.get());
	}
	
	@Test
	public void example1() {
		
		Doit doit = new Doit();
		Print print = new Print();
		FreeVars fv = new FreeVars();
		
		Function<SubstArgs<Supplier<String>>, Supplier<String>> src = buildSrc(doit);
		Supplier<String> exp = replacement1(print);
		Set<String> fvs = replacement1(fv);

		SubstArgs<Supplier<String>> args = new SubstArgs<Supplier<String>>(exp, "y", fvs, Collections.emptyMap());
		Supplier<String> printer = src.apply(args);
		
		//            (lambda (x)  (+ (+ 3 (+ x  (lambda (x)  y))) (+ y (lambda (y) y)))) [ y -> x ]
		assertEquals("(lambda (x_) (+ (+ 3 (+ x_ (lambda (x_) x))) (+ x (lambda (y) y))))", printer.get());
	}
}
