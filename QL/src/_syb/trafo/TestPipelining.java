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
import transform.G_IUnlessAlgTransform;
import transform.IExpAlgTransform;
import transform.IFormAlgTransform;
import transform.IStmtAlgTransform;
import transform.IUnlessAlgTransform;

public class TestPipelining {
  // desugar unless, desugar repeat, flatten
	
	public static class Rename<E, S, F> implements RenameVariable<E, S>, IFormAlgTransform<E, S, F> {
		private Map<String, String> renaming;

		private IExpAlg<E> expAlg;
		private IStmtAlg<E, S> stmtAlg;
		private IFormAlg<E, S, F> formAlg;

		public <Alg extends IExpAlg<E> & IStmtAlg<E,S> & IFormAlg<E,S,F>> Rename(Map<String, String> renaming, Alg alg) {
			this.renaming = renaming;
			this.expAlg = alg;
			this.stmtAlg = alg;
			this.formAlg = alg;
		}
		@Override
		public IExpAlg<E> iExpAlg() { return expAlg; }

		@Override
		public IFormAlg<E, S, F> iFormAlg() { return formAlg; }

		@Override
		public IStmtAlg<E, S> iStmtAlg() { return stmtAlg; }
		
		
		public Map<String,String> renaming() { return renaming; }

	}
	
