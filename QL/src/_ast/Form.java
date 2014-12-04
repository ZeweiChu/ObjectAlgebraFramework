package _ast;

import java.util.List;

public class Form {

	private final String id;
	private final List<Stmt> body;

	public Form(String id, List<Stmt> statements) {
		this.id = id;
		this.body = statements;
	}

}
