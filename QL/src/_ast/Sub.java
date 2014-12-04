package _ast;

public class Sub extends BinaryExp {

	public Sub(Exp lhs, Exp rhs) {
		super(lhs, rhs);
	}

	@Override
	protected Exp make(Exp lhs, Exp rhs) {
		return new Sub(lhs, rhs);
	}

}
