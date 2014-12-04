package _ast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
