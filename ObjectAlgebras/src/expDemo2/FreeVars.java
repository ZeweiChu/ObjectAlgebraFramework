package expDemo2;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static java.util.stream.Stream.concat;

import java.util.Set;
import static java.util.stream.Collectors.toSet;


//BEGIN_FREEVARS_WITHOUT_MONOID
class FreeVars implements ExpAlg<Set<String>> {
	public Set<String> Var(String s) { return singleton(s); }
	public Set<String> Lit(int i) { return emptySet(); }
	public Set<String> Add(Set<String> e1, Set<String> e2) {
		return concat(e1.stream(), e2.stream()).collect(toSet());
	}
}
//END_FREEVARS_WITHOUT_MONOID
