package _ast;

import java.util.Map;
import java.util.Set;

public class Not extends Exp {

	private Exp arg;

	public Not(Exp exp) {
		this.arg = exp;
	}

	@Override
	public Exp rename(Map<String, String> ren) {
		return new Not(arg.rename(ren));
	}

	@Override
	public Set<String> freeVars() {
		return arg.freeVars();
	}
	
	@Override
	public Exp desugar(String n) {
	  return new Not(arg.desugar(n));
	}

}
