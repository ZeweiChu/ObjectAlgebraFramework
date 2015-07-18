package example_DoubleAlg2;

import library.Monoid;
import query.ExpAlgQuery;

public class PrintExp implements ExpAlgQuery<String> {

	public String Var(String s) {
		return s;
	}

	public String Lit(int i) {
		return "" + i;
	}

	public String Add(String e1, String e2) {
		return "(" + e1 + " + " + e2 + ")";
	}

	public Monoid<String> m() {
		return new Monoid<String>(){
			public String empty() { return ""; }
			public String join(String s1, String s2) { return s1 + " " + s2; }
		};
	}
	
}
