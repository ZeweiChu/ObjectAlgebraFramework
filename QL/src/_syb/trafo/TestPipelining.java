package _syb.trafo;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

import ql_obj_alg.box.IFormat;
import ql_obj_alg.check.types.TBoolean;
import ql_obj_alg.format.Format;
import ql_obj_alg.format.IFormatWithPrecedence;
import ql_obj_alg.syntax.IExpAlg;
import ql_obj_alg.syntax.IFormAlg;
import ql_obj_alg.syntax.IRepeatAlg;
import ql_obj_alg.syntax.IStmtAlg;
import ql_obj_alg.syntax.IUnlessAlg;
import transform.IFormAlgTransform;

public class TestPipelining {
  // desugar unless, desugar repeat, flatten
	
	// does not work, duplicate default methods...
//	static class DesugarBoth<E, S, F> implements DesugarUnless<E, S>,  DesugarRepeat<E, S, F>, IFormAlgTransform<E, S, F> {
//		private IExpAlg<E> expAlg;
//		private IStmtAlg<E, S> stmtAlg;
//		private IFormAlg<E, S, F> formAlg;
//
//		public DesugarBoth(IExpAlg<E> expAlg, IStmtAlg<E, S> stmtAlg, IFormAlg<E, S, F> formAlg) {
//			this.expAlg = expAlg;
//			this.stmtAlg = stmtAlg;
//			this.formAlg = formAlg;
//		}
//		
//		@Override
//		public IExpAlg<E> iExpAlg() { return expAlg; }
//
//		@Override
//		public IUnlessAlg<E, S> iUnlessAlg() { return null; }
//
//		@Override
//		public IRepeatAlg<S> iRepeatAlg() { return null; }
//
//		@Override
//		public IFormAlg<E, S, F> iFormAlg() { return formAlg; }
//
//		@Override
//		public IStmtAlg<E, S> iStmtAlg() { return stmtAlg; }
//	}
	
	public static class DoItRename<E, S, F> implements RenameVariable<E, S>, IFormAlgTransform<E, S, F> {
		private Map<String, String> renaming;

		private IExpAlg<E> expAlg;
		private IStmtAlg<E, S> stmtAlg;
		private IFormAlg<E, S, F> formAlg;

		public DoItRename(Map<String, String> renaming, IExpAlg<E> expAlg, IStmtAlg<E, S> stmtAlg, IFormAlg<E, S, F> formAlg) {
			this.renaming = renaming;
			this.expAlg = expAlg;
			this.stmtAlg = stmtAlg;
			this.formAlg = formAlg;
		}
		@Override
		public IExpAlg<E> iExpAlg() { return expAlg; }

		@Override
		public IFormAlg<E, S, F> iFormAlg() { return formAlg; }

		@Override
		public IStmtAlg<E, S> iStmtAlg() { return stmtAlg; }
		
		
		public Map<String,String> renaming() { return renaming; }

	}
	
	static class DoItUnless<E, S, F> implements DesugarUnless<E, S>, IFormAlgTransform<E, S, F> {
		private IExpAlg<E> expAlg;
		private IStmtAlg<E, S> stmtAlg;
		private IFormAlg<E, S, F> formAlg;

		public DoItUnless(IExpAlg<E> expAlg, IStmtAlg<E, S> stmtAlg, IFormAlg<E, S, F> formAlg) {
			this.expAlg = expAlg;
			this.stmtAlg = stmtAlg;
			this.formAlg = formAlg;
		}
		@Override
		public IExpAlg<E> iExpAlg() { return expAlg; }

		@Override
		public IUnlessAlg<E, S> iUnlessAlg() { return null; }

		@Override
		public IFormAlg<E, S, F> iFormAlg() { return formAlg; }

		@Override
		public IStmtAlg<E, S> iStmtAlg() { return stmtAlg; }
	}
	
	static class DoItRepeat<E, S, F> implements DesugarRepeat<E, S, F> {
		private IExpAlg<E> expAlg;
		private IStmtAlg<E, S> stmtAlg;
		private IFormAlg<E, S, F> formAlg;

		public DoItRepeat(IExpAlg<E> expAlg, IStmtAlg<E, S> stmtAlg, IFormAlg<E, S, F> formAlg) {
			this.expAlg = expAlg;
			this.stmtAlg = stmtAlg;
			this.formAlg = formAlg;
		}
		@Override
		public IExpAlg<E> iExpAlg() { return expAlg; }

		@Override
		public IRepeatAlg<S> iRepeatAlg() { return null; }

		@Override
		public IFormAlg<E, S, F> iFormAlg() { return formAlg; }

		@Override
		public IStmtAlg<E, S> iStmtAlg() { return stmtAlg; }
		
	}
	
	public static class DoItInline<E, S, F> implements InlineConditions<E, S, F> {
		private IExpAlg<E> expAlg;
		private IStmtAlg<E, S> stmtAlg;
		private IFormAlg<E, S, F> formAlg;

		public DoItInline(IExpAlg<E> expAlg, IStmtAlg<E, S> stmtAlg, IFormAlg<E, S, F> formAlg) {
			this.expAlg = expAlg;
			this.stmtAlg = stmtAlg;
			this.formAlg = formAlg;
		}
		
		@Override
		public IExpAlg<E> iExpAlg() { return expAlg; }

		@Override
		public IFormAlg<E, S, F> iFormAlg() { return formAlg; }

		@Override
		public IStmtAlg<E, S> iStmtAlg() { return stmtAlg; }

	}
	
	public static void main(String[] args) {
		Format base = new Format();
		
		
		DoItRename<IFormatWithPrecedence, IFormat, IFormat> d4 = new DoItRename<>(Collections.singletonMap("x",  "y"), base, base, base);
		DoItInline<IFormatWithPrecedence, IFormat, IFormat> d3 = new DoItInline<>(d4, d4, d4);
		DoItRepeat<Function<IFormatWithPrecedence, IFormatWithPrecedence>, Function<IFormatWithPrecedence, IFormat>, Function<IFormatWithPrecedence, IFormat>> d2 = new DoItRepeat<>(d3, d3, d3);
		DoItUnless<Function<String, Function<IFormatWithPrecedence, IFormatWithPrecedence>>, Function<String, Function<IFormatWithPrecedence, IFormat>>, Function<String, Function<IFormatWithPrecedence, IFormat>>> d1 = new DoItUnless<>(d2, d2, d2);

		//d2.repeat(2, /// cheating!!! So, bad example, for desugarings the extensions should be all in the outer interface.

		Function<String, Function<IFormatWithPrecedence, IFormat>> pp = 
				d1.form("bla", Arrays.asList(
						  d1.unless(d1.var("x"), d1.block(Arrays.asList(d1.question("x", "X?", new TBoolean()))))));
		
		StringWriter w = new StringWriter();
		pp.apply("").apply(base.bool(true)).format(0, true, w);
		System.out.println(w);
	
	}
	
}
