import java.util.HashMap;
import java.util.Vector;

/* Values: not extensible */
import base.ExpAlg;

/* data Value = VInt Int | VBool Bool */

/* Subtyping and inheritance are often quite critizised, but they play 
 * and important role in our modularity approach.
 */

class BadTypeException extends RuntimeException {}

/* Types for the type system */

enum Type {INT, BOOL}

class Typed<A> {
	public final A x;
	public final Type t;
	
	public Typed(A x, Type t) {
		this.x = x;
		this.t = t;
	}
}

interface DelayTyped<A> {
	public A carrier();
	public Type type();
}


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


/* Evaluator: not extensible on values */

/* We need to be careful with evaluation because we don't 
 * want a naive bottom-up computation. So, we are going 
 * to use the factory approach and create an interface that 
 * allows us to control when evaluation happens.
 */

interface IEval {
	/*
	static IEval build(final Value v) {
		return new IEval() {
			public Value eval() {
				return v;
			}
		};
	}*/

	Value eval();
}

interface MkExp {
	<A> A mkExp(ExpAlg<A> factory);
}

class T {
	MkExp parseExp(final String s) {
		return (new MkExp() {
			public <A> A mkExp(ExpAlg<A> factory) {
				if (s.equals("0")) 
					return factory.lit(0);
				else if (s.equals("1"))
					return factory.lit(1);
				else return factory.lit(10);
			}
		});
	}
	
	void test() {
		MkExp mk = parseExp("1");
		mk.mkExp(new Eval3());
		mk.mkExp(new PrintStmt());
	}
}

// BEGIN_SIMPLE_EVAL
class Eval implements ExpAlg<IEval> {
	public IEval lit(final int x) {
		return new IEval() {
			public Value eval() {
				return new VInt(x);
		}};
	}
	public IEval add(final IEval e1, final IEval e2) {
		return new IEval() {
			public Value eval() {
				Integer v1 = e1.eval().getInt();
				Integer v2 = e2.eval().getInt();
				return new VInt(v1 + v2);
		}};
}}
//END_SIMPLE_EVAL

/* Evaluator; William style */

interface ExpEval {
	Integer eval();  
}

class ExpLit implements ExpEval {
	int x;
	public ExpLit(int x) {
		this.x = x;		
	}
	public Integer eval() {
		return x;
	}
}

class ExpAdd implements ExpEval {
	ExpEval e1, e2; 
	public ExpAdd(ExpEval e1, ExpEval e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	public Integer eval() {
		return e1.eval() + e2.eval();
	}
}

/* Evaluate to a value */

class Eval3 implements ExpAlg<Value> {


	public Value lit(int x) {
		return new VInt(x);
	}


	public Value add(Value e1, Value e2) {
		Integer x = e1.getInt();
		Integer y = e2.getInt();

		return new VInt(x + y);		
	}
} 

/* Adding a new operation: easy, we already have 2 examples */

/* Another small language component */

/* EXTENSIBILITY */

/* Some more algebras that we may want to consider */

//BEGIN_EXTENSIBILITY__VAR
interface ExpBoolExt<A> extends ExpAlg<A> {
	A bool(boolean x);
	A iff(A b, A e1, A e2);
}
//END_EXTENSIBILITY__VAR

//BEGIN_EXTENSIBILITY_OP
class EvalBoolExt extends Eval implements ExpBoolExt<IEval> {
	public IEval bool(final boolean x) {
		return new IEval() {
			public Value eval() {
				return new VBool(x);
		}};
	}
	public IEval iff(final IEval b, final IEval e1, final IEval e2) {
		return new IEval() {
			public Value eval() {
				if (b.eval().getBool()) 
					return e1.eval();
				else
					return e2.eval();
		}};
}}
//END_EXTENSIBILITY_OP

/* Some more algebras that we may want to consider */

/* Combining different modular extensions */

//BEGIN_MODULARITY__ALGEBRA
interface ExpBool<A> {
	A bool(boolean x);
	A iff(A b, A e1, A e2);
}

interface ExpIntBool<A> extends ExpBool<A>, ExpAlg<A> {}
//END_MODULARITY__ALGEBRA

//BEGIN_MODULARITY_BOOL
class EvalBool implements ExpBool<IEval> {
	public IEval bool(final boolean x) {
		return new IEval() {
			public Value eval() {
				return new VBool(x);
		}};
	}
	public IEval iff(final IEval b, final IEval e1, final IEval e2) {
		return new IEval() {
			public Value eval() {
				if (b.eval().getBool()) 
					return e1.eval();
				else
					return e2.eval();
		}};  
}}
//END_MODULARITY_BOOL

//BEGIN_MODULARITY__COMPOSITION
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
}}
//END_MODULARITY__COMPOSITION

