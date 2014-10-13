package generic;

import trees.StmAlg;

public interface G_Stm {
	<Stm,EExp,Typ> Stm accept(StmAlg<Stm,EExp,Typ> alg);
}