package _syb.trafo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import noa.Builder;
import ql_obj_alg.box.IFormat;
import ql_obj_alg.check.types.Type;
import ql_obj_alg.format.Format;
import ql_obj_alg.format.IFormatWithPrecedence;
import ql_obj_alg.parse.TheParser;
import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IFormAlg;
import ql_obj_alg.syntax.IStmtAlg;


public class TestFlatten {

	static class DoIt implements Flatten<IFormatWithPrecedence, IFormat> {
		private Format format;
		
		public DoIt(Format f) {
			this.format = f;
		}
		
		@Override
		// TODO: the first argument here is wrong...
		public IFormAlg<Function<IFormatWithPrecedence, List<IFormat>>, IFormatWithPrecedence, List<IFormat>> iFormAlg() {
			return null;
		}

		@Override
		public IStmtAlg<IFormatWithPrecedence, List<IFormat>> iStmtAlg() {
			return format;
		}


		@Override
		public IExpAlg<IFormatWithPrecedence> iExpAlg() {
			return format;
		}

		
	}
	
	@SuppressWarnings("serial")
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Builder builder = TheParser.parse(new FileInputStream(
				"resources/inputfiles/test.QL"));


		Format algebra = new Format();
		
		IFormat pp = builder.build(new Flatten<IFormatWithPrecedence, IFormat>() {

			@Override
			public IExpAlg<IFormatWithPrecedence> iExpAlg() {
				return algebra;
			}

			@Override
			public IStmtAlg<IFormatWithPrecedence, List<IFormat>> iStmtAlg() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public IFormAlg<IFormatWithPrecedence, IFormat, IFormat> iFormAlg() {
				return algebra;
			}

			
			@Override
			public Map<String, String> renaming() {
				return ren;
			}
		});

		StringWriter w = new StringWriter();
		pp.format(0, true, w);
		System.out.println(w);
	}
}
