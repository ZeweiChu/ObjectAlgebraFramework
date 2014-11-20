package deps;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import library.Pair;
import query.G_ExpAlgQuery;
import query.G_StatAlgQuery;

public interface DepGraph extends G_ExpAlgQuery<Set<String>>, G_StatAlgQuery<Set<String>, Set<Pair<String,String>>> {

	@Override 
	default Set<String> Var(String p0) {
		return Collections.singleton(p0);
	}
	
	@Override 
	default Set<Pair<String, String>> Assign(String p0,Set<String> p1) {
		Set<Pair<String,String>> deps = new HashSet<>();
		for (String x: p1) {
			deps.add(new Pair<>(p0, x));
		}
		return deps;
	}
	
}
