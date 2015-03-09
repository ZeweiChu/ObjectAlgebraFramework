package example_QLAlg3;

import java.util.Collections;
import java.util.Set;
import library.Monoid;
import monoid.SetMonoid;
import query.QLAlgQuery;

class UsedVars implements QLAlgQuery<Set<String>> {

	public Monoid<Set<String>> m() {
		return new SetMonoid<String>();
	}
	
	public Set<String> var(String varName) {
		return Collections.singleton(varName);
	}

}
