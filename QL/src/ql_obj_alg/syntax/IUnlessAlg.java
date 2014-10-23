package ql_obj_alg.syntax;

import java.util.List;

import com.zewei.annotation.processor.Algebra;

@Algebra
public interface IUnlessAlg<E, S>  {
	S unless(E cond, List<S> body);
}
