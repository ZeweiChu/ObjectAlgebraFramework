package expDemo3;

//BEGIN_FREEVARS_MONOID
public class FreeVarsMonoid implements Monoid<String[]>{
	@Override
	public String[] empty() {
		return new String[]{};
	}
	@Override
	public String[] join(String[] e1, String[] e2) {
		int e1len = e1.length;
		int e2len = e2.length;
		String[] res = new String[e1len+e2len];
		System.arraycopy(e1, 0, res, 0, e1len);
		System.arraycopy(e2, 0, res, e1len, e2len);
		return res;
	}
}
//END_FREEVARS_MONOID