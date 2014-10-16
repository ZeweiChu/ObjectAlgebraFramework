package sybDemo2;

import trees.SybAlg;

public interface G_Person {
	<Company,Dept,SubUnit,Employee,Person,Salary> Person accept(SybAlg<Company,Dept,SubUnit,Employee,Person,Salary> alg);
}