/* Extending an existing modular extension */

class EvalIntBool2 extends Eval implements ExpIntBool<IEval> {

	public IEval bool(final boolean x) {
		return new IEval() {
			public Value eval() {
				return new VBool(x);
			}
			
		};
				//IEval.build(new VBool(x));
	}


	public IEval iff(final IEval b, final IEval e1, final IEval e2) {
		return new IEval() {
			public Value eval() {
				if (b.eval().getBool()) 
					return e1.eval();
				else
					return e2.eval();
			}
		};
	}
}

class Unit {
	Unit() {}
}

//BEGIN_PRINT__BASE
class Print implements ExpIntBool<String> {
	public String bool(boolean x) {
		return (new Boolean(x)).toString();
	}

	public String iff(String b, String e1, String e2) {
		return ("if (" + b + ") then (" + e1 + ") else (" + e2 + ")");
	}

	public String lit(int x) {
		return (new Integer(x)).toString();
	}

	public String add(String e1, String e2) {
		return (e1 + " + " + e2);
	}
}
//END_PRINT__BASE

/* Mutually recursive datatypes */

//BEGIN_MUTUAL__STMT
interface StmtAlg<E, S> extends ExpIntBool<E> {
	E var(String x);
	E assign(String x, E e);
	S expr(E e);
	S comp(S e1, S e2);
}
//END_MUTUAL__STMT


/*
interface EvalFunc {
	Pair<Value,HashMap<String,Value> eval(HashMap<String,Value> map);
}*/

//BEGIN_EVAL__STMT
class EvalStmt extends EvalIntBool2 implements StmtAlg<IEval, IEval> {
	HashMap<String, Value> map; // = new HashMap<String, Value>();
	
	EvalStmt() {
		map = new HashMap<String, Value>();
	}
	EvalStmt(HashMap<String, Value> initialEnv) {
		map = initialEnv;
	}
	public void setEnv(HashMap<String, Value> env) {
		map = env;
	}
	public IEval var(final String x) {
		return new IEval() {
			public Value eval() {
				return map.get(x);
		}};
	}
	public IEval assign(final String x, final IEval e) {
		return new IEval() {
			public Value eval() {
				Value v = e.eval();		
				map.put(x, v);
				return v;
		}};
	}
	public IEval comp(final IEval e1, final IEval e2) {
		return new IEval() {
			public Value eval() {
				e1.eval();
				return e2.eval();
		}};
	}
	public IEval expr(IEval e) {
		return e;
}}
//END_EVAL__STMT

/* Another Evaluator: Using bounds to make it more extensible */

interface IEvalExp {
	Value evalExp();
}

interface IEvalStmt {
	Value evalStmt();
}



/* Limited form of visitor abstraction: quite useful for visitor combinators. 
 * Type constructor abstraction would be nicer, but this seems to do the trick.
 * */

/* Solution with type constructors:

class GCombineInt<A,B,V extends ExpAlg> implements ExpAlg<Pair<A,B>> {
	V<A> expAlg1;
	V<B> expAlg2;
	...
}

 * 
 */

/* TYPE CHECKING */
/*
interface TCFeature<A> {
	Typed<A>
}*/

class Typecheck<E,S> implements BreakpointAlg<Typed<E>,Typed<S>> {
	HashMap<String,Type> map = new HashMap<String,Type>();
	BreakpointAlg<E,S> alg;
	
	public Typecheck(BreakpointAlg<E,S> alg) {
		this.alg = alg;
	}
	
	public Typed<E> var(String x) {
		return new Typed<E>(alg.var(x), map.get(x));
	}

	public Typed<E> assign(String x, Typed<E> e) {
		map.put(x, e.t);
		return new Typed<E>(alg.assign(x, e.x),e.t);
	}

	public Typed<S> expr(Typed<E> e) {
		return new Typed<S>(alg.expr(e.x),e.t);
	}

	public Typed<S> comp(Typed<S> e1, Typed<S> e2) {
		return new Typed<S>(alg.comp(e1.x, e2.x),e2.t);
	}

	public Typed<E> bool(boolean x) {
		return new Typed<E>(alg.bool(x),Type.BOOL);
	}

	public Typed<E> iff(Typed<E> b, Typed<E> e1, Typed<E> e2) {
		switch (b.t) {
			case BOOL: 
				if (e1.t == e2.t) {
					return new Typed<E>(alg.iff(b.x, e1.x, e2.x),e1.t);
				} else {
					throw new BadTypeException(); 
				}
		}
		throw new BadTypeException();
	}

	public Typed<E> lit(int n) {
		return new Typed<E>(alg.lit(n),Type.INT);
	}

