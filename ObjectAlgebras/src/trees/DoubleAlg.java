package trees;

import com.zewei.annotation.processor.Algebra;

//BEGIN_DOUBLE_TREE
@Algebra
public interface DoubleAlg<Exp> {
	Exp Double(Exp e);
}
//END_DOUBLE_TREE
