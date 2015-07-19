package _ast;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import library.Pair;

public class If extends Conditional {

	public If(Exp cond, Stmt statements) {
		super(cond, statements);
	}

	@Override
	public Stmt rename(Map<String, String> ren) {
		return new If(cond.rename(ren), then.rename(ren));
	}

	@Override
	public Set<Pair<String, String>> controlDeps() {
		return new IfElse(cond, then, new Block(Collections.emptyList())).controlDeps();
	}

	@Override
	public Stmt flatten(Exp guard) {
		return new Block(Arrays.asList(then.flatten(new And(guard, cond))));
	}

	@Override
  public Stmt desugar() {
	  return new If(cond, then.desugar());
  }
	

}
