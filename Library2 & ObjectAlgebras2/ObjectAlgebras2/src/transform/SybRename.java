package transform;

import trees.SybAlg;

public class SybRename extends SybAlgTransform<String, String, String, String, String, String> {

	public SybRename(SybAlg<String, String, String, String, String, String> alg) {
		super(alg);
	}

	public String P(String name, String address) {
		return alg.P("_" + name, address);
	}
	
}
