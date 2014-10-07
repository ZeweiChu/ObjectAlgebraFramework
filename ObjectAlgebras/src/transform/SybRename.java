package transform;

import generic.*;
import trees.SybAlg;

public interface SybRename extends SybAlgTransform {
	@Override
	default G_Person P(String name, String address) {
		return new G_Person() {
			@Override
			public <Company, Dept, SubUnit, Employee, Person, Salary> Person accept(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {
				return alg.P("_" + name, address);
			}
		};
	}
}
