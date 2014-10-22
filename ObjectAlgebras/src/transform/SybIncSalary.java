package transform;

import generic.*;
import trees.SybAlg;

public interface SybIncSalary extends SybAlgTransform {
	@Override
	default G_SybAlg_Salary S(float p0) {
		return new G_SybAlg_Salary() {
			@Override
			public <Company, Dept, SubUnit, Employee, Person, Salary> Salary accept(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {
				return alg.S(1.1f * p0);
			}
		};
	}
}
