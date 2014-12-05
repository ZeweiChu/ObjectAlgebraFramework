package expDemo2;

public interface FreeVarsExpAlg extends ExpAlg<String[]> {
	default String[] Var(String s) {
		return new String[]{s};
	}
	default String[] Lit(int i) {
		return new String[]{};
	}
	default String[] Add(String[] e1, String[] e2) {
		int e1len = e1.length;
		int e2len = e2.length;
		String[] res = new String[e1len+e2len];
		System.arraycopy(e1, 0, res, 0, e1len);
		System.arraycopy(e2, 0, res, e1len, e2len);
		return res;
	}
}