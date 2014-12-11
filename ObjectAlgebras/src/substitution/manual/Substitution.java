package substitution.manual;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import trees.ExpAlg;
import trees.LamAlg;

/*
 * This is the clean, manual implementation, i.e. without "args" objects.
 * But with boilerplate, because G_..transform only supports a single argument.
 */

interface Subst<Exp> {
	Exp subst(String x, // the variable to be substituted
			  Exp e, // the resulting expression
			  Set<String> fv, // the set of free variables of e
			  Map<String,String> ren // a rename mapping to rename uses of variables
			  );
}


public interface Substitution<Exp> extends ExpAlg<Subst<Exp>>, LamAlg<Subst<Exp>> {

	ExpAlg<Exp> expAlg();
	LamAlg<Exp> lamAlg();

	
	@Override
	default Subst<Exp> Var(String s) {
		return (x, e, fv, ren) -> {
			if (s.equals(x)) return e;
			if (ren.containsKey(s)) return expAlg().Var(ren.get(s));
			return expAlg().Var(s);
		};
	}
	
	@Override
	default Subst<Exp> Lam(String x, Subst<Exp> e) {
		return (y, to, fv, ren) -> {
			if (x.equals(y)) 
				return lamAlg().Lam(x, e.subst(x, expAlg().Var(x), Collections.emptySet(), ren));
			
			if (fv.contains(x)) {
				String z = fresh(x, fv);
				ren = new HashMap<>(ren);
				ren.put(x, z);
				return lamAlg().Lam(z, e.subst(y, to, fv, ren));
			}
			
			ren = new HashMap<>(ren);
			ren.remove(x);
			return lamAlg().Lam(x, e.subst(y, to, fv, ren));
		};
	}
	
	static String fresh(String x, Set<String> fv) {
		while (fv.contains(x)) {
			x = x + "_";
		}
		return x;
	}
	
	// The following should be inherited from G_...Transforms when they are interfaces
	
	@Override
	default Subst<Exp> Lit(int i) {
		return (x, e, fv, ren) -> {
			return expAlg().Lit(i);
		};
	}
	
	@Override
	default Subst<Exp> Add(Subst<Exp> e1, Subst<Exp> e2) {
		return (x, e, fv, ren) -> {
			return expAlg().Add(e1.subst(x, e, fv, ren), e2.subst(x, e, fv, ren));
		};
	}
	
	@Override
	default Subst<Exp> Apply(Subst<Exp> e1, Subst<Exp> e2) {
		return (x, e, fv, ren) -> {
			return lamAlg().Apply(e1.subst(x, e, fv, ren), e2.subst(x, e, fv, ren));
		};
	}
	
}

