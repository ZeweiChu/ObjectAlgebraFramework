package expDemo3;

//BEGIN_GENERIC_QUERY
public interface ExpAlgQuery<Exp> extends ExpAlg<Exp> {
	Monoid<Exp> m();
	default Exp Var(String s) { 
		Exp res = m().empty();
		return res;
	}
	default Exp Lit(int i) { 
		Exp res = m().empty();
		return res;
	}
	default Exp Add(Exp e1, Exp e2) {
		Exp res = m().empty();
		res = m().join(res, e1);
		res = m().join(res, e2);
		return res;
	}
}
//END_GENERIC_QUERY
