package example_DoubleAlg2;

import trees.DoubleAlg;
import trees.ExpAlg;

public class Test {

//BEGIN_MAKE_DOUBLE_EXP
<E, Alg extends DoubleAlg<E> & ExpAlg<E>> 
  E makeExp(Alg a) {
	  return a.Add(a.Lit(5), a.Double(a.Var("a")));
  }
//END_MAKE_DOUBLE_EXP
	
	void go() {
//BEGIN_PRINT_DESUGARED
		ExpAlg<String> print = new PrintExp();
		Desugar<String> desugar = new Desugar<String>() {
			@Override
			public ExpAlg<String> expAlg() {
			  return print;
			}
		};
		System.out.println(makeExp(desugar));
//END_PRINT_DESUGARED
	}
	
	public static void main(String args[]) {
		Test t = new Test();
		t.go();
	}
	
}
