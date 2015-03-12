package example_object_algebras;

//BEGIN_INT_ALG_INTERFACE
interface IntAlg<A> {
	A lit(int x);
	A add(A e1, A e2);}
//END_INT_ALG_INTERFACE

//BEGIN_INT_BOOL_ALG_INTERFACE
interface IntBoolAlg<A> extends IntAlg<A>{
	A bool(Boolean x);
	A iff(A e1, A e2, A e3);}
//END_INT_BOOL_ALG_INTERFACE

//BEGIN_EXP_FACTORY
interface Exp{
	public int eval();
}
class IntFactory implements IntAlg<Exp> {
	public Exp lit(int x){
		return new Exp(){
			public int eval(){return x;}
		};}
	
	public Exp add(Exp e1, Exp e2){
		return new Exp(){
			public int eval(){return e1.eval() + e2.eval();}
		};}
}
//END_EXP_FACTORY

//BEGIN_INT_PRINT
interface IPrint{String print();}
class IntPrint implements IntAlg<IPrint> {
	public IPrint lit(final int x){
		return new IPrint(){
			public String print(){return new Integer(x).toString();}
		};}
	public IPrint add(final IPrint e1, final IPrint e2) {
		return new IPrint(){
			public String print(){return e1.print() + " + " + e2.print();}
		};}
}
//END_INT_PRINT

public class ObjectAlgebras {

	//BEGIN_OA_TEST_CODE
	public static <A> A make3Plus5(IntAlg<A> alg){
		return alg.add(alg.lit(3), alg.lit(5));
	}
	public static void test(){
		IntFactory base = new IntFactory();
		IntPrint print = new IntPrint();	
		int x = make3Plus5(base).eval();
		String s = make3Plus5(print).print();
		System.out.println("int: " + x); //int: 8
		System.out.println("String: " + s); //String: 3 + 5
	}
	//END_OA_TEST_CODE
	public static void main(String[] argv){
		test();
	}
}