	static class Desugar<E, S, F> implements DesugarUnless<E, S>, IFormAlgTransform<E, S, F>, IStmtAlgTransform<E, S>, 
	  IExpAlgTransform<E> {
		private IExpAlg<E> expAlg;
		private IStmtAlg<E, S> stmtAlg;
		private IFormAlg<E, S, F> formAlg;

		public  <Alg extends IExpAlg<E> & IStmtAlg<E,S> & IFormAlg<E,S,F>>  Desugar(Alg alg) {
			this.expAlg = alg;
			this.stmtAlg = alg;
			this.formAlg = alg;
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
	
	static class DesugarBoth<E, S, F> implements DesugarUnless<Function<String,E>, Function<String,S>>, DesugarRepeat<E, S, F> {
		private IExpAlg<E> expAlg;
		private IStmtAlg<E, S> stmtAlg;
		private IFormAlg<E, S, F> formAlg;
		private IRepeatAlg<S> repeatAlg;
		private IUnlessAlg<Function<String,E>,Function<String,S>> unlessAlg;

		public <Alg extends IExpAlg<E> & IStmtAlg<E,S> & IFormAlg<E,S,F> & IRepeatAlg<S> & IUnlessAlg<Function<String,E>, Function<String,S>>>  DesugarBoth(Alg alg) {
			this.expAlg = alg;
			this.stmtAlg = alg;
			this.formAlg = alg;
			this.repeatAlg = alg;
			this.unlessAlg = alg;
		}
		
		@Override
		public IExpAlg<E> iExpAlg() { return expAlg; }

		@Override
		public IFormAlg<E, S, F> iFormAlg() { return formAlg; }

		@Override
		public IStmtAlg<E, S> iStmtAlg() { return stmtAlg; }
		
		@Override
		public IRepeatAlg<S> iRepeatAlg() { return repeatAlg; }
		
		@Override
		public IUnlessAlg<Function<String,E>, Function<String,S>> iUnlessAlg() { return unlessAlg; }
	}
	
	static class Repeat<E, S, F> implements DesugarRepeat<E, S, F>, IUnlessAlgTransform<E, S> {
		private IExpAlg<E> expAlg;
		private IStmtAlg<E, S> stmtAlg;
		private IFormAlg<E, S, F> formAlg;
		private IUnlessAlg<E,S> unlessAlg;

		public <Alg extends IExpAlg<E> & IStmtAlg<E,S> & IFormAlg<E,S,F> & IUnlessAlg<E, S>>  Repeat(Alg alg) {
			this.expAlg = alg;
			this.stmtAlg = alg;
			this.formAlg = alg;
			this.unlessAlg = alg;
		}
		@Override
		public IExpAlg<E> iExpAlg() { return expAlg; }

		@Override
		public IRepeatAlg<S> iRepeatAlg() { return null; }

		@Override
		public IFormAlg<E, S, F> iFormAlg() { return formAlg; }

		@Override
		public IStmtAlg<E, S> iStmtAlg() { return stmtAlg; }
		
		@Override
		public IUnlessAlg<E, S> iUnlessAlg() { return unlessAlg; }
		
	}
	
	public static class Inline<E, S, F> implements InlineConditions<E, S, F> {
		private IExpAlg<E> expAlg;
		private IStmtAlg<E, S> stmtAlg;
		private IFormAlg<E, S, F> formAlg;

		public  <Alg extends IExpAlg<E> & IStmtAlg<E,S> & IFormAlg<E,S,F>> Inline(Alg alg) {
			this.expAlg = alg;
			this.stmtAlg = alg;
			this.formAlg = alg;
		}
		
		@Override
		public IExpAlg<E> iExpAlg() { return expAlg; }

		@Override
		public IFormAlg<E, S, F> iFormAlg() { return formAlg; }

		@Override
		public IStmtAlg<E, S> iStmtAlg() { return stmtAlg; }
		
	}
	
	public static void main(String[] args) {
		
		Rename<IFormatWithPrecedence, IFormat, IFormat> d3;
		Inline<IFormatWithPrecedence, IFormat, IFormat> d2;
		Desugar<Function<IFormatWithPrecedence, IFormatWithPrecedence>, 
		   Function<IFormatWithPrecedence, IFormat>, 
		   Function<IFormatWithPrecedence, IFormat>> alg;

		
		//BEGIN_PIPELINEQL
alg = new Desugar<>(new Inline<>(
		new Rename<>(Collections.singletonMap("x",  "y"), 
			new Format())));
		//END_PIPELINEQL
		
		//BEGIN_PIPELINEQL_CALL
		Function<IFormatWithPrecedence, IFormat> pp 
		  = alg.form("myForm", Arrays.asList(
				  alg.unless(alg.var("x"),  
				  alg.question("x", "X?", new TBoolean()))));
		//END_PIPELINEQL_CALL

		StringWriter w = new StringWriter();
		pp.apply(new Format().bool(true)).format(0, true, w);
		System.out.println(w);
		
//  		DesugarBoth<Function<String, Function<IFormatWithPrecedence, IFormatWithPrecedence>>, Function<String, Function<IFormatWithPrecedence, IFormat>>, Function<String, Function<IFormatWithPrecedence, IFormat>>> 
//  		dr = new DesugarBoth<Function<String, Function<IFormatWithPrecedence, IFormatWithPrecedence>>,
//  				Function<String, Function<IFormatWithPrecedence, IFormat>>,
//  				Function<String, Function<IFormatWithPrecedence, IFormat>>>(
//  						new Rename<Function<String, Function<IFormatWithPrecedence, IFormatWithPrecedence>>,
//  	  				Function<String, Function<IFormatWithPrecedence, IFormat>>,
//  	  				Function<String, Function<IFormatWithPrecedence, IFormat>>>(Collections.singletonMap("x", "y"),
//  	  						
//  	  						
//  	  						
//  	  						new Format2()));
//		
//		
//	//BEGIN_PIPELINEQL_CALL_REPEAT
//			Function<String, Function<IFormatWithPrecedence, IFormat>> pp2 
//			  = dr.form("myForm", Arrays.asList(
//				    dr.repeat(5,  
//				    	dr.unless(dr.var("x"),
//				    			dr.question("x", "X?", new TBoolean()))));
//			//END_PIPELINEQL_CALL_REPEAT
//		
//			StringWriter w2 = new StringWriter();
//			pp2.apply("").apply(new Format().bool(true)).format(0, true, w2);
//			System.out.println(w2);
	
	}
	
}
