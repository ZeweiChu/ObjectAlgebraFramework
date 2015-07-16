package trees;

import com.zewei.annotation.processor.Algebra;

//BEGIN_DOUBLE_TREE
@Algebra
public interface DoubleAlg<E> {
	E Double(E e);
}
//END_DOUBLE_TREE
