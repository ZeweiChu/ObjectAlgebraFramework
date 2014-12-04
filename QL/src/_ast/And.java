package _ast;

public class And extends BinaryExp {

	public And(Exp lhs, Exp rhs) {
		super(lhs, rhs);
	}

	@Override
	protected Exp make(Exp lhs, Exp rhs) {
		return new And(lhs, rhs);
	}

}
