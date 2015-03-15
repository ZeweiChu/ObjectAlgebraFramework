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
	abstract Exp rename();
}

abstract class Stmt {
	Set<String> usedVars() { return emptySet(); }
	abstract Stmt rename();
}

class Form {
	private String id;
	private List<Stmt> stmts;
	Form(String id, List<Stmt> stmts) {
		this.id = id;
		this.stmts = new ArrayList<Stmt>(stmts);
	}
	Set<String> usedVars() {
		return stmts.stream()
				.map(x -> x.usedVars())
				.reduce(emptySet(),
					(x, y) -> concat(x.stream(), y.stream())
				.collect(toSet()));
	}
	Form rename() {
		return new Form(id, stmts.stream()
				.map(s -> s.rename())
				.collect(toList()));
	}
}

class Iff extends Stmt {
	private Exp cond;
	private Stmt then;
	Iff(Exp cond, Stmt then) {
		this.cond = cond;
		this.then = then;
	}
	Set<String> usedVars() {
		return concat(cond.usedVars().stream(),
				then.usedVars().stream()).collect(toSet());
	}
	Iff rename() {
		return new Iff(cond.rename(), then.rename());
	}
}

class Question extends Stmt {
	private String id, lbl, type;
	Question(String id, String lbl, String type) {
		this.id = id;
		this.lbl = lbl;
		this.type = type;
	}
	Question rename() {
		return new Question(id + "_", lbl, type);
	}
}

class Lit extends Exp {
	private int x;
	Lit(int x) { this.x = x; }
	Lit rename() { return new Lit(x); }
}

class Var extends Exp {
	private String varName;
	Var(String varName) { this.varName = varName; }
	Set<String> usedVars() {
		return singleton(varName);
	}
	Var rename() { return new Var(varName + "_"); }
}

class Geq extends Exp {
	private Exp lhs, rhs;
	Geq(Exp lhs, Exp rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	Set<String> usedVars() {
		return concat(lhs.usedVars().stream(),
				rhs.usedVars().stream()).collect(toSet());
	}
	Geq rename() {
		return new Geq(lhs.rename(), rhs.rename());
	}
}
//END_OO_APPROACH

/*

//BEGIN_OO_APPROACH_SIMP
import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static java.util.stream.Stream.concat;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.toList;

abstract class Exp {
	Set<String> usedVars() { return emptySet(); }
	abstract Exp rename();
}

abstract class Stmt {
	Set<String> usedVars() { return emptySet(); }
	abstract Stmt rename();
}

class Form {
	private String id;
	private List<Stmt> stmts;
	Form(String id, List<Stmt> stmts) {
		this.id = id;
		this.stmts = new ArrayList<Stmt>(stmts);
	}
	Set<String> usedVars() {
		return stmts.stream()
				.map(x -> x.usedVars())
				.reduce(emptySet(),
					(x, y) -> concat(x.stream(), y.stream())
				.collect(toSet()));
	}
	Form rename() {
		return new Form(id, stmts.stream()
				.map(s -> s.rename())
				.collect(toList()));
	}
}

class Var extends Exp {
	private String varName;
	Var(String varName) { this.varName = varName; }
	Set<String> usedVars() {
		return singleton(varName);
	}
	Var rename() { return new Var(varName + "_"); }
}

class Question extends Stmt { ... }
class Iff extends Stmt { ... }
class Lit extends Exp { ... }
class Geq extends Exp { ... }
//END_OO_APPROACH_SIMP

*/
