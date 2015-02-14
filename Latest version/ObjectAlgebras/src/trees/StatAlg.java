package trees;

import com.zewei.annotation.processor.Algebra;

@Algebra
//BEGIN_STATALG
public interface StatAlg<Exp, Stat> {
	Stat Seq(Stat s1, Stat s2);
	Stat Assign(String x, Exp e);
}
//END_STATALG
