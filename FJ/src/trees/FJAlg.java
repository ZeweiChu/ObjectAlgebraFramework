package trees;

import java.util.List;

import com.zewei.annotation.processor.Algebra;


@Algebra
public interface FJAlg<J, T, N, L, K, M, E, X, P1, P2> {
	J Code(List<L> classes);
	T TypeX(X x); // Type variables.
	T TypeN(N n); // Non-variable Types.
	N ClassType(String className, List<T> sorts);
	//L ClassDec(String className, List<Pair<X, N>> sorts, N superClass, List<Pair<T, String>> fields, K constr, List<M> methods);
	L ClassDec(String className, List<P1> sorts, N superClass, List<P2> fields, K constr, List<M> methods);
	//K ConstrDec(String className, List<Pair<T, String>> paras);
	K ConstrDec(String className, List<P2> paras);
	//M MethodDec(List<Pair<X, N>> sorts, T returnType, String methodName, List<Pair<T, String>> paras, E returnStmt);
	M MethodDec(List<P1> sorts, T returnType, String methodName, List<P2> paras, E returnStmt);
	E Var(String name);
	E FieldAccess(E e, String fieldName);
	E MethodInvoke(E e, String methodName, List<T> sorts, List<E> paras);
	E ObjectCreate(N className, List<E> paras);
	E Cast(N castType, E e);
	X TVar(String name);
}

