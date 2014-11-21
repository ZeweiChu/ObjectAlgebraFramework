package _syb.lambda;

import java.util.Set;

import library.Subst;
import transform.G_LambdaAlgTransform;

public interface Substitution extends G_LambdaAlgTransform<String, Set<String>> {

	@Override 
	default Subst<String, Set<String>> lambda(String p0,Subst<String, Set<String>> p1) {
		return vars -> "x";
	}
}
