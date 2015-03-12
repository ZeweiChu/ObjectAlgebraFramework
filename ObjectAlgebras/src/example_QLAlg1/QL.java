package example_QLAlg1;

import java.util.*;

//BEGIN_OO_APPROACH
import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static java.util.stream.Stream.concat;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.toList;

abstract class Exp {
	Set<String> usedVars() { return emptySet(); }
	abstract Exp renaming();
}

abstract class Stmt {
	Set<String> usedVars() { return emptySet(); }
	abstract Stmt renaming();
}

class Form {
	private String id;
	private List<Stmt> statements;
	Form(String id, List<Stmt> statements) {
		this.id = id;
		this.statements = new ArrayList<Stmt>(statements);
	}
	Set<String> usedVars() {
		return statements.stream().map(x -> x.usedVars()).reduce(emptySet(), (x, y) -> concat(x.stream(), y.stream()).collect(toSet()));
	}
	Form renaming() {
		return new Form(id, statements.stream().map(s -> s.renaming()).collect(toList()));
	}
}

class Iff extends Stmt {
	private Exp cond;
	private Stmt then;
	Iff(Exp cond, Stmt then) { this.cond = cond; this.then = then; }
	Set<String> usedVars() {
		return concat(cond.usedVars().stream(), then.usedVars().stream()).collect(toSet());
	}
	Iff renaming() {
		return new Iff(cond.renaming(), then.renaming());
	}
}

class Question extends Stmt {
	private String id, label, type;
	Question(String id, String label, String type) {
		this.id = id;
		this.label = label;
		this.type = type;
	}
	Question renaming() { return new Question(id + "_", label, type); }
}

class Lit extends Exp {
	private int x;
	Lit(int x) { this.x = x; }
	Lit renaming() { return new Lit(x); }
}

class Var extends Exp {
	private String varName;
	Var(String varName) { this.varName = varName; }
	Set<String> usedVars() { return singleton(varName); }
	Var renaming() { return new Var(varName + "_"); }
}

class Geq extends Exp {
	private Exp lhs, rhs;
	Geq(Exp lhs, Exp rhs) { this.lhs = lhs; this.rhs = rhs; }
	Set<String> usedVars() {
		return concat(lhs.usedVars().stream(), rhs.usedVars().stream()).collect(toSet());
	}
	Geq renaming() {
		return new Geq(lhs.renaming(), rhs.renaming());
	}
}
//END_OO_APPROACH

