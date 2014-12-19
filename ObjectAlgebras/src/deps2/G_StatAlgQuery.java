package deps2;

import library.Monoid;
import trees.StatAlg;

//BEGIN_GSTAT_QUERY
interface G_StatAlgQuery<Exp, Stat> extends StatAlg<Exp, Stat> {
	Monoid<Exp> mExp(); Monoid<Stat> mStat();
	default Stat Assign(String p0, Exp p1) { return mStat().empty(); }
	default Stat Seq(Stat p0, Stat p1) { return mStat().join(p0, p1); }
}
//END_GSTAT_QUERY
