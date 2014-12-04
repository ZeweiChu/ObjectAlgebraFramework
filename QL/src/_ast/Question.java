package _ast;

import java.util.Collections;
import java.util.Map;

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
	
	@Override
	public Map<String, Type> typeEnv() {
		return Collections.singletonMap(id, type);
	}

}
