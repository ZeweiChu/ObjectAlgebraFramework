package deps2;

import java.util.Set;

import trees.ExpAlg;
import trees.StatAlg;
import library.Monoid;
import library.Pair;
import monoid.SetMonoid;

public class TestDepGraph {

//BEGIN_GEN_DEPGRAPH
<E, S, A extends ExpAlg<E> & StatAlg<E, S>>
		S makeExp(A alg) {
	return alg.Seq(
			alg.Assign("x", alg.Add(alg.Var("x"),
					alg.Lit(3))), 
			alg.Assign("y", alg.Add(alg.Var("x"),
					alg.Var("z"))));
}
//END_GEN_DEPGRAPH

	void Test() {


DepGraph depGraph = new DepGraph() {
	public Monoid<Set<String>> mExp() { return new SetMonoid<>(); }
	public Monoid<Set<Pair<String, String>>> mStat() { return new SetMonoid<>(); }
};
//BEGIN_CLIENTCODE_DEPGRAPH
println(makeExp(depGraph));
//END_CLIENTCODE_DEPGRAPH

	}
	
	void println(Object o) { System.out.println(o); }
	
	public static void main(String[] args) {
		TestDepGraph testDepGraph = new TestDepGraph();
		testDepGraph.Test();
	}
}
