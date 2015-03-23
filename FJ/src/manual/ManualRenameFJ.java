package manual;

import java.util.List;

import trees.FJAlg;

public class ManualRenameFJ<J, T, N, L, K, M, E, X> implements FJAlg<J, T, N, L, K, M, E, X, Pair<X, N>, Pair<T, String>>{

	FJAlg<J, T, N, L, K, M, E, X, Pair<X, N>, Pair<T, String>> alg;
	
	public ManualRenameFJ(FJAlg<J, T, N, L, K, M, E, X, Pair<X, N>, Pair<T, String>> alg){
		this.alg = alg;
	}
	
	@Override
	public J Code(List<L> classes) {
		return alg.Code(classes);
	}

	@Override
	public T TypeX(X x) {
		return alg.TypeX(x);
	}

	@Override
	public T TypeN(N n) {
		return alg.TypeN(n);
	}

	@Override
	public N ClassType(String className, List<T> sorts) {
		return alg.ClassType(className, sorts);
	}

	@Override
	public L ClassDec(String className, List<Pair<X, N>> sorts, N superClass,
			List<Pair<T, String>> fields, K constr, List<M> methods) {
		for (Pair<T, String> s: fields){
			s.b = "_" + s.b;
		}
		return alg.ClassDec("_"+className, sorts, superClass, fields, constr, methods);
	}

	@Override
	public K ConstrDec(String className, List<Pair<T, String>> paras) {
		for (Pair<T, String> p: paras){
			p.b = "_" + p.b;
		}
		return alg.ConstrDec("_" + className, paras);
	}

	@Override
	public M MethodDec(List<Pair<X, N>> sorts, T returnType, String methodName,
			List<Pair<T, String>> paras, E returnStmt) {
		for (Pair<T, String> p: paras){
			p.b = "_" + p.b;
		}
		return alg.MethodDec(sorts, returnType, "_" + methodName, paras, returnStmt);
	}

	@Override
	public E Var(String name) {
		return alg.Var("_" + name);
	}

	@Override
	public E FieldAccess(E e, String fieldName) {
		return alg.FieldAccess(e, "_" + fieldName);
	}

	@Override
	public E MethodInvoke(E e, String methodName, List<T> sorts, List<E> paras) {
		return alg.MethodInvoke(e, "_" + methodName, sorts, paras);
	}

	@Override
	public E ObjectCreate(N className, List<E> paras) {
		return alg.ObjectCreate(className, paras);
	}

	@Override
	public E Cast(N castType, E e) {
		return alg.Cast(castType, e);
	}

	@Override
	public X TVar(String name) {
		return alg.TVar("_" + name);
	}
}
