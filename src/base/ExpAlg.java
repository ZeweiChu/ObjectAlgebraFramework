package base;


/* The initial algebra(s) */
// BEGIN_EXP__ALG
public interface ExpAlg<A> {
	A lit(int n);
	A add(A e1, A e2);
}
// END_EXP__ALG
