package expDemo3;

public class LitQueryExpAlg extends QueryExpAlg<Integer>{
	public LitQueryExpAlg(Monoid<Integer> m) {super(m);}
	public Integer Lit(int i){return i;}
}
