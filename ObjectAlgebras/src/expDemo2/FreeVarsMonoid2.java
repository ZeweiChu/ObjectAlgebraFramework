package expDemo2;

import library.Monoid;

public class FreeVarsMonoid2 implements Monoid<FreeVars2>{

	@Override
	public FreeVars2 empty() {
		return new FreeVars2(){
			@Override
			public String[] freeVars() {
				return new String[]{};
			}
		};
	}

	@Override
	public FreeVars2 join(FreeVars2 e1, FreeVars2 e2) {
		return new FreeVars2(){
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
