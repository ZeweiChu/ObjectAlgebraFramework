package substitution;

import java.util.HashMap;
import java.util.Map;

public class ArgObj {

	private final Map<String, Object> args;
	
	public ArgObj() {
		this(new HashMap<>());
	}
	
	private ArgObj(Map<String, Object> args) {
		this.args = args;
	}
	
	@SuppressWarnings("unchecked")
	<X> X get(String name) {
		return (X)args.get(name);
	}
	
	<X> ArgObj set(String name, X x) {
		Map<String, Object> m = new HashMap<>(args);
		m.put(name, x);
		return new ArgObj(m);
	}
	
}
