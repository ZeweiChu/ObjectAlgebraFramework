package _ast;


public class Gt extends BinaryExp {

	public Gt(Exp lhs, Exp rhs) {
		super(lhs, rhs);
	}

	@Override
	protected Exp make(Exp lhs, Exp rhs) {
		return new Gt(lhs, rhs);
	}
	

}
