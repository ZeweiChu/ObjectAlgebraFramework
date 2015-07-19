package _ast;


public class Geq extends BinaryExp {

	public Geq(Exp lhs, Exp rhs) {
		super(lhs, rhs);
	}

	@Override
	protected Exp make(Exp lhs, Exp rhs) {
		return new Geq(lhs, rhs);
	}
	
}
