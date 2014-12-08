package _ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import library.Pair;
import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IStmtAlg;

public class If extends Conditional {

	public If(Exp cond, List<Stmt> statements) {
		super(cond, statements);
	}

	@Override
	public Stmt rename(Map<String, String> ren) {
		List<Stmt> body = new ArrayList<>();
		for (Stmt s: this.then) {
			body.add(s.rename(ren));
		}
		return new If(cond.rename(ren), body);
	}

	@Override
	public <E, S> S recons(IExpAlg<E> expAlg, IStmtAlg<E, S> stmtAlg) {
		List<S> stats = new ArrayList<>();
		for (Stmt s: then) {
			stats.add(s.recons(expAlg, stmtAlg));
		}
		return stmtAlg.iff(cond.recons(expAlg), stats);
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
		return new IfElse(cond, then, Collections.emptyList()).controlDeps();
	}

	

}