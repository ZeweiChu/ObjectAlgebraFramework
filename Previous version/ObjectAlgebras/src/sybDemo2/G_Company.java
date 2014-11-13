package sybDemo2;

import trees.SybAlg;

//BEGIN_G_COMPANY
public interface G_Company {
	<Company,Dept,SubUnit,Employee,Person,Salary> Company accept(SybAlg<Company,Dept,SubUnit,Employee,Person,Salary> alg);
}
//END_G_COMPANY