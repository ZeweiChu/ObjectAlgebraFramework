package example_QLAlg3;

import java.util.Arrays;
import java.util.Set;

import trees.QLAlg;

public class Test {
	
	//BEGIN_CLIENTCODE_MAKEQL
	<E, S, F> F makeQL(QLAlg<E, S, F> alg) {
		S s0 = alg.question("name", "What is your name?",
				"string");
		S s1 = alg.question("age", "What is your age?",
				"integer");
		E ifStmt = alg.geq(alg.var("age"), alg.lit(18));
		S thenStmt = alg.question("license",
				"Do you have a driver's license?", "boolean");
		S s2 = alg.iff(ifStmt, thenStmt);
		return alg.form("DriverLicense",
				Arrays.asList(s0, s1, s2));		
	}
	//END_CLIENTCODE_MAKEQL
	
	void go() {
		//BEGIN_CLIENTCODE_QLTEST
		UsedVars usedVars = new UsedVars();
		System.out.println(makeQL(usedVars));
		Rename<Set<String>, Set<String>, Set<String>> rename = new Rename<Set<String>,
					Set<String>, Set<String>>(usedVars);
		System.out.println(makeQL(rename));
		//END_CLIENTCODE_QLTEST
	}
	
	public static void main(String args[]) {
		Test t = new Test();
		t.go();
	}
	
}
