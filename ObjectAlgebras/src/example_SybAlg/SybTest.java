package example_SybAlg;

import java.util.ArrayList;
import java.util.List;
import library.Pair;
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
		
		List<SubUnit> s = new ArrayList<SubUnit>();
		s.add(alg.PU(joost(alg)));
		s.add(alg.PU(marlow(alg)));
		List<Dept> d = new ArrayList<Dept>();
		d.add(alg.D("Research", ralf(alg), s));
		d.add(alg.D("Strategy", blair(alg), new ArrayList<SubUnit>()));
		return alg.C(d);
	
	}
	
	public static void main(String[] args) {
		
		StringQuery sQuery = new StringQuery();
		SalaryBill fQuery = new SalaryBill();
		
		System.out.println("Result 1: (All names)\n\n" + genCom(sQuery) + "\n");
		System.out.println("Result 2: (Total salary)\n\n" + genCom(fQuery) + "\n");
		
		Rename rename = new Rename(sQuery);
		IncSalary incSalary = new IncSalary(fQuery);
		
		System.out.println("Result 3: (Renaming)\n\n" + genCom(rename) + "\n");
		System.out.println("Result 4: (Increasing salary)\n\n" + genCom(incSalary) + "\n");
		
		StringFloatQuery combine1 = new StringFloatQuery(sQuery, fQuery);
		StringFloatQuery combine2 = new StringFloatQuery(rename, incSalary);
		Pair<String, Float> result1 = genCom(combine1);
		Pair<String, Float> result2 = genCom(combine2);
		
		System.out.println("Result 5: (All names, total salary)\n\nPair element1 = " + result1.a() + "\nPair element2 = " + result1.b() + "\n");
		System.out.println("Result 6: (Renaming, increasing salary)\n\nPair element1 = " + result2.a() + "\nPair element2 = " + result2.b() + "\n");
	
		Rename2 rename2 = new Rename2(sQuery);
		System.out.println("Result 7: (Renaming2)\n\n" + genCom(rename2).apply("-"));
		
	}
	
}
