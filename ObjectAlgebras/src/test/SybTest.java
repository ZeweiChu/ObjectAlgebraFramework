package test;

import generic.G_Company;

import java.util.ArrayList;
import java.util.List;

import library.Pair;
import monoid.DoubleMonoid;
import monoid.StringMonoid;
import transform.SybIncSalary;
import transform.SybAlgTransform;
import transform.SybRename;
import trees.SybAlg;

public class SybTest {
	static <Company, Dept, SubUnit, Employee, Person, Salary> Employee 
		ralf(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {
		return alg.E(alg.P("Ralf", "Amsterdam"), alg.S(8000.0));
	}
	static <Company, Dept, SubUnit, Employee, Person, Salary> Employee 
		joost(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {
		return alg.E(alg.P("Joost", "Amsterdam"), alg.S(1000.0));
	}
	static <Company, Dept, SubUnit, Employee, Person, Salary> Employee 
		marlow(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {
		return alg.E(alg.P("Marlow", "Cambridge"), alg.S(2000.0));
	}
	static <Company, Dept, SubUnit, Employee, Person, Salary> Employee 
		blair(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {
		return alg.E(alg.P("Blair", "London"), alg.S(100000.0));
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
		DoubleQuerySybAlgebra dQuery = new DoubleQuerySybAlgebra(new DoubleMonoid());
		
		System.out.println("Result 1: (All names)\n\n" + genCom(sQuery) + "\n");
		System.out.println("Result 2: (Total salary)\n\n" + genCom(dQuery) + "\n");
		
		SybAlgTransform transform = new SybAlgTransform(){};
		G_Company gCom2 = genCom(transform).accept(new SybRename(){}).accept(new SybRename(){}).accept(new SybRename(){});
		G_Company gCom3 = genCom(transform).accept(new SybIncSalary(){});
		
		System.out.println("Result 3: (Renaming)\n\n" + gCom2.accept(sQuery) + "\n");
		System.out.println("Result 4: (Increasing salary)\n\n" + gCom3.accept(dQuery) + "\n");
		
		StringDoubleQuerySybAlgebra combine = new StringDoubleQuerySybAlgebra(sQuery, dQuery);
		Pair<String, Double> result = genCom(combine);
		System.out.println("Result 5: (All names, total salary)\n\nPair element1 = " + result.a() + "\nPair element2 = " + result.b() + "\n");
		
		G_Company gCom4 = genCom(transform).accept(new SybRename(){}).accept(new SybRename(){}).accept(new SybRename(){}).accept(new SybIncSalary(){});
		Pair<String, Double> result2 = gCom4.accept(combine);
		System.out.println("Result 6: (Renaming, increasing salary)\n\nPair element1 = " + result2.a() + "\nPair element2 = " + result2.b() + "\n");
	}
}
