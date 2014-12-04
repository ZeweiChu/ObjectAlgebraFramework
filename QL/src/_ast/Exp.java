package _ast;

import java.util.Map;
import java.util.Set;


public abstract class Exp {

	public abstract Exp rename(Map<String, String> ren);
	public abstract Set<String> freeVars();
}
