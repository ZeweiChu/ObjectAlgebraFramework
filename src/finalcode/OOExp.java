package finalcode;

class BadTypeException extends RuntimeException {}

interface Value {
	Integer getInt();
	Boolean getBool();
}

class VInt implements Value {
	int x;

	VInt(int x) {
		this.x = x;
	}


	public Integer getInt() {
		return x;
	}


	public Boolean getBool() {
		throw new BadTypeException();
	}

	public String toString() {
		return (new Integer(x)).toString();
	}
}

class VBool implements Value {
	boolean b;

	VBool(boolean x) {
		this.b = x;
	}


	public Integer getInt() {
		throw new BadTypeException();
	}


	public Boolean getBool() {
		return b;
	}

	public String toString() {
		return (new Boolean(b)).toString();
	}
}

//BEGIN_OO_EVAL
interface Exp {
	Value eval();
}
class Lit implements Exp {
	int x;
	public Lit(int x) { this.x = x; }
	
	public Value eval() { 
		return new VInt(x); 
}}
class Add implements Exp {
	Exp l, r;
	public Add(Exp l, Exp r) { this.l = l; this.r = r; }	

	public Value eval() {
		return new VInt(l.eval().getInt() + r.eval().getInt());
}}
//END_OO_EVAL
