package manual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import trees.FJAlg;

class Main {
	static <J, T, N, L, K, M, E, X> J genJava(FJAlg<J, T, N, L, K, M, E, X, Pair<X, N>, Pair<T, String>> alg) {
		N object = alg.ClassType("Object", Collections.emptyList());
		List<Pair<T, String>> lineFields = new ArrayList<Pair<T, String>>();
		lineFields.add(new Pair<T, String>(alg.TypeN(alg.ClassType("Point", Collections.singletonList(alg.TypeX(alg.TVar("T"))))), "p1"));
		lineFields.add(new Pair<T, String>(alg.TypeN(alg.ClassType("Point", Collections.singletonList(alg.TypeX(alg.TVar("T"))))), "p2"));
		List<Pair<T, String>> lineMethodParas = new ArrayList<Pair<T, String>>();
		lineMethodParas.add(new Pair<T, String>(alg.TypeX(alg.TVar("W")), "w0"));
		lineMethodParas.add(new Pair<T, String>(alg.TypeX(alg.TVar("W")), "w1"));
		List<E> getP1XParas = new ArrayList<E>();
		getP1XParas.add(alg.Var("w0")); getP1XParas.add(alg.Var("w1"));
		L line = alg.ClassDec("Line", Collections.singletonList(new Pair<X, N>(alg.TVar("T"), object)), object,
				lineFields, alg.ConstrDec("Line", lineFields), Collections.singletonList(alg.MethodDec(Collections.singletonList(new Pair<X, N>(alg.TVar("W"), object)), alg.TypeX(alg.TVar("W")), "getP1X", lineMethodParas,
						alg.MethodInvoke(alg.ObjectCreate(alg.ClassType("Point", Collections.singletonList(alg.TypeX(alg.TVar("W")))), getP1XParas), "getX", Collections.emptyList(), Collections.emptyList()))));
		List<Pair<T, String>> pointFields = new ArrayList<Pair<T, String>>();
		pointFields.add(new Pair<T, String>(alg.TypeX(alg.TVar("T")), "x"));
		pointFields.add(new Pair<T, String>(alg.TypeX(alg.TVar("T")), "y"));
		List<M> pointMethods = new ArrayList<M>();
		pointMethods.add(alg.MethodDec(Collections.emptyList(), alg.TypeX(alg.TVar("T")), "getX", Collections.emptyList(), alg.Var("x")));
		pointMethods.add(alg.MethodDec(Collections.emptyList(), alg.TypeX(alg.TVar("T")), "getY", Collections.emptyList(), alg.Var("y")));
		L point = alg.ClassDec("Point", Collections.singletonList(new Pair<X, N>(alg.TVar("T"), object)), object,
				pointFields, alg.ConstrDec("Point", pointFields), pointMethods);
		List<L> code = new ArrayList<L>();
		code.add(line); code.add(point);
		return alg.Code(code);
	}
	
	public static void main(String args[]) {
		PrintFJ print = new PrintFJ();
		//ManualRenameFJ<String, String, String, String, String, String, String, String> renameFJ = 
		//		new ManualRenameFJ<String, String, String, String, String, String, String, String>(print);
		//AutoRenameFJ<String, String, String, String, String, String, String, String> autoRenameFJ = 
		//		new AutoRenameFJ<String, String, String, String, String, String, String, String>(print);
		ManualFreeVariables mfv = new ManualFreeVariables();
		System.out.println(genJava(mfv));
		System.out.println(genJava(print));
	}

}