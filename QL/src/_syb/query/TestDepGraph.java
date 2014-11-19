package _syb.query;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import library.Monoid;
import library.Pair;
import monoids.SetMonoid;
import noa.Builder;
import ql_obj_alg.parse.TheParser;

public class TestDepGraph {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Builder builder = TheParser.parse(new FileInputStream(
				"resources/inputfiles/test.QL"));
		
		Set<Pair<String,String>> deps = builder.build(new DepGraph() {
			
			@Override
			public Monoid<Set<String>> mE() {
				return new SetMonoid<>();
			}
			
			@Override
			public Monoid<Set<Pair<String,String>>> mF() {
				return new SetMonoid<>();
			}
			
			@Override
			public Monoid<Set<Pair<String,String>>> mS() {
				return new SetMonoid<>();
			}
		});
		
		System.out.println(deps);
	}
	
}
