package test;

import generic.*;

import java.util.ArrayList;
import java.util.List;

import query.CombineSybAlg;
import library.Pair;
import monoid.FloatMonoid;
import monoid.StringMonoid;
import transform.SybIncSalary;
import transform.SybAlgTransform;
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
		FloatQuerySybAlgebra dQuery = new FloatQuerySybAlgebra(new FloatMonoid());
		
		System.out.println("Result 1: (All names)\n\n" + genCom(sQuery) + "\n");
		System.out.println("Result 2: (Total salary)\n\n" + genCom(dQuery) + "\n");
		
		SybAlgTransform transform = new SybAlgTransform(){};
		G_SybAlg_Company gComRename = genCom(transform).accept(new SybRename(){}).accept(new SybRename(){}).accept(new SybRename(){});
		G_SybAlg_Company gComIncSalary = genCom(transform).accept(new SybIncSalary(){});
		
		System.out.println("Result 3: (Renaming)\n\n" + gComRename.accept(sQuery) + "\n");
		System.out.println("Result 4: (Increasing salary)\n\n" + gComIncSalary.accept(dQuery) + "\n");
		
		Pair<String, Float> result = genCom(
				new CombineSybAlg<String, String, String, String, String, String, Float, Float, Float, Float, Float, Float>
					(sQuery, dQuery)
			);
		System.out.println("Result 5: (All names, total salary)\n\nPair element1 = " + result.a() + "\nPair element2 = " + result.b() + "\n");
		
		Pair<G_SybAlg_Company, G_SybAlg_Company> gComTrans = genCom(
				new CombineSybAlg<G_SybAlg_Company, G_SybAlg_Dept, G_SybAlg_SubUnit, G_SybAlg_Employee, G_SybAlg_Person, G_SybAlg_Salary, G_SybAlg_Company, G_SybAlg_Dept, G_SybAlg_SubUnit, G_SybAlg_Employee, G_SybAlg_Person, G_SybAlg_Salary>
					(transform, transform)
			);
		gComTrans = new Pair<G_SybAlg_Company, G_SybAlg_Company>(
				gComTrans.a().accept(new SybRename(){}).accept(new SybRename(){}).accept(new SybRename(){}), 
				gComTrans.b().accept(new SybIncSalary(){})
			);
		Pair<String, Float> result2 = new Pair<String, Float>(
				gComTrans.a().accept(sQuery), 
				gComTrans.b().accept(dQuery)
			);
		System.out.println("Result 6: (Renaming, increasing salary)\n\nPair element1 = " + result2.a() + "\nPair element2 = " + result2.b() + "\n");
	}
}
