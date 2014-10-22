package _syb;

import java.util.Map;

import library.Monoid;
import ql_obj_alg.check.types.Type;
import query.IStmtAlgQuery;

public class TypeEnv extends IStmtAlgQuery<Map<String,Type>> {
	/* 
	 * Collect the type environment used in type checking
	 * Only needs to visit question(x, l, t) and question(x, l, t, e).
	 * 
	 * Monoid is map of name -> type, where join is right overriding.
	 * (Could also be set of pairs)
	 */

	public TypeEnv(Monoid<Map<String, Type>> m) {
		super(m);
	}
	
	@Override
	public Map<String, Type> question(String p0, String p1, Type p2) {
		// TODO Auto-generated method stub
		return super.question(p0, p1, p2);
	}
	
	@Override
	public Map<String, Type> question(String p0, String p1, Type p2, Map<String, Type> p3) {
		// TODO Auto-generated method stub
		return super.question(p0, p1, p2, p3);
	}

}
