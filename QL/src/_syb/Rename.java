package _syb;


import generic.G_IExpAlg_E;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import noa.Builder;
import noa.Union;
import ql_obj_alg.box.IFormat;
import ql_obj_alg.check.types.Type;
import ql_obj_alg.format.ExprFormat;
import ql_obj_alg.format.ExprPrecedence;
import ql_obj_alg.format.FormFormat;
import ql_obj_alg.format.StmtFormat;
import ql_obj_alg.parse.TheParser;
import ql_obj_alg.syntax.IAllAlg;
import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IStmtAlg;
import transform.IExpAlgTransform;

public class Rename {

	static String rename(Map<String, String> ren, String old) {
		String newName = old;
		if (ren.containsKey(old)) {
			newName = ren.get(old);
			System.err.println("Renaming " + old + " to " + newName);
		}
		return newName;
	}
	
	
	// TODO: turn into one class that implements both IExpAlgTransform, 
	// IStmtAlgTransformManySorted and IFormAlgTransformManySorted.
	
	static class RenameExp implements IExpAlgTransform {

		private final Map<String, String> renaming;

		public RenameExp(Map<String, String> renaming) {
			this.renaming = renaming;
		}

		@Override
		public G_IExpAlg_E var(String x) {
			return new G_IExpAlg_E() {
				@Override
				public <E> E accept(IExpAlg<E> alg) {
					return alg.var(rename(renaming, x));
				}
			};
		}
		
	}
	
	static class RenameStmt implements IStmtAlgTransformManySorted {
		private final Map<String, String> renaming;

		public RenameStmt(Map<String, String> renaming) {
			this.renaming = renaming;
		}

		@Override
		public G_IStmtAlg_S question(String p0, String p1, Type p2) {
			return new G_IStmtAlg_S() {
				@Override
				public <E, S> S accept(IExpAlg<E> expAlg, IStmtAlg<E, S> alg) {
					return alg.question(rename(renaming, p0), p1, p2);
				}
				
			};
		}

		@Override
		public G_IStmtAlg_S question(String p0, String p1, Type p2, G_IExpAlg_E p3) {
			return new G_IStmtAlg_S() {
				@Override
				public <E, S> S accept(IExpAlg<E> expAlg, IStmtAlg<E, S> alg) {
					return alg.question(rename(renaming, p0), p1, p2, p3.accept(expAlg));
				}
			};
		}
	}

	@SuppressWarnings("serial")
	public static void main(String[] args) throws FileNotFoundException,
	IOException {
		Builder builder = TheParser.parse(new FileInputStream(
				"resources/inputfiles/test.QL"));

		Map<String, String> ren = new HashMap<String, String>() {{
			put("privateDebt", "publicDebt");
			put("soldHouse", "didYouSellAHouse");
		}};
				

		IExpAlgTransform e = new RenameExp(ren);
		IStmtAlgTransformManySorted s = new RenameStmt(ren);
		IFormAlgTransformManySorted f = new IFormAlgTransformManySorted() { };

		G_IFormAlg_F trafo = builder.build(Union.union(IAllAlg.class, f, s, e));

		FormFormat fFormat = new FormFormat();
		StmtFormat sFormat = new StmtFormat();
		ExprPrecedence prec = new ExprPrecedence();
		ExprFormat<ExprPrecedence> eFormat = new ExprFormat<ExprPrecedence>(prec);

		StringWriter w = new StringWriter();
		IFormat pp = trafo.accept(eFormat, sFormat, fFormat);
		pp.format(0, true, w);
		System.out.println(w);
	}

}
