package _syb.query;

import java.util.Collections;
import java.util.Map;

import ql_obj_alg.check.types.Type;
import query.IExpAlgQuery;
import query.IFormAlgQuery;
import query.IStmtAlgQuery;

public interface TypeEnv extends IFormAlgQuery<Map<String,Type>>, IStmtAlgQuery<Map<String,Type>>, IExpAlgQuery<Map<String,Type>> {

	@Override 
	default Map<String, Type> question(String p0,String p1,Type p2) {
		return Collections.singletonMap(p0, p2);
	}
	
	@Override 
	default Map<String, Type> question(String p0,String p1,Type p2,Map<String, Type> p3) {
		return Collections.singletonMap(p0, p2);
	}
	
}
