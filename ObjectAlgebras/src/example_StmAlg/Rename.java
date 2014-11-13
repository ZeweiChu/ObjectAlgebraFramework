package example_StmAlg;

import transform.StmAlgTransform;
import trees.StmAlg;

public class Rename extends StmAlgTransform<String, String, String> {

	public Rename(StmAlg<String, String, String> alg) {
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
