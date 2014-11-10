package sybDemo2;

import trees.SybAlg;

public interface G_SubUnit {
	<Company,Dept,SubUnit,Employee,Person,Salary> SubUnit accept(SybAlg<Company,Dept,SubUnit,Employee,Person,Salary> alg);
}