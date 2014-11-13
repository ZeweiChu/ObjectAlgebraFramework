package sybDemo2;

import trees.SybAlg;

public interface G_Employee {
	<Company,Dept,SubUnit,Employee,Person,Salary> Employee accept(SybAlg<Company,Dept,SubUnit,Employee,Person,Salary> alg);
}