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
		Map<String, Type> tenv = typeEnvMonoid.empty();
		for (Stmt s: then) {
			tenv =  typeEnvMonoid.join(tenv, s.typeEnv());
		}
		return tenv;
	}
	
}
