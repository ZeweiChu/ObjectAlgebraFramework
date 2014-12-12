package expDemo3;

import static java.util.Collections.emptySet;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.*;

//BEGIN_FREEVARS_MONOID
public class FreeVarsMonoid implements Monoid<Set<String>> {
	public Set<String> empty() { return emptySet(); }
	public Set<String> join(Set<String> e1, Set<String> e2) {
		return concat(e1.stream(), e2.stream()).collect(toSet());
	}
}
//END_FREEVARS_MONOID
