package sybDemo2;

import trees.SybAlg;

public interface G_Company {
	<Company,Dept,SubUnit,Employee,Person,Salary> Company accept(SybAlg<Company,Dept,SubUnit,Employee,Person,Salary> alg);
}