package _ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import library.Pair;
import ql_obj_alg.check.types.Type;

public class Repeat extends Stmt {

	private int times;
	private Stmt body;

	public Repeat(int n, Stmt body) {
		this.times = n;
		this.body = body;
  }
	
	@Override
  public Stmt rename(Map<String, String> ren) {
	  return new Repeat(times, body.rename(ren));
  }

	@Override
  public Map<String, Type> typeEnv() {
	  return body.typeEnv();
  }

	@Override
  public Set<Pair<String, String>> controlDeps() {
	  return desugar("").controlDeps();
  }

	@Override
  public Set<Pair<String, String>> dataDeps() {
	  return desugar("").dataDeps();
  }

	@Override
  public Stmt flatten(Exp guard) {
	  return desugar("").flatten(guard);
  }

	@Override
  public Stmt desugar() {
	  return new Repeat(times, body.desugar());
  }

	@Override
  public Stmt desugar(String n) {
		List<Stmt> body = new ArrayList<>();
		for (int i = 0; i < times; i++) {
			body.add(this.body.desugar(n + "_" + i));
		}
		return new Block(body);
  }

}
