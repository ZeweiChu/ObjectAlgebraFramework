package _ast;

public class Leq extends BinaryExp {

	public Leq(Exp lhs, Exp rhs) {
		super(lhs, rhs);
	}

	@Override
	protected Exp make(Exp lhs, Exp rhs) {
		return new Leq(lhs, rhs);
	}

}
