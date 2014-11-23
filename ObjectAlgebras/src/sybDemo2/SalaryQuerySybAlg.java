package sybDemo2;

import java.util.List;
import trees.SybAlg;

//BEGIN_QUERY_SALARY
public class SalaryQuerySybAlg implements SybAlg<Float,Float,Float,Float,Float,Float> {
	@Override
	public Float C(List<Float> depts){
		Float r = 0f;
		for (Float f: depts) r += f;
		return r;
	}
	@Override
	public Float D(String name, Float manager, List<Float> subUnits){
		Float r = manager;
		for (Float f: subUnits) r += f;
		return r;
	}
	@Override
	public Float PU(Float employee){
		return employee;
	}
	@Override
	public Float DU(Float dept){
		return dept;
	}
	@Override
	public Float E(Float p, Float s){
		return p + s;
	}
	@Override
	public Float P(String name, String address){
		return 0f;
	}
	@Override
	public Float S(float salary){
		return salary;
	}
}
//END_QUERY_SALARY