	public Typed<E> add(Typed<E> e1, Typed<E> e2) {
		switch (e1.t) {
			//case BOOL: throw new BadTypeException();
			case INT: switch (e2.t) {
				//case BOOL : throw new BadTypeException();
				case INT: 
					return new Typed<E>(alg.add(e1.x, e2.x),Type.INT);
			}
		}
		throw new BadTypeException();
	}

	public Typed<S> breakp(Typed<S> x) {
		return x;
	}	
}

class Typecheck2<E,S> implements BreakpointAlg<DelayTyped<E>,DelayTyped<S>> {
	HashMap<String,Type> map = new HashMap<String,Type>();
	BreakpointAlg<E,S> alg;
	
	public Typecheck2(BreakpointAlg<E,S> alg) {
		this.alg = alg;
	}
	
	public DelayTyped<E> var(final String x) {
		return new DelayTyped<E>() {
			public E carrier() {
				return alg.var(x);
			}

			public Type type() {
				return map.get(x);
			}
		};
	}

	public DelayTyped<E> assign(final String x, final DelayTyped<E> e) {
		return new DelayTyped<E>() {
			public E carrier() {
				return alg.assign(x,e.carrier());
			}

			public Type type() {
				map.put(x, e.type());
				return e.type();
			}		
		};
	}

	public DelayTyped<S> expr(final DelayTyped<E> e) {
		return new DelayTyped<S>() {
			public S carrier() {
				return alg.expr(e.carrier());
			}

			public Type type() {
				return e.type();
			}	
		};
	}

	public DelayTyped<S> comp(final DelayTyped<S> e1, final DelayTyped<S> e2) {
		return new DelayTyped<S>() {
			public S carrier() {
				return alg.comp(e1.carrier(),e2.carrier());
			}

			public Type type() {
				return e2.type();
			}
		};
	}

	public DelayTyped<E> bool(final boolean x) {
		return new DelayTyped<E>() {
			public E carrier() {
				return alg.bool(x);
			}

			public Type type() {
				return Type.BOOL;
			}
		};
	}

	public DelayTyped<E> iff(final DelayTyped<E> b, final DelayTyped<E> e1, final DelayTyped<E> e2) {
		return new DelayTyped<E>() {
			public E carrier() {
				return alg.iff(b.carrier(), e1.carrier(), e2.carrier());
			}

			public Type type() {
				switch (b.type()) {
					case BOOL: 
					if (e1.type() == e2.type()) {
						return e1.type();
					} else {
						throw new BadTypeException(); 
					}
				}
				throw new BadTypeException();
			}
		};
	}

	public DelayTyped<E> lit(final int n) {
		return new DelayTyped<E>() {
			public E carrier() {
				return alg.lit(n);
			}

			public Type type() {
				return Type.INT;
			}
		};			
	}

	public DelayTyped<E> add(final DelayTyped<E> e1, final DelayTyped<E> e2) {
		return new DelayTyped<E>() {
			public E carrier() {
				return alg.add(e1.carrier(), e2.carrier());
			}

			public Type type() {
				switch (e1.type()) {
					case INT: switch (e2.type()) {
						case INT: 
							return Type.INT;
					}
				}
				throw new BadTypeException();
			}
		};
	}

	public DelayTyped<S> breakp(DelayTyped<S> x) {
		return x;
	}	
}


// Write something interesting here
class Optimize<E,S> extends Wrap2<Typed<E>,Typed<S>,Typecheck<E,S>> {
	public Optimize(Typecheck<E, S> alg) {
		super(alg);
	}
}

//Write something interesting here
class Optimize2<E,S> extends Wrap2<DelayTyped<E>,DelayTyped<S>,Typecheck2<E,S>> {
	public Optimize2(Typecheck2<E, S> alg) {
		super(alg);
	}
}

// Constant folding
interface Constant<A> {
	public boolean isConstant();
	public A carrier();
}

class ConstantFolding<E,S> implements BreakpointAlg<Constant<E>,S> {
	BreakpointAlg<E,S> alg;
	Vector<String> constantVariables = new Vector<String>();
	//HashMap<String,Constant<E>> constantVariables = new HashMap<String,Constant<E>>();
	
	public ConstantFolding(BreakpointAlg<E,S> alg) {
		this.alg = alg;
	}
	
	public Constant<E> var(final String x) {
		return new Constant<E>() {
			public boolean isConstant() {
				return constantVariables.contains(x); // false
			}

			public E carrier() {
				return alg.var(x);
			}
		};
	}

