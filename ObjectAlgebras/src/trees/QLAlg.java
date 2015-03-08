package trees;

import java.util.List;
import com.zewei.annotation.processor.Algebra;

@Algebra
public interface QLAlg<E, S, F> {
	F form(String id, List<S> statements);
	S iffelse(E cond, S then, S els);
	S question(String id, String label, String type);
	S question(String id, String label, String type, E exp);
	E lit(int x);
	E bool(boolean b);
	E string(String s);
	E var(String varName);
	E geq(E lhs, E rhs);
}

