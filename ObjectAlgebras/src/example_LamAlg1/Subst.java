package example_LamAlg1;

import java.util.Map;
import java.util.Set;

public interface Subst<Exp> {
	Exp subst(String x, Exp e, Set<String> fv, Map<String, String> ren);
}
