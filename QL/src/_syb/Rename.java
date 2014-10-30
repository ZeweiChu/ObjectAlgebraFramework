package _syb;


import generic.G_IExpAlg_E;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import noa.Builder;
import ql_obj_alg.box.IFormat;
import ql_obj_alg.check.types.Type;
import ql_obj_alg.format.Format;
import ql_obj_alg.parse.TheParser;
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
	
	interface RenameExp extends IExpAlgTransform {

		Map<String, String> renaming();


		@Override
		default G_IExpAlg_E var(String x) {
			return new G_IExpAlg_E() {
				@Override
				public <E> E accept(IExpAlg<E> alg) {
					return alg.var(rename(renaming(), x));
				}
			};
		}
		
	}
	
	interface RenameStmt extends IStmtAlgTransformManySorted {
		Map<String, String> renaming();

		@Override
		default G_IStmtAlg_S question(String p0, String p1, Type p2) {
			return new G_IStmtAlg_S() {
				@Override
				public <E, S> S accept(IExpAlg<E> expAlg, IStmtAlg<E, S> alg) {
					return alg.question(rename(renaming(), p0), p1, p2);
				}
				
			};
		}

		@Override
		default G_IStmtAlg_S question(String p0, String p1, Type p2, G_IExpAlg_E p3) {
			return new G_IStmtAlg_S() {
				@Override
				public <E, S> S accept(IExpAlg<E> expAlg, IStmtAlg<E, S> alg) {
					return alg.question(rename(renaming(), p0), p1, p2, p3.accept(expAlg));
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
				

		class Trafo implements RenameExp, RenameStmt, IFormAlgTransformManySorted {
			
			@Override
			public Map<String, String> renaming() {
				return ren;
			}
			
		}

		G_IFormAlg_F trafo = builder.build(new Trafo());

		Format algebra = new Format();
		StringWriter w = new StringWriter();
		IFormat pp = trafo.accept(algebra, algebra, algebra);
		pp.format(0, true, w);
		System.out.println(w);
	}

}
