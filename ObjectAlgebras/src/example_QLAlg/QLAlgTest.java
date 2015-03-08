package example_QLAlg;

import java.util.Arrays;

import trees.QLAlg;

public class QLAlgTest {
	
	static <E, S, F> F makeQL(QLAlg<E, S, F> alg) {
		S s1 = alg.question("age", "What is your age?", "integer");
		E ifStmt = alg.geq(alg.var("age"), alg.lit(18));
		S thenStmt = alg.question("license", "Do you have a driver's license?", "boolean");
		S elseStmt = alg.question("license", "Do you have a driver's license?", "boolean", alg.bool(false));
		S s2 = alg.iffelse(ifStmt, thenStmt, elseStmt);
		return alg.form("DriverLicense", Arrays.asList(s1, s2));
	}
	
	void makeTest() {
		FreeVars freeVars = new FreeVars();
		System.out.println(makeQL(freeVars));
	}
	
	public static void main(String[] args) {
		QLAlgTest t = new QLAlgTest();
		t.makeTest();
	}
	
}
