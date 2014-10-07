package generic;
import trees.StmAlg;
public interface G_Stm{
  <Stm,Exp,Typ> Stm accept(StmAlg<Stm,Exp,Typ> alg);
}