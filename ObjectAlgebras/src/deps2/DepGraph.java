package deps2;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import library.Pair;
import query.G_ExpAlgQuery;
import query.G_StatAlgQuery;

//BEGIN_DEPS2
interface DepGraph extends
		G_ExpAlgQuery<Set<String>>,
		G_StatAlgQuery<Set<String>,
				Set<Pair<String, String>>> {
	default Set<String> Var(String x) {
		return Collections.singleton(x);
	}
	default Set<Pair<String, String>> Assign(String x, Set<String> e) {
		return e.stream().map(y -> new Pair<>(x, y))
				.collect(Collectors.toSet());
	}
}
//END_DEPS2