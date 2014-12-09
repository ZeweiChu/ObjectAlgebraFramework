package _syb.trafo;

import java.io.StringWriter;
import java.util.Arrays;
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
		
		DoItInline<IFormatWithPrecedence, IFormat, IFormat> d3 = new DoItInline<>(base, base, base);
		DoItRepeat<Function<IFormatWithPrecedence, IFormatWithPrecedence>, Function<IFormatWithPrecedence, IFormat>, Function<IFormatWithPrecedence, IFormat>> d2 = new DoItRepeat<>(d3, d3, d3);
		DoItUnless<Function<String, Function<IFormatWithPrecedence, IFormatWithPrecedence>>, Function<String, Function<IFormatWithPrecedence, IFormat>>, Function<String, Function<IFormatWithPrecedence, IFormat>>> d1 = new DoItUnless<>(d2, d2, d2);

		Function<String, Function<IFormatWithPrecedence, IFormat>> pp = 
				d1.form("bla", Arrays.asList(
						d2.repeat(2,
						  d1.unless(d1.lit(4), d1.block(Arrays.asList(d1.question("x", "X?", new TBoolean())))))));
		
		StringWriter w = new StringWriter();
		pp.apply("").apply(base.bool(true)).format(0, true, w);
		System.out.println(w);
	
	}
	
}
