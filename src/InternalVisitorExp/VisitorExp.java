package InternalVisitorExp;

import base.ExpAlg;

//BEGIN_INT_VIS_EVAL
interface Exp {
	<A> A accept(ExpAlg<A> v);
}
class Lit implements Exp {
	int val;
	public Lit(int x) { this.val = x; }

	public <A> A accept(ExpAlg<A> v) {
		return v.lit(val);
}}
class Add implements Exp {
	Exp left, right;
	public Add(Exp l, Exp r) { this.left = l; this.right = r; }

	public <A> A accept(ExpAlg<A> v) {
		return v.add(left.accept(v), right.accept(v));
}}
//END_INT_VIS_EVAL

//BEGIN_FACTORY__EVAL
class ExpFactory implements ExpAlg<Exp> {
	public Exp lit(int x) {
		return new Lit(x);
	}
	public Exp add(Exp e1, Exp e2) {
		return new Add(e1, e2);
	}
}
//END_FACTORY__EVAL
