package expDemo1;

import trees.ExpAlg;

public interface FreeVarsExpAlg1 extends ExpAlg<FreeVars1>{
	@Override
	default FreeVars1 Var(String s){
		return new FreeVars1(){
			@Override
			public String[] freeVars() {
				return new String[]{s};
			}
		};
	}
	@Override
	default FreeVars1 Lit(int i){
		return new FreeVars1(){
			@Override
			public String[] freeVars() {
				return new String[]{};
			}
		};
	}
	@Override
	default FreeVars1 Add(FreeVars1 e1, FreeVars1 e2){
		return new FreeVars1(){
			@Override
			public String[] freeVars() {
				String[] s1 = e1.freeVars();
				String[] s2 = e2.freeVars();
				int s1len = s1.length;
				int s2len = s2.length;
				String[] res = new String[s1len+s2len];
				System.arraycopy(s1, 0, res, 0, s1len);
				System.arraycopy(s2, 0, res, s1len, s2len);
				return res;
			}
		};
	}
}
