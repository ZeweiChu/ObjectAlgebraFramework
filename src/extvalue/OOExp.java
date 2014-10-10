package extvalue;

// integer values

interface IntVal<A> {
	A num(int x);
}

// integer expressions
interface IntExp<A> extends IntVal<A> {A add(A x, A y);}

// integer evaluation interface
interface IntValue {int getInt();}

interface IEval<V> {V eval();} // TODO: change things to use this interface

// extensible evaluation with extensible return type
class Eval<A extends IntValue, V extends IntVal<A>> implements IntExp<A> {
	protected V valFact;

	public Eval(V valFact) {
		this.valFact = valFact;
	}
	
	public A num(int x) {
		return valFact.num(x);
	}

	public A add(A x, A y) {
		return valFact.num(x.getInt() + y.getInt());
	}
}

// integer + boolean values
interface IntBoolVal<A> extends IntVal<A> {
	A bool(boolean b);
}

// integer + boolean expressions
interface IntBoolExp<A> extends IntBoolVal<A>, IntExp<A> {
	A iff(A e1, A e2, A e3);
} 

// integer + boolean evaluation interface
interface IntBoolEval extends IntValue {
	boolean getBool();
}

// integer + boolean evaluation
class EvalIntBool<A extends IntBoolEval, V extends IntBoolVal<A>> extends Eval<A, V> implements IntBoolExp<A> {
	public EvalIntBool(V valFactory) {
		super(valFactory);
	}
	
	public A bool(boolean b) {
		return valFact.bool(b);
	}

	public A iff(A e1, A e2, A e3) {
		if (e1.getBool()) 
			return e2;
		else 
			return e3;
	}	
}

// An implementation of values
class VInt implements IntBoolEval {
	int x;

	VInt(int x) {
		this.x = x;
	}


	public int getInt() {
		return x;
	}


	public boolean getBool() {
		throw new RuntimeException();
	}

	public String toString() {
		return (new Integer(x)).toString();
	}
}

class VBool implements IntBoolEval {
	boolean b;

	VBool(boolean x) {
		this.b = x;
	}


	public int getInt() {
		throw new RuntimeException();
	}


	public boolean getBool() {
		return b;
	}

	public String toString() {
		return (new Boolean(b)).toString();
	}
}

class ValFactory implements IntBoolVal<IntBoolEval> {
	public IntBoolEval num(int x) {
		return new VInt(x);
	}

	public IntBoolEval bool(boolean b) {
		return new VBool(b);
	}	
}

public class OOExp {
	static <A> A exp(IntBoolExp<A> f) {
		return f.iff(f.bool(true), f.num(3), f.num(4));
	}
	
	public static void main(String args[]) {
		EvalIntBool<IntBoolEval, ValFactory> eval = new EvalIntBool<IntBoolEval, ValFactory>(new ValFactory());
		
		IntBoolEval e = exp(eval);
		
		System.out.println(e.getInt());
	}
}
