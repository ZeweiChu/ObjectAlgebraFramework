package expDemo3;

import java.util.List;

public interface Monoid<R> {
    R join(R x, R y);
    R empty();
    default R fold(List<R> lr){
    	R res = empty();
    	for (R r: lr){
    		res = join(res, r);
    	}
    	return res;
    }
}
