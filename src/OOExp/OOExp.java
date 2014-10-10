package OOExp;

//BEGIN_OO__EVAL
interface Exp {
	int eval();
}
class Lit implements Exp {
	int x;
	public Lit(int x) { this.x = x; }
	
	public int eval() { 
		return x; 
}}
class Add implements Exp {
	Exp l, r;
	public Add(Exp l, Exp r) { this.l = l; this.r = r; }
	
	public int eval() {
		return l.eval() + r.eval();
}}
//END_OO__EVAL
