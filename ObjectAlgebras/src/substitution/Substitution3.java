package substitution;

import java.util.Collections;
import java.util.HashMap;
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


@SuppressWarnings("unchecked")
public interface Substitution3<Exp> extends G_ExpAlgTransform<ArgObj, Exp>, G_LamAlgTransform<ArgObj, Exp> {

	ExpAlg<Exp> expAlg();
	LamAlg<Exp> lamAlg();
	
	
	@Override
	default Function<ArgObj, Exp> Var(String s) {
		return (args) -> {
			if (s.equals(args.get("x"))) {
				return args.get("e");
			}
			if (((Map<String,String>)args.get("ren")).containsKey(s)) {
				return expAlg().Var(((Map<String,String>)args.get("ren")).get(s));
			}
			return expAlg().Var(s);
		};
	}
	
	@Override
	default Function<ArgObj,Exp> Lambda(String x, Function<ArgObj,Exp> e) {
		return (args) -> {
			if (x.equals(args.get("x"))) {
				// don't subtitute == substitute with itself.
				return lamAlg().Lambda(x, e.apply(args.set("e", expAlg().Var(x)).set("fv", Collections.emptySet())));
			}
			
			
			if (((Set<String>)args.get("fv")).contains(x)) {
				// rename the lambda
				String z = fresh(x, args.get("fv"));
				Map<String,String> ren = new HashMap<String,String>(args.get("ren"));
				ren.put(x, z);
				return lamAlg().Lambda(z, e.apply(args.set("ren",  ren)));
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

