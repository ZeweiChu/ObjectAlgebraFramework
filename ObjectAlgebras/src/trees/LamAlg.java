package trees;

import com.zewei.annotation.processor.Algebra;

//BEGIN_LAM_TREE
@Algebra
public interface LamAlg<Exp> {
	Exp Lam(String x, Exp e);
	Exp Apply(Exp e1, Exp e2);
}
//END_LAM_TREE
