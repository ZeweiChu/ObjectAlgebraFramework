package expDemo2;

//BEGIN_QUERY
public interface FreeVarsExpAlg extends ExpAlg<String[]>{
	@Override
	default String[] Var(String s){
		return new String[]{s};
	}
	@Override
	default String[] Lit(int i){
		return new String[]{};
	}
	@Override
	default String[] Add(String[] e1, String[] e2){
		int e1len = e1.length;
		int e2len = e2.length;
		String[] res = new String[e1len+e2len];
		System.arraycopy(e1, 0, res, 0, e1len);
		System.arraycopy(e2, 0, res, e1len, e2len);
		return res;
	}
}
//END_QUERY