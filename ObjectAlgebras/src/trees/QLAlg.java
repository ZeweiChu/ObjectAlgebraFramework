package trees;

import java.util.List;
import com.zewei.annotation.processor.Algebra;

@Algebra
public interface QLAlg<Exp, Stmt, Form> {
	Form form(String id, List<Stmt> statements);
	Stmt iff(Exp cond, Stmt then);
	Stmt question(String id, String label, String type);
	Exp lit(int x);
	Exp var(String varName);
	Exp geq(Exp lhs, Exp rhs);
}

