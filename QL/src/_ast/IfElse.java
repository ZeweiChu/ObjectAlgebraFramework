package _ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IfElse extends Conditional {

	private final List<Stmt> els;

	public IfElse(Exp cond, List<Stmt> statementsIf, List<Stmt> statementsElse) {
		super(cond, statementsIf);
		this.els = statementsElse;
	}
	
	@Override
	public Stmt rename(Map<String, String> ren) {
		List<Stmt> body = new ArrayList<>();
		for (Stmt s: this.then) {
			body.add(s.rename(ren));
		}
		List<Stmt> elseBody = new ArrayList<>();
		for (Stmt s: els) {
			elseBody.add(s.rename(ren));
		}
		return new IfElse(cond.rename(ren), body, elseBody);
	}

}
