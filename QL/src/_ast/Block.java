package _ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import library.Pair;
import ql_obj_alg.check.types.Type;
import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IStmtAlg;

public class Block extends Stmt {

	private List<Stmt> stats;

	public Block(List<Stmt> stats) {
		this.stats = stats;
	}

	@Override
	public Stmt rename(Map<String, String> ren) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Type> typeEnv() {
		Map<String, Type> tenv = typeEnvMonoid.empty();
		for (Stmt s: stats) {
			tenv =  typeEnvMonoid.join(tenv, s.typeEnv());
		}
		return tenv;
	}

	@Override
	public Set<Pair<String, String>> controlDeps() {
		return stats.stream().flatMap((x) -> x.controlDeps().stream()).collect(Collectors.toSet());
	}

	@Override
	public <E, S> S recons(IExpAlg<E> expAlg, IStmtAlg<E, S> stmtAlg) {
		List<S> ss = new ArrayList<>();
		for (Stmt s: stats) {
			ss.add(s.recons(expAlg, stmtAlg));
		}
		return stmtAlg.block(ss);
	}

	@Override
	public int count() {
		int count = 1;
		for (Stmt s: stats) {
			return count += s.count();
		}
		return count;
	}
}
