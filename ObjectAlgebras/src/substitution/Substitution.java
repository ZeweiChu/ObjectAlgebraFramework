package substitution;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import trees.ExpAlg;
import trees.LamAlg;



class Args<Exp> {
	final Set<String> freeVars;
	final String name;
	final Exp exp;
	final Map<String,String> toRename;
	
	public Args(String name, Exp exp, Set<String> freeVars, Map<String,String> toRename) {
		this.name = name;
		this.exp = exp;
		this.freeVars = freeVars;
		this.toRename = toRename;
	}

//	public Args bind(String x) {
//		Set<String> set = new HashSet<>(bound);
//		set.add(x);
//		Args args = this;
//		return new Args(name, set) {
//			@Override
//			<E, Alg extends ExpAlg<E> & LamAlg<E>> E exp(Alg alg) {
//				return args.exp(alg);
//			}
//		};
//	}
	
}

public interface Substitution<Exp> extends ExpAlg<Function<Args<Exp>, Exp>>, LamAlg<Function<Args<Exp>, Exp>> {

	ExpAlg<Exp> expAlg();
	LamAlg<Exp> lamAlg();
	
	@Override
	default Function<Args<Exp>, Exp> Var(String s) {
		return (args) -> {
			if (s.equals(args.name)) {
				return args.exp;
			}
			if (args.toRename.containsKey(s)) {
				return expAlg().Var(args.toRename.get(s));
			}
			return expAlg().Var(s);
		};
	}
	
	@Override
	default Function<Args<Exp>, Exp> Lambda(String x, Function<Args<Exp>, Exp> e) {
		return (args) -> {
			if (x.equals(args.name)) {
				// don't subtitute == substitute with itself.
				return lamAlg().Lambda(x, e.apply(new Args<Exp>(x, expAlg().Var(x), Collections.emptySet(), args.toRename)));
			}
			
			Args<Exp> newArgs = args;
			String newName = x;
			if (args.freeVars.contains(x)) {
				// rename the lambda
				newName = fresh(x, args.freeVars);
				Map<String, String> toRename = new HashMap<>(args.toRename);
				toRename.put(x, newName);
				newArgs = new Args<Exp>(args.name, args.exp, args.freeVars, toRename);
			}
			return lamAlg().Lambda(newName, e.apply(newArgs));
		};
	}
	
	static String fresh(String x, Set<String> fv) {
		while (fv.contains(x)) {
			x = x + "_";
		}
		return x;
	}
	
}
