package deps2;

import static java.util.Collections.singleton;

import java.util.Set;
import static java.util.stream.Collectors.toSet;
import library.Pair;
import query.G_ExpAlgQuery;
import query.G_StatAlgQuery;

//BEGIN_DEPS2
public interface DepGraph extends G_ExpAlgQuery<Set<String>>, G_StatAlgQuery<Set<String>, Set<Pair<String,String>>> {
	default Set<String> Var(String p0) { return singleton(p0); }
	default Set<Pair<String, String>> Assign(String p0, Set<String> p1) {
		return p1.stream().map((x) -> new Pair<>(p0, x)).collect(toSet());
	}
}
//END_DEPS2