	public Constant<E> assign(final String x, final Constant<E> e) {
		if (e.isConstant())
			constantVariables.add(x); // this should happen as soon as possible
		
		return new Constant<E>() {
			public boolean isConstant() {
				return e.isConstant(); // should this be false here?
			}

			public E carrier() {
				return alg.assign(x, e.carrier());
			}
		};
	}

	public S expr(Constant<E> e) {
		return alg.expr(e.carrier());
	}
	
	public S comp(S e1, S e2) {
		return alg.comp(e1, e2);
	}

	public Constant<E> bool(final boolean x) {
		return new Constant<E>() {
			public boolean isConstant() {
				return true;
			}

			public E carrier() {
				return alg.bool(x);
			}
		};
	}

	public Constant<E> iff(final Constant<E> b, final Constant<E> e1, final Constant<E> e2) {
		return new Constant<E>() {
			public boolean isConstant() {
				return b.isConstant();
			}

			public E carrier() {
				return alg.iff(b.carrier(), e1.carrier(), e2.carrier());
			}
		};
	}

	public Constant<E> lit(final int n) {
		return new Constant<E>() {
			public boolean isConstant() {
				return true;
			}

			public E carrier() {
				return alg.lit(n);
			}			
		};
	}

	public Constant<E> add(final Constant<E> e1, final Constant<E> e2) {
		return new Constant<E>() {
			public boolean isConstant() {
				return e1.isConstant() && e2.isConstant();
			}

			public E carrier() {
				return alg.add(e1.carrier(), e2.carrier());
			}
		};
	}

	public S breakp(S x) {
		return x;
	}
}

interface PE<A> extends Constant<A>, IEval {
	Value eval();
}

/* Try making the code less verbose with factories */

/* More boring composition code ... */
class PartialEvaluator<E,S> implements BreakpointAlg<PE<E>,S> {
	ConstantFolding<E,S> alg;
	EvalBreak eval;
	HashMap<String,Value> constantVariables = new HashMap<String,Value>(); 
	
	PartialEvaluator(ConstantFolding<E,S> alg, EvalBreak eval) {
		this.alg = alg;
		this.eval = eval;
	}

	public PE<E> var(final String x) {
		return new PE<E>() {// Manual mixin composition :(
			public boolean isConstant() {
				return /*constantVariables.containsKey(x);*/ alg.var(x).isConstant();
			}
			
			// Partial evaluation for variables assigned to constants
			public E carrier() {
				if (isConstant()) {
					eval.setEnv(constantVariables);
					try {
						return alg.lit(eval().getInt()).carrier();
					} catch (BadTypeException ex) {
						return alg.bool(eval().getBool()).carrier();
					}
				}
				else 
					return alg.var(x).carrier();
			}

			public Value eval() {
				return eval.var(x).eval();
			}
		};
	}

	// Design is not great. Java limitations mostly.
	public PE<E> assign(final String x, final PE<E> e) {	
		return new PE<E>() {
			public boolean isConstant() {
				return alg.assign(x, e).isConstant();
			}
			
			// Need to refactor; make it functional state!!! Too dependent on the order of evaluation.
			public E carrier() { 
				Constant<E> p = alg.assign(x, e);
				E ret;
				
				if (e.isConstant()) {
					ret = e.carrier();
					constantVariables.put(x, e.eval());
				} else {
					ret = p.carrier();
				}
				
				return ret;
			}

			public Value eval() {
				//return constantVariables.get(x);
				return eval.assign(x, e).eval();
			}
		};
	}

	public S expr(PE<E> e) {
		return alg.expr(e);
	}

	public S comp(S e1, S e2) {
		return alg.comp(e1, e2);
	}

	public PE<E> bool(final boolean x) {
		return new PE<E>() {
			public boolean isConstant() {
				return alg.bool(x).isConstant();
			}

			public E carrier() {
				return alg.bool(x).carrier();
			}

			public Value eval() {
				return eval.bool(x).eval();
			}
		};
	}

	public PE<E> iff(final PE<E> b, final PE<E> e1, final PE<E> e2) {
		return new PE<E>() {
			public boolean isConstant() {
				return alg.iff(b, e1, e2).isConstant();
			}
			
			/* Partial evaluation for if expressions */
			public E carrier() {
				eval.setEnv(constantVariables);
				if (b.isConstant()) {
					if (b.eval().getBool()) 
						return e1.carrier();
					else 
						return e2.carrier();
				} else 
					return alg.iff(b, e1, e2).carrier();
			}

			public Value eval() {
				return eval.iff(b, e1, e2).eval();
			}			
		};
	}

	public PE<E> lit(final int n) {
		return new PE<E>() {
			public boolean isConstant() {
				return alg.lit(n).isConstant();
			}

			public E carrier() {
				return alg.lit(n).carrier();
			}

			public Value eval() {
				return eval.lit(n).eval();
			}
		};
	}

