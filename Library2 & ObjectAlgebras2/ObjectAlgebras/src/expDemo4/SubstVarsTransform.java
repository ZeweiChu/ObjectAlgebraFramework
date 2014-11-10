package expDemo4;

import transform.ExpAlgTransform;
import trees.ExpAlg;

class SubstVarsTransform extends ExpAlgTransform<String[]> {
	
	private String s1, s2;

	public SubstVarsTransform(ExpAlg<String[]> alg, String s1, String s2) {
		super(alg);
		this.s1 = s1;
		this.s2 = s2;
	}

	@Override
	public String[] Var(String p0) {
		if (p0.equals(s1)) return alg.Var(s2);
		return alg.Var(p0);
	}
	
}
