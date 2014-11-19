package _syb.query;

import java.util.Collections;
import java.util.Set;

import ql_obj_alg.check.types.Type;
import query.G_IExpAlgQuery;
import query.G_IFormAlgQuery;
import query.G_IStmtAlgQuery;

public interface FreeVars extends G_IExpAlgQuery<Set<String>>, 
	G_IStmtAlgQuery<Set<String>, Set<String>>, G_IFormAlgQuery<Set<String>, Set<String>, Set<String>> {

	
	@Override
	default java.util.Set<String> form(String p0, java.util.List<java.util.Set<String>> p1) {
		return mS().fold(p1);
	};
	
	@Override 
	default Set<String> var(String varName) {
		return Collections.singleton(varName);
	}

	@Override 
	default Set<String> question(String p0,String p1,Type p2,Set<String> p3) {
		return p3;
	}
	
	@Override
	default Set<String> iff(Set<String> p0, java.util.List<Set<String>> p1) {
		// join stms p1  + exprs p0
		return mS().join(p0, mS().fold(p1));
	}
	
	@Override
	default Set<String> iffelse(Set<String> p0, java.util.List<Set<String>> p1, java.util.List<Set<String>> p2) {
		// join stms p1  + exprs p0
		return mS().join(p0, mS().join(mS().fold(p1), mS().fold(p2)));
	}
	
}
