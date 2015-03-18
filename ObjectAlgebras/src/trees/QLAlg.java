package trees;

import java.util.List;
import com.zewei.annotation.processor.Algebra;

//BEGIN_QL_TREE
@Algebra
public interface QLAlg<E, S, F> {
	F form(String name, List<S> body);
	S iff(E cond, S then);
	S question(String name,String label,String type);
	E lit(int n);
	E var(String x);
	E geq(E lhs, E rhs);
}
//END_QL_TREE
