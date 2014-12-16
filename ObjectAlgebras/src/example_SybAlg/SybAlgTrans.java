package example_SybAlg;

import transform.SybAlgTransform;
import trees.SybAlg;

public class SybAlgTrans<Company, Dept, SubUnit, Employee, Person, Salary> implements SybAlgTransform<Company, Dept, SubUnit, Employee, Person, Salary> {
	private SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg;
	public SybAlgTrans(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {this.alg = alg;}
	public SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> sybAlg() {return alg;}
}
