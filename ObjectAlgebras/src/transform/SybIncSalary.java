package transform;

import generic.*;
import trees.SybAlg;

//BEGIN_TRANSFORM_WITH_OAFRAMEWORK
public interface SybIncSalary extends SybAlgTransform {
	default G_SybAlg_Salary S(float p0) {
		return new G_SybAlg_Salary() {
			public <Company, Dept, SubUnit, Employee, Person, Salary> Salary accept(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {
				return alg.S(1.1f * p0);
			}
		};
	}
}
//END_TRANSFORM_WITH_OAFRAMEWORK