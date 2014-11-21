package _syb.query;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import library.Monoid;
import monoids.MapMonoid;
import noa.Builder;
import ql_obj_alg.check.types.Type;
import ql_obj_alg.parse.TheParser;

public class TestTypeEnv {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Builder builder = TheParser.parse(new FileInputStream(
				"resources/inputfiles/test.QL"));
		
		Map<String, Type> deps = builder.build(new TypeEnv() {
			@Override
			public Monoid<Map<String, Type>> m() {
				return new MapMonoid<>();
			}
		});		
		System.out.println(deps);
	}
	
}
