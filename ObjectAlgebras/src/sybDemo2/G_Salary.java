package sybDemo2;

import trees.SybAlg;

public interface G_Salary {
	<Company,Dept,SubUnit,Employee,Person,Salary> Salary accept(SybAlg<Company,Dept,SubUnit,Employee,Person,Salary> alg);
}