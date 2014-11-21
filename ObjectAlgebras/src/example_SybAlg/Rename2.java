package example_SybAlg;

import java.util.List;

import library.Subst;
import transform.G_SybAlgTransform;
import trees.SybAlg;

public class Rename2 implements G_SybAlgTransform<String, String> {
	private SybAlg<String, String, String, String, String, String> alg;
	public Rename2(SybAlg<String, String, String, String, String, String> alg) {this.alg = alg;}
	public SybAlg<String, String, String, String, String, String> sybAlg() {return alg;}
	public Subst<String, String> D(String name, Subst<String, String> manager, List<Subst<String, String>> subUnits) {
		return acc -> alg.D(name, manager.subst(name), substList(subUnits, name));
	}
	public Subst<String, String> P(String name, String address) {
		return acc -> alg.P(name + "(" + acc + ")", address);
	}
}
