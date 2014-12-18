package trees;

import java.util.List;

import com.zewei.annotation.processor.Algebra;

//BEGIN_SYB_TREE
@Algebra
public interface SybAlg<Company, Dept, Unit, Employee, Person, Salary>{
  public Company C(List<Dept> depts);
  public Dept D(String name, Employee manager, List<Unit> units);
  public Unit PU(Employee employee);
  public Unit DU(Dept dept);
  public Employee E(Person p, Salary s);
  public Person P(String name, String address);
  public Salary S(float salary);
}
//END_SYB_TREE
