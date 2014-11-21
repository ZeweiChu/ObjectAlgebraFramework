package example_SybAlg;

import java.util.List;
import java.util.function.Function;
import transform.G_SybAlgTransform;
import trees.SybAlg;

public class Rename2 implements G_SybAlgTransform<String, String> {
	private SybAlg<String, String, String, String, String, String> alg;
	public Rename2(SybAlg<String, String, String, String, String, String> alg) {this.alg = alg;}
	public SybAlg<String, String, String, String, String, String> sybAlg() {return alg;}
	public Function<String, String> D(String name, Function<String, String> manager, List<Function<String, String>> subUnits) {
		return acc -> alg.D(name, manager.apply(name), substList(subUnits, name));
	}
	public Function<String, String> P(String name, String address) {
		return acc -> alg.P(name + "(" + acc + ")", address);
	}
}
