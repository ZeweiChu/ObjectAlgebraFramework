package _ast;

public class Neq extends BinaryExp {

	public Neq(Exp lhs, Exp rhs) {
		super(lhs, rhs);
	}

	@Override
	protected Exp make(Exp lhs, Exp rhs) {
		return new Neq(lhs, rhs);
	}

}
