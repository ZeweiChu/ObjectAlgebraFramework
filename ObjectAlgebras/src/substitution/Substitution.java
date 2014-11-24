package substitution;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import transform.G_ExpAlgTransform;
import transform.G_LamAlgTransform;


@SuppressWarnings("serial")
public interface Substitution<Exp> extends G_ExpAlgTransform<SubstArgs<Exp>, Exp>, G_LamAlgTransform<SubstArgs<Exp>, Exp> {
	@Override 
	default List<Exp> substList(List<Function<SubstArgs<Exp>, Exp>> list,SubstArgs<Exp> acc) {
		return null; // not used here, but overridden to avoid duplicate default method error.
	}
	
	@Override
	default Function<SubstArgs<Exp>, Exp> Var(String s) {
		return (args) -> {
			if (s.equals(args.x)) 
				return args.e;
			if (args.ren.containsKey(s)) 
				return expAlg().Var(args.ren.get(s));
			return expAlg().Var(s);
		};
	}
	
	@Override
	default Function<SubstArgs<Exp>,Exp> Lambda(String x, Function<SubstArgs<Exp>,Exp> e) {
		return (args) -> {
			if (x.equals(args.x)) 
				return lamAlg().Lambda(x, e.apply(args.setE(expAlg().Var(x)).setFV(Collections.emptySet())));
			
			if (args.fv.contains(x)) {
				String z = fresh(x, args.fv);
				return lamAlg().Lambda(z, e.apply(args.setRen(rename(args.ren, x, z))));
			}
			
			return lamAlg().Lambda(x, e.apply(args));
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

