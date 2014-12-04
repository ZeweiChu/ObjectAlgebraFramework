package _ast;

import java.util.Map;
import java.util.Set;

import library.Pair;
import ql_obj_alg.check.types.Type;

public abstract class Stmt {
	public abstract Stmt rename(Map<String, String> ren);
	public abstract Map<String,Type> typeEnv();
	public abstract Set<Pair<String,String>> controlDeps();
}
