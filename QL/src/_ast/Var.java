package _ast;

import java.util.Map;

import _ast.util.Rename;

public class Var extends Exp {
	private String name;

	public Var(String name) {
		this.name = name;
	}

	@Override
	public Exp rename(Map<String, String> ren) {
		return new Var(Rename.rename(ren, name));
	}

	
}
