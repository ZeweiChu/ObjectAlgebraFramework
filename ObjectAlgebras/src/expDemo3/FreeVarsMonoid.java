package expDemo3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//BEGIN_FREEVARS_MONOID
public class FreeVarsMonoid implements Monoid<List<String>> {
	public List<String> empty() {
		return Collections.emptyList();
	}
	public List<String> join(List<String> e1, List<String> e2) {
		List<String> res = new ArrayList<String>(e1);
		res.addAll(e2);
		return res;
	}
}
//END_FREEVARS_MONOID
