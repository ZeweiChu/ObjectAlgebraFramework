package _ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
		Map<String, Type> tenv = typeEnvMonoid.empty();
		for (Stmt s: els) {
			tenv =  typeEnvMonoid.join(tenv, s.typeEnv());
		}
		return typeEnvMonoid.join(tenv, super.typeEnv());
	}

	@Override
	public Set<Pair<String, String>> controlDeps() {
		Set<Pair<String, String>> s1 = depMonoid.fold(then.stream().map((x) -> x.controlDeps()).collect(Collectors.toList()));
		Set<Pair<String, String>> s2 = depMonoid.fold(els.stream().map((x) -> x.controlDeps()).collect(Collectors.toList()));
		
		Set<Pair<String,String>> result = new HashSet<>();
		for (Set<Pair<String,String>> s: Arrays.asList(s1, s2)) {
			for (Pair<String,String> x: s) {
				if (x.b().equals("")) {
					for (String c: cond.freeVars()) {
						result.add(new Pair<>(x.a(), c));
					}
				}
			}
		}
		return depMonoid.join(s1, depMonoid.join(s2, result));
	}
	
	@Override
	public <E, S> S recons(IExpAlg<E> expAlg, IStmtAlg<E, S> stmtAlg) {
		List<S> stats = new ArrayList<>();
		for (Stmt s: then) {
			stats.add(s.recons(expAlg, stmtAlg));
		}
		List<S> elseStats = new ArrayList<>();
		for (Stmt s: els) {
			elseStats.add(s.recons(expAlg, stmtAlg));
		}
		return stmtAlg.iffelse(cond.recons(expAlg), stats, elseStats);
	}

	@Override
	public int count() {
		int count = 1 + cond.count();
		for (Stmt s: then) {
			count += s.count();
		}
		for (Stmt s: els) {
			count += s.count();
		}
		return count;
	}
	
}
