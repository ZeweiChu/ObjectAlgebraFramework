package VisitorExp;

//BEGIN_VIS_EVAL_MAIN
interface Exp {
	<A> A accept(VisitorExp<A> v);
}
public interface VisitorExp<A> {
	A lit(Lit l);
	A add(Add a);
}
//END_VIS_EVAL_MAIN

class Lit implements Exp{
	int x;
	
	public int getX() {return x;}
	public void setX(int x) {this.x = x;}

	public Lit(int x) {this.x = x;}
	
	public <A> A accept(VisitorExp<A> v) {return v.lit(this);}
}

class Add implements Exp {
	Exp l, r;
	
	public Exp getL() {
		return l;
	}

	public void setL(Exp l) {
		this.l = l;
	}

	public Exp getR() {
		return r;
	}

	public void setR(Exp r) {
		this.r = r;
	}

	public Add(Exp l, Exp r) {this.l = l; this.r = r;}
	
	public <A> A accept(VisitorExp<A> v) {return v.add(this);}
}

//BEGIN_VIS_EVAL_FUNC
class Eval implements VisitorExp<Integer> {
	public Integer lit(Lit l) {
		return l.getX();
	}
	public Integer add(Add a) {
		return a.getL().accept(this) + a.getR().accept(this);
}}
//END_VIS_EVAL_FUNC
