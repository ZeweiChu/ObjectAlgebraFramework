package transform;

import trees.StmAlg;

public class StmRename extends StmAlgTransform<String, String, String> {

	public StmRename(StmAlg<String, String, String> alg) {
		super(alg);
	}

	@Override
	public String EVar(String v) {
		return alg.EVar("_" + v);
	}

	@Override
	public String SAss(String v, String e) {
		return alg.SAss("_" + v, e); 
	}

	@Override
	public String SDecl(String t, String v) {
		return super.SDecl(t, "_" + v);
	}
	
}