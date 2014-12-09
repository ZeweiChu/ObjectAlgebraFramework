package _syb.query;

import java.util.Collections;
import java.util.Set;

import query.IExpAlgQuery;
import query.IFormAlgQuery;
import query.IStmtAlgQuery;

public interface FreeVars extends IExpAlgQuery<Set<String>>, IStmtAlgQuery<Set<String>>, IFormAlgQuery<Set<String>> {
	
	@Override 
	default Set<String> var(String varName) {
		return Collections.singleton(varName);
	}
	
}
