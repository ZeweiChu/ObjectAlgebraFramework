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
		
		StringQuerySybAlgebra stringQuery = new StringQuerySybAlgebra(new StringMonoid());
		SybRename renaming = new SybRename(stringQuery);
		System.out.println(genCom(stringQuery));
		System.out.println(genCom(renaming));
		
		FloatQuerySybAlgebra floatQuery = new FloatQuerySybAlgebra(new FloatMonoid());
		SybIncSalary incSalary = new SybIncSalary(floatQuery);
		System.out.println(genCom(floatQuery));
		System.out.println(genCom(incSalary));
		
		StringFloatQuerySybAlgebra combine1 = new StringFloatQuerySybAlgebra(stringQuery, floatQuery);
		StringFloatQuerySybAlgebra combine2 = new StringFloatQuerySybAlgebra(renaming, incSalary);
		Pair<String, Float> result1 = genCom(combine1);
		Pair<String, Float> result2 = genCom(combine2);
		System.out.println("result1.a = " + result1.a() + "\nresult1.b = " + result1.b());
		System.out.println("result2.a = " + result2.a() + "\nresult2.b = " + result2.b());
		
	}
}
