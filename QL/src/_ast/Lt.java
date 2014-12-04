package _ast;

public class Lt extends BinaryExp {

	public Lt(Exp lhs, Exp rhs) {
		super(lhs, rhs);
	}

	@Override
	protected Exp make(Exp lhs, Exp rhs) {
		return new Lt(lhs, rhs);
	}

}
