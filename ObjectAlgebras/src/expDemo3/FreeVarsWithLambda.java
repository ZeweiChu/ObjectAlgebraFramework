package expDemo3;

import java.util.Set;
import static java.util.stream.Collectors.toSet;

//BEGIN_EXTENDFREEVARS
public interface FreeVarsWithLambda extends FreeVars, LamAlgQuery<Set<String>> {
	default Monoid<Set<String>> m() { return new SetMonoid<>(); }
	
	default Set<String> Lam(String x, Set<String> fv) {
		return fv.stream().filter((y) -> !y.equals(x)).collect(toSet());
	}
}
//END_EXTENDFREEVARS
