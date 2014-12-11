package substitution;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import transform.G_ExpAlgTransform;
import transform.G_LamAlgTransform;


public interface Substitution<Exp> extends G_ExpAlgTransform<Map<String,String>, Exp>, G_LamAlgTransform<Map<String,String>, Exp> {
	String x(); Exp e(); Set<String> fv();
	
	@Override
	default Function<Map<String,String>, Exp> Var(String s) {
		return (ren) -> rename(ren, s).equals(x()) ? e() : expAlg().Var(rename(ren, s)); 
	}
	
	@Override
	default Function<Map<String,String>,Exp> Lam(String y, Function<Map<String,String>,Exp> e) {
		return (ren) -> {
			if (y.equals(x())) {
				return lamAlg().Lam(y, e.apply(Collections.singletonMap(y, y)));
			}
			
			if (fv().contains(y)) {
				// rename lambda binder using fresh variable z.
				String z = fresh(y, fv()); 
				return lamAlg().Lam(z, e.apply(add(ren, y, z)));	
			}
			
			// no risk of capture, but don't rename across binder y
			return lamAlg().Lam(y, e.apply(remove(ren, y)));
			
			
		};
	}
	
	
	/*
	 * Some helper utilities
	 */

	static Map<String, String> remove(Map<String, String> ren, String y) {
		ren = new HashMap<>(ren);
		ren.remove(y);
		return ren;
	}

	static Map<String,String> add(Map<String, String> ren, String x, String z) {
		return new HashMap<String,String>(ren) {{
			put(x, z);
		}};
	}
	
	static String rename(Map<String,String> renaming, String x) {
		if (renaming.containsKey(x)) {
			return renaming.get(x);
		}
		return x;
	}
	
	static String fresh(String x, Set<String> fv) {
		// returned name is automatically ensured to be
		// disjoint from x itself through suffixing.
		while (fv.contains(x)) {
			x = x + "_";
		}
		return x;
	}
	
	
	
	
	
}

