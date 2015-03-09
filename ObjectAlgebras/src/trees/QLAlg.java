package trees;

import java.util.List;
import com.zewei.annotation.processor.Algebra;

@Algebra
public interface QLAlg<E, S, F> {
	F form(String id, List<S> statements);
	S iff(E cond, S then);
	S question(String id, String label, String type);
	E lit(int x);
	E bool(boolean b);
	E var(String varName);
	E geq(E lhs, E rhs);
}

