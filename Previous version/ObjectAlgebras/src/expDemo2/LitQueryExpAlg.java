package expDemo2;

public class LitQueryExpAlg implements ExpAlg<Integer>{
	@Override
	public Integer Var(String s){
		return 0;
	}
	@Override
	public Integer Lit(int i){
		return i;
	}
	@Override
	public Integer Add(Integer e1, Integer e2){
		return e1+e2;
	}
}
