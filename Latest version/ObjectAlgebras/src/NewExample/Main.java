package NewExample;

import java.util.Arrays;
import java.util.List;

import combinator.CombineCompanyAlg;
import library.Monoid;
import library.Pair;
import query.CompanyAlgQuery;

public class Main {

	static <C,D,U,E,P,S> C makeCompany(CompanyAlg<C,D,U,E,P,S> alg) {
		E ralf  = alg.E(alg.P(), alg.S(new Ref<Double>(8000.0d)));
		E joost = alg.E(alg.P(), alg.S(new Ref<Double>(1000.0d)));
		E simon = alg.E(alg.P(), alg.S(new Ref<Double>(2000.0d)));
		E blair = alg.E(alg.P(), alg.S(new Ref<Double>(100000.0d)));
		List<U> us = Arrays.asList(alg.PU(joost), alg.PU(simon));
		List<D> ds = Arrays.asList(alg.D(ralf, us), alg.D(blair, Arrays.asList()));
		return alg.C(ds);
	}
	
	public static void main(String[] args) {
		Pair<SalaryBill, IncSalary> c = makeCompany(new CombineAlg(new SalaryBillAlg(), new IncSalaryAlg()));	
		System.out.println(c.a().salaryBill());
		c.b().increaseSalary();
		System.out.println(c.a().salaryBill());
	}

}

interface SalaryBill {
	Double salaryBill();
}

interface IncSalary {
	void increaseSalary();
}

class SalaryBillAlg implements CompanyAlgQuery<SalaryBill> {

	@Override
	public SalaryBill S(Ref<Double> value) {
		return () -> value.getValue();
	}

	@Override
	public Monoid<SalaryBill> m() {
		return new SalaryBillMonoid();
	}
	
}

class IncSalaryAlg implements
	CompanyAlg<IncSalary, IncSalary, IncSalary, IncSalary, IncSalary, IncSalary> {

	@Override
	public IncSalary C(List<IncSalary> depts) {
		return () -> depts.stream().forEach(IncSalary::increaseSalary);
	}

	@Override
	public IncSalary D(IncSalary manager, List<IncSalary> units) {
		return () -> {
			manager.increaseSalary();
			units.stream().forEach(IncSalary::increaseSalary);
		};
	}

	@Override
	public IncSalary E(IncSalary person, IncSalary salary) {
		return () -> {
			person.increaseSalary();
			salary.increaseSalary();
		};
	}

	@Override
	public IncSalary S(Ref<Double> value) {
		return () -> value.setValue(value.getValue() * 1.1);
	}

	@Override
	public IncSalary P() {
		return () -> {};
	}

	@Override
	public IncSalary PU(IncSalary employee) {
		return () -> employee.increaseSalary();
	}

	@Override
	public IncSalary DU(IncSalary dept) {
		return () -> dept.increaseSalary();
	}
	
}

class CombineAlg extends CombineCompanyAlg<SalaryBill, SalaryBill, SalaryBill, SalaryBill, SalaryBill, SalaryBill,
	IncSalary, IncSalary, IncSalary, IncSalary, IncSalary, IncSalary> {

	public CombineAlg(CompanyAlg<SalaryBill, SalaryBill, SalaryBill, SalaryBill, SalaryBill, SalaryBill> _alg1,
			CompanyAlg<IncSalary, IncSalary, IncSalary, IncSalary, IncSalary, IncSalary> _alg2) {
		super(_alg1, _alg2);
	}
	
}
