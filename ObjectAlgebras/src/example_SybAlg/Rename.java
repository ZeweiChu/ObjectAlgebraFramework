package example_SybAlg;

import transform.SybAlgTransform;
import trees.SybAlg;

public class Rename implements SybAlgTransform<String, String, String, String, String, String> {
	private SybAlg<String, String, String, String, String, String> alg;
	public Rename(SybAlg<String, String, String, String, String, String> alg) {this.alg = alg;}
	public SybAlg<String, String, String, String, String, String> sybAlg() {return alg;}
	public String P(String name, String address) {return alg.P("_" + name, address);}
}
