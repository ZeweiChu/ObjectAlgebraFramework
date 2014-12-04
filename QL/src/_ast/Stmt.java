package _ast;

import java.util.Map;

public abstract class Stmt {
	public abstract Stmt rename(Map<String, String> ren);
}
