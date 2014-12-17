package trees;

import com.zewei.annotation.processor.Algebra;

//BEGIN_EXP_ALG
@Algebra
public interface ExpAlg<Exp> {
	Exp Var(String s);
	Exp Lit(int i);
	Exp Add(Exp e1, Exp e2);
}
//END_EXP_ALG
