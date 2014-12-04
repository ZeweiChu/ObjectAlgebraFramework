package _ast;

import java.util.Map;

public class Bracket extends Exp {

	private Exp exp;

	public Bracket(Exp e) {
		this.exp = e;
	}

	@Override
	public Exp rename(Map<String, String> ren) {
		return new Bracket(exp.rename(ren));
	}

}
