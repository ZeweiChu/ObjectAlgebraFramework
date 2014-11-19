package _syb.trafo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import noa.Builder;
import ql_obj_alg.box.IFormat;
import ql_obj_alg.format.Format;
import ql_obj_alg.format.IFormatWithPrecedence;
import ql_obj_alg.parse.TheParser;
import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IFormAlg;
import ql_obj_alg.syntax.IStmtAlg;

public class TestRenameVariable {

	@SuppressWarnings("serial")
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Builder builder = TheParser.parse(new FileInputStream(
				"resources/inputfiles/test.QL"));

		Map<String, String> ren = new HashMap<String, String>() {{
			put("privateDebt", "publicDebt");
			put("soldHouse", "didYouSellAHouse");
		}};
				

		Format algebra = new Format();
		
		IFormat pp = builder.build(new RenameVariable<IFormatWithPrecedence, IFormat, IFormat>() {

			@Override
			public IExpAlg<IFormatWithPrecedence> iExpAlg() {
				return algebra;
			}

			@Override
			public IStmtAlg<IFormatWithPrecedence, IFormat> iStmtAlg() {
				return algebra;
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
