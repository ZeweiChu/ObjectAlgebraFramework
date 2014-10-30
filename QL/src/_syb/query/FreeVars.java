package _syb.query;

import java.util.Collections;
import java.util.Set;

public interface FreeVars extends ExpAlgMonoid<Set<String>>, StmtAlgMonoid<Set<String>>, FormAlgMonoid<Set<String>> {

	@Override 
	default Set<String> var(String varName) {
		return Collections.singleton(varName);
	}
	
}
