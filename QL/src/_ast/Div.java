package _ast;


public class Div extends BinaryExp {

	public Div(Exp lhs, Exp rhs) {
		super(lhs, rhs);
	}

	@Override
	protected Exp make(Exp lhs, Exp rhs) {
		return new Div(lhs, rhs);
	}

}
