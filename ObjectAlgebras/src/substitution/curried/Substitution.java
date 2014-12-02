package substitution.curried;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import transform.G_ExpAlgTransform;
import transform.G_LamAlgTransform;
import trees.ExpAlg;
import trees.LamAlg;


// (x) -> (e) -> (fv) -> (ren) -> Exp
@SuppressWarnings("serial")
public interface Substitution<Exp> extends 
	G_ExpAlgTransform<String, Function<Exp, Function<Set<String>, Function<Map<String, String>, Exp>>>>, 
	G_LamAlgTransform<String, Function<Exp, Function<Set<String>, Function<Map<String, String>, Exp>>>> {
	
	ExpAlg<Exp> myExpAlg();
	LamAlg<Exp> myLamAlg();
	
	@Override
	default Function<String, Function<Exp, Function<Set<String>, Function<Map<String, String>, Exp>>>> Var(String s) {
		return (x) -> (e) -> (fv) -> (ren) -> {
			if (s.equals(x)) 
				return e;
			if (ren.containsKey(s)) 
				return expAlg().Var(ren.get(s)).apply(e).apply(fv).apply(ren);
			return expAlg().Var(s).apply(e).apply(fv).apply(ren);
		};
	}
	
	@Override
	default Function<String, Function<Exp, Function<Set<String>, Function<Map<String, String>, Exp>>>>  Lambda(String x, Function<String, Function<Exp, Function<Set<String>, Function<Map<String, String>, Exp>>>>  e) {
		return (y) -> (e2) -> (fv) -> (ren)  -> {
			if (x.equals(y)) {
				return null;
				//return lamAlg().Lambda(x, e.apply(x).apply(myExpAlg().Var(x)).apply(Collections.emptySet()).apply(ren)).apply(e).apply(fv).apply(ren);
			}
			
			if (fv.contains(x)) {
				String z = fresh(x, fv);
				return myLamAlg().Lambda(z, e.apply(y).apply(e2).apply(fv).apply(rename(ren, x, z)));
			}
			
			return myLamAlg().Lambda(x, e.apply(y).apply(e2).apply(fv).apply(ren));
		};
	}
	
	static Map<String,String> rename(Map<String, String> ren, String x, String z) {
		return new HashMap<String,String>(ren) {{
			put(x, z);
		}};
	}
	
	static String fresh(String x, Set<String> fv) {
		while (fv.contains(x)) {
			x = x + "_";
		}
		return x;
	}
	
}

