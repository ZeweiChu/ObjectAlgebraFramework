package _ast;

import java.util.Map;

public abstract class Const extends Exp {

	@Override
	public Exp rename(Map<String, String> ren) {
		return this;
	}
}
