package expDemo3;

import java.util.Collections;
import java.util.List;

public interface FreeVarsExpAlg extends ExpAlgQuery<List<String>> {
	default Monoid<List<String>> m() {
		return new FreeVarsMonoid();
	}
	default List<String> Var(String s) {
		return Collections.singletonList(s);
	}
}