	public PE<E> add(final PE<E> e1, final PE<E> e2) {
		return new PE<E>() {
			public boolean isConstant() {
				return alg.add(e1, e2).isConstant();
			}

			/* Partial evaluation for addition */ 
			public E carrier() {
				eval.setEnv(constantVariables);
				
				if (isConstant()) 
					return alg.lit(eval().getInt()).carrier();
				else
					return alg.add(e1, e2).carrier();
			}

			public Value eval() {
				return eval.add(e1, e2).eval();
			}
		};
	}

	public S breakp(S x) {
		return alg.breakp(x);
	}
}

class GCombineInt<A,B,VA extends ExpAlg<A>, VB extends ExpAlg<B>> implements ExpAlg<Pair<A,B>> {
	VA expAlg1;
	VB expAlg2;
	 
	GCombineInt(VA expAlg1, VB expAlg2) {
		this.expAlg1 = expAlg1;
		this.expAlg2 = expAlg2;
	}
	
	public Pair<A, B> lit(int n) {
		return new Pair<A,B>(expAlg1.lit(n), expAlg2.lit(n));
	}
	
	public Pair<A, B> add(Pair<A, B> e1, Pair<A, B> e2) {
		return new Pair<A,B>(expAlg1.add(e1.fst(), e2.fst()), expAlg2.add(e1.snd(),e2.snd()));
	}
}

class GCombineBool<A,B,VA extends ExpBool<A>, VB extends ExpBool<B>> implements ExpBool<Pair<A,B>> {
	VA expAlg1;
	VB expAlg2;
	 
	GCombineBool(VA expAlg1, VB expAlg2) {
		this.expAlg1 = expAlg1;
		this.expAlg2 = expAlg2;
	}

	public Pair<A, B> bool(boolean x) {
		return new Pair<A,B>(expAlg1.bool(x), expAlg2.bool(x));
	}

	public Pair<A, B> iff(Pair<A, B> b, Pair<A, B> e1, Pair<A, B> e2) {
		return new Pair<A,B>(expAlg1.iff(b.fst(), e1.fst(), e2.fst()), expAlg2.iff(b.snd(), e1.snd(),e2.snd()));
	}
}

class GCombineBoolExt<A,B,VA extends ExpBoolExt<A>, VB extends ExpBoolExt<B>> extends GCombineInt<A,B,VA,VB> implements ExpBoolExt<Pair<A,B>> {
	VA expAlg1;
	VB expAlg2;
	 
	GCombineBoolExt(VA expAlg1, VB expAlg2) {
		super(expAlg1,expAlg2);
	}

	public Pair<A, B> bool(boolean x) {
		return new Pair<A,B>(expAlg1.bool(x), expAlg2.bool(x));
	}

	public Pair<A, B> iff(Pair<A, B> b, Pair<A, B> e1, Pair<A, B> e2) {
		return new Pair<A,B>(expAlg1.iff(b.fst(), e1.fst(), e2.fst()), expAlg2.iff(b.snd(), e1.snd(),e2.snd()));
	}
}

class FixCombine<A,B> extends GCombineBoolExt<A,B,ExpBoolExt<A>,ExpBoolExt<B>> {
	FixCombine(ExpBoolExt<A> expAlg1, ExpBoolExt<B> expAlg2) {
		super(expAlg1, expAlg2);
	}
}

/* Seems I cannot generalize it further :( */

abstract class ZipWith<A,B,C, VA extends ExpAlg<A>, VB extends ExpAlg<B>> implements ExpAlg<C> {
	VA expAlg1;
	VB expAlg2;
	 
	ZipWith(VA expAlg1, VB expAlg2) {
		this.expAlg1 = expAlg1;
		this.expAlg2 = expAlg2;
	}
	
	// Need to be overriden in the subclass
	public abstract C combine(A x, B y);
	
	public C lit(int n) {
		return combine(expAlg1.lit(n), expAlg2.lit(n));
	}
	
	public C add(C e1, C e2) {
		return null;
		//return new Pair<A,B>(expAlg1.add(e1.fst(), e2.fst()), expAlg2.add(e1.snd(),e2.snd()));
	}
}

/*
class GCombineBool<A,B,V1 extends ExpBool<A>, V2 extends ExpBool<B>> extends GCombineimplements ExpAlg<Pair<A,B>> {
	V1 expAlg1;
	V2 expAlg2;
	
	public Pair<A, B> lit(int n) {
		return new Pair<A,B>(expAlg1.lit(n), expAlg2.lit(n));
		
	}
	
	public Pair<A, B> add(Pair<A, B> e1, Pair<A, B> e2) {
		return new Pair<A,B>(expAlg1.add(e1.fst(), e2.fst()), expAlg2.add(e1.snd(),e2.snd()));
	}
}*/

