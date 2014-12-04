package _ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ql_obj_alg.check.types.Type;
import library.Pair;

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
	
	public Set<Pair<String,String>> controlDeps() {
		Set<Pair<String, String>> deps = new HashSet<>();
		for (Stmt s: body) {
			deps.addAll(s.controlDeps());
		}
		return deps;
	}
	
	public Map<String,Type> typeEnv() {
		Map<String,Type> tenv = new HashMap<String, Type>(); 
		for (Stmt s: body) {
			tenv.putAll(s.typeEnv());
		}
		return tenv;
	}
}
