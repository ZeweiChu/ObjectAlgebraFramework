package example_SybAlg;

import trees.SybAlg;
import util.SybAlgTrans;

//BEGIN_TRANSFORM_WITH_OAFRAMEWORK
class IncreaseSalary<Company, Dept, SubUnit, Employee, Person, Salary> extends SybAlgTrans<Company, Dept, SubUnit, Employee, Person, Salary> {
	IncreaseSalary(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {super(alg);}
	public Salary S(float salary) {return sybAlg().S(1.1f * salary);}
}
//END_TRANSFORM_WITH_OAFRAMEWORK
