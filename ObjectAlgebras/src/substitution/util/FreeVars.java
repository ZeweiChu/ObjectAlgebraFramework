package substitution.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import library.Monoid;
import monoid.SetMonoid;
import query.ExpAlgQuery;
import query.LamAlgQuery;

public class FreeVars implements LamAlgQuery<Set<String>>, ExpAlgQuery<Set<String>> {
	@Override
	public Monoid<Set<String>> m() {
		return new SetMonoid<>();
	}

	@Override
	public Set<String> Lambda(String x, Set<String> e) {
		Set<String> set = new HashSet<>(e);
		set.remove(x);
		return set;
	}

	@Override
	public Set<String> Var(String s) {
		return m().join(m().empty(), Collections.singleton(s));
	}

	
}