package expDemo3;

public class FreeVarsMonoid implements Monoid<String[]> {
	public String[] empty() {
		return new String[]{};
	}
	public String[] join(String[] e1, String[] e2) {
		int e1len = e1.length;
		int e2len = e2.length;
		String[] res = new String[e1len+e2len];
		System.arraycopy(e1, 0, res, 0, e1len);
		System.arraycopy(e2, 0, res, e1len, e2len);
		return res;
	}
}
