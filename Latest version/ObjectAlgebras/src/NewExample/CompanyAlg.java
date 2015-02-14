package NewExample;

import java.util.List;

import com.zewei.annotation.processor.Algebra;

@Algebra
public interface CompanyAlg<Company, Dept, Unit, Employee, Person, Salary> {
	Company C(List<Dept> depts);
	Dept D(Employee manager, List<Unit> units);
	Employee E(Person person, Salary salary);
	Salary S(Ref<Double> value);
	Person P();
	Unit PU(Employee employee);
	Unit DU(Dept dept);
}

