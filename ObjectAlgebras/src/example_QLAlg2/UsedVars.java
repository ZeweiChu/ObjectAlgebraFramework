package example_QLAlg2;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import trees.QLAlg;

//BEGIN_QL_QUERY_ALG
class UsedVars implements QLAlg<Set<String>, Set<String>, Set<String>>{
	public Set<String> form(String id, List<Set<String>> statements) {
		Set<String> res = new HashSet<String>();
		for (Set<String> s : statements)
			res.addAll(s);
		return res;
	}
	public Set<String> iff(Set<String> cond, Set<String> then) {
		Set<String> res = new HashSet<String>(cond);
		res.addAll(then);
		return res;
	}
	public Set<String> question(String id, String label, String type) {
		return Collections.emptySet();
	}
	public Set<String> lit(int x) {
		return Collections.emptySet();
	}
	public Set<String> var(String varName) {
		return Collections.singleton(varName);
	}
	public Set<String> geq(Set<String> lhs, Set<String> rhs) {
		Set<String> res = new HashSet<String>(lhs);
		res.addAll(rhs);
		return res;
	}
}
//END_QL_QUERY_ALG
