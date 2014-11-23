package example_StmAlg;

import transform.StmAlgTransform;
import trees.StmAlg;

public class Rename implements StmAlgTransform<String, String, String> {
	private StmAlg<String, String, String> alg;
	public Rename(StmAlg<String, String, String> alg) {this.alg = alg;}
	@Override
	public StmAlg<String, String, String> stmAlg() {return alg;}
	@Override
	public String EVar(String v) {return stmAlg().EVar("_" + v);}
	@Override
	public String SAss(String v, String e) {return stmAlg().SAss("_" + v, e);}
	@Override
	public String SDecl(String t, String v) {return stmAlg().SDecl(t, "_" + v);}
}
