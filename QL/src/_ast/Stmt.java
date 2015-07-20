package _ast;

import java.util.Map;
import java.util.Set;

import library.Pair;
import monoids.MapMonoid;
import monoids.SetMonoid;
import ql_obj_alg.check.types.Type;

public abstract class Stmt {
	
	public static MapMonoid<String, Type> typeEnvMonoid = new MapMonoid<>();
	public static SetMonoid<Pair<String, String>> depMonoid = new SetMonoid<>();
	
	public abstract Stmt rename(Map<String, String> ren);
	public abstract Map<String,Type> typeEnv();
	public abstract Set<Pair<String,String>> controlDeps();
	public abstract Set<Pair<String,String>> dataDeps();
	public abstract Stmt flatten(Exp guard);
	public abstract Stmt desugar();
	public abstract Stmt desugar(String n);
	
}
