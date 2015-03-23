package manual;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import trees.FJAlg;

public class ManualFreeVariables implements FJAlg<Set<String>, Set<String>, Set<String>, Set<String>, Set<String>, Set<String>, Set<String>, Set<String>, Pair<Set<String>, Set<String>>, Pair<Set<String>, String>>{
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
			List<Pair<Set<String>, Set<String>>> sorts, Set<String> superClass,
			List<Pair<Set<String>, String>> fields, Set<String> constr,
			List<Set<String>> methods) {
		Set<String> res = new HashSet<String>();
		for(Set<String> s: methods){
				res.addAll(s);
		}
		res.addAll(constr);
		for (Pair<Set<String>, Set<String>> p: sorts){
			res.addAll(p.b());
		}
		for (Pair<Set<String>, String> p: fields){
			res.remove(p.b());
		}
		for (Pair<Set<String>, Set<String>> p: sorts){
			res.removeAll(p.a());
		}
		res.remove(className);
		return res;
	}
	@Override
	public Set<String> ConstrDec(String className,
			List<Pair<Set<String>, String>> paras) {
		Set<String> res = new HashSet<String>();
		for (Pair<Set<String>, String> p: paras){
			res.addAll(p.a());
		}
		res.add(className);
		
		for (Pair<Set<String>, String> p: paras){
			res.remove(p.b());
		}
		return res;
	}
	@Override
	public Set<String> MethodDec(List<Pair<Set<String>, Set<String>>> sorts,
			Set<String> returnType, String methodName,
			List<Pair<Set<String>, String>> paras, Set<String> returnStmt) {
		
		Set<String> res = new HashSet<String>();
		res.addAll(returnStmt);
		for (Pair<Set<String>, String> s: paras){
			res.addAll(s.a());
		}
		for (Pair<Set<String>, String> s: paras){
			res.remove(s.b());
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
}
