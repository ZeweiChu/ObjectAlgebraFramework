package _ast;

public class Or extends BinaryExp {

	public Or(Exp lhs, Exp rhs) {
		super(lhs, rhs);
	}

	@Override
	protected Exp make(Exp lhs, Exp rhs) {
		return new Or(lhs, rhs);
	}

}
