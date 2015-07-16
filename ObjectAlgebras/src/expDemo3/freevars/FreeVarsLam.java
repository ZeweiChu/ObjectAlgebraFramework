package expDemo3.freevars;

import static java.util.stream.Collectors.toSet;

import java.util.Set;

import query.LamAlgQuery;

// BEGIN_EXTENDFREEVARS
interface FreeVarsLam extends LamAlgQuery<Set<String>> {
	default Set<String> Lam(String x, Set<String> f) {
		return f.stream().filter(y -> !y.equals(x)).collect(toSet());
	}
}
// END_EXTENDFREEVARS
