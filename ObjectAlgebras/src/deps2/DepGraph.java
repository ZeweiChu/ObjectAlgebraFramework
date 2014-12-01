package deps2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import library.Pair;
import query.G_ExpAlgQuery;
import query.G_StatAlgQuery;

//BEGIN_DEPS2
public interface DepGraph extends G_ExpAlgQuery<Set<String>>, G_StatAlgQuery<Set<String>, Set<Pair<String,String>>> {
	default Set<String> Var(String p0) {return Collections.singleton(p0);}
	default Set<Pair<String, String>> Assign(String p0,Set<String> p1) {
		Set<Pair<String,String>> deps = new HashSet<>();
		for (String x: p1) {
			deps.add(new Pair<>(p0, x));
		}
		return deps;}
}
//END_DEPS2