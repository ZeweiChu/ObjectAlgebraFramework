package expDemo3;

import static java.util.Collections.singleton;

import java.util.Set;

//BEGIN_FREEVARS_WITH_MONOID
class FreeVars implements ExpAlgQuery<Set<String>> {
	public Monoid<Set<String>> m() { return new SetMonoid<String>(); }
	public Set<String> Var(String s) { return singleton(s); }
}
//END_FREEVARS_WITH_MONOID
