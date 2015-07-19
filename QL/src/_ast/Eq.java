package _ast;


public class Eq extends BinaryExp {

	public Eq(Exp lhs, Exp rhs) {
		super(lhs, rhs);
	}

	@Override
	protected Exp make(Exp lhs, Exp rhs) {
		return new Eq(lhs, rhs);
	}
	

}
