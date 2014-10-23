package _syb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import library.Monoid;
import monoids.MapMonoid;
import noa.Builder;
import noa.Union;
import ql_obj_alg.check.types.Type;
import ql_obj_alg.parse.TheParser;
import ql_obj_alg.syntax.IAllAlg;
import query.IExpAlgQuery;
import query.IFormAlgQuery;
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
	public Map<String, Type> question(String name, String label, Type type) {
		return Collections.singletonMap(name, type);
	}
	
	@Override
	public Map<String, Type> question(String name, String label, Type type, Map<String, Type> exp) {
		return Collections.singletonMap(name, type);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Builder builder = TheParser.parse(new FileInputStream("resources/inputfiles/test.QL"));
		
		MapMonoid<String, Type> m = new MapMonoid<String,Type>();
		IExpAlgQuery<Map<String, Type>> e = new IExpAlgQuery<>(m);
		IFormAlgQuery<Map<String,Type>> f = new IFormAlgQuery<>(m);
		TypeEnv s = new TypeEnv(m);
		
		Map<String,Type> tenv = builder.build(Union.union(IAllAlg.class, f, s, e));
		System.out.println(tenv);
	}

}
