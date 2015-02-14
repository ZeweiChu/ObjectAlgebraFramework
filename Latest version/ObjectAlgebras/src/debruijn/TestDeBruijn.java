package debruijn;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import trees.ExpAlg;
import trees.LamAlg;

public class TestDeBruijn {

	static class PrintLambdaExp implements ExpAlg<String>, LamAlg<String> {

		@Override
		public String Lam(String x, String e) {
			return "\\" + x + "." + e;
		}

		@Override
		public String Apply(String e1, String e2) {
			return "(" + e1 + " " + e2 + ")";
		}

		@Override
		public String Var(String s) {
			return s;
		}

		@Override
		public String Lit(int i) {
			return i + "";
		}

		@Override
		public String Add(String e1, String e2) {
			return "(" + e1 + " + " + e2 + ")";
		}
		
	}
	
	static class DoIt implements DeBruijn<String> {
		PrintLambdaExp alg = new PrintLambdaExp();
		
		@Override
		public ExpAlg<String> expAlg() {
			return alg;
		}
		
		@Override
		public LamAlg<String> lamAlg() {
			return alg;
		}
	}
	
	
	public static void main(String[] args) {
		DoIt d = new DoIt();
		Function<List<String>, String> f = d.Lam("x", d.Lam("y", d.Lam("z", d.Apply(d.Apply(d.Var("x"), d.Var("z")), d.Apply(d.Var("y"), d.Var("z"))))));
		String s = f.apply(Collections.emptyList());
		System.out.println(s);

		f = d.Lam("x", d.Lam("y", d.Add(d.Var("x"), d.Var("y"))));
		s = f.apply(Collections.emptyList());
		System.out.println(s);

	}
}
