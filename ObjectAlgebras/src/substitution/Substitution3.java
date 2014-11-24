package substitution;

import java.util.ArrayList;
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



//interface Subst<Exp> {
//	Exp subst(String x, // the variable to be substituted
//			  Exp e, // the resulting expression
//			  Set<String> fv, // the set of free variables of e
//			  Map<String,String> ren // a rename mapping to rename uses of variables
//			  );
//}

class SubstArgs<Exp> {
	public final Exp e;
	public final String x;
	public final Set<String> fv;
	public final Map<String, String> ren;

	SubstArgs(Exp e, String x, Set<String> fv, Map<String,String> ren) {
		this.e = e;
		this.x = x;
		this.fv = fv;
		this.ren = ren;
	}
	
	public SubstArgs<Exp> setE(Exp e) {
		return new SubstArgs<Exp>(e, x, fv, ren);
	}
	
	public SubstArgs<Exp> setX(String x) {
		return new SubstArgs<Exp>(e, x, fv, ren);
	}
	
	public SubstArgs<Exp> setFV(Set<String> fv) {
		return new SubstArgs<Exp>(e, x, fv, ren);
	}
	
	public SubstArgs<Exp> setRen(Map<String,String> ren) {
		return new SubstArgs<Exp>(e, x, fv, ren);
	}
	
}


@SuppressWarnings("serial")
public interface Substitution3<Exp> extends G_ExpAlgTransform<SubstArgs<Exp>, Exp>, G_LamAlgTransform<SubstArgs<Exp>, Exp> {
	ExpAlg<Exp> expAlg();
	LamAlg<Exp> lamAlg();
	
	@Override 
	default List<Exp> substList(List<Function<SubstArgs<Exp>, Exp>> list,SubstArgs<Exp> acc) {
		return null; // not used here
	}
	
	@Override
	default Function<SubstArgs<Exp>, Exp> Var(String s) {
		return (args) -> {
			if (s.equals(args.x)) {
				return args.e;
			}
			if (args.ren.containsKey(s)) {
				return expAlg().Var(args.ren.get(s));
			}
			return expAlg().Var(s);
		};
	}
	
	@Override
	default Function<SubstArgs<Exp>,Exp> Lambda(String x, Function<SubstArgs<Exp>,Exp> e) {
		return (args) -> {
			if (x.equals(args.x)) {
				// don't subtitute == substitute with itself.
				return lamAlg().Lambda(x, e.apply(args.setE(expAlg().Var(x)).setFV(Collections.emptySet())));
			}
			
			
			if (args.fv.contains(x)) {
				// rename the lambda
				String z = fresh(x, args.fv);
				Map<String,String> ren = new HashMap<String,String>(args.ren) {{
					put(x, z);
				}};
				return lamAlg().Lambda(z, e.apply(args.setRen(ren)));
			}
			return lamAlg().Lambda(x, e.apply(args));
		};
	}
	
	static String fresh(String x, Set<String> fv) {
		while (fv.contains(x)) {
			x = x + "_";
		}
		return x;
	}
	
}