//BEGIN_MODULARITY__COMBINE
class Pair<A,B> {
	A x;
	B y;
	Pair(A x, B y) { this.x = x; this.y = y; }

	A fst() { return x; }
	B snd() { return y; }
}

class Combine<A,B> implements ExpAlg<Pair<A,B>> {
	ExpAlg<A> expAlg1;
	ExpAlg<B> expAlg2;

	Combine(ExpAlg<A> expAlg1, ExpAlg<B> expAlg2) {
		this.expAlg1 = expAlg1;
		this.expAlg2 = expAlg2;
	}
	public Pair<A, B> lit(int x) {
		return new Pair<A,B>(expAlg1.lit(x), expAlg2.lit(x));
	}
	public Pair<A, B> add(Pair<A, B> e1, Pair<A, B> e2) {
		return new Pair<A,B>(expAlg1.add(e1.fst(), e2.fst()), expAlg2.add(e1.snd(),e2.snd()));
	}
}
//END_MODULARITY__COMBINE

class PrintStmt extends Print implements StmtAlg<String,String> {
	public String var(String x) {
		return x;
	}
	public String assign(String x, String e) {
		return (x + " = " + e);
	}
	public String comp(String e1, String e2) {
		return (e1 + " ; " + e2);
	}
	public String expr(String e) {
		return e;
	}
}

class PrintBreak extends PrintStmt implements BreakpointAlg<String,String> {
	public String breakp(String x) {
		return x;
	}
}

/* Will I need something like this */

/*

interface Debug<E,S> {
	Value evalExp(E xval);
	Value evalStmt(S xval);
	String printExp(E xval);
	String printStmt(S xval);
} 

 */

class CombineBreak<A,B,C,D> extends GCombineBreak<A,B,C,D,BreakpointAlg<A,C>,BreakpointAlg<B,D>> {
	CombineBreak(BreakpointAlg<A, C> arg0, BreakpointAlg<B, D> arg1) {
		super(arg0, arg1);
	}
}

class GCombineBreak<A,B,C,D,A1 extends BreakpointAlg<A,C>, A2 extends BreakpointAlg<B,D>> implements BreakpointAlg<Pair<A,B>,Pair<C,D>> {
	A1 bkAlg1;
	A2 bkAlg2;

	GCombineBreak(A1 bkAlg1, A2 bkAlg2) {
		this.bkAlg1 = bkAlg1;
		this.bkAlg2 = bkAlg2;
	}


	public Pair<A, B> var(String x) {
		return new Pair<A, B>(bkAlg1.var(x), bkAlg2.var(x));
	}


	public Pair<A, B> assign(String x, Pair<A, B> e) {
		return new Pair<A, B>(bkAlg1.assign(x,e.fst()), bkAlg2.assign(x,e.snd()));
	}


	public Pair<C, D> expr(Pair<A, B> e) {
		return new Pair<C, D>(bkAlg1.expr(e.fst()), bkAlg2.expr(e.snd()));
	}


	public Pair<C, D> comp(Pair<C, D> e1, Pair<C, D> e2) {
		return new Pair<C, D>(bkAlg1.comp(e1.fst(),e2.fst()), bkAlg2.comp(e1.snd(),e2.snd()));
	}


	public Pair<A, B> bool(boolean x) {
		return new Pair<A, B>(bkAlg1.bool(x), bkAlg2.bool(x));
	}


	public Pair<A, B> iff(Pair<A, B> b, Pair<A, B> e1, Pair<A, B> e2) {
		return new Pair<A, B>(bkAlg1.iff(b.fst(), e1.fst(), e2.fst()), bkAlg2.iff(b.snd(),e1.snd(),e2.snd()));
	}


	public Pair<A, B> add(Pair<A, B> e1, Pair<A, B> e2) {
		return new Pair<A, B>(bkAlg1.add(e1.fst(), e2.fst()), bkAlg2.add(e1.snd(),e2.snd()));
	}


	public Pair<A, B> lit(int x) {
		return new Pair<A, B>(bkAlg1.lit(x), bkAlg2.lit(x));
	}


	public Pair<C, D> breakp(Pair<C, D> x) {
		return new Pair<C, D>(bkAlg1.breakp(x.fst()), bkAlg2.breakp(x.snd()));
	}
}

/* Tedious weaving of Evaluation and printing for debugging */ 

//BEGIN_MUTUAL_EVALPRINT
class IDebug {
	private IEval v;
	private String s;
	public IDebug(IEval v, String s) {
		this.v = v;
		this.s = s;
	}

