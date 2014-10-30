package _syb.trafo;

import java.util.List;

import ql_obj_alg.check.types.Type;
import ql_obj_alg.syntax.IStmtAlg;

public interface StmtAlgId<E, S> extends IStmtAlg<E, S> {

	IStmtAlg<E,S> stmtAlg();
	
	@Override
	default S iff(E cond, List<S> statements) {
		return stmtAlg().iff(cond, statements);
	}

	@Override
	default S iffelse(E cond, List<S> statementsIf, List<S> statementsElse) {
		return stmtAlg().iffelse(cond, statementsIf, statementsElse);
	}

	@Override
	default S question(String id, String label, Type type) {
		return stmtAlg().question(id, label, type);
	}

	@Override
	default S question(String id, String label, Type type, E exp) {
		return stmtAlg().question(id, label, type, exp);
	}

}
