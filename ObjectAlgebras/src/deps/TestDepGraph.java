package deps;

import java.util.Set;

import trees.ExpAlg;
import trees.StatAlg;
import library.Monoid;
import library.Pair;
import monoid.SetMonoid;

public class TestDepGraph {

	
	static class DoIt implements DepGraph {
		private Monoid<Set<String>> mexp;
		private Monoid<Set<Pair<String, String>>> mstat;

		public DoIt() {
			this.mexp = new SetMonoid<>();
			this.mstat = new SetMonoid<>();
		}

		@Override
		public Monoid<Set<String>> mExp() {
			return mexp;
		}
		
		@Override
		public Monoid<Set<Pair<String, String>>> mStat() {
			return mstat;
		}
	}

	static <E, S, Alg extends ExpAlg<E> & StatAlg<E, S>> S build(Alg alg) {
		return alg.Seq(alg.Assign("x", alg.Add(alg.Var("x"), alg.Lit(3))), 
				alg.Assign("y", alg.Add(alg.Var("x"), alg.Var("z"))));
	}
	
	public static void main(String[] args) {
		DoIt doIt = new DoIt();
		Set<Pair<String, String>> result = build(doIt);
		System.out.println(result);
	}
}
