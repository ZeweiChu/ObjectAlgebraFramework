package _ast;

import java.util.Map;

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
}
