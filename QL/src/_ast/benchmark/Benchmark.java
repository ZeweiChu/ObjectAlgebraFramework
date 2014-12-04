package _ast.benchmark;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import library.Monoid;
import library.Pair;
import monoids.MapMonoid;
import monoids.SetMonoid;
import noa.Builder;
import ql_obj_alg.check.types.Type;
import ql_obj_alg.parse.TheParser;
import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IFormAlg;
import ql_obj_alg.syntax.IStmtAlg;
import ql_obj_alg.util.GenerateBinarySearchForm;
import _ast.BuildExpAST;
import _ast.BuildFormAST;
import _ast.BuildStmtAST;
import _ast.Form;
import _syb.query.ControlDepGraph;
import _syb.query.TypeEnv;

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
			results.add(x.build(alg)); // building is computing the result
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
				results.add(m.invoke(f, args));
				long nAfter = System.nanoTime();
				double time = (1.0 * (nAfter - nBefore)) / 1000000000.0;
				System.out.println(src.length() + ", " +  String.format("%f", time));
				output.println(src.length() + ", " +  String.format("%f", time));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				throw new RuntimeException(e);
			}
			finally {
				output.close();
			}
		}
		return results;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		List<Object> r1, r2;
		
		r1 = benchmarkAST(0, 1000, 100, "controlDeps", "controlDeps", new Class<?>[] {}, new Object[] {});
		r2 = benchmarkAlg(0, 1000, 100, "controlDeps", new ControlDepGraph() {
			@Override
			public Monoid<Set<String>> mE() {
				return new SetMonoid<>();
			}
			
			@Override
			public Monoid<Set<Pair<String,String>>> mF() {
				return new SetMonoid<>();
			}
			
			@Override
			public Monoid<Set<Pair<String,String>>> mS() {
				return new SetMonoid<>();
			}
		});
		
		System.out.println("Result equal: " + r1.equals(r2));
		
		r1 = benchmarkAST(0, 1000, 100, "typeEnv", "typeEnv", new Class<?>[] {}, new Object[] {});
		r2 = benchmarkAlg(0, 1000, 100, "typeEnv", new TypeEnv() {
			@Override
			public Monoid<Map<String, Type>> m() {
				return new MapMonoid<>();
			}
		});		
		
		System.out.println("Result equal: " + r1.equals(r2));
		
	}
}