	public IEval getEval()   { return v;        }
	public String getPrint() { return s;        }
	public Value eval()      { return v.eval(); }
	public String print()    { return s;        }
}
//END_MUTUAL_EVALPRINT

interface EvalPrintExp {
	Value evalExp();
	String printExp();
}

interface EvalPrintStmt {
	Value evalStmt();
	String printStmt();
}

/* DEBUGGING --> subtyping is not expressive enough to make this more convenient*/

//BEGIN_MUTUAL_DEBUG
class Debug implements BreakpointAlg<IDebug,IDebug> {
	EvalBreak evalStmt = new EvalBreak();
	PrintBreak printStmt = new PrintBreak();

	public IDebug var(String x) {
		return new IDebug(evalStmt.var(x), printStmt.var(x));
	}

	public IDebug assign(String x, IDebug e) {
		return new IDebug(evalStmt.assign(x, e.getEval()),printStmt.assign(x, e.getPrint()));
	}

	public IDebug expr(IDebug e) {
		return new IDebug(evalStmt.expr(e.getEval()), printStmt.expr(e.getPrint()));
	}

	public IDebug comp(IDebug e1, IDebug e2) {
		return new IDebug(evalStmt.comp(e1.getEval(), e2.getEval()),printStmt.comp(e1.print(),e2.print()));
	}

	public IDebug bool(boolean x) {
		return new IDebug(evalStmt.bool(x), printStmt.bool(x));
	}

	public IDebug iff(IDebug b, IDebug e1, IDebug e2) {
		return new IDebug(evalStmt.iff(b.getEval(), e1.getEval(), e2.getEval()), printStmt.iff(b.getPrint(), e1.getPrint(), e2.getPrint()));
	}

	public IDebug lit(int n) {
		return new IDebug(evalStmt.lit(n),printStmt.lit(n));
	}

	public IDebug add(IDebug e1, IDebug e2) {
		return new IDebug(evalStmt.add(e1.getEval(), e2.getEval()), printStmt.add(e1.getPrint(), e2.getPrint()));
	}

	public IDebug breakp(IDebug x) {
		return new IDebug(evalStmt.breakp(x.getEval()), printStmt.breakp(x.getPrint()));
	}
}
//END_MUTUAL_DEBUG

/*
class Debug extends CombineBreak<Value,String,Value,String> {

	Debug(BreakpointAlg<Value, Value> bkAlg1,
			BreakpointAlg<String, String> bkAlg2) {
		super(bkAlg1, bkAlg2);
	}


	public Pair<Value, String> breakp(Pair<Value, String> xval) {
		return super.breakp(xval);
	}
} */

/* Adding breakpoints */

interface BreakpointAlg<E,S> extends StmtAlg<E,S> {
	S breakp(S x);
}

class EvalBreak extends EvalStmt implements BreakpointAlg<IEval,IEval> {
	
	public EvalBreak() {
		super();
	}
	
	public EvalBreak(HashMap<String,Value> initialEnv) {
		super(initialEnv);
	}
	
	public IEval breakp(IEval x) {
		System.out.println("Breakpoint");

		return x;
	}
}

/* Generic traversal/wrapping */

/* More like a generic wrapper, but I think can act 
 * as a generic traversal. */

class Wrap<E,S> implements BreakpointAlg<E,S> {
	BreakpointAlg<E,S> alg;
	
	public Wrap(BreakpointAlg<E,S> alg) {
		this.alg = alg;
	}

	public E bool(boolean x) {
		return alg.bool(x);
	}

	public E iff(E b, E e1, E e2) {
		return alg.iff(b, e1, e2);
	}

	public E lit(int n) {
		return alg.lit(n);
	}

	public E add(E e1, E e2) {
		return alg.add(e1, e2);
	}

	public E var(String x) {
		return alg.var(x);
	}

	public E assign(String x, E e) {
		return alg.assign(x, e);
	}

	public S expr(E e) {
		return alg.expr(e);
	}

	public S comp(S e1, S e2) {
		return alg.comp(e1, e2);
	}

	public S breakp(S x) {
		return alg.breakp(x);
	}
}

class Wrap2<E,S,Alg extends BreakpointAlg<E,S>> implements BreakpointAlg<E,S> {
	Alg alg;
	
	public Wrap2(Alg alg) {
		this.alg = alg;
	}

	public E bool(boolean x) {
		return alg.bool(x);
	}

	public E iff(E b, E e1, E e2) {
		return alg.iff(b, e1, e2);
	}

	public E lit(int n) {
		return alg.lit(n);
	}

	public E add(E e1, E e2) {
		return alg.add(e1, e2);
	}

	public E var(String x) {
		return alg.var(x);
	}

