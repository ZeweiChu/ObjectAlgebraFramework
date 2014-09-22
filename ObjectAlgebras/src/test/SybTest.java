package test;

import generic.G_Company;

import java.util.ArrayList;
import java.util.List;

import library.StringMonoid;
import transform.SybAlgTransform;
import transform.SybRename;
import trees.SybAlg;

public class SybTest {
	static <Company, Dept, SubUnit, Employee, Person, Salary> Employee 
		ralf(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg){
		return alg.E(alg.P("Ralf", "Amsterdam"), alg.S(8000.0));
	}
	static <Company, Dept, SubUnit, Employee, Person, Salary> Employee 
		joost(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg){
		return alg.E(alg.P("Joost", "Amsterdam"), alg.S(1000.0));
	}
	static <Company, Dept, SubUnit, Employee, Person, Salary> Employee 
		marlow(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg){
		return alg.E(alg.P("Marlow", "Cambridge"), alg.S(2000.0));
	}
	static <Company, Dept, SubUnit, Employee, Person, Salary> Employee 
		blair(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg){
		return alg.E(alg.P("Blair", "London"), alg.S(100000.0));
	}
	static <Company, Dept, SubUnit, Employee, Person, Salary> Company 
		genCom(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg){
		
		List<SubUnit> s = (List<SubUnit>)new ArrayList<SubUnit>();
		s.add(alg.PU(joost(alg)));
		s.add(alg.PU(marlow(alg)));
		List<Dept> d = (List<Dept>)new ArrayList<Dept>();
		d.add(alg.D("Research", ralf(alg), s));
		d.add(alg.D("Strategy", blair(alg), (List<SubUnit>)new ArrayList<SubUnit>()));
		return alg.C(d);
	
	}
	public static void main(String[] args){
		StringQuerySybAlgebra stringQuerySybAlgebra = new StringQuerySybAlgebra(new StringMonoid());
		System.out.println(genCom(stringQuerySybAlgebra));
		
		
		SybAlgTransform sybAlgTransform = new SybAlgTransform(){};
		G_Company gCom = genCom(sybAlgTransform);
		G_Company gCom2 = gCom.accept(new SybRename(){}).accept(new SybRename(){}).accept(new SybRename(){}).accept(new SybRename(){}).accept(new SybRename(){}).accept(new SybRename(){}).accept(new SybRename(){}).accept(new SybRename(){});
		System.out.println(gCom2.accept(stringQuerySybAlgebra));
	
	}
}
