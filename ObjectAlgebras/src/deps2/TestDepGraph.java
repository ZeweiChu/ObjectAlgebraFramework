package deps2;

import trees.ExpAlg;
import trees.StatAlg;

public class TestDepGraph {

//BEGIN_GEN_DEPGRAPH
<E, S, A extends ExpAlg<E> & StatAlg<E, S>> 
  S makeStat(A a) {
	  return a.Seq(
			a.Assign("x", a.Add(a.Var("x"), a.Lit(3))), 
			a.Assign("y", a.Add(a.Var("x"), a.Var("z"))));
}
//END_GEN_DEPGRAPH

	void Test() {

//BEGIN_CLIENTCODE_DEPGRAPH
println(makeStat(new DepGraph(){}));
//END_CLIENTCODE_DEPGRAPH

	}
	
	void println(Object o) { System.out.println(o); }
	
	public static void main(String[] args) {
		TestDepGraph testDepGraph = new TestDepGraph();
		testDepGraph.Test();
	}
}
