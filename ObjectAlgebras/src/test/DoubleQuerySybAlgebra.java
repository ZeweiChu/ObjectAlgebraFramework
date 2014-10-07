package test;

import library.Monoid;
import query.QuerySybAlg;

public class DoubleQuerySybAlgebra extends QuerySybAlg<Double> {

	public DoubleQuerySybAlgebra(Monoid<Double> m) {
		super(m);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Double S(double p0){
		return p0;
	}

}
