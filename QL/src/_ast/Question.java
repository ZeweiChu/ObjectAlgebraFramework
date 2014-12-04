package _ast;

import ql_obj_alg.check.types.Type;

public abstract class Question extends Stmt {
	
	protected final String id;
	protected final String label;
	protected final Type type;

	public Question(String id, String label, Type type) {
		this.id = id;
		this.label = label;
		this.type = type;
	}

}
