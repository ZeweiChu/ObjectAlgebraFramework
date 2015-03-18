package example_object_algebras;

//BEGIN_INT_ALG_INTERFACE
interface ExpAlg<E> {
	E lit(int n);
	E add(E l, E r);
}
//END_INT_ALG_INTERFACE

//BEGIN_INT_BOOL_ALG_INTERFACE
interface MulAlg<E> extends ExpAlg<E> {
	E mul(E l, E r);
}
//END_INT_BOOL_ALG_INTERFACE

//BEGIN_MUL_EVAL
class MulEval extends Eval 
 implements MulAlg<Integer> {
	public Integer mul(Integer l, Integer r) {
		return l * r;
	}
}
//END_MUL_EVAL


//BEGIN_EXP_FACTORY
class Eval implements ExpAlg<Integer> {
	public Integer lit(int n) {
		return n; 
	}
	public Integer add(Integer l, Integer r) {
		return l + r;
	}
}
//END_EXP_FACTORY

//BEGIN_INT_PRINT
class Print implements ExpAlg<String> {
	public String lit(int n){
		return "" + n; 
	}
	public String add(String l, String r) {
		return l + " + " + r;
	}
}
//END_INT_PRINT

public class ObjectAlgebras {

	//BEGIN_OA_TEST_CODE
	public static <E> E make3Plus5(ExpAlg<E> alg){
		return alg.add(alg.lit(3), alg.lit(5));
	}
	public static void test(){
		Eval eval = new Eval();
		Print print = new Print();	
		int x = make3Plus5(eval);
		String s = make3Plus5(print);
		System.out.println("int: " + x); //int: 8
		System.out.println("String: " + s); //String: 3 + 5
	}
	//END_OA_TEST_CODE
	public static void main(String[] argv){
		test();
	}
}
