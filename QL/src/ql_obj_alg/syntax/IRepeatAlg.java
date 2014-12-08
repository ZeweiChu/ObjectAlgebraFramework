package ql_obj_alg.syntax;

import java.util.List;

import com.zewei.annotation.processor.Algebra;

@Algebra
public interface IRepeatAlg<S> {
	S repeat(int n, List<S> body);
}
