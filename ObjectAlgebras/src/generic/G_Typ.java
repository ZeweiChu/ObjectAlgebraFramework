package generic;

import trees.StmAlg;

public interface G_Typ {
	<Stm,EExp,Typ> Typ accept(StmAlg<Stm,EExp,Typ> alg);
}