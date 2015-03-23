package auto;

import java.util.HashSet;
import java.util.Set;

import library.Monoid;

public class StringSetMonoid implements Monoid<Set<String>> {

	@Override
	public Set<String> empty() {
		return new HashSet<String>();
	}

	@Override
	public Set<String> join(Set<String> arg0, Set<String> arg1) {
		Set<String> res = new HashSet<String>();
		res.addAll(arg0);
		res.addAll(arg1);
		return res;
	}

}
