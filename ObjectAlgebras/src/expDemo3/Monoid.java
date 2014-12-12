package expDemo3;

import java.util.List;

//BEGIN_MONOID
public interface Monoid<R> {
    R join(R x, R y);
    R empty();
    default R fold(List<R> lr) { 
      return lr.stream().reduce(empty(), this::join); 
    }
}
//END_MONOID
