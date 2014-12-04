package _ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import library.Pair;
import ql_obj_alg.check.types.Type;
import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IStmtAlg;

public class IfElse extends Conditional {

	private final List<Stmt> els;

	public IfElse(Exp cond, List<Stmt> statementsIf, List<Stmt> statementsElse) {
		super(cond, statementsIf);
		this.els = statementsElse;
	}

	@Override
	public Stmt rename(Map<String, String> ren) {
		List<Stmt> body = new ArrayList<>();
		for (Stmt s : this.then) {
			body.add(s.rename(ren));
		}
		List<Stmt> elseBody = new ArrayList<>();
		for (Stmt s : els) {
			elseBody.add(s.rename(ren));
		}
		return new IfElse(cond.rename(ren), body, elseBody);
	}

	@Override
	public Map<String, Type> typeEnv() {
		Map<String, Type> tenv = new HashMap<String, Type>();
		tenv.putAll(super.typeEnv());
		for (Stmt s : els) {
			tenv.putAll(s.typeEnv());
		}
		return tenv;
	}

	@Override
	public Set<Pair<String, String>> controlDeps() {
		Set<Pair<String, String>> deps = new HashSet<>();
		
		Set<Pair<String, String>> elseDeps = bodyDeps(els);
		for (Pair<String, String> p : elseDeps) {
			if (p.b().equals("")) {
				for (String x : cond.freeVars()) {
					deps.add(new Pair<>(p.a(), x));
				}
			}
		}
		deps.addAll(super.controlDeps());
		deps.addAll(elseDeps);
		return deps;
	}
	
	@Override
	public <E, S> S recons(IExpAlg<E> expAlg, IStmtAlg<E, S> stmtAlg) {
		List<S> stats = new ArrayList<>();
		for (Stmt s: then) {
			stats.add(s.recons(expAlg, stmtAlg));
		}
		List<S> elseStats = new ArrayList<>();
		for (Stmt s: els) {
			stats.add(s.recons(expAlg, stmtAlg));
		}
		return stmtAlg.iffelse(cond.recons(expAlg), stats, elseStats);
	}

}
