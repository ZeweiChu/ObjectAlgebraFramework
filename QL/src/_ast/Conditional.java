package _ast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import library.Pair;
import ql_obj_alg.check.types.Type;

public abstract class Conditional extends Stmt {
	protected final Exp cond;
	protected final List<Stmt> then;

	public Conditional(Exp cond, List<Stmt> then) {
		this.cond = cond;
		this.then = then;
	}

	@Override
	public Map<String, Type> typeEnv() {
		Map<String, Type> tenv = new HashMap<String, Type>();
		for (Stmt s: then) {
			tenv.putAll(s.typeEnv());
		}
		return tenv;
	}
	
	@Override
	public Set<Pair<String, String>> controlDeps() {
		Set<Pair<String, String>> deps = new HashSet<>();
		Set<Pair<String, String>> bodyDeps = bodyDeps(then);
		for (Pair<String,String> p: bodyDeps) {
			if (p.b().equals("")) {
				for (String x: cond.freeVars()) {
					deps.add(new Pair<>(p.a(), x));
				}
			}
		}
		deps.addAll(bodyDeps);
		return deps;
	}
	
	protected static Set<Pair<String,String>> bodyDeps(List<Stmt> body) {
		Set<Pair<String,String>> deps = new HashSet<>();
		for (Stmt s: body) {
			deps.addAll(s.controlDeps());
		}
		return deps;
	}
}
