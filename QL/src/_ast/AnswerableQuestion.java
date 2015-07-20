package _ast;

import java.util.Map;
import java.util.Set;

import library.Pair;
import ql_obj_alg.check.types.Type;
import _ast.util.Rename;

public class AnswerableQuestion extends Question {

	public AnswerableQuestion(String id, String label, Type type) {
		super(id, label, type);
	}

	@Override
	public Stmt rename(Map<String, String> ren) {
		return new AnswerableQuestion(Rename.rename(ren, id), label, type);
	}

	@Override
	public Stmt desugar(String n) {
	  return new AnswerableQuestion(id + n, label, type);
	}

	@Override
  public Set<Pair<String, String>> dataDeps() {
	  return Stmt.depMonoid.empty();
  }
}