	public E assign(String x, E e) {
		return alg.assign(x, e);
	}

	public S expr(E e) {
		return alg.expr(e);
	}

	public S comp(S e1, S e2) {
		return alg.comp(e1, e2);
	}

	public S breakp(S x) {
		return alg.breakp(x);
	}
}

/* A simple transformation */
class Transform<E,S> extends Wrap<E,S> {	
	public Transform(BreakpointAlg<E,S> alg) {
		super(alg);
	}

	public E lit(int n) {
		if (n == 3) 
			return alg.lit(0); 
		else 
			return super.lit(n);
	}
}

/* Type-checking */



/* Almost an element in the visitor pattern */

/* we can create visitors after we fixed the functionality */

interface AbstractExp {
	<A> A value(ExpIntBool<A> v);
}

class exp2 implements AbstractExp {
	public <A> A value(ExpIntBool<A> v) {
		return v.iff(v.bool(false), v.lit(3), v.add(v.lit(3), v.lit(4)));
	}
}

interface AbstractStmt {
	<E,S> S stmt(StmtAlg<E,S> v);
}

class AStmt implements AbstractStmt {
	public <E,S> S stmt(StmtAlg<E,S> v) {
		return v.comp(v.expr(v.assign("xval", v.add(v.lit(7), v.lit(4)))),v.expr(v.assign("y",v.add(v.var("xval"),v.lit(3)))));
	}
}

/* NOTE 1: We want to create values with the abstract constructions 
 * to ensure the syntactic constraints (ex: essions cannot be used 
 * where statements are expected).
 * 
 * NOTE 2: We may want to create the full (non-extensible) visitor code 
 * when we finally decided which extensions we want.
 */

/* Mutually recursive functions */

public class Test {
	/* the accept method: writing syntax once and for all */
	
	//BEGIN_MODULARITY_SUBTYPE
	static <A> A exp1(ExpAlg<A> v) {
		return v.add(v.lit(3), v.lit(4));
	}
	static <A> A exp2(ExpIntBool<A> v) {
		return v.iff(v.bool(false), v.lit(3), exp1(v));
	}
	//END_MODULARITY_SUBTYPE

	static <E,S> S astmt(BreakpointAlg<E,S> v) {
		return v.comp(v.comp(v.breakp(v.expr(v.assign("xval", exp2(v)))),v.expr(v.assign("y",v.add(v.var("xval"),v.lit(3))))),v.expr(v.assign("y", v.add(v.var("xval"), v.var("y")))));
	}

	public static void printTest(int n, String typ, String s1, String s2) {
		System.out.println("==========");
		System.out.println("TEST " + n);
		System.out.println("==========");

		System.out.println(typ + ": " + s1 + "\n" + s2 + "\n\n");
	}

	public static void main(String args[]) {
		EvalIntBool2 v = new EvalIntBool2();
		Print p = new Print();
		
		// The next line is just to test transformations.
		//BreakpointAlg<IEval,IEval> evalStmt = new Transform<IEval,IEval>(new EvalBreak());
		EvalBreak evalStmt = new EvalBreak();
		PrintBreak printStmt = new PrintBreak();
		Typecheck<IEval,IEval> tcheck = new Typecheck<IEval, IEval>(evalStmt);

		CombineBreak<IEval, String, IEval, String> combStmt = new CombineBreak<IEval, String, IEval, String>(evalStmt, printStmt);
		PartialEvaluator<String, String> partial = 
				new PartialEvaluator<String, String>(new ConstantFolding<String, String>(new PrintBreak()), evalStmt);

		IEval t = exp2(v);
		String s = exp2(p);
		IEval f = astmt(evalStmt);
		String s1 = astmt(printStmt);
		
		Typed<IEval> typedExp = exp2(tcheck);
		t = typedExp.x;
		
		Typed<IEval> typedStmt = astmt(tcheck);
		f = typedStmt.x;
		
		String partialStmt = astmt(partial); 
		
		Pair<IEval,String> combinedRes = astmt(combStmt);
		IDebug debugRes = astmt(new Debug());

		// Running programs faster, by combining traversals
		Combine<Value,String> c = new Combine<Value,String>(new Eval3(), new Print());

		Pair<Value,String> combinedRes1 = exp1(c);

		printTest(1,"Expression",s,t.eval().toString() + " : " + typedExp.t);
		printTest(2,"Statement",s1,f.eval().toString() + " : " + typedStmt.t);
		printTest(20,"Partial Statement",partialStmt,"");
		printTest(3,"Expression",combinedRes1.snd(), combinedRes1.fst().toString());
		printTest(4,"Statement",debugRes.print(), debugRes.eval().toString());
	}
}