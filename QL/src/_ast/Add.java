package _ast;


public class Add extends BinaryExp {

	public Add(Exp lhs, Exp rhs) {
		super(lhs, rhs);
	}

	@Override
	protected Exp make(Exp lhs, Exp rhs) {
		return new Add(lhs, rhs);
	}

}
