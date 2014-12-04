package _ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import library.Pair;
import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IStmtAlg;

public class Unless extends Conditional {
	
	public Unless(Exp cond, List<Stmt> body) {
		super(cond, body);
	}

	@Override
	public Stmt rename(Map<String, String> ren) {
		List<Stmt> stats = new ArrayList<>();
		for (Stmt s: then) {
			stats.add(s.rename(ren));
		}
		return new Unless(cond.rename(ren), stats);
	}


	@Override
	public <E, S> S recons(IExpAlg<E> expAlg, IStmtAlg<E, S> stmtAlg) {
		//throw new RuntimeException("extending visitor not possible, no recons for unless");
		// NB: do the desugaring
		List<S> stats = new ArrayList<>();
		for (Stmt s: then) {
			stats.add(s.recons(expAlg, stmtAlg));
		}
		return stmtAlg.iff(expAlg.not(cond.recons(expAlg)), stats);
	}

	@Override
	public int count() {
		int count = 1 + cond.count();
		for (Stmt s: then) {
			count += s.count();
		}
		return count;
	}

	@Override
	public Set<Pair<String, String>> controlDeps() {
		return new If(cond, then).controlDeps();
	}

}
