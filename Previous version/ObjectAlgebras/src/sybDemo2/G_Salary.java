package sybDemo2;

import trees.SybAlg;

//BEGIN_G_SALARY
public interface G_Salary {
	<Company,Dept,SubUnit,Employee,Person,Salary> Salary accept(SybAlg<Company,Dept,SubUnit,Employee,Person,Salary> alg);
}
//END_G_SALARY