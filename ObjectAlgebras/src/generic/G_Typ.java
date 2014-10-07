package generic;
import trees.StmAlg;
public interface G_Typ{
  <Stm,Exp,Typ> Typ accept(StmAlg<Stm,Exp,Typ> alg);
}