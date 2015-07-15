package generated_code;


public class Comments {}

/*
//BEGIN_QLALGQUERY_GENERATED
public interface QLAlgQuery<R>
		extends QLAlg<R, R, R> {

	Monoid<R> m();

	default R Form(java.lang.String p0,
			java.util.List<R> p1) {
		R res = m().empty();
		res = m().join(res, m().fold(p1));
		return res;
	}

	default R Geq(R p0, R p1) {
		R res = m().empty();
		res = m().join(res, p0);
		res = m().join(res, p1);
		return res;
	}

	default R If(R p0, R p1) {
		R res = m().empty();
		res = m().join(res, p0);
		res = m().join(res, p1);
		return res;
	}

	default R Lit(int p0) {
		R res = m().empty();
		return res;
	}

	default R Question(java.lang.String p0,
			java.lang.String p1, java.lang.String p2) {
		R res = m().empty();
		return res;
	}

	default R Var(java.lang.String p0) {
		R res = m().empty();
		return res;
	}

}
//END_QLALGQUERY_GENERATED

//BEGIN_QLALGTRANSFORM_GENERATED
public interface QLAlgTransform<A0, A1, A2>
		extends QLAlg<A0, A1, A2> {

	QLAlg<A0, A1, A2> qLAlg();

	@Override
	default A2 Form(java.lang.String p0,
			java.util.List<A1> p1) {
		return qLAlg().Form(p0, p1);
	}

	@Override
	default A0 Geq(A0 p0, A0 p1) {
		return qLAlg().Geq(p0, p1);
	}

	@Override
	default A1 If(A0 p0, A1 p1) {
		return qLAlg().If(p0, p1);
	}

	@Override
	default A0 Lit(int p0) {
		return qLAlg().Lit(p0);
	}

	@Override
	default A1 Question(java.lang.String p0,
			java.lang.String p1, java.lang.String p2) {
		return qLAlg().Question(p0, p1, p2);
	}

	@Override
	default A0 Var(java.lang.String p0) {
		return qLAlg().Var(p0);
	}

}

public class QLAlgTrans<A0, A1, A2>
		implements QLAlgTransform<A0, A1, A2> {

	private QLAlg<A0, A1, A2> alg;

	public QLAlgTrans(QLAlg<A0, A1, A2> alg) {
		this.alg = alg;
	}

	public QLAlg<A0, A1, A2> qLAlg() {return alg;}

}
//END_QLALGTRANSFORM_GENERATED

//BEGIN_G_EXPALGQUERY_GENERATED
public interface G_ExpAlgQuery<A0>
		extends ExpAlg<A0> {

	Monoid<A0> mExp();

	@Override
	default A0 Add(A0 p0, A0 p1) {
		A0 res = mExp().empty();
		res = mExp().join(res, p0);
		res = mExp().join(res, p1);
		return res;
	}

	@Override
	default A0 Lit(int p0) {
		A0 res = mExp().empty();
		return res;
	}

	@Override
	default A0 Var(java.lang.String p0) {
		A0 res = mExp().empty();
		return res;
	}

}
//END_G_EXPALGQUERY_GENERATED

//BEGIN_G_EXPALG_LAMALG_TRANSFORM_GENERATED
public interface G_ExpAlgTransform<A, B0>
		extends ExpAlg<Function<A, B0>> {

	ExpAlg<B0> expAlg();

	default <B> List<B> substListExpAlg(List<Function<A, B>> list, A acc) {
		List<B> res = new ArrayList<B>();
		for (Function<A, B> i : list)
			res.add(i.apply(acc));
		return res;
	}

	@Override
	default Function<A, B0> Add(Function<A, B0> p0, Function<A, B0> p1) {
		return acc -> expAlg().Add(p0.apply(acc),
				p1.apply(acc));
	}

	@Override
	default Function<A, B0> Lit(int p0) {
		return acc -> expAlg().Lit(p0);
	}

	@Override
	default Function<A, B0> Var(java.lang.String p0) {
		return acc -> expAlg().Var(p0);
	}

}

public interface G_LamAlgTransform<A, B0>
		extends LamAlg<Function<A, B0>> {

	LamAlg<B0> lamAlg();

	default <B> List<B> substListLamAlg(List<Function<A, B>> list, A acc) {
		List<B> res = new ArrayList<B>();
		for (Function<A, B> i : list)
			res.add(i.apply(acc));
		return res;
	}

	@Override
	default Function<A, B0> Apply(Function<A, B0> p0, Function<A, B0> p1) {
		return acc -> lamAlg().Apply(p0.apply(acc),
				p1.apply(acc));
	}

	@Override
	default Function<A, B0> Lam(java.lang.String p0, Function<A, B0> p1) {
		return acc -> lamAlg().Lam(p0, p1.apply(acc));
	}

}
//END_G_EXPALG_LAMALG_TRANSFORM_GENERATED

*/
