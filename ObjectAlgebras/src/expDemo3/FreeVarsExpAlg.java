package expDemo3;

public interface FreeVarsExpAlg extends ExpAlgQuery<String[]> {
	default Monoid<String[]> m() { return new FreeVarsMonoid(); }
	default String[] Var(String s) { return new String[]{s}; }
}
