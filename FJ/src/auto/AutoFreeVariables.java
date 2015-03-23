package auto;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import library.Monoid;
import query.FJAlgQuery;

public class AutoFreeVariables implements FJAlgQuery<Set<String>>{
	@Override
	public Set<String> Code(List<Set<String>> classes) {
		Set<String> res = new HashSet<String>();
		for (Set<String> s: classes){
			res.addAll(s);
		}
		return res;
	}
	@Override
	public Set<String> TypeX(Set<String> x) {
		return x;
	}
	@Override
	public Set<String> TypeN(Set<String> n) {
		return n;
	}
	@Override
	public Set<String> ClassType(String className, List<Set<String>> sorts) {
		Set<String> res = new HashSet<String>();
		for (Set<String> s: sorts){
			res.addAll(s);
		}
		res.add(className);
		return res;
	}

	@Override
	public Set<String> ClassDec(String className,
			List<Set<String>> sorts, Set<String> superClass,
			List<Set<String>> fields, Set<String> constr,
			List<Set<String>> methods) {
		Set<String> res = new HashSet<String>();
		for(Set<String> s: methods){
				res.addAll(s);
		}
		res.addAll(constr);
		for (Set<String> s: sorts){
			res.addAll(s);
		}
		for (Set<String> s: fields){
			res.remove(s);
		}
		res.remove(className);
		return res;
	}
	@Override
	public Set<String> ConstrDec(String className,
			List<Set<String>> paras) {
		Set<String> res = new HashSet<String>();
		for (Set<String> s: paras){
			res.addAll(s);
		}
		res.add(className);
		return res;
	}
	@Override
	public Set<String> MethodDec(List<Set<String>> sorts,
			Set<String> returnType, String methodName,
			List<Set<String>> paras, Set<String> returnStmt) {
		
		Set<String> res = new HashSet<String>();
		res.addAll(returnStmt);
		for (Set<String> s: paras){
			res.addAll(s);
		}
		for (Set<String> s: paras){
			res.remove(s);
		}
		return res;
	}
	@Override
	public Set<String> Var(String name) {
		return Collections.singleton(name);
	}
	@Override
	public Set<String> FieldAccess(Set<String> e, String fieldName) {
		return e;
	}
	@Override
	public Set<String> MethodInvoke(Set<String> e, String methodName,
			List<Set<String>> sorts, List<Set<String>> paras) {
		Set<String> res = new HashSet<String>();
		res.addAll(e);
		res.add(methodName);
		for (Set<String> s: paras){
			res.addAll(s);
		}
		return res;
	}
	@Override
	public Set<String> ObjectCreate(Set<String> className,
			List<Set<String>> paras) {
		Set<String> res = new HashSet<String>();
		res.addAll(className);
		for (Set<String> s: paras){
			res.addAll(s);
		}
		return res;
	}
	@Override
	public Set<String> Cast(Set<String> castType, Set<String> e) {
		return e;
	}

	@Override
	public Set<String> TVar(String name) {
		return new HashSet<String>();
	}
	@Override
	public Monoid<Set<String>> m() {
		return new StringSetMonoid();
	}
}
