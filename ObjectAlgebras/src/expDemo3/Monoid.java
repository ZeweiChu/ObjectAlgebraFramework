package expDemo3;


//BEGIN_MONOID
interface Monoid<R> {
	R join(R x, R y);
	R empty();
}
//END_MONOID
