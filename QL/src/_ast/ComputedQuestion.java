package _ast;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import library.Pair;
import ql_obj_alg.check.types.Type;
import _ast.util.Rename;

public class ComputedQuestion extends Question {

	private final Exp exp;

	public ComputedQuestion(String id, String label, Type type, Exp exp) {
		super(id, label, type);
		this.exp = exp;
	}

	@Override
	public Stmt rename(Map<String, String> ren) {
		return new ComputedQuestion(Rename.rename(ren, id), label, type, exp.rename(ren));
	}

	@Override
  public Stmt desugar(String n) {
	  return new ComputedQuestion(id + n, label, type, exp.desugar(n));
  }

	@Override
  public Set<Pair<String, String>> dataDeps() {
		Set<Pair<String,String>> deps = new HashSet<>(); //.empty();
		for (String x: exp.freeVars()) {
			deps.add(new Pair<>(id, x));
		}
		return deps;
  }
}
