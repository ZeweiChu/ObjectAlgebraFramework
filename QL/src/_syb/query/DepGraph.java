package _syb.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import library.Pair;
import ql_obj_alg.check.types.Type;
import query.G_IExpAlgQuery;
import query.G_IFormAlgQuery;
import query.G_IStmtAlgQuery;

public interface DepGraph extends 
	G_IFormAlgQuery<Set<String>, Set<Pair<String, String>>, Set<Pair<String, String>>>,
	G_IStmtAlgQuery<Set<String>, Set<Pair<String, String>>>,  
	G_IExpAlgQuery<Set<String>> {
	
	@Override
	default java.util.Set<Pair<String,String>> form(String p0, java.util.List<java.util.Set<Pair<String, String>>> p1) {
		return mS().fold(p1);
	};
	
	@Override 
	default Set<String> var(String varName) {
		return Collections.singleton(varName);
	}

	@Override 
	default Set<Pair<String,String>> question(String p0,String p1,Type p2,Set<String> p3) {
		Set<Pair<String,String>> deps = new HashSet<>(); //.empty();
		for (String x: p3) {
			deps.add(new Pair<>(p0, x));
		}
		return deps;
	}
	
	@Override
	default Set<Pair<String, String>> iff(Set<String> p0, java.util.List<Set<Pair<String,String>>> p1) {
		return iffelse(p0, p1, Collections.emptyList());
	}

	@Override
	default Set<Pair<String, String>> iffelse(Set<String> p0, java.util.List<Set<Pair<String,String>>> p1, java.util.List<Set<Pair<String,String>>> p2) {
		Set<Pair<String,String>> deps = new HashSet<>(); //mS().empty();
		deps = mS().join(deps, mS().fold(p1));
		deps = mS().join(deps, mS().fold(p2));
		for (String x: p0) {
			for (List<Set<Pair<String, String>>> l: Arrays.asList(p1, p2)) {
				for (Set<Pair<String, String>> s: l) {
					for (Pair<String,String> p: s) {
						deps.add(new Pair<>(p.a(), x));
					}
				}
			}
		}
		return deps;
	}

}
