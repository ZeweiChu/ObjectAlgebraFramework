package _ast;

import java.util.Map;


public abstract class Exp {

	public abstract Exp rename(Map<String, String> ren);
	
}
