package expDemo3.unique;

import trees.ExpAlg;
import trees.LamAlg;

public class TestUniqueWithLambdaIndep {

	//BEGIN_COMBINE_UNIQUES
	interface UniqueExpLam<E> extends
	  Unique<E>, UniqueLam<E> { }
	//END_COMBINE_UNIQUES
	
	
	static class UniquesImpl<E> implements UniqueExpLam<E> {
		private int count = 0;
		private final ExpAlg<E> expAlg;
		private final LamAlg<E> lamAlg;
		
		public <Alg extends ExpAlg<E> & LamAlg<E>> UniquesImpl(Alg alg) {
			this.expAlg = alg;
			this.lamAlg = alg;
		}
		
		@Override
		public int nextInt() {
			return count++;
		}

		@Override
		public ExpAlg<E> expAlg() {
			return expAlg;
		}

		
		@Override
		public LamAlg<E> lamAlg() {
			return lamAlg;
		}
	}
	
	static class Printer implements ExpAlg<String>, LamAlg<String> {

		@Override
    public String Lam(String x, String e) {
	    return "L" + x + "." + e;
    }

		@Override
    public String Apply(String e1, String e2) {
	    return "(" + e1 + " " + e2 + ")";
    }

		@Override
    public String Var(String s) {
	    return s;
    }

		@Override
    public String Lit(int i) {
	    return "" + i;
    }

		@Override
    public String Add(String e1, String e2) {
			return "(" + e1 + " + " + e2 + ")";
    }
		
	}
	
	public static void main(String[] args) {
		UniquesImpl<String> unq = new UniquesImpl<>(new Printer());
		String term = unq.Lam("x", unq.Add(unq.Var("x"), unq.Var("y")));
		System.out.println(term);
	}
}
