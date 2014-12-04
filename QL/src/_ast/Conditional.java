package _ast;

import java.util.List;

public abstract class Conditional extends Stmt {
	protected final Exp cond;
	protected final List<Stmt> then;

	public Conditional(Exp cond, List<Stmt> then) {
		this.cond = cond;
		this.then = then;
	}

}
