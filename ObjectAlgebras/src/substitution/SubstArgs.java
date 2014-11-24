package substitution;

import java.util.Map;
import java.util.Set;

public class SubstArgs<Exp> {
	public final Exp e;
	public final String x;
	public final Set<String> fv;
	public final Map<String, String> ren;

	SubstArgs(Exp e, String x, Set<String> fv, Map<String,String> ren) {
		this.e = e;
		this.x = x;
		this.fv = fv;
		this.ren = ren;
	}
	
	public SubstArgs<Exp> setE(Exp e) {
		return new SubstArgs<Exp>(e, x, fv, ren);
	}
	
	public SubstArgs<Exp> setX(String x) {
		return new SubstArgs<Exp>(e, x, fv, ren);
	}
	
	public SubstArgs<Exp> setFV(Set<String> fv) {
		return new SubstArgs<Exp>(e, x, fv, ren);
	}
	
	public SubstArgs<Exp> setRen(Map<String,String> ren) {
		return new SubstArgs<Exp>(e, x, fv, ren);
	}
	
}
