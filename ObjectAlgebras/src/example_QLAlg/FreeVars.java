package example_QLAlg;

import java.util.Collections;
import java.util.Set;
import library.Monoid;
import monoid.SetMonoid;
import query.QLAlgQuery;

class FreeVars implements QLAlgQuery<Set<String>> {
	
	public Monoid<Set<String>> m() {
		return new SetMonoid<String>();
	}
	
	public Set<String> question(String id, String label, String type) {
		return Collections.singleton(id);
	}

}

/*
class FreeVars implements QLAlg<Set<String>, Set<String>, Set<String>> {

	public Set<String> form(String id, List<Set<String>> statements) {
		return statements.stream().reduce(Collections.emptySet(), (x, y) -> {
			Set<String> res = new HashSet<String>(x);
			res.addAll(y);
			return res;
		});
	}
	
	...

	public Set<String> question(String id, String label, String type) {
		return Collections.singleton(id);
	}
	
}
*/