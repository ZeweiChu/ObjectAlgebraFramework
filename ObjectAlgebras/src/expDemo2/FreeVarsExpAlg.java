package expDemo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface FreeVarsExpAlg extends ExpAlg<List<String>> {
	default List<String> Var(String s) {
		return Collections.singletonList(s);
	}
	default List<String> Lit(int i) {
		return Collections.emptyList();
	}
	default List<String> Add(List<String> e1, List<String> e2) {
		List<String> res = new ArrayList<String>(e1);
		res.addAll(e2);
		return res;
	}
}
