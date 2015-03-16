package trees;

import java.util.List;
import com.zewei.annotation.processor.Algebra;

//BEGIN_QL_TREE
@Algebra
public interface QLAlg<Exp, Stmt, Form> {
	Form form(String id, List<Stmt> stmts);
	Stmt iff(Exp cond, Stmt then);
	Stmt question(String id, String lbl, String type);
	Exp lit(int x);
	Exp var(String name);
	Exp geq(Exp lhs, Exp rhs);
}
//END_QL_TREE
