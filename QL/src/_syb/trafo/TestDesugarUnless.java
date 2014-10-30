package _syb.trafo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;

import noa.Builder;
import ql_obj_alg.box.IFormat;
import ql_obj_alg.format.Format;
import ql_obj_alg.format.IFormatWithPrecedence;
import ql_obj_alg.parse.TheParser;
import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IFormAlg;
import ql_obj_alg.syntax.IStmtAlg;
import ql_obj_alg.syntax.IUnlessAlg;

public class TestDesugarUnless {
	
	static class Desugar implements DesugarUnless<IFormatWithPrecedence, IFormat>, FormAlgId<IFormatWithPrecedence, IFormat, IFormat> {
		private Format algebra;

		public Desugar(Format f) {
			this.algebra = f;
		}
		@Override
		public IExpAlg<IFormatWithPrecedence> alg() {
			return algebra;
		}

		@Override
		public IStmtAlg<IFormatWithPrecedence, IFormat> stmtAlg() {
			return algebra;
		}

		@Override
		public IFormAlg<IFormatWithPrecedence, IFormat, IFormat> formAlg() {
			return algebra;
		}

		@Override
		public IUnlessAlg<IFormatWithPrecedence, IFormat> unlessAlg() {
			return this; // NB!!!
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Builder builder = TheParser.parse(new FileInputStream(
				"resources/inputfiles/test.QL"));

		Format algebra = new Format();
		IFormat pp = builder.build(new Desugar(algebra));
		
		StringWriter w = new StringWriter();
		pp.format(0, true, w);
		System.out.println(w);
	}
}
