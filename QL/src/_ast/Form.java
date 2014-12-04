package _ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Form {

	private final String id;
	private final List<Stmt> body;

	public Form(String id, List<Stmt> statements) {
		this.id = id;
		this.body = statements;
	}

	public Form rename(Map<String, String> ren) {
		List<Stmt> newBody = new ArrayList<>();
		for (Stmt s: body) {
			newBody.add(s.rename(ren));
		}
		return new Form(id, newBody);
	}
}
