package example_SybAlg;

import transform.SybAlgTransform;
import trees.SybAlg;

//BEGIN_TRANSFORM_WITH_OAFRAMEWORK
public class IncSalary<Company, Dept, SubUnit, Employee, Person, Salary> implements SybAlgTransform<Company, Dept, SubUnit, Employee, Person, Salary> {
	private SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg;
	public SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> sybAlg() {return alg;}
	public IncSalary(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {this.alg=alg;}
	public Salary S(float salary) {return alg.S(1.1f * salary);}
}
//END_TRANSFORM_WITH_OAFRAMEWORK