package expDemo3;

import static java.util.Collections.singleton;

import java.util.Set;

//BEGIN_FREEVARS_WITH_MONOID
public interface FreeVarsExpAlg extends ExpAlgQuery<Set<String>> {
	default Monoid<Set<String>> m() { return new FreeVarsMonoid(); }
	default Set<String> Var(String s) { return singleton(s); }
}
//END_FREEVARS_WITH_MONOID
