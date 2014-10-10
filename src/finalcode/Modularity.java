package finalcode;

//BEGIN_MODULARITY_ALGEBRA
interface BoolAlg<A> {
	A bool(boolean x);
	A iff(A b, A e1, A e2);
}

interface ExpIntBool<A> extends BoolAlg<A>, IntAlg<A> {}
//END_MODULARITY_ALGEBRA

//BEGIN_MODULARITY_COMPOSITION
class Union<A> implements IntBoolAlg<A> {
	BoolAlg<A> v1;
	IntAlg<A> v2;
	Union(BoolAlg<A> v1, IntAlg<A> v2) { this.v1 = v1; this.v2 = v2; }

	public A lit(int x)            { return v2.lit(x);          }
	public A add(A e1, A e2)       { return v2.add(e1, e2);     }
	public A bool(Boolean b)       { return v1.bool(b);         }
	public A iff(A e1, A e2, A e3) { return v1.iff(e1, e2, e3); }
} 
//END_MODULARITY_COMPOSITION

/* Extended Expression Factory */
class BoolFactory implements BoolAlg<Exp> {
	public Exp bool(boolean b) {
		return new Bool(b);
	}

	public Exp iff(Exp e1, Exp e2, Exp e3) {
		return new Iff(e1, e2, e3);
	}
}

//BEGIN_INDEPENDENT_EXTENSIBILITY
class IntBoolFactory2 extends Union<Exp> {
	IntBoolFactory2() {
		super(new BoolFactory(), new IntFactory());
}}
//END_INDEPENDENT_EXTENSIBILITY

//BEGIN_MODULARITY_COMBINE
class Pair<A, B> {
	A a; B b;
	Pair(A a, B b) { this.a = a; this.b = b; }

	A a() { return a; }
	B b() { return b; }
}

class Combine<A, B> implements IntAlg<Pair<A, B>> {
	IntAlg<A> v1;
	IntAlg<B> v2;

	Combine(IntAlg<A> v1, IntAlg<B> v2) { this.v1 = v1;this.v2 = v2; }

	public Pair<A, B> lit(int x) {
		return new Pair<A, B>(v1.lit(x), v2.lit(x));
	}
	public Pair<A, B> add(Pair<A, B> e1, Pair<A, B> e2) {
		return new Pair<A, B>(v1.add(e1.a(), e2.a()), v2.add(e1.b(), e2.b()));
	}
}
//END_MODULARITY_COMBINE

//BEGIN_MODULARITY_DEBUG
class Debug extends Combine<Exp, IPrint> {
	Debug() { super(new IntFactory(), new IntPrint()); }
	
	public Pair<Exp, IPrint> add(Pair<Exp, IPrint> e1, Pair<Exp, IPrint> e2) {
		System.out.println("The first expression " + e1.b().print() + 
				" evaluates to " + e1.a().eval().toString());
		System.out.println("The second expression " + e2.b().print() + 
				" evaluates to " + e2.a().eval().toString());
		return super.add(e1, e2);
}}
//END_MODULARITY_DEBUG

class GUnion<A, V1 extends BoolAlg<A>, V2 extends IntAlg<A>> implements IntBoolAlg<A> {
	V1 v1;
	V2 v2;
	
	GUnion(V1 v1, V2 v2) { this.v1 = v1;this.v2 = v2; }

	public A lit(int x)            { return v2.lit(x); }
	public A add(A e1, A e2)       { return v2.add(e1, e2); }
	public A bool(Boolean b)       { return v1.bool(b); }
	public A iff(A e1, A e2, A e3) { return v1.iff(e1, e2, e3); }
}

class GCombine<A, B, V1 extends IntAlg<A>, V2 extends IntAlg<B>> implements IntAlg<Pair<A, B>> {
	V1 v1;
	V2 v2;
	
	GCombine(V1 v1, V2 v2) { this.v1 = v1;this.v2 = v2; }
	
	public Pair<A, B> lit(int x) {
		return new Pair<A, B>(v1.lit(x), v2.lit(x));
	}
	public Pair<A, B> add(Pair<A, B> e1, Pair<A, B> e2) {
		return new Pair<A, B>(v1.add(e1.a(), e2.a()), v2.add(e1.b(), e2.b()));
	}
} 



/*
class EvalIntBool implements ExpIntBool<IEval> {
	EvalBool evBool;
	Eval evInt;

	EvalIntBool(EvalBool evBool, Eval evInt) {
		this.evBool = evBool;
		this.evInt = evInt;
	}

	public IEval bool(boolean x) {
		return evBool.bool(x);
	}

	public IEval iff(IEval b, IEval e1, IEval e2) {
		return evBool.iff(b, e1, e2);
	}

	public IEval lit(int n) {
		return evInt.lit(n);
	}

	public IEval add(IEval e1, IEval e2) {
		return evInt.add(e1, e2);
	}
}
*/
