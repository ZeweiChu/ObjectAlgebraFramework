package _ast.benchmark;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import noa.Builder;
import ql_obj_alg.format.Format;
import ql_obj_alg.parse.TheParser;
import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IFormAlg;
import ql_obj_alg.syntax.IStmtAlg;
import ql_obj_alg.util.GenerateBinarySearchForm;
import _ast.BuildExpAST;
import _ast.BuildFormAST;
import _ast.BuildStmtAST;
import _ast.Form;
import _syb.query.TestControlDepGraph;
import _syb.query.TestTypeEnv;
import _syb.trafo.TestRenameVariable;
import _syb.trafo.TestRenameVariable.DoIt;

public class Benchmark  {

	private static <E,S,F, Alg extends IExpAlg<E> & IStmtAlg<E, S> & IFormAlg<E, S, F>> List<Object> benchmarkAlg(int min, int max, int step, String label, Alg alg) throws FileNotFoundException {
		GenerateBinarySearchForm gen = new GenerateBinarySearchForm(min, max, step);
		gen = new GenerateBinarySearchForm(min, max, step);
		System.out.println("Benchmarking algebra implementation " + label + " (min = " + min + ", max = " + max + ", step = " + step + ")");
		PrintStream output = new PrintStream(new File("resources/benchmark/with-alg-" + min + "-" + max + "-" + step + "-" + label + ".csv"));
		output.println("size, seconds");
		List<Object> results = new ArrayList<>();
		for (String src: gen) {
			Builder x = TheParser.parse(src);
			long nBefore = System.nanoTime();
			x.build(alg); // building is computing the result
			long nAfter = System.nanoTime();
			double time = (1.0 * (nAfter - nBefore)) / 1000000000.0;
			System.out.println(src.length() + ", " +  String.format("%f", time));
			output.println(src.length() + ", " +  String.format("%f", time));
		}
		output.close();
		return results;
	}
	
	
	static class ASTBuilder implements BuildExpAST, BuildStmtAST, BuildFormAST { 
		
	}
	
	private static List<Object> benchmarkAST(int min, int max, int step, String label, String method, Class<?>[] types, Object[] args) throws FileNotFoundException {
		GenerateBinarySearchForm gen = new GenerateBinarySearchForm(min, max, step);
		gen = new GenerateBinarySearchForm(min, max, step);
			
		System.out.println("Benchmarking AST implementation " + label + " (min = " + min + ", max = " + max + ", step = " + step + ")");
		
		PrintStream output = new PrintStream(new File("resources/benchmark/with-ast-" + min + "-" + max + "-" + step + "-" + label + ".csv"));
		output.println("size, seconds");
		List<Object> results = new ArrayList<>();
		for (String src: gen) {
			Builder x = TheParser.parse(src);
			Form f = (Form)x.build(new ASTBuilder());
			try {
				Method m = f.getClass().getMethod(method, types);
				long nBefore = System.nanoTime();
				m.invoke(f, args);
				long nAfter = System.nanoTime();
				double time = (1.0 * (nAfter - nBefore)) / 1000000000.0;
				System.out.println(src.length() + ", " +  String.format("%f", time));
				output.println(src.length() + ", " +  String.format("%f", time));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				output.close();
				throw new RuntimeException(e);
			}
			
		}
		output.close();
		return results;
	}
	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		//benchmarkControlDeps();
		benchmarkTypeEnv();
//		benchmarkRename();
	}

	private static void benchmarkRename() throws FileNotFoundException {
		// x_14_15 -> bla
		
		Format algebra = new Format();
		Map<String, String> ren = new HashMap<String, String>();
		ren.put("x_14_15", "BLA");
				
		
		DoIt renamer = new TestRenameVariable.DoIt(ren, algebra);

		benchmarkAST(0, 10000, 100, "rename", "rename", new Class<?>[] {Map.class}, new Object[] {ren});
		benchmarkAlg(0, 10000, 100, "rename", renamer);
		
//		assert r1.size() == r2.size();
//		
//		for (int i = 0; i < r1.size(); i++) {
//			Form f = (Form) r1.get(i);
//			IFormat format1 = f.recons(algebra, algebra, algebra);
//			StringWriter writer1 = new StringWriter();
//			format1.format(0, true, writer1);
//			
//			IFormat format2 = (IFormat)r2.get(i);
//			StringWriter writer2 = new StringWriter();
//			format2.format(0, true, writer2);
//			
//			String src1 = writer1.toString();
//			String src2 = writer2.toString();
//			if (!src1.equals(src2)) {
//				System.err.println("Not equal!!! " + i);
//				System.err.println("FIRST: ---------");
//				System.err.println(src1);
//				System.err.println("SECOND: --------------");
//				System.err.println(src2);
//				System.exit(1);
//			}
//		}
//		System.out.println("Results equal.");
	}

	private static void benchmarkNoOp() throws FileNotFoundException {
		Format algebra = new Format();

		benchmarkAST(0, 10000, 100, "noop", "recons", new Class<?>[] {IExpAlg.class, IStmtAlg.class, IFormAlg.class}, 
				new Object[] {algebra, algebra, algebra});
		benchmarkAlg(0, 10000, 100, "noop", algebra);
	}
	
	private static void benchmarkTypeEnv() throws FileNotFoundException {
		List<Object> r1;
		List<Object> r2;
		r1 = benchmarkAST(0, 10000, 100, "typeEnv", "typeEnv", new Class<?>[] {}, new Object[] {});
		r2 = benchmarkAlg(0, 10000, 100, "typeEnv", new TestTypeEnv.DoIt());
		
		System.out.println("Result equal: " + r1.equals(r2));
	}

	private static void benchmarkControlDeps() throws FileNotFoundException {
		List<Object> r1, r2;
		
		r1 = benchmarkAST(0, 10000, 100, "controlDeps", "controlDeps", new Class<?>[] {}, new Object[] {});
		r2 = benchmarkAlg(0, 10000, 100, "controlDeps", new TestControlDepGraph.DoIt());
		
		System.out.println("Result equal: " + r1.equals(r2));
	}
}
