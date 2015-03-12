package example_QLAlg2;

import java.util.*;
import trees.QLAlg;

//BEGIN_QL_QUERY_ALG
import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static java.util.stream.Stream.concat;
import static java.util.stream.Collectors.toSet;

class UsedVars implements QLAlg<Set<String>, Set<String>, Set<String>> {
	public Set<String> form(String id, List<Set<String>> statements) {
		return statements.stream().reduce(emptySet(), (x, y) -> concat(x.stream(), y.stream()).collect(toSet()));
	}
	public Set<String> iff(Set<String> cond, Set<String> then) {
		return concat(cond.stream(), then.stream()).collect(toSet());
	}
	public Set<String> question(String id, String label, String type) { return emptySet(); }
	public Set<String> lit(int x) { return emptySet(); }
	public Set<String> var(String varName) { return singleton(varName); }
	public Set<String> geq(Set<String> lhs, Set<String> rhs) {
		return concat(lhs.stream(), rhs.stream()).collect(toSet());
	}
}
//END_QL_QUERY_ALG
