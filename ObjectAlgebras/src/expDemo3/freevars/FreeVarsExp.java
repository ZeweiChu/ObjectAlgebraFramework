package expDemo3.freevars;

import java.util.Collections;
import java.util.Set;

import query.ExpAlgQuery;

//BEGIN_FREEVARS_WITH_MONOID_INDEP
interface FreeVarsExp extends ExpAlgQuery<Set<String>>
{
	default Set<String> Var(String s) {
		return Collections.singleton(s);
	}
}
//END_FREEVARS_WITH_MONOID
