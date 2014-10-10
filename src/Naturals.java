
public class Naturals {
	
	static <A> A value(Nat<A> v) {
		return v.succ(v.succ(v.zero()));
	}
	/*
	public static void main(String args[]) {
		Pred<Integer> pred = new Pred<Integer>(new ToInt());
		
		P<Integer> p = value(pred);
		
		System.out.println(p.compute());
	} */
}

interface Nat<A> {
	A zero();
	A succ(A x);
}

interface P<A> {
	A compute();
}

class Pred<A> implements Nat<P<A>> {
	Nat<A> alg;
	Boolean first = true;
	
	Pred(Nat<A> alg) {
		this.alg = alg;
	}

	public P<A> zero() {
		return new P<A>() {
			public A compute() {
				return alg.zero();
			}
			
		};
	}

	public P<A> succ(final P<A> x) {
		P<A> p = new P<A>() {
			public A compute() {
				A res;
				if (first)
					res = x.compute();
				else 
					res = alg.succ(x.compute());
				return res;
			}
		};
		first = false;
		return p;
	}
}

class ToInt implements Nat<Integer> {
	public Integer zero() {	
		return 0;
	}

	public Integer succ(Integer x) {
		return 1 + x;
	}
}
