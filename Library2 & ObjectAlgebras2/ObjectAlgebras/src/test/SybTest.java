package test;

import java.util.ArrayList;
import java.util.List;
import library.Pair;
import monoid.FloatMonoid;
import monoid.StringMonoid;
import transform.SybIncSalary;
import transform.SybRename;
import trees.SybAlg;

public class SybTest {
	static <Company, Dept, SubUnit, Employee, Person, Salary> Employee 
		ralf(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {
		return alg.E(alg.P("Ralf", "Amsterdam"), alg.S(8000.0f));
	}
	static <Company, Dept, SubUnit, Employee, Person, Salary> Employee 
		joost(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {
		return alg.E(alg.P("Joost", "Amsterdam"), alg.S(1000.0f));
	}
	static <Company, Dept, SubUnit, Employee, Person, Salary> Employee 
		marlow(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {
		return alg.E(alg.P("Marlow", "Cambridge"), alg.S(2000.0f));
	}
	static <Company, Dept, SubUnit, Employee, Person, Salary> Employee 
		blair(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {
		return alg.E(alg.P("Blair", "London"), alg.S(100000.0f));
	}
	static <Company, Dept, SubUnit, Employee, Person, Salary> Company 
		genCom(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {
		
		List<SubUnit> s = (List<SubUnit>)new ArrayList<SubUnit>();
		s.add(alg.PU(joost(alg)));
		s.add(alg.PU(marlow(alg)));
		List<Dept> d = (List<Dept>)new ArrayList<Dept>();
		d.add(alg.D("Research", ralf(alg), s));
		d.add(alg.D("Strategy", blair(alg), (List<SubUnit>)new ArrayList<SubUnit>()));
		return alg.C(d);
	
	}
	public static void main(String[] args) {
		StringQuerySybAlgebra sQuery = new StringQuerySybAlgebra(new StringMonoid());
		FloatQuerySybAlgebra fQuery = new FloatQuerySybAlgebra(new FloatMonoid());
		
		System.out.println("Result 1: (All names)\n\n" + genCom(sQuery) + "\n");
		System.out.println("Result 2: (Total salary)\n\n" + genCom(fQuery) + "\n");
		
		SybRename rename = new SybRename(sQuery);
		SybIncSalary incSalary = new SybIncSalary(fQuery);
		
		System.out.println("Result 3: (Renaming)\n\n" + genCom(rename) + "\n");
		System.out.println("Result 4: (Increasing salary)\n\n" + genCom(incSalary) + "\n");
		
		StringFloatQuerySybAlgebra combine1 = new StringFloatQuerySybAlgebra(sQuery, fQuery);
		StringFloatQuerySybAlgebra combine2 = new StringFloatQuerySybAlgebra(rename, incSalary);
		Pair<String, Float> result1 = genCom(combine1);
		Pair<String, Float> result2 = genCom(combine2);
		
		System.out.println("Result 5: (All names, total salary)\n\nPair element1 = " + result1.a() + "\nPair element2 = " + result1.b() + "\n");
		System.out.println("Result 6: (Renaming, increasing salary)\n\nPair element1 = " + result2.a() + "\nPair element2 = " + result2.b() + "\n");
	}
}
