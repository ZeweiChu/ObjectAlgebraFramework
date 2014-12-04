package _ast;

import java.util.Map;

public class Not extends Exp {

	private Exp arg;

	public Not(Exp exp) {
		this.arg = exp;
	}

	@Override
	public Exp rename(Map<String, String> ren) {
		return new Not(arg.rename(ren));
	}

}
