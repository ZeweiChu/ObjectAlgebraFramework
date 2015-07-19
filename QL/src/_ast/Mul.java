package _ast;


public class Mul extends BinaryExp {

	public Mul(Exp lhs, Exp rhs) {
		super(lhs, rhs);
	}

	@Override
	protected Exp make(Exp lhs, Exp rhs) {
		return new Mul(lhs, rhs);
	}

}
