package example_SybAlg;

import java.util.List;
import library.Subst;
import transform.G_SybAlgTransform;
import trees.SybAlg;

public class Rename2 extends G_SybAlgTransform<String, String> {

	public Rename2(SybAlg<String, String, String, String, String, String> alg) {
		super(alg);
	}
	
	@Override
	public Subst<String, String> D(String name, Subst<String, String> manager,
			List<Subst<String, String>> subUnits) {
		return acc -> alg.D(name, manager.subst(name), substList(subUnits, name));
	}

	@Override
	public Subst<String, String> P(String name, String address) {
		return acc -> alg.P(name + "(" + acc + ")", address);
	}

}
