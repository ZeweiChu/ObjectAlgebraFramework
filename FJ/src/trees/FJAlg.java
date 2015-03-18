package trees;

import java.util.List;

import com.zewei.annotation.processor.Algebra;

import manual.Pair;

@Algebra
public interface FJAlg<J, T, N, L, K, M, E, X> {
	J Code(List<L> classes);
	T TypeX(X x); // Type variables.
	T TypeN(N n); // Non-variable Types.
	N ClassType(String className, List<T> sorts);
	L ClassDec(String className, List<Pair<X, N>> sorts, N superClass, List<Pair<T, String>> fields, K constr, List<M> methods);
	K ConstrDec(String className, List<Pair<T, String>> paras);
	M MethodDec(List<Pair<X, N>> sorts, T returnType, String methodName, List<Pair<T, String>> paras, E returnStmt);
	E Var(String name);
	E FieldAccess(E e, String fieldName);
	E MethodInvoke(E e, String methodName, List<T> sorts, List<E> paras);
	E ObjectCreate(N className, List<E> paras);
	E Cast(N castType, E e);
	X TVar(String name);
}

