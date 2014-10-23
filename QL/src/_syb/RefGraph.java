package _syb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import library.Monoid;
import library.Pair;
import monoids.SetMonoid;
import noa.Builder;
import noa.Union;
import ql_obj_alg.check.types.Type;
import ql_obj_alg.parse.TheParser;
import ql_obj_alg.syntax.IAllAlg;
import query.IExpAlgQuery;
import query.IFormAlgQuery;
import query.IStmtAlgQuery;

public class RefGraph {
	/*
	 * Construct the reference graph (use -> def) of a QL program.
	 * 
	 * NB: this needs some kind of identity to distinguish different use sites of the same variable.
	 * 
	 * It works in two steps, first collect definitions (defs: rel[string,integer]),
	 * then collect the uses (uses: rel[integer, string]).
	 * The reference graph is then constructed using relational composition: uses o defs.
	 */


	static class Definitions extends IStmtAlgQuery<Set<Pair<String, Integer>>> {
		private int id = 0;
		
		public Definitions(Monoid<Set<Pair<String, Integer>>> m) {
			super(m);
		}
		
		@Override
		public Set<Pair<String, Integer>> question(String name, String p1, Type p2) {
			return Collections.singleton(new Pair<>(name, id++));
		}
		
		@Override
		public Set<Pair<String, Integer>> question(String name, String p1, Type p2, Set<Pair<String, Integer>> p3) {
			return Collections.singleton(new Pair<>(name, id++));
		}
	}

	static class Uses extends IExpAlgQuery<Set<Pair<Integer, String>>> {
		private int id = 0;
		
		public Uses(Monoid<Set<Pair<Integer, String>>> m) {
			super(m);
		}
		
		@Override
		public Set<Pair<Integer, String>> var(String p0) {
			return Collections.singleton(new Pair<>(id--, p0));
		}
	
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Builder builder = TheParser.parse(new FileInputStream("resources/inputfiles/test.QL"));
		
		// Definitions:
		SetMonoid<Pair<String, Integer>> m = new SetMonoid<>();
		IExpAlgQuery<Set<Pair<String, Integer>>> ed = new IExpAlgQuery<>(m);
		IFormAlgQuery<Set<Pair<String,Integer>>> fd = new IFormAlgQuery<>(m);
		IStmtAlgQuery<Set<Pair<String,Integer>>> sd = new Definitions(m);
		
		Set<Pair<String,Integer>> defs = builder.build(Union.union(IAllAlg.class, fd, sd, ed));
		System.out.println(defs);
		
		// Uses
		SetMonoid<Pair<Integer, String>> mu = new SetMonoid<>();
		IExpAlgQuery<Set<Pair<Integer, String>>> eu = new Uses(mu);
		IStmtAlgQuery<Set<Pair<Integer, String>>> su = new IStmtAlgQuery<>(mu);
		IFormAlgQuery<Set<Pair<Integer, String>>> fu = new IFormAlgQuery<>(mu);
		
		Set<Pair<Integer,String>> uses = builder.build(Union.union(IAllAlg.class, fu, su, eu));
		System.out.println(uses);
	}
}
