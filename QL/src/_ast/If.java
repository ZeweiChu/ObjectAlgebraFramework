package _ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

}
