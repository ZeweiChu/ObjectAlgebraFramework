package sybDemo2;

import trees.SybAlg;

public interface G_Dept {
	<Company,Dept,SubUnit,Employee,Person,Salary> Dept accept(SybAlg<Company,Dept,SubUnit,Employee,Person,Salary> alg);
}