package example_QLAlg1;

import java.util.*;

//BEGIN_OO_APPROACH
import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static java.util.stream.Stream.concat;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.toList;

interface Exp {
	Set<String> usedVars();
	Exp rename();
}

interface Stmt {
	Set<String> usedVars();
	Stmt rename();
}

class Form {
	private String id;
	private List<Stmt> stmts;
	Form(String id, List<Stmt> stmts) {
		this.id = id;
		this.stmts = new ArrayList<Stmt>(stmts);
	}
	public Set<String> usedVars() {
		return stmts.stream()
				.map(x -> x.usedVars())
				.reduce(emptySet(),
					(x, y) -> concat(x.stream(), y.stream())
				.collect(toSet()));
	}
	public Form rename() {
		return new Form(id, stmts.stream()
				.map(s -> s.rename())
				.collect(toList()));
	}
}

class Iff implements Stmt {
	private Exp cond;
	private Stmt then;
	Iff(Exp cond, Stmt then) {
		this.cond = cond;
		this.then = then;
	}
	public Set<String> usedVars() {
		return concat(cond.usedVars().stream(),
				then.usedVars().stream()).collect(toSet());
	}
	public Iff rename() {
		return new Iff(cond.rename(), then.rename());
	}
}

class Question implements Stmt {
	private String id, lbl, type;
	Question(String id, String lbl, String type) {
		this.id = id;
		this.lbl = lbl;
		this.type = type;
	}
	public Set<String> usedVars() {
		return emptySet();
	}
	public Question rename() {
		return new Question(id + "_", lbl, type);
	}
}

class Lit implements Exp {
	private int x;
	Lit(int x) { this.x = x; }
	public Set<String> usedVars() {
		return emptySet();
	}
	public Lit rename() { return new Lit(x); }
}

class Var implements Exp {
	private String name;
	Var(String name) { this.name = name; }
	public Set<String> usedVars() {
		return singleton(name);
	}
	public Var rename() {
		return new Var(name + "_");
	}
}

class Geq implements Exp {
	private Exp lhs, rhs;
	Geq(Exp lhs, Exp rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public Set<String> usedVars() {
		return concat(lhs.usedVars().stream(),
				rhs.usedVars().stream()).collect(toSet());
	}
	public Geq rename() {
		return new Geq(lhs.rename(), rhs.rename());
	}
}
//END_OO_APPROACH

/*

//BEGIN_OO_APPROACH_SIMP

interface Exp {
	Set<String> usedVars();
	Exp rename();
}

interface Stmt {
	Set<String> usedVars();
	Stmt rename();
}

class Form {
	private String id;
	private List<Stmt> stmts;
	Form(String id, List<Stmt> stmts) {
		this.id = id;
		this.stmts = new ArrayList<Stmt>(stmts);
	}
	public Set<String> usedVars() {
		return stmts.stream()
				.map(x -> x.usedVars())
				.reduce(emptySet(),
					(x, y) -> concat(x.stream(), y.stream())
				.collect(toSet()));
	}
	public Form rename() {
		return new Form(id, stmts.stream()
				.map(s -> s.rename())
				.collect(toList()));
	}
}

class Var implements Exp {
	private String name;
	Var(String name) { this.name = name; }
	public Set<String> usedVars() {
		return singleton(name);
	}
	public Var rename() {
		return new Var(name + "_");
	}
}

...
//END_OO_APPROACH_SIMP

*/
