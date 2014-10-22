package _syb;

import java.util.Collections;
import java.util.Set;

import query.IExpAlgQuery;
import library.Monoid;

public class FreeVars extends IExpAlgQuery<Set<String>>{

	public FreeVars(Monoid<Set<String>> m) {
		super(m);
	}

	
	@Override
	public Set<String> var(String s){
		return Collections.singleton(s);
	}

}
