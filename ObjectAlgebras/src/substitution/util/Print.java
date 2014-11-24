package substitution.util;

import java.util.function.Supplier;

import trees.ExpAlg;
import trees.LamAlg;

public class Print implements ExpAlg<Supplier<String>>, LamAlg<Supplier<String>> {

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