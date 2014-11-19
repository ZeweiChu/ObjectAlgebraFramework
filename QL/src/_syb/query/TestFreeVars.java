package _syb.query;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import library.Monoid;
import monoids.SetMonoid;
import noa.Builder;
import ql_obj_alg.parse.TheParser;

public class TestFreeVars {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Builder builder = TheParser.parse(new FileInputStream(
				"resources/inputfiles/test.QL"));
		
		Set<String> fv = builder.build(new FreeVars() {
			@Override
			public Monoid<Set<String>> mE() {
				return new SetMonoid<>();
			}
			
			@Override
			public Monoid<Set<String>> mF() {
				return new SetMonoid<>();
			}
			
			@Override
			public Monoid<Set<String>> mS() {
				return new SetMonoid<>();
			}
		});
		
		System.out.println(fv);
		
		// The following does not work because FreeVars decides over which Monoid
		// to query (Sets). If monoids have a unit, we could abstract over that and
		// compute free vars using lists, or ints, or whatever monoid.
		// IOW: you can collect, and count using the same code :-)
//		List<String> ls = builder.build(new FreeVars() {
//			@Override
//			public Monoid<List<String>> m() {
//				return new ListMonoid<>();
//			}
//		});

	}
	
}
