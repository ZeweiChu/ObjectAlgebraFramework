package example_QLAlg1;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class QL {
	Set<String> usedVars() { return Collections.emptySet(); }
	void renaming() {}
}

class Exp extends QL {}

class Stmt extends QL {}

class Form extends QL {
	private String id;
	private List<Stmt> statements;
	Form(String id, List<Stmt> statements) {
		this.id = id;
		this.statements = statements;
	}
	Set<String> usedVars() {
		Set<String> res = new HashSet<String>();
		for (Stmt s : statements)
			res.addAll(s.usedVars());
		return res;
	}
	void renaming() {
		for (Stmt s : statements)
			s.renaming();
	}
}

class Iff extends Stmt {
	private Exp cond;
	private Stmt then;
	Iff(Exp cond, Stmt then) { this.cond = cond; this.then = then; }
	Set<String> usedVars() {
		Set<String> res = new HashSet<String>(cond.usedVars());
		res.addAll(then.usedVars());
		return res;
	}
	void renaming() {
		cond.renaming();
		then.renaming();
	}
}

class Question extends Stmt {
	private String id, label, type;
	Question(String id, String label, String type) {
		this.id = id;
		this.label = label;
		this.type = type;
	}
	void renaming() { id += "_"; }
}

class Lit extends Exp {
	private int x;
	Lit(int x) { this.x = x; }
}

class Var extends Exp {
	private String varName;
	Var(String varName) { this.varName = varName; }
	Set<String> usedVars() { return Collections.singleton(varName); }
	void renaming() { varName += "_"; }
}

class Geq extends Exp {
	private Exp lhs, rhs;
	Geq(Exp lhs, Exp rhs) { this.lhs = lhs; this.rhs = rhs; }
	Set<String> usedVars() {
		Set<String> res = new HashSet<String>(lhs.usedVars());
		res.addAll(rhs.usedVars());
		return res;
	}
	void renaming() {
		lhs.renaming();
		rhs.renaming();
	}
}