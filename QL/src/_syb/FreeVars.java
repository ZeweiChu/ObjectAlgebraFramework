package _syb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import library.Monoid;
import monoids.SetMonoid;
import noa.Builder;
import noa.Union;
import ql_obj_alg.parse.TheParser;
import ql_obj_alg.syntax.IAllAlg;
import query.IExpAlgQuery;
import query.IFormAlgQuery;
import query.IStmtAlgQuery;

public class FreeVars extends IExpAlgQuery<Set<String>>{

	public FreeVars(Monoid<Set<String>> m) {
		super(m);
	}
	
	@Override
	public Set<String> var(String s){
		return Collections.singleton(s);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Builder builder = TheParser.parse(new FileInputStream("resources/inputfiles/test.QL"));
		
		SetMonoid<String> m = new SetMonoid<String>();
		IExpAlgQuery<Set<String>> e = new FreeVars(m);
		IStmtAlgQuery<Set<String>> s = new IStmtAlgQuery<>(m);
		IFormAlgQuery<Set<String>> f = new IFormAlgQuery<>(m);
		
		Set<String> fv = builder.build(Union.union(IAllAlg.class, f, s, e));
		System.out.println(fv);
	}

}
