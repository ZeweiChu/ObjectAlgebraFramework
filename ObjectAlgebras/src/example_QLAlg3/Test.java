package example_QLAlg3;

import java.util.Arrays;
import java.util.Set;

import trees.QLAlg;

public class Test {
	
	//BEGIN_CLIENTCODE_MAKEQL
	<E, S, F> F makeQL(QLAlg<E, S, F> alg) {
		return alg.form("DriverLicense", Arrays.asList(
				alg.question("name", "Name?", "string"), 
				alg.question("age", "Age?", "integer"), 
				alg.iff(alg.geq(alg.var("age"), 
						               alg.lit(18)), 
					           alg.question("license", 
					        		   "License?", "boolean"))));		
	}
	//END_CLIENTCODE_MAKEQL
	
	void println(Object o) {
		System.out.println(o);
	}
	
	void go() {
		//BEGIN_CLIENTCODE_QLTEST
		println(makeQL(new UsedVars()));
		println(makeQL(new Rename<>(new UsedVars())));
		//END_CLIENTCODE_QLTEST
	}
	
	public static void main(String args[]) {
		Test t = new Test();
		t.go();
	}
	
}
