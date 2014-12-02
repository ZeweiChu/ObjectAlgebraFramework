package substitution;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import transform.G_ExpAlgTransform;
import transform.G_LamAlgTransform;


@SuppressWarnings("serial")
public interface Substitution<Exp> extends G_ExpAlgTransform<SubstArgs<Exp>, Exp>, G_LamAlgTransform<SubstArgs<Exp>, Exp> {
	
	@Override
	default Function<SubstArgs<Exp>, Exp> Var(String s) {
		return (args) -> {
			if (rename(args.ren, s).equals(args.x)) { 
				return args.e;
			}
			return expAlg().Var(rename(args.ren, s));
		};
	}
	
	@Override
	default Function<SubstArgs<Exp>,Exp> Lambda(String y, Function<SubstArgs<Exp>,Exp> e) {
		return (args) -> {
			if (y.equals(args.x)) {
				// don't substitute == substitute with identity.
				return lamAlg().Lambda(y, e.apply(args.setE(expAlg().Var(y)).setFV(Collections.singleton(y))));
			}
			
			if (!args.fv.contains(y)) {
				// no risk of capture, but don't rename across binder y
				return lamAlg().Lambda(y, e.apply(args.setRen(remove(args.ren, y))));
			}
			
			// rename lambda binder using fresh variable z.
			String z = fresh(y, args.fv); 
			return lamAlg().Lambda(z, e.apply(args.setRen(add(args.ren, y, z))));
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

