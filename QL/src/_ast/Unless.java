package _ast;

import java.util.Map;
import java.util.Set;

import library.Pair;

public class Unless extends Conditional {
	
	public Unless(Exp cond, Stmt body) {
		super(cond, body);
	}

	@Override
	public Stmt rename(Map<String, String> ren) {
		return new Unless(cond.rename(ren), then.rename(ren));
	}

	@Override
	public Set<Pair<String, String>> controlDeps() {
		return new If(cond, then).controlDeps();
	}
	
	@Override
	public Stmt flatten(Exp guard) {
		return then.flatten(new And(guard, new Not(cond)));
	}

	@Override
  public Stmt desugar() {
	  return new If(new Not(cond), then.desugar());
  }

	@Override
  public Stmt desugar(String n) {
	 return new Unless(cond.desugar(n), then.desugar(n));
  }

	@Override
  public Set<Pair<String, String>> dataDeps() {
	  return then.dataDeps();
  }
}
