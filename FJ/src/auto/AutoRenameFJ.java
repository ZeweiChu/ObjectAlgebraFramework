package auto;

import manual.Pair;
import transform.FJAlgTransform;
import trees.FJAlg;

public class AutoRenameFJ<J, T, N, L, K, M, E, X> implements FJAlgTransform<J, T, N, L, K, M, E, X, Pair<X, N>, Pair<T, String>>{

	private FJAlg<J, T, N, L, K, M, E, X, Pair<X, N>, Pair<T, String>> alg;
	public AutoRenameFJ(FJAlg<J, T, N, L, K, M, E, X, Pair<X, N>, Pair<T, String>> alg){
		this.alg = alg;
	}
	
	public FJAlg<J, T, N, L, K, M, E, X, Pair<X, N>, Pair<T, String>> fJAlg(){
		return alg;
	}
	
	@Override
	public E Var(String name) {
		return fJAlg().Var("_" + name);
	}

	
}
