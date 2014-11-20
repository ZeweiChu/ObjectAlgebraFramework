package substitution;

import java.util.Collections;
import java.util.function.Supplier;

import trees.ExpAlg;
import trees.LamAlg;

public class TestSubstitution {

	static class Print implements ExpAlg<Supplier<String>>, LamAlg<Supplier<String>> {

		@Override
		public Supplier<String> Lambda(String x, Supplier<String> e) {
			return () -> "(lambda (" + x + ") " + e.get() + ")";
		}

		@Override
		public Supplier<String> Apply(Supplier<String> e1, Supplier<String> e2) {
			return () -> "(" + e1.get() + " " + e2.get() + ")";
		}

		@Override
		public Supplier<String> Var(String s) {
			return () -> s;
		}

		@Override
		public Supplier<String> Lit(int i) {
			return () -> "" + i;
		}

		@Override
		public Supplier<String> Add(Supplier<String> e1, Supplier<String> e2) {
			return () -> "(+ " + e1.get() + " " + e2.get() + ")";
		}
		
	}
	
	static class Doit implements Substitution2<Supplier<String>> {
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
		return alg.Lambda("x", alg.Var("y"));
	}
	
	public static void main(String[] args) {
		Doit doit = new Doit();
		Subst<Supplier<String>> x = build(doit);
		Print print = new Print();
		Supplier<String> printer = x.subst("y", print.Var("x"), Collections.singleton("x"), Collections.emptyMap());
		System.out.println(printer.get());
	}
	
}
