package _ast;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class BinaryExp extends Exp {
	protected final Exp lhs;
	protected final Exp rhs;

	public BinaryExp(Exp lhs, Exp rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	@Override
	public Exp rename(Map<String, String> ren) {
		return make(lhs.rename(ren), rhs.rename(ren));
	}
	
	protected abstract Exp make(Exp lhs, Exp rhs);
	
	@Override
	public Set<String> freeVars() {
		Set<String> set = new HashSet<>(lhs.freeVars());
		set.addAll(rhs.freeVars());
		return set;
	}